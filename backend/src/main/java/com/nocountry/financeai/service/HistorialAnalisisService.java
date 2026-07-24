package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;

import java.util.List;

public interface HistorialAnalisisService {
    List<HistorialAnalisisResponse> obtenerHistorial();
    List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id);
}
