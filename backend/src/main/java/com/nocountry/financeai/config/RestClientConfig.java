package com.nocountry.financeai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Value("${ia.api.url}")
    private String iaApiUrl;
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(iaApiUrl)
                .build();
    }
}
