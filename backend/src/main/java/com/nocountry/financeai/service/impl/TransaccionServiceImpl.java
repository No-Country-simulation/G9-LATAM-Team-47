package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.entity.TransactionEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public TransaccionResponse crearTransaccionAutenticado(String email, TransactionRequest transactionRequest) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        TransactionEntity transaccion = TransactionEntity.builder()
                        .nombreComercio(transactionRequest.nombreComercio())
                        .montoTransaccion(transactionRequest.montoTransaccion())
                        .medioPago(transactionRequest.mediopago())
                        .usuario(usuario)
                        .fecha(LocalDateTime.now())
                        .build();

        TransactionEntity transaccionGuardada = transactionRepository.save(transaccion);

        return convertirRespuesta(
                transaccionGuardada
        );
    }

    @Override
    public List<TransaccionResponse> obtenerTransaccionesAutenticado(String email) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return transactionRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public TransaccionResponse crearTransaccion(Long usuarioId, TransactionRequest transactionRequest) {
        UserEntity usuario = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TransactionEntity transaccion = TransactionEntity.builder()
                .nombreComercio(transactionRequest.nombreComercio())
                .montoTransaccion(transactionRequest.montoTransaccion())
                .medioPago(transactionRequest.mediopago())
                .usuario(usuario)
                .fecha(LocalDateTime.now())
                .build();

        TransactionEntity transaccionGuardada = transactionRepository.save(transaccion);

        return new TransaccionResponse(
                transaccionGuardada.getNombreComercio(),
                transaccionGuardada.getMontoTransaccion(),
                transaccionGuardada.getMedioPago(),
                transaccionGuardada.getFecha()
        );
    }

    @Override
    public List<TransaccionResponse> obtenerTransaccionesPorUsuario(Long idUsuario) {
        return transactionRepository.findByUsuarioId(idUsuario)
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    public List<TransaccionResponse> obtenerTransacciones() {
        return transactionRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    private TransaccionResponse convertirRespuesta(TransactionEntity transactionEntity) {
        return new TransaccionResponse(
                transactionEntity.getNombreComercio(),
                transactionEntity.getMontoTransaccion(),
                transactionEntity.getMedioPago(),
                transactionEntity.getFecha()
        );
    }
}
