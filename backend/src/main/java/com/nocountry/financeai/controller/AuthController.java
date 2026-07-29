package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;
import com.nocountry.financeai.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull; // <-- Nuevo import
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    // Agregamos @NotNull para eliminar la advertencia del IDE
    public ResponseEntity<AuthResponse> register(@Valid @NotNull @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}