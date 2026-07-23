package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record AnalisisRequest (
        @JsonProperty("ingreso_mensual")
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @JsonProperty("nivel_endeudamiento")
        @NotNull(message = "El nivel de endeudamiento es obligatorio")
        @Min(value = 0, message = "El endeudamiento no puede ser negativo")
        @Max(value = 100, message ="El endeudamiento no puede pasar el 100%")
        Integer nivelEndeudamiento,
        @NotNull(message = "La frecuencia de ahorro es obligatoria")
        @JsonProperty("frecuencia_ahorro")
        String frecuenciaAhorro,
        @NotEmpty(message = "Se debe enviar al menos una transaccion")
        List<TransactionRequest> transactions
) {}
