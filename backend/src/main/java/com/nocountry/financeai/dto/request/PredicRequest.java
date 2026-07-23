package com.nocountry.financeai.dto.request;

import java.math.BigDecimal;
import java.util.List;

public record PredicRequest (
    BigDecimal ingresoMensual,
    Integer nivelEndeudamiento,
    String frecuencaAhorro,
    List<TransactionRequest> transacciones
)
{}
