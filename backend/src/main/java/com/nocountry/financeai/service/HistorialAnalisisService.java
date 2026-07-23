package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.model.HistorialAnalisis;

import java.util.List;

public interface HistorialAnalisisService {
    List<HistorialAnalisisResponse> obtenerHistorial();
}
