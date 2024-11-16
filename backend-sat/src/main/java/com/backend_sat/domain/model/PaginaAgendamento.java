package com.backend_sat.domain.model;

import org.springframework.data.domain.Sort;

public class PaginaAgendamento {
    private int numero = 0;
    private int quantidade = 10;
    private Sort.Direction direcao = Sort.Direction.ASC;
    private String ordenarPor = "contaOrigem";

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Sort.Direction getDirecao() {
        return direcao;
    }

    public void setDirecao(Sort.Direction direcao) {
        this.direcao = direcao;
    }

    public String getOrdenarPor() {
        return ordenarPor;
    }

    public void setOrdenarPor(String ordenarPor) {
        this.ordenarPor = ordenarPor;
    }
}
