package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;

import java.util.List;

public interface HistorialAnalisisService {
    List<HistorialAnalisisResponse> obtenerHistorial();
    List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id);
    List<HistorialAnalisisResponse> obtenerHistorialAutenticado(String email);
    // Devuelve el análisis más reciente del usuario autenticado; lanza ResourceNotFoundException si no tiene ninguno
    HistorialAnalisisResponse obtenerUltimoAutenticado(String email);
}
