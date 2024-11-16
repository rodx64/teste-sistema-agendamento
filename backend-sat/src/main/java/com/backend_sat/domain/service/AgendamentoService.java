package com.backend_sat.domain.service;


import com.backend_sat.domain.model.Agendamento;
import com.backend_sat.domain.model.PaginaAgendamento;
import com.backend_sat.domain.model.AgendamentoCriteriosDeBusca;
import org.springframework.data.domain.Page;

public interface AgendamentoService {
    Agendamento agendar(Agendamento agendamento);
    Page<Agendamento> buscarTodosAgendamentos(PaginaAgendamento paginaAgendamento, AgendamentoCriteriosDeBusca agendamentoCriteriosDeBusca);
}
