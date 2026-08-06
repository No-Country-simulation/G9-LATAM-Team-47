package com.nocountry.financeai.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(
        name = "Test",
        description = "Prebas preeliminares del sistema "
)
public class TestSecurityController {

    // Ruta pública (dentro de /auth/**)
    @GetMapping("/auth/ping")
    public ResponseEntity<String> publicPing() {
        return ResponseEntity.ok("Ruta pública OK - Acceso permitido sin token");
    }

    // Ruta protegida
    @GetMapping("/test/protected")
    public ResponseEntity<String> protectedPing() {
        return ResponseEntity.ok("Ruta protegida OK - Requiere token JWT válido");
    }
}
