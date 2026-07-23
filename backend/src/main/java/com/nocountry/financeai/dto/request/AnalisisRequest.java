package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

public record AnalisisRequest (
        @JsonProperty("ingreso_mensual")
        BigDecimal ingresoMensual,

        @JsonProperty("nivel_endeudamiento")
        Integer nivelEndeudamiento,

        @JsonProperty("frecuencia_ahorro")
        String frecuenciaAhorro,

        List<TransactionRequest> transactions
) {}
