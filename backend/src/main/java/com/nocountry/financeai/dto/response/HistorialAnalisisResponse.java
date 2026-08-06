package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.entity.enums.RangoAhorro;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record HistorialAnalisisResponse (
        @JsonProperty("perfil_financiero")
        PerfilFinanciero perfilFinanciero,

        BigDecimal probabilidad,
        @JsonProperty("nivel_endeudamiento")
        BigDecimal nivelEndeudamiento,
        @JsonProperty("rango_ahorro")
        RangoAhorro rangoAhorro,
        @JsonProperty("resumen_gastos")
        Map<String, BigDecimal> resumenGastos,
        @JsonProperty
        List<String> recomendaciones
){}
