package com.nocountry.financeai.controller;
import com.nocountry.financeai.dto.request.ChangePasswdRequest;
import com.nocountry.financeai.dto.request.UserRequest;
import com.nocountry.financeai.dto.response.UserResponse;
import com.nocountry.financeai.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@Tag(
        name = "Usuarios",
        description = "Gestion del perfil de usuario"
)
public class UserController {
    private final UserService userService;

    @GetMapping("/miPerfil")
    public UserResponse obtenerMiPerfil(Authentication authentication) {

        return userService.obtenerMiPerfil(authentication.getName());
    }

    @PatchMapping("/miPerfil")
    public UserResponse actualizarMiPerfil(Authentication authentication, @Valid @RequestBody UserRequest userRequest) {
        return userService.actualizarMiPerfil(authentication.getName(), userRequest);
    }

    @PutMapping("/miPerfil/passwd")
    public ResponseEntity<Map<String, String>> cambiarPasswd(Authentication autenticacion, @Valid @RequestBody ChangePasswdRequest request){
        userService.cambiarPasswd(autenticacion.getName(), request);
        return ResponseEntity.ok(Map.of("message", "Se actualizor correctamente la contraseña"));
    }
}
