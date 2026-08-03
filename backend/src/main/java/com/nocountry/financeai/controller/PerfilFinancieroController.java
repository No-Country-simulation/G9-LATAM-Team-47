package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.service.PerfilFinancieroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/perfil")
@RequiredArgsConstructor
@Tag(name = "Perfil Financiero", description = "Gestión del perfil financiero del usuario")
public class PerfilFinancieroController {
    private final PerfilFinancieroService perfilFinancieroService;

    @PostMapping
    public PerfilFinancieroResponse crearPerfil(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody PerfilFinancieroRequest request
    ) {
        System.out.println("Request recibido: " + request);
        return perfilFinancieroService.crearPerfil(userDetails.getUsername(), request);

    }

}
