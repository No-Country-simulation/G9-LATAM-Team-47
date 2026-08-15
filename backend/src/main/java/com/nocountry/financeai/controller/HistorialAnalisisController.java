package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.service.HistorialAnalisisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analisis")
@RequiredArgsConstructor
@Tag(
        name = "Historial Resultado Analisis",
        description = "Listado de historiales realizados de un usuario"
)
public class HistorialAnalisisController {
    private final HistorialAnalisisService historialAnalisisService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuario/{userId}")
    public List<HistorialAnalisisResponse> obtenerHistorialPorId(@PathVariable Long userId) {
        return historialAnalisisService.obtenerHistorialPorId(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<HistorialAnalisisResponse> obtenerHistorial() {
        return historialAnalisisService.obtenerHistorial();
    }

    @GetMapping("/usuario/historial")
    public List<HistorialAnalisisResponse> obtenerMiHistorial(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return historialAnalisisService.obtenerHistorialAutenticado(userDetails.getUsername());
    }

    // Nuevo: pull del análisis más reciente, sin recalcular. Usado por el botón "Ver mi análisis".
    @GetMapping("/usuario/ultimo")
    public HistorialAnalisisResponse obtenerMiUltimoAnalisis(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return historialAnalisisService.obtenerUltimoAutenticado(userDetails.getUsername());
    }
}