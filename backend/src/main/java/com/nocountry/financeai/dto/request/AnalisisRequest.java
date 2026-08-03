package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
@Schema(description = "Datos solicitodos al cliente, necesarios para realizar el analisis")
public record AnalisisRequest (
        @NotNull(message = "Edad es obligatoria")
        @Min(value = 18, message = "La edad minima es 18 años")
        Integer edad,

        @NotNull(message = "El sexo es obligatorio")
        Sexo sexo,

        @JsonProperty("estado_civil")
        @NotNull(message = "Estado civil es obligatorio")
        EstadoCivil estadoCivil,

        @JsonProperty("numero_hijos")
        @NotNull(message = "si tiene hijos, indicar cuantos" )
        @Min(value = 0, message = "numero de hijos no puede ser negativo")
        Integer numeroHijos,

        @JsonProperty("empleo_formal")
        @NotNull(message = "si tiene empleo, indicar cuantos")
        @Min(value = 0, message = "El numero de empleos no puede ser negativo")
        Integer empleoFormal,

        @JsonProperty("ingreso_mensual")
        @Schema(
                description = "Ingreso mensual del usuario",
                example = "4500"
        )
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @JsonProperty("linea_credito")
        @NotNull(message = "La línea de crédito es obligatoria")
        @DecimalMin(
                value = "0.0",
                inclusive = true,
                message = "La línea de crédito no puede ser negativa"
        )
        BigDecimal lineaCredito,

        @Schema(
                description = "Lista de transacciones que un usuario realiza, Debe incluir minimo una"
        )
        @NotEmpty(message = "Se debe enviar al menos una transaccion")
        List<@Valid TransactionRequest> transacciones
) {}
