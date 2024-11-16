package com.backend_sat;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public abstract class CalculoUtils {

    public static long calcularDiferencaDias(LocalDate dataAgendamento) {
        return ChronoUnit.DAYS.between(LocalDate.now(), dataAgendamento);
    }

    public static BigDecimal calcularTotalTaxas(BigDecimal valorTaxa, BigDecimal percentual, BigDecimal valorTransferencia) {
        return percentual
                .multiply(new BigDecimal("0.01"))
                .multiply(valorTransferencia)
                .add(valorTaxa)
                .setScale(2, RoundingMode.HALF_EVEN);
    }

}
