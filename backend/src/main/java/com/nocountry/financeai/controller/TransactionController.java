package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.service.TransaccionService;
import com.nocountry.financeai.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transacciones")
@Tag(
        name = "Transacciones",
        description = "Registro y consulta de transacciones")
public class TransactionController {

    private final UserService userService;
    private final TransaccionService transaccionService;

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
    public ResponseEntity<List<TransaccionResponse>> obtenerTransaccionesAutenticado(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) String categoria) {

        List<TransaccionResponse> transacciones = transaccionService
                .obtenerTransaccionesPorCategoria(userDetails.getUsername(), categoria);

        return ResponseEntity.ok(transacciones);
    }

    @PatchMapping("/usuario/transacciones/{idTransaccion}")
    public TransaccionResponse actualizarTransaccionAutenticado(
            Authentication authentication,
            @PathVariable Long idTransaccion,
            @Valid @RequestBody TransactionRequest transactionRequest) {
        return transaccionService.actualizarTransaccion(authentication.getName(), idTransaccion, transactionRequest);
    }

    @DeleteMapping("/usuario/transacciones/{idTransaccion}")
    public ResponseEntity<Map<String,String>> eliminarTransaccionAutenticado(
            Authentication authentication,
            @PathVariable Long idTransaccion){
        transaccionService.eliminarTransaccion(authentication.getName(), idTransaccion);
        return ResponseEntity.ok(Map.of("message", "Transaccion eliminada correctamente"));
    }
}