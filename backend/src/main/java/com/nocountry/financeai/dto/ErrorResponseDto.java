package com.nocountry.financeai.dto;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String error,
        String message,
        int status,
        LocalDateTime timestamp
) {
    public ErrorResponseDto(String error, String message, int status) {
        this(error, message, status, LocalDateTime.now());
    }
}