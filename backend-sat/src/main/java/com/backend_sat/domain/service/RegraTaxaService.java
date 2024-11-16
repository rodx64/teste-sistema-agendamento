package com.backend_sat.domain.service;

import com.backend_sat.domain.model.RegraTaxa;

import java.time.LocalDate;

public interface RegraTaxaService {
    RegraTaxa getTaxaByDataTransferencia(LocalDate dataTransferencia);
}
