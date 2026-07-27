package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.TransactionRequestDTO;
import com.nocountry.financeai.dto.TransactionResponseDTO;
import com.nocountry.financeai.entity.TransactionEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;


    @Override
    public List<TransactionResponseDTO> listarTransacciones() {
        return transactionRepository.findAll()
                .stream()
                .map(transaction -> new TransactionResponseDTO(
                        transaction.getId(),
                        transaction.getMonto(),
                        transaction.getTipo(),
                        transaction.getCategoria(),
                        transaction.getDescripcion(),
                        transaction.getFecha(),
                        transaction.getUsuario().getId()
                ))
                .toList();
    }

    @Override
    public TransactionResponseDTO crearTransaccion(TransactionRequestDTO dto) {
        UserEntity usuario = userRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TransactionEntity transaction = TransactionEntity.builder()
                .monto(dto.monto())
                .tipo(dto.tipo())
                .categoria(dto.categoria())
                .descripcion(dto.descripcion())
                .fecha(dto.fecha())
                .usuario(usuario)
                .build();

        TransactionEntity guardada = transactionRepository.save(transaction);

        return new TransactionResponseDTO(
                guardada.getId(),
                guardada.getMonto(),
                guardada.getTipo(),
                guardada.getCategoria(),
                guardada.getDescripcion(),
                guardada.getFecha(),
                guardada.getUsuario().getId()
        );
    }

    @Override
    public TransactionResponseDTO actualizarTransaccion(Long id, TransactionRequestDTO dto) {
        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        transaction.setMonto(dto.monto());
        transaction.setTipo(dto.tipo());
        transaction.setCategoria(dto.categoria());
        transaction.setDescripcion(dto.descripcion());
        transaction.setFecha(dto.fecha());

        TransactionEntity actualizada = transactionRepository.save(transaction);
        return new TransactionResponseDTO(
                actualizada.getId(),
                actualizada.getMonto(),
                actualizada.getTipo(),
                actualizada.getCategoria(),
                actualizada.getDescripcion(),
                actualizada.getFecha(),
                actualizada.getUsuario().getId()
        );
    }

    @Override
    public void eliminarTransaccion(Long id) {
        TransactionEntity transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transacción no encontrada"));

        transactionRepository.delete(transaction);
    }
}
