package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Medio de pago que usa un usuario para cancelar una transaccion",
        example = "TRANSFERENCIA"
)
public enum MedioPago {
    EFECTIVO,
    DEBITO,
    CREDITO,
    TRANSFERENCIA;

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
