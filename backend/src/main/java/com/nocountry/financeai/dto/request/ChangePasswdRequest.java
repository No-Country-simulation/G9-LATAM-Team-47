package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ChangePasswdRequest (
        @Schema(
                description = "Solicita contraseña actual",
                example = "abc123456"
        )
        @JsonProperty("current_password")
        @NotBlank(message = "Contranseña actual puede estar vacio")
        String currentPasswd,
        @Schema(
                description = "Solicita nueva clave",
                example = "ABC123*"
        )
        @NotBlank(message = "Nueva contraseña no puede estar vacio")
        String newPasswd,
        @Schema(
                description = "Solitia confirmar la nueva clave",
                example = "ABC123*"
        )
        @NotBlank(message = "Confirmar contraseña no puede estar vacio")
        String confirmPasswd
) {
}
