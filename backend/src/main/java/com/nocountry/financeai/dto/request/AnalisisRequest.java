package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
@Schema(description = "Datos solicitodos al cliente, necesarios para realizar el analisis")
public record AnalisisRequest (
        @Schema(
                description = "Ingreso mensual del usuario",
                example = "4500"
        )
        @JsonProperty("ingreso_mensual")
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @Schema(
                description = "Nivel de porcentaje de endeudamiento de un cliente (0 a 100",
                example = "35"
        )
        @JsonProperty("nivel_endeudamiento")
        @NotNull(message = "El nivel de endeudamiento es obligatorio")
        @Min(value = 0, message = "El endeudamiento no puede ser negativo")
        @Max(value = 100, message ="El endeudamiento no puede pasar el 100%")
        Integer nivelEndeudamiento,

        @Schema(
                description = "Frecuencia con que el usuario ahorra parte de su ingreso",
                example = "Alta",
                allowableValues = {"Baja", "Media", "Alta"}
        )
        @NotNull(message = "La frecuencia de ahorro es obligatoria")
        @JsonProperty("frecuencia_ahorro")
        String frecuenciaAhorro,

        @Schema(
                description = "Lista de transacciones que un usuario realiza, Debe incluir minimo una"
        )
        @NotEmpty(message = "Se debe enviar al menos una transaccion")
        List<@Valid TransactionRequest> transacciones
) {}
