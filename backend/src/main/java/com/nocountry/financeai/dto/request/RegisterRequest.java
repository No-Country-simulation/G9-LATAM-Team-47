package com.nocountry.financeai.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    // --- Campos Base de Registro ---
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Email(message = "Debe ser un correo válido")
    @NotBlank(message = "El correo es obligatorio")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    // --- Nuevos Campos del Perfil Financiero ---
    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "Debe ser mayor de edad")
    private Integer edad;

    @NotBlank(message = "El sexo es obligatorio")
    @Pattern(
            regexp = "^(?i)(masculino|femenino|prefiero no dar esa informacion)$",
            message = "El sexo debe ser: masculino, femenino o prefiero no dar esa informacion"
    )
    private String sexo;

    @NotBlank(message = "El estado civil es obligatorio")
    @Pattern(
            regexp = "^(?i)(soltero|casado)$",
            message = "El estado civil debe ser: soltero o casado"
    )
    private String estadoCivil;

    @NotNull(message = "El número de hijos es obligatorio")
    @Min(value = 0, message = "El número de hijos no puede ser negativo")
    private Integer numeroHijos;

    // Al ser Boolean, Spring Boot automáticamente validará que el cliente solo envíe true (sí) o false (no).
    @NotNull(message = "Debe indicar si tiene empleo formal (true o false)")
    private Boolean empleoFormal;

    @NotNull(message = "El ingreso mensual es obligatorio")
    @DecimalMin(value = "0.0", inclusive = true, message = "El ingreso no puede ser negativo")
    private BigDecimal ingresoMensual;

    @NotNull(message = "La línea de crédito es obligatoria")
    @DecimalMin(value = "0.0", inclusive = true, message = "La línea de crédito no puede ser negativa")
    private BigDecimal lineaCredito;
}