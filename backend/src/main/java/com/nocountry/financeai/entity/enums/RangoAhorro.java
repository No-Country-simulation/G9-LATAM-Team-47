package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Rango de ahorro del usuario",
        example = "Alta"
)
public enum RangoAhorro {
    ALTA,
    MEDIA,
    BAJA,
    NINGUNA;

    @JsonCreator
    public static PerfilFinanciero forString(String value) {
        return PerfilFinanciero.valueOf(value.trim().toUpperCase());
    }

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
