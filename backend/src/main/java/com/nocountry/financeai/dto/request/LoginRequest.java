package com.nocountry.financeai.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo es inválido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}