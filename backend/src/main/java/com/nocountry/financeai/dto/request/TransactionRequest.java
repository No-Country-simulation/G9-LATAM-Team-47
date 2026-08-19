package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(

        @Schema(
                description = "Nombre del comercio donde se realizó la transacción",
                example = "Supermercado Éxito"
        )
        @JsonProperty("nombre_comercio")
        @NotBlank(message = "El nombre del comercio es obligatorio")
        String nombreComercio,

        @Schema(
                description = "Monto de la transacción",
                example = "210.00"
        )
        @JsonProperty("monto_transaccion")
        @NotNull(message = "El monto de la transacción es obligatorio")
        @Positive(message = "El monto debe ser mayor a cero")
        BigDecimal montoTransaccion,

        @Schema(
                description = "Medio de pago utilizado",
                example = "debito"
        )

        @JsonProperty("medio_pago")
        @NotNull(message = "El medio de pago es obligatorio")
        MedioPago medioPago,

        @Schema(description = "Categoría de la transacción (generada por IA o ingresada manualmente)", example = "Alimentación")
        @JsonProperty("categoria")
                String categoria
) {
}
