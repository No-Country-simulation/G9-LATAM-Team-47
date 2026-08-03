package com.nocountry.financeai.dto.response;

import com.nocountry.financeai.entity.enums.MedioPago;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransaccionResponse(
        String nombreComercio,
        BigDecimal montoTransaccion,
        MedioPago medioPago,
        LocalDateTime fecha
) {}
