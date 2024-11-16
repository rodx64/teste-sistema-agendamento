package com.backend_sat.domain.repository;

import com.backend_sat.domain.model.RegraTaxa;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegraTaxaRepository extends CrudRepository<RegraTaxa, Long> {

    @Query(value = "SELECT * FROM TB_REGRA_TAXA WHERE :dia BETWEEN dia_inicio AND dia_fim", nativeQuery = true)
    Optional<RegraTaxa> findByDiaBetweenDiaInicioAndDiaFim(@Param("dia") long dia);
    
}
