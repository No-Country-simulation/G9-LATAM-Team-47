package com.nocountry.financeai.dto.request;

import java.math.BigDecimal;

public record TransactionRequest(
    String descripcion,
    BigDecimal valor
) {}
