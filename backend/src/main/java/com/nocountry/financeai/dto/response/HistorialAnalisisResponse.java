package com.nocountry.financeai.dto.response;

import com.nocountry.financeai.entity.enums.PerfilFinanciero;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record HistorialAnalisisResponse (
  Long id,
  Long usuarioId,
  PerfilFinanciero perfilFinanciero,
  BigDecimal probabilidad,
  Map<String, BigDecimal> resumenGastos,
  List<String> recomendaciones
){}
