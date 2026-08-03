package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.entity.TransactionEntity;

import com.nocountry.financeai.service.TransaccionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transacciones")
@Tag(name = "Transacciones", description = "Registro y consulta de transacciones")
public class TransactionController {

    private final TransaccionService transaccionService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TransaccionResponse> listarTransacciones(){
        return transaccionService.obtenerTransacciones();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuario/{usuarioId}")
    public List<TransaccionResponse> listarTransaccionesPorUsuario(@PathVariable Long usuarioId) {
        return transaccionService.obtenerTransaccionesPorUsuario(usuarioId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/usuario/{usuarioId}")
    public TransaccionResponse crearTransaccion(
            @PathVariable Long usuarioId,
            @Valid @RequestBody TransactionRequest transactionRequest) {
        return transaccionService.crearTransaccion(usuarioId, transactionRequest);
    }

    @PostMapping("/usuario/transacciones")
    public TransaccionResponse crearTransaccionAutenticado(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody TransactionRequest transactionRequest
    ) {
        return transaccionService.crearTransaccionAutenticado(
                userDetails.getUsername(),
                transactionRequest
        );
    }

    @GetMapping("/usuario/transacciones")
    public List<TransaccionResponse> obtenerMisTransacciones(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return transaccionService.obtenerTransaccionesAutenticado(userDetails.getUsername());
    }
}

