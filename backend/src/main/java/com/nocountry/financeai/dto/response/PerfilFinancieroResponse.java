package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record PerfilFinancieroResponse (
        @Schema(
                description = "Cantidad de empleos que tiene un usuario",
                example = "1"
        )
        @JsonProperty("empleo_formal")
        Integer empleoFormal,

        @Schema(
                description = "Cantidad de ingresos que persibe un usuario",
                example = "5500"
        )
        @JsonProperty("ingreso_mensual")
        BigDecimal ingresoMensual,

        @Schema(
                description = "Monto de credito que tiene un usuario",
                example = "10000"
        )
        @JsonProperty("linea_credito")
        BigDecimal lineaCredito
) {
}
