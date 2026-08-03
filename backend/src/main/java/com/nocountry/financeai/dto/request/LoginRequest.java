package com.nocountry.financeai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Schema(
            description = "email del usuario",
            example = "carlosgomez@gmail.com"
    )
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo es inválido")
    private String email;

    @Schema(
            description = "clave del usuario",
            example = "abc123456"
    )
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}