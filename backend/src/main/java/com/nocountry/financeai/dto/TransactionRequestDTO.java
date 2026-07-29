package com.nocountry.financeai.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransactionRequestDTO(

        @NotNull
        @Positive
        BigDecimal monto,

        @NotBlank
        String tipo,

        @NotBlank
        String categoria,

        @NotBlank
        String descripcion,

        @NotNull
        LocalDateTime fecha,

        @NotNull
        Long usuarioId
) {
}
