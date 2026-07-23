package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.service.AnalisisIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class AnalisisIAServiceImpl implements AnalisisIAService {
    private final RestClient restClient;

    @Override
    public AnalisisResponse analizar(AnalisisRequest request) {

        return restClient.post()
                .uri("/predict")
                .body(request)
                .retrieve()
                .body(AnalisisResponse.class);
    }
}
