package com.backend_sat.domain.repository;

import com.backend_sat.domain.model.Agendamento;
import com.backend_sat.domain.model.PaginaAgendamento;
import com.backend_sat.domain.model.AgendamentoCriteriosDeBusca;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Objects;

@Repository
public class AgendamentoPaginadoRepository {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public AgendamentoPaginadoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Agendamento> buscarPorFiltro(PaginaAgendamento paginaAgendamento, AgendamentoCriteriosDeBusca agendamentoCriteriosDeBusca) {
        var criteriaQuery = criteriaBuilder.createQuery(Agendamento.class);
        var agendamentoRoot = criteriaQuery.from(Agendamento.class);
        var filtros = selecionarFiltros(agendamentoCriteriosDeBusca, agendamentoRoot);

        criteriaQuery.where(filtros);
        ordenar(paginaAgendamento, criteriaQuery, agendamentoRoot);

        var query = entityManager.createQuery(criteriaQuery);

        query.setFirstResult(paginaAgendamento.getNumero() * paginaAgendamento.getQuantidade());
        query.setMaxResults(paginaAgendamento.getQuantidade());

        var paginas = buscarPaginas(paginaAgendamento);
        var quantidade = filtrarPorCriterio(filtros);

        return new PageImpl<>(query.getResultList(), paginas, quantidade);
    }

    private long filtrarPorCriterio(Predicate predicate) {
        var query = criteriaBuilder.createQuery(Long.class);
        var root = query.from(Agendamento.class);
        query.select(criteriaBuilder.count(root)).where(predicate);
        return entityManager.createQuery(query).getFirstResult();
    }

    private void ordenar(PaginaAgendamento paginaAgendamento, CriteriaQuery<Agendamento> criteriaQuery, Root<Agendamento> agendamentoRoot) {
        if (paginaAgendamento.getDirecao().equals(Sort.Direction.ASC)) {
            criteriaQuery.orderBy(criteriaBuilder.asc(agendamentoRoot.get(paginaAgendamento.getOrdenarPor())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(agendamentoRoot.get(paginaAgendamento.getOrdenarPor())));
        }
    }

    private Pageable buscarPaginas(PaginaAgendamento paginaAgendamento) {
        var sort = Sort.by(paginaAgendamento.getDirecao(), paginaAgendamento.getOrdenarPor());
        return PageRequest.of(paginaAgendamento.getNumero(), paginaAgendamento.getQuantidade(), sort);
    }
    
    private Predicate selecionarFiltros(AgendamentoCriteriosDeBusca agendamentoCriteriosDeBusca, Root<Agendamento> agendamentoRoot) {
        var predicates = new ArrayList<>();
        if (Objects.nonNull(agendamentoCriteriosDeBusca.getContaOrigem())) {
            predicates.add(
                    criteriaBuilder.like(
                            agendamentoRoot.get("contaOrigem"),
                            "%" + agendamentoCriteriosDeBusca.getContaOrigem() + "%"
                    )
            );
        }
        if (Objects.nonNull(agendamentoCriteriosDeBusca.getContaDestino())) {
            predicates.add(
                    criteriaBuilder.like(
                            agendamentoRoot.get("contaDestino"),
                            "%" + agendamentoCriteriosDeBusca.getContaDestino() + "%"
                    )
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
