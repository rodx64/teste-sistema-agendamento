package com.backend_sat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static com.backend_sat.CalculoUtils.calcularDiferencaDias;
import static com.backend_sat.CalculoUtils.calcularTotalTaxas;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UtilsTest {
    
    private static Stream<Arguments> valoresValidosParaResultadoCalculadosDeTaxa() {
        BigDecimal valorTransferencia = new BigDecimal("1000.00");
        return Stream.of(
                Arguments.of(new BigDecimal("3.00"), new BigDecimal("2.5"), valorTransferencia, new BigDecimal("28.00")),
                Arguments.of(new BigDecimal("12.00"), BigDecimal.ZERO, valorTransferencia, new BigDecimal("12.00")),
                Arguments.of(BigDecimal.ZERO, new BigDecimal("8.2"), valorTransferencia, new BigDecimal("82.00")),
                Arguments.of(BigDecimal.ZERO, new BigDecimal("6.9"), valorTransferencia, new BigDecimal("69.00")),
                Arguments.of(BigDecimal.ZERO, new BigDecimal("4.7"), valorTransferencia, new BigDecimal("47.00")),
                Arguments.of(BigDecimal.ZERO, new BigDecimal("1.7"), valorTransferencia, new BigDecimal("17.00"))
        );
    }

    @Test
    void quandoCalcularDiferencaDias_deveRetornarQuantidadeCorretaParaDataFutura() {
        // Dado
        LocalDate dataFutura = LocalDate.now().plusDays(5);
        long resultadoEsperado = 5L;

        // Quando
        long resultado = calcularDiferencaDias(dataFutura);

        // Entao
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    void quandoCalcularDiferencaDias_deveRetornarQuantidadeCorretaParaDataPassada() {
        // Dado
        LocalDate dataPassada = LocalDate.now().minusDays(3);
        long resultadoEsperado = -3L;

        // Quando
        long resultado = calcularDiferencaDias(dataPassada);

        // Entao
        assertEquals(resultadoEsperado, resultado);
    }

    @Test
    void quandoCalcularDiferencaDias_deveRetornarZeroParaDataAtual() {
        // Dado
        LocalDate dataAtual = LocalDate.now();
        long resultadoEsperado = 0;

        // Quando
        long resultado = calcularDiferencaDias(dataAtual);

        // Entao
        assertEquals(resultadoEsperado, resultado);
    }

    @ParameterizedTest
    @MethodSource("valoresValidosParaResultadoCalculadosDeTaxa")
    @DisplayName("Testa o cálculo das taxas com diferentes valores de entrada")
    void quandoCalcularOValorDasTaxas_deveRetornarResultadoEsperado(BigDecimal valorTaxa, BigDecimal percentual, BigDecimal valorTransferencia, BigDecimal resultadoEsperado) {
        // Quando
        BigDecimal resultadoCalculado = calcularTotalTaxas(valorTaxa, percentual, valorTransferencia);

        // Entao
        assertEquals(resultadoEsperado, resultadoCalculado);
    }
}
