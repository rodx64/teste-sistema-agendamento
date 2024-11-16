package com.backend_sat.api;

import com.backend_sat.api.exceptions.AgendamentoInvalidoException;
import com.backend_sat.domain.model.Agendamento;
import com.backend_sat.domain.model.AgendamentoCriteriosDeBusca;
import com.backend_sat.domain.model.PaginaAgendamento;
import com.backend_sat.domain.service.AgendamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.backend_sat.Constantes.DESCRICAO_AGENDAMENTOS_LISTADOS;
import static com.backend_sat.Constantes.DESCRICAO_AGENDAR;
import static com.backend_sat.Constantes.DESCRICAO_AGENDAR_ERRO;
import static com.backend_sat.Constantes.DESCRICAO_AGENDAR_NAO_PROCESSAVEL;
import static com.backend_sat.Constantes.HTTP_STATUS_CREATED;
import static com.backend_sat.Constantes.HTTP_STATUS_OK;
import static com.backend_sat.Constantes.HTTP_STATUS_SERVER_ERROR;
import static com.backend_sat.Constantes.HTTP_STATUS_UNPROCESSABLE_ENTITY;
import static com.backend_sat.Constantes.MEDIA_TYPE;
import static com.backend_sat.Constantes.OPERACAO_AGENDAR;
import static com.backend_sat.Constantes.OPERACAO_LISTAR;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @Operation(summary = OPERACAO_AGENDAR)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = HTTP_STATUS_CREATED,
                    description = DESCRICAO_AGENDAR,
                    content = {
                            @Content(mediaType = MEDIA_TYPE,
                                    schema = @Schema(implementation = Agendamento.class))
                    }
            ),
            @ApiResponse(
                    responseCode = HTTP_STATUS_UNPROCESSABLE_ENTITY,
                    description = DESCRICAO_AGENDAR_NAO_PROCESSAVEL,
                    content = {
                            @Content(mediaType = MEDIA_TYPE,
                                    schema = @Schema(implementation = Agendamento.class))
                    }
            ),
            @ApiResponse(
                    responseCode = HTTP_STATUS_SERVER_ERROR,
                    description = DESCRICAO_AGENDAR_ERRO,
                    content = {
                            @Content(mediaType = MEDIA_TYPE,
                                    schema = @Schema(implementation = Agendamento.class))
                    }
            )

    })
    @PostMapping("/agendar")
    public ResponseEntity<?> agendar(@RequestBody Agendamento agendamento) {
        try {
            var transferenciaAtualizada = agendamentoService.agendar(agendamento);
            return new ResponseEntity<>(transferenciaAtualizada, HttpStatus.CREATED);
        } catch (AgendamentoInvalidoException | ArithmeticException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = OPERACAO_LISTAR)
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = HTTP_STATUS_OK,
                    description = DESCRICAO_AGENDAMENTOS_LISTADOS,
                    content = {
                            @Content(mediaType = MEDIA_TYPE,
                                    schema = @Schema(implementation = Agendamento.class))
                    }
            ),
            @ApiResponse(
                    responseCode = HTTP_STATUS_SERVER_ERROR,
                    description = DESCRICAO_AGENDAR_ERRO,
                    content = {
                            @Content(mediaType = MEDIA_TYPE,
                                    schema = @Schema(implementation = Agendamento.class))
                    }
            )

    })
    @GetMapping("/gerar-extrato")
    public ResponseEntity<?> gerarExtrato(PaginaAgendamento paginaAgendamento,
                                                AgendamentoCriteriosDeBusca agendamentoCriteriosDeBusca) {
        Page<Agendamento> agendamentoList = agendamentoService.buscarTodosAgendamentos(paginaAgendamento, agendamentoCriteriosDeBusca);
        return new ResponseEntity<>(agendamentoList.getContent(), HttpStatus.OK);
    }

}
