package com.nocountry.financeai.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionRequestDTO(
        BigDecimal monto,
        String tipo,
        String categoria,
        String descripcion,
        LocalDateTime fecha,
        Long usuarioId
) {
}
