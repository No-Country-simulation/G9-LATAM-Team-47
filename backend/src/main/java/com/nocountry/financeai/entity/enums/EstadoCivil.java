package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Estado civil de un usuario",
        example = "DIVORCIADO"

)
public enum EstadoCivil {
    SOLTERO,
    CASADO,
    DIVORCIADO,
    VIUDO;

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
