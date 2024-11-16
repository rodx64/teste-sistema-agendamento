package com.backend_sat.domain.model;


import com.backend_sat.domain.converter.LocalDateConverter;
import com.backend_sat.domain.converter.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY;

@Entity
@Table(name = "TB_TRANSFERENCIA")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private Long id;

    @Column(nullable = false)
    private String contaOrigem;
    
    @Column(nullable = false)
    private String contaDestino;
    
    @Column(nullable = false)
    private BigDecimal valor;
    
    @Schema(hidden = true)
    private BigDecimal valorTaxa;
    
    @Schema(accessMode = READ_ONLY)
    @JsonIgnore
    private BigDecimal taxa;

    @Column(nullable = false)
    @Convert(converter = LocalDateConverter.class)
    private LocalDate dataTransferencia;

    @CreationTimestamp
    @Schema(hidden = true)
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime dataAgendamento;

    public Agendamento() {
    }

    public Agendamento(String contaOrigem, String contaDestino, BigDecimal valor, BigDecimal valorTaxa, LocalDate dataTransferencia) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
        this.valorTaxa = valorTaxa;
        this.dataTransferencia = dataTransferencia;
    }

    public Agendamento(Builder builder) {
        this(builder.contaOrigem, builder.contaDestino, builder.valor, builder.valorComTaxa, builder.dataTransferencia);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContaOrigem() {
        return contaOrigem;
    }

    public void setContaOrigem(String contaOrigem) {
        this.contaOrigem = contaOrigem;
    }

    public String getContaDestino() {
        return contaDestino;
    }

    public void setContaDestino(String contaDestino) {
        this.contaDestino = contaDestino;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public LocalDate getDataTransferencia() {
        return dataTransferencia;
    }

    public void setDataTransferencia(LocalDate dataTransferencia) {
        this.dataTransferencia = dataTransferencia;
    }

    public LocalDateTime getDataAgendamento() {
        return dataAgendamento;
    }

    public BigDecimal getValorTaxa() {
        return valorTaxa;
    }

    public void setValorTaxa(BigDecimal valorTaxa) {
        this.valorTaxa = valorTaxa;
    }

    public static class Builder {
        private String contaOrigem;
        private String contaDestino;
        private BigDecimal valor;
        private BigDecimal taxa;
        private BigDecimal valorComTaxa;
        private LocalDate dataTransferencia;

        public Builder contaOrigem(String contaOrigem) {
            this.contaOrigem = contaOrigem;
            return this;
        }

        public Builder contaDestino(String contaDestino) {
            this.contaDestino = contaDestino;
            return this;
        }

        public Builder valor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        public Builder valorComTaxa(BigDecimal valorComTaxa) {
            this.valorComTaxa = valorComTaxa;
            return this;
        }

        public Builder taxa(BigDecimal taxa) {
            this.taxa = taxa;
            return this;
        }

        public Builder setDataTransferencia(LocalDate dataTransferencia) {
            this.dataTransferencia = dataTransferencia;
            return this;
        }

        public Agendamento build() {
            return new Agendamento(this);
        }
    }

    @Override
    public String toString() {
        return "Agendamento{" +
                "id=" + id +
                ", contaOrigem='" + contaOrigem + '\'' +
                ", contaDestino='" + contaDestino + '\'' +
                ", valor=" + valor +
                ", valorTaxa=" + valorTaxa +
                ", taxa=" + taxa +
                ", dataTransferencia=" + dataTransferencia +
                ", dataAgendamento=" + dataAgendamento +
                '}';
    }
}
