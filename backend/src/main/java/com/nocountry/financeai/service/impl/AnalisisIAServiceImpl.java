package com.nocountry.financeai.service.impl;

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
    private final RestClient restClient;
    private final HistorialAnalisisRepository historialAnalisisRepository;

    @Override
    public AnalisisResponse analizar(AnalisisRequest request) {

         AnalisisResponse response = restClient.post()
                .uri("/predict")
                .body(request)
                .retrieve()
                .body(AnalisisResponse.class);

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
