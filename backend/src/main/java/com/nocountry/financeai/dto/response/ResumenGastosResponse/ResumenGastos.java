package com.nocountry.financeai.dto.response.ResumenGastosResponse;

import java.math.BigDecimal;

public record ResumenGastos(
        BigDecimal alimentacion,
        BigDecimal transporte,
        BigDecimal entretenimiento
) {
}
