package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.service.AnalisisIAService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analisis/predict")
@RequiredArgsConstructor
public class AnalisisController {
    private final AnalisisIAService  analisisIAService;

    @PostMapping
    public AnalisisResponse  predict(@Valid @RequestBody AnalisisRequest analisisRequest) {
        return analisisIAService.analizar(analisisRequest);
    }
}
