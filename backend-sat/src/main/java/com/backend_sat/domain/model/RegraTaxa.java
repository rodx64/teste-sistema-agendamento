package com.backend_sat.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "TB_REGRA_TAXA")
public class RegraTaxa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int diaInicio;
    private int diaFim;
    private BigDecimal valor;
    private BigDecimal percentual;

    public RegraTaxa() {
    }

    public RegraTaxa(int diaInicio, int diaFim, BigDecimal valor, BigDecimal percentual) {
        this.diaInicio = diaInicio;
        this.diaFim = diaFim;
        this.valor = valor;
        this.percentual = percentual;
    }

    public RegraTaxa(Builder builder) {
        this(builder.diaInicio, builder.diaFim, builder.valor, builder.percentual);
    }

    public Long getId() {
        return id;
    }

    public int getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(int diaInicio) {
        this.diaInicio = diaInicio;
    }

    public int getDiaFim() {
        return diaFim;
    }

    public void setDiaFim(int diaFim) {
        this.diaFim = diaFim;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public void setPercentual(BigDecimal percentual) {
        this.percentual = percentual;
    }

    public static class Builder {
        private int diaInicio;
        private int diaFim;
        private BigDecimal valor;
        private BigDecimal percentual;

        public Builder setDiaInicio(int diaInicio) {
            this.diaInicio = diaInicio;
            return this;
        }

        public Builder setDiaFim(int diaFim) {
            this.diaFim = diaFim;
            return this;
        }

        public Builder setValor(BigDecimal valor) {
            this.valor = valor;
            return this;
        }

        public Builder setPercentual(BigDecimal percentual) {
            this.percentual = percentual;
            return this;
        }

        public RegraTaxa build() {
            return new RegraTaxa(this);
        }
    }

    @Override
    public String toString() {
        return "RegraTaxa{" +
                "id=" + id +
                ", diaInicio=" + diaInicio +
                ", diaFim=" + diaFim +
                ", valor=" + valor +
                ", percentual=" + percentual +
                '}';
    }
}
