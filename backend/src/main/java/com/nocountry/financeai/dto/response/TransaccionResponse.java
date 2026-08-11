package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransaccionResponse(
        @Schema(
                description = "Nombre del comercio que aparece en la factura",
                example = "telcel"
        )
        @JsonProperty("nombre_comercio")
        String nombreComercio,

        @Schema(
                description = "Valor de la transaccion",
                example = "365"
        )
        @JsonProperty("monto_transaccion")
        BigDecimal montoTransaccion,

        @Schema(
                description = "Medio de pago en el que se pago/cancelo la transaccion",
                example = "EFECTIVO"
        )
        @JsonProperty("medio_pago")
        MedioPago medioPago,
        @Schema(
                description = "Fecha de la transaccion",
                example = "2026-08-06T20:06:38.692Z"
        )
        LocalDateTime fecha
) {}
