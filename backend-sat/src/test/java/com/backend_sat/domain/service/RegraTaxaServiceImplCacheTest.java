package com.backend_sat.domain.service;

import com.backend_sat.domain.model.RegraTaxa;
import com.backend_sat.domain.repository.RegraTaxaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

import static com.backend_sat.Constantes.NOME_CACHE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@EnableCaching
class RegraTaxaServiceImplCacheTest {

    @Autowired
    private RegraTaxaServiceImpl regraTaxaService;

    @Autowired
    private CacheManager cacheManager;

    @MockBean
    private RegraTaxaRepository regraTaxaRepository;

    @Test
    void quandoRealizarDuasRequisicoes_aSegundaRequisicaoDeveAcessarOsDadosDoCache() {
        // Dado
        final int numeroEsperadoDeRequisicoes = 1;
        final LocalDate diaTransferencia = LocalDate.now();
        final BigDecimal valorEsperado = new BigDecimal("12.00");
        final BigDecimal taxaEsperada = new BigDecimal("0.00");
        var regraTaxaMock = new RegraTaxa.Builder().setValor(valorEsperado).setPercentual(taxaEsperada).build();

        // Quando: primeira requisicao
        when(regraTaxaRepository.findByDiaBetweenDiaInicioAndDiaFim(anyLong()))
                .thenReturn(Optional.of(regraTaxaMock));
        var taxaTransferencia = regraTaxaService.getTaxaByDataTransferencia(diaTransferencia);

        // Entao
        assertThat(taxaTransferencia).isNotNull();
        assertThat(taxaTransferencia.getValor()).isEqualByComparingTo(valorEsperado);
        assertThat(taxaTransferencia.getPercentual()).isEqualByComparingTo(taxaEsperada);
        assertThat(Objects.requireNonNull(cacheManager.getCache(NOME_CACHE))).isNotNull();

        // Quando: segunda requisição
        var taxaDia2 = regraTaxaService.getTaxaByDataTransferencia(diaTransferencia);

        // Entao
        assertThat(taxaDia2).isSameAs(taxaTransferencia);
        verify(regraTaxaRepository, times(numeroEsperadoDeRequisicoes))
                .findByDiaBetweenDiaInicioAndDiaFim(anyLong());
    }
}
