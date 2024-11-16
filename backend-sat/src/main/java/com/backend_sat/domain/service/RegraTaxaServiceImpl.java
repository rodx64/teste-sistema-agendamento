package com.backend_sat.domain.service;

import com.backend_sat.api.exceptions.AgendamentoInvalidoException;
import com.backend_sat.domain.model.RegraTaxa;
import com.backend_sat.domain.repository.RegraTaxaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.backend_sat.CalculoUtils.calcularDiferencaDias;
import static com.backend_sat.Constantes.MENSAGEM_ERRO_DB;
import static com.backend_sat.Constantes.MENSAGEM_ERRO_INESPERADO;
import static com.backend_sat.Constantes.MENSAGEM_TAXA_NAO_APLICAVEL;
import static com.backend_sat.Constantes.NOME_CACHE;

@Service
public class RegraTaxaServiceImpl implements RegraTaxaService {

    private static final Logger log = LoggerFactory.getLogger(RegraTaxaServiceImpl.class);
    private final RegraTaxaRepository regraTaxaRepository;

    public RegraTaxaServiceImpl(RegraTaxaRepository regraTaxaRepository) {
        this.regraTaxaRepository = regraTaxaRepository;
    }

    @Override
    @Cacheable(value = NOME_CACHE)
    public RegraTaxa getTaxaByDataTransferencia(LocalDate dataTransferencia) {
        long dias = calcularDiferencaDias(dataTransferencia);
        return getTaxaByDiaTransferencia(dias);
    }

    private RegraTaxa getTaxaByDiaTransferencia(long diaTransferencia) {
        try {
            return regraTaxaRepository
                    .findByDiaBetweenDiaInicioAndDiaFim(diaTransferencia)
                    .orElseThrow(() -> {
                        log.warn("{}: diaTransferencia: {}", MENSAGEM_TAXA_NAO_APLICAVEL, diaTransferencia);
                        return new AgendamentoInvalidoException(MENSAGEM_TAXA_NAO_APLICAVEL);
                    });
        } catch (DataAccessException ex) {
            log.error("{} {}: {}", MENSAGEM_ERRO_DB, diaTransferencia, ex.getMessage());
            throw new RuntimeException(MENSAGEM_ERRO_DB, ex);
        } catch (Exception ex) {
            if (ex instanceof AgendamentoInvalidoException) {
                throw ex;
            }
            log.error("{} {}: {}", MENSAGEM_ERRO_INESPERADO, diaTransferencia, ex.getMessage(), ex);
            throw new RuntimeException(MENSAGEM_ERRO_INESPERADO, ex);
        }
    }

}
