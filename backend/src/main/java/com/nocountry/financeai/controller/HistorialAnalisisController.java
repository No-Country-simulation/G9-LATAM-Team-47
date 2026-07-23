package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.model.HistorialAnalisis;
import com.nocountry.financeai.service.HistorialAnalisisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analisis")
@RequiredArgsConstructor
public class HistorialAnalisisController {
    private final HistorialAnalisisService historialAnalisisService;

    @GetMapping
    public List<HistorialAnalisisResponse> obtenerHistorial() {
        return historialAnalisisService.obtenerHistorial();
    }
}
