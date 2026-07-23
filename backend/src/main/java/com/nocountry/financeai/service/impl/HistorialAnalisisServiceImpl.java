package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.model.HistorialAnalisis;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.service.HistorialAnalisisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialAnalisisServiceImpl implements HistorialAnalisisService {
    private final HistorialAnalisisRepository historialAnalisisRepository;


    @Override
    public List<HistorialAnalisisResponse> obtenerHistorial() {

        return historialAnalisisRepository.findAll()
                .stream()
                .map(historial -> new HistorialAnalisisResponse(
                        historial.getId(),
                        historial.getUsuarioId(),
                        historial.getPerfilFinanciero(),
                        historial.getProbabilidad(),
                        historial.getRecomendaciones()
                ))
                .toList();

    }
}
