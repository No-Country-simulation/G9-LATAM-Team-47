package com.nocountry.financeai.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AuthResponse(
        @Schema(
                description = ""
        )
        String message,

        @Schema(
                description = ""
        )
        String email
) {}
