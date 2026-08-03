package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RegisterRequest(

        @Schema(
                description = "Nombre del usuario",
                example = "Carlos"
        )
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @Schema(
                description = "Apellido del usuario",
                example = "Gómez"
        )
        @NotBlank(message = "El apellido no puede estar vacío")
        String apellido,

        @Schema(
                description = "Email del usuario",
                example = "carlosgomez@gmail.com"
        )
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El formato del email no es válido")
        String email,

        @Schema(
                description = "Contraseña del usuario",
                example = "abc123456"
        )
        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(
                min = 8,
                message = "La contraseña debe tener al menos 8 caracteres"
        )
        String password,

        @Schema(
                description = "Fecha de nacimiento del usuario",
                example = "1995-06-15"
        )
        @JsonProperty("fecha_nacimiento")
        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe estar en el pasado")
        LocalDate fechaNacimiento,

        @Schema(
                description = "Sexo del usuario",
                example = "MASCULINO"
        )
        @NotNull(message = "El sexo es obligatorio")
        Sexo sexo,

        @Schema(
                description = "Estado civil del usuario",
                example = "SOLTERO"
        )
        @JsonProperty("estado_civil")
        @NotNull(message = "El estado civil es obligatorio")
        EstadoCivil estadoCivil,

        @Schema(
                description = "Número de hijos del usuario",
                example = "0"
        )
        @JsonProperty("numero_hijos")
        @NotNull(message = "El número de hijos es obligatorio")
        @Min(
                value = 0,
                message = "El número de hijos no puede ser negativo"
        )
        Integer numeroHijos

) {
    @AssertTrue(
            message = "El usuario debe ser mayor de 18 años"
    )
    public boolean esMayorDeEdad() {
        return fechaNacimiento != null
                && !fechaNacimiento.isAfter(
                LocalDate.now().minusYears(18)
        );
    }


}
