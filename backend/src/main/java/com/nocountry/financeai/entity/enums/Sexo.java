package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {
    MASCULINO("M"),
    FEMENINO("F"); // Corregido el typo AUD-20 (antes FEMININO)

    private final String codigo;

    Sexo(String codigo) {
        this.codigo = codigo;
    }

    // @JsonValue indica que al convertir este Enum a JSON,
    // se debe usar el valor de este metodo ("M" o "F")
    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    // @JsonCreator intercepta el JSON entrante y lo convierte al Enum correcto
    @JsonCreator
    public static Sexo fromCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Sexo sexo : Sexo.values()) {
            if (sexo.codigo.equalsIgnoreCase(codigo.trim())) {
                return sexo;
            }
        }
        throw new IllegalArgumentException("Valor no aceptado para Sexo. Se esperaba 'M' o 'F', pero se recibió: " + codigo);
    }
}
