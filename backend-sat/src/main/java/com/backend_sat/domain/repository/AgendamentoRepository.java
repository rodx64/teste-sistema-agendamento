package com.backend_sat.domain.repository;

import com.backend_sat.domain.model.Agendamento;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgendamentoRepository extends CrudRepository<Agendamento, Long> {
}
