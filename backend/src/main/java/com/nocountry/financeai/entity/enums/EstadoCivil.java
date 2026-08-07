package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoCivil {
    SOLTERO("SOLTERO"),
    CASADO("CASADO"),
    DIVORCIADO("DIVORCIADO"),
    VIUDO("VIUDO");

    private final String valor;

    EstadoCivil(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static EstadoCivil fromValor(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        String normalized = valor.trim().toUpperCase();
        for (EstadoCivil ec : EstadoCivil.values()) {
            if (ec.name().equalsIgnoreCase(normalized) || ec.valor.equalsIgnoreCase(normalized)) {
                return ec;
            }
        }
        // Fallback flexible para evitar errores 400 por tildes o variaciones
        if (normalized.contains("SOLTERO")) return SOLTERO;
        if (normalized.contains("CASADO")) return CASADO;
        if (normalized.contains("DIVORCIADO")) return DIVORCIADO;
        if (normalized.contains("VIUDO")) return VIUDO;

        throw new IllegalArgumentException("Valor no aceptado para EstadoCivil: " + valor);
    }
}
