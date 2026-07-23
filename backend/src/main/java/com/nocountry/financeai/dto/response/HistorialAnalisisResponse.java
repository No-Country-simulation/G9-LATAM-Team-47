package com.nocountry.financeai.dto.response;

import com.nocountry.financeai.model.enums.PerfilFinanciero.PerfilFinanciero;

import java.math.BigDecimal;

public record HistorialAnalisisResponse (
  Long id,
  Long usuarioId,
  PerfilFinanciero perfilFinanciero,
  BigDecimal probabilidad,
  String recomendaciones
){}
