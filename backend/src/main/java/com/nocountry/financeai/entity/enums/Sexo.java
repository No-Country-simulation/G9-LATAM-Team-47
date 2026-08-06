package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Genero del usuario",
        example = "Femenino"
)
public enum Sexo {
    FEMENINO,
    MASCULINO;

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
