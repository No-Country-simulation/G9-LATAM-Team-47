package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.client.IAClient;
import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.service.AnalisisIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class AnalisisIAServiceImpl implements AnalisisIAService {
    
    private final IAClient iaClient;
    private final HistorialAnalisisRepository historialAnalisisRepository;
    @Override
    public AnalisisResponse analizar(AnalisisRequest request) {
    AnalisisResponse response = iaClient.analizar(request);

        HistorialAnalisisEntity historialAnalisisEntity = HistorialAnalisisEntity.builder()
                .perfilFinanciero(PerfilFinanciero.valueOf(response.perfilFinanciero()))
                .probabilidad(response.probabilidad())
                .resumenGastos(response.resumenGastos())
                .recomendaciones(response.recomendaciones())
                .build();

        historialAnalisisRepository.save(historialAnalisisEntity);

        return response;
    }
}
