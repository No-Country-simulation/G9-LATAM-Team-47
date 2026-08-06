package com.nocountry.financeai.client;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class IAClient {
    private final RestClient restClient;

    public AnalisisResponse analizar(AnalisisRequest request) {

        return restClient.post()
                .uri("/analisis-financiero")
                .body(request)
                .retrieve()
                .body(AnalisisResponse.class);
    }
}
