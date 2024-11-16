package com.backend_sat.domain.service;

import com.backend_sat.api.exceptions.AgendamentoInvalidoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Stream;

import static com.backend_sat.Constantes.MENSAGEM_TAXA_NAO_APLICAVEL;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RegraTaxaServiceImplTest {

    @Autowired
    private RegraTaxaService regraTaxaService;

    private static Stream<Arguments> valoresValidosParaRegraDeTaxa() {
        return Stream.of(
                Arguments.of(LocalDate.now().plusDays(0), new BigDecimal("3.00"), new BigDecimal("2.50")),
                Arguments.of(LocalDate.now().plusDays(7), new BigDecimal("12.00"), new BigDecimal("0.00")),
                Arguments.of(LocalDate.now().plusDays(11), new BigDecimal("0.00"), new BigDecimal("8.20")),
                Arguments.of(LocalDate.now().plusDays(23), new BigDecimal("0.00"), new BigDecimal("6.90")),
                Arguments.of(LocalDate.now().plusDays(40), new BigDecimal("0.00"), new BigDecimal("4.70")),
                Arguments.of(LocalDate.now().plusDays(50), new BigDecimal("0.00"), new BigDecimal("1.70"))
        );
    }

    @ParameterizedTest
    @MethodSource("valoresValidosParaRegraDeTaxa")
    void quandoInseridosDadosValidosParaRegraDeTaxa_deveCalcularATaxaComOsValoresEsperados(LocalDate data, BigDecimal valorTaxaEsperado, BigDecimal taxaEsperada) {
        // Quando
        var regraTaxa = regraTaxaService.getTaxaByDataTransferencia(data);

        // Então
        assertNotNull(regraTaxa);
        assertEquals(taxaEsperada, regraTaxa.getPercentual());
        assertEquals(valorTaxaEsperado, regraTaxa.getValor());
    }

    @Test
    void quandoInseridosDadosInValidosParaRegraDeTaxa_deveLancarErroDeTaxaNaoAplicavel() {
        // Dado
        final long diasForaDoRangePermitido = 51L;
        final LocalDate dataInvalida = LocalDate.now().plusDays(diasForaDoRangePermitido);

        // Então
        assertThatThrownBy(() -> regraTaxaService.getTaxaByDataTransferencia(dataInvalida))
                .isInstanceOf(AgendamentoInvalidoException.class)
                .hasMessageContaining(MENSAGEM_TAXA_NAO_APLICAVEL);
    }
}
