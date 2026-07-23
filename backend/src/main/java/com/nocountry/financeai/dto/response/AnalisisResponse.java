package com.nocountry.financeai.dto.response;

import com.nocountry.financeai.dto.response.ResumenGastosResponse.ResumenGastos;

import java.math.BigDecimal;
import java.util.List;

public record AnalisisResponse(
        String perfilFinanciero,
        BigDecimal probabilidad,
        ResumenGastos resumenGastos,
        List<String> recomendaciones
) {
}
