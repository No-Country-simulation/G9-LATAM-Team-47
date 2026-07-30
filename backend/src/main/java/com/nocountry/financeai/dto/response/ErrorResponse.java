package com.nocountry.financeai.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(
        String error,
        String message,
        int status,
        LocalDateTime timestamp
) {
    // Constructor secundario inteligente para asignar la fecha y hora automáticamente
    public ErrorResponse(String error, String message, int status) {
        this(error, message, status, LocalDateTime.now());
    }
}