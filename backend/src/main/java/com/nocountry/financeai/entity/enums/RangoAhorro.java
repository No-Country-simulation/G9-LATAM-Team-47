package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RangoAhorro {
    ALTA,
    MEDIA,
    BAJA,
    NINGUNA;

    @JsonCreator
    public static RangoAhorro fromString(String value) {
        return RangoAhorro.valueOf(value.trim().toUpperCase());
    }
}
