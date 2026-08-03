package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record PerfilFinancieroResponse (
        @JsonProperty("empleo_formal")
        Integer empleoFormal,
        @JsonProperty("ingreso_mensual")
        BigDecimal ingresoMensual,
        @JsonProperty("linea_credito")
        BigDecimal lineaCredito
) {
}
