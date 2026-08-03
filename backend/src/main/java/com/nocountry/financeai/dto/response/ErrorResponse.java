package com.nocountry.financeai.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        List<String> message,
        LocalDateTime timestamp
) {
    // Constructor secundario inteligente para asignar la fecha y hora automáticamente
    public ErrorResponse(
            int status,
            String error,
            List<String> message
    ) {
        this(status, error, message, LocalDateTime.now());
    }
}