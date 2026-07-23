package com.nocountry.financeai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(
        @Schema(
                description = "Descripción de la transacción",
                example = "Recreación"
        )
        @NotBlank(message = "La descripcion es obligatoria")
        String descripcion,

        @Schema(
                description = "Valor de la transacción",
                example = "210"
        )
        @NotNull(message = "El valor es obligatorio")
        @Positive(message = "El valor debe ser mayor a cero")
        BigDecimal valor
) {}
