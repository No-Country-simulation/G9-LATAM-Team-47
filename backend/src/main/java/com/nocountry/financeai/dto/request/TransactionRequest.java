package com.nocountry.financeai.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(
        @NotBlank(message = "La descripcion es obligatoria")
        String descripcion,

        @NotNull(message = "El valor es obligatorio")
        @Positive(message = "El valor debe ser mayor a cero")
        BigDecimal valor
) {}
