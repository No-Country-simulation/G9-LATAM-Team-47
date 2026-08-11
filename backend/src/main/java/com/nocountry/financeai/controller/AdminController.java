package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.dto.response.UserResponse;
import com.nocountry.financeai.service.AnalisisIAService;
import com.nocountry.financeai.service.TransaccionService;
import com.nocountry.financeai.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
@Tag(
        name = "Administradores",
        description = "Administra el sistema"
)
public class AdminController {
    private final UserService userService;
    private final TransaccionService transaccionService;
    private final AnalisisIAService analisisIAService;

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> obtenerUsuarios() {
        return userService.obtenerUsuarios();
    }

    @GetMapping("/usuarios/documento/{documento}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse obtenerUsuario(@PathVariable String documento) {
        return userService.obtenerUsuarioPorDocumento(documento);
    }

    @GetMapping("/transacciones")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TransaccionResponse> listarTransacciones(){
        return transaccionService.obtenerTransacciones();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/transacciones/usuario/{usuarioId}")
    public List<TransaccionResponse> listarTransaccionesPorUsuario(@PathVariable Long usuarioId) {
        return transaccionService.obtenerTransaccionesPorUsuario(usuarioId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("transacciones/usuario/{usuarioId}")
    public TransaccionResponse crearTransaccion(
            @PathVariable Long usuarioId,
            @Valid @RequestBody TransactionRequest transactionRequest) {
        return transaccionService.crearTransaccion(usuarioId, transactionRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/usuario/{documento}/analizar")
    public AnalisisResponse analisisPorUsuario(
            @PathVariable String documento
    ) {
        return analisisIAService.analizarPorDocumento(documento);
    }


}
