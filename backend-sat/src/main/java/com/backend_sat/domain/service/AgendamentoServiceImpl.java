package com.backend_sat.domain.service;

import com.backend_sat.domain.model.Agendamento;
import com.backend_sat.domain.model.PaginaAgendamento;
import com.backend_sat.domain.model.AgendamentoCriteriosDeBusca;
import com.backend_sat.domain.repository.AgendamentoPaginadoRepository;
import com.backend_sat.domain.repository.AgendamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import static com.backend_sat.CalculoUtils.calcularTotalTaxas;

@Service
public class AgendamentoServiceImpl implements AgendamentoService {

    private final RegraTaxaService regraTaxaService;
    private final AgendamentoRepository agendamentoRepository;
    private final AgendamentoPaginadoRepository agendamentoPaginadoRepository;

    public AgendamentoServiceImpl(RegraTaxaService regraTaxaService, AgendamentoRepository agendamentoRepository, AgendamentoPaginadoRepository agendamentoPaginadoRepository) {
        this.regraTaxaService = regraTaxaService;
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoPaginadoRepository = agendamentoPaginadoRepository;
    }

    public Agendamento agendar(Agendamento agendamento) {
        return agendamentoRepository.save(enriquecerDadosAgendamento(agendamento));
    }

    public Page<Agendamento> buscarTodosAgendamentos(PaginaAgendamento paginaAgendamento,
                                                     AgendamentoCriteriosDeBusca agendamentoCriteriosDeBusca) {
        return agendamentoPaginadoRepository
                .buscarPorFiltro(paginaAgendamento, agendamentoCriteriosDeBusca);
    }

    private Agendamento enriquecerDadosAgendamento(Agendamento agendamento) {
        var regraTaxa = regraTaxaService.getTaxaByDataTransferencia(agendamento.getDataTransferencia());
        var taxaTotal = calcularTotalTaxas(regraTaxa.getValor(), regraTaxa.getPercentual(), agendamento.getValor());

        agendamento.setValor(agendamento.getValor());
        agendamento.setTaxa(regraTaxa.getPercentual());
        agendamento.setValorTaxa(taxaTotal);
        return agendamento;
    }
}
