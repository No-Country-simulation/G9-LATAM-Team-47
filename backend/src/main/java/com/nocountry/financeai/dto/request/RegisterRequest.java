package com.nocountry.financeai.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El formato del email no es válido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password
) {}