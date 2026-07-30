package com.nocountry.financeai.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(
        String error,      // Ej: "Unauthorized" o "Bad Request"
        String message,    // Ej: "Credenciales inválidas..."
        int status,        // Ej: 401
        LocalDateTime timestamp
) {}