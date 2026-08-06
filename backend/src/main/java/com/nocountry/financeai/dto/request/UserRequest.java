package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record UserRequest(
        @Schema(
                description = "Nombre del usuario" ,
                example = "carlos"
        )
        @Size(max = 100, message = "Nombre no puede superar los 100 caracteres")
        String nombre,

        @Schema(
                description = "apellido del usuario" ,
                example = "gomez"
        )
        @Size(max = 100, message = "Apellido no puede superar los 100 caracteres")
        String apellido,
        @Schema(
                description = "Documento de identificacion del usuario" ,
                example = "PEMJ920323HJCZNN0"
        )
        String documento,

        @Schema(
                description = "Correo electronico del usuario" ,
                example = "carlosgomez@alura.com"
        )
        @Email(message = "El formato del correo no es valido")
        String email,

        @Schema(
                description = "Fecha de naciemiento del usuario" ,
                example = "1999-03-24"
        )
        @JsonProperty("fecha_nacimiento")
        LocalDate fechaNacimiento,

        @Schema(
                description = "Estado civil del usuario" ,
                example = "SOLTERO"
        )

        @JsonProperty("estado_civil")
        EstadoCivil estadoCivil,

        @Schema(
                description = "Sexo de nacimiento del usuario" ,
                example = "MASCULINO"
        )
        Sexo sexo,

        @Schema(
                description = "Cantidad de hijos del usuario" ,
                example = "1"
        )
        @JsonProperty("numero_hijos")
        @Min(value = 0, message = "El numero de hijos no puede ser negativo")
        Integer numeroHijos
) {
}
