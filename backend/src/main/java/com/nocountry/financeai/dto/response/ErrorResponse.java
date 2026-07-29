package com.nocountry.financeai.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse
        (
        String error,
        String message,
        int status,
        LocalDateTime timestamp
) {
    // Constructor secundario inteligente para asignar timestamp automático
    public ErrorResponse(String error, String message, int status) {
        this(error, message, status, LocalDateTime.now());
    }
}