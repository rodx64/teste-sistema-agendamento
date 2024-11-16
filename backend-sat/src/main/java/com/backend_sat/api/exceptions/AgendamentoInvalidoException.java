package com.backend_sat.api.exceptions;

public class AgendamentoInvalidoException extends RuntimeException {
    public AgendamentoInvalidoException(String msg) {
        super(msg);
    }
}
