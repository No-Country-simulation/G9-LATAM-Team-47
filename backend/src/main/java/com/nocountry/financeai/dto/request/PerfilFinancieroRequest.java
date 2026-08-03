package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PerfilFinancieroRequest(
        @Schema(
                description = "Indica si el usuario tiene empleo formal (1) o no (0)",
                example = "1")
        @JsonProperty("empleo_formal")
        @NotNull(message = "Debe indicar si tiene empleo formal")
        @Min(value = 0, message = "El valor debe ser 0 o 1")
        Integer empleoFormal,

        @Schema(
                description = "Ingreso mensual del usuario",
                example = "3500.00")
        @JsonProperty("ingreso_mensual")
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @Schema(
                description = "Línea de crédito disponible del usuario",
                example = "1000.00")
        @JsonProperty("linea_credito")
        @NotNull(message = "La línea de crédito es obligatoria")
        @DecimalMin(value = "0.0", inclusive = true, message = "La línea de crédito no puede ser negativa")
        BigDecimal lineaCredito
) {
}
