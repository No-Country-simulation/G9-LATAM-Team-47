package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.service.AnalisisIAService;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analisis/predict")
@RequiredArgsConstructor
@Tag(
        name = "Analisis",
        description = "Generacion de diagnosticos financieros generados por modelo dataScience"
)
public class AnalisisController {
    private final AnalisisIAService  analisisIAService;

    @PostMapping
    public AnalisisResponse  analizar(@AuthenticationPrincipal UserDetails userDetails) {
        return analisisIAService.analizar(userDetails.getUsername());

    }
}
