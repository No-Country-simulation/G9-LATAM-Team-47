package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record UserResponse(
        @Schema(
                description = "Nombre del usuario",
                example = "carlos"
        )
        String nombre,

        @Schema(
                description = "Apellido del usuario",
                example = "gomez"
        )
        String apellido,

        @Schema(
                description = "Documento de identificacion del usuario",
                example = "1022332456"
        )
        String documento,

        @Schema(
                description = "Email registrado por el usuario",
                example = "carlosgomez@nocountry.com"
        )
        String email,

        @Schema(
                description = "Fecha de nacimiento del usuario",
                example = "1996-05-31"
        )
        @JsonProperty("fecha_nacimiento")
        LocalDate fechaNacimiento,

        @Schema(
                description = "Estado civil del usuario",
                example = "viudo"
        )
        @JsonProperty("estado_civil")
        EstadoCivil estadoCivil,

        @Schema(
                description = "Genero del usuario",
                example = "masculino"
        )
        Sexo sexo,

        @Schema(
                description = "Cantidad de hijos que tiene el usuario",
                example = "2"
        )
        @JsonProperty("numero_hijos")
        Integer numeroHijos


) {
}
