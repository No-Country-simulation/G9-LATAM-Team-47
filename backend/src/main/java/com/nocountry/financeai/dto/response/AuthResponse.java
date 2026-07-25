package com.nocountry.financeai.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String message,
        String email
) {}
