package com.nocountry.financeai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Schema(
                description = "email del usuario",
                example = "carlosgomez@gmail.com"
        )
        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El formato del correo es inválido")
        String email,

        @Schema(
                description = "clave del usuario",
                example = "abc123456"
        )
        @NotBlank(message = "La contraseña es obligatoria")
       String password
) {
}