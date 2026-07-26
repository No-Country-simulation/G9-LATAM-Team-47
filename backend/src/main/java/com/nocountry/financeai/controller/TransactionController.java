package com.nocountry.financeai.controller;

import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.entity.TransactionEntity;

import com.nocountry.financeai.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import com.nocountry.financeai.dto.TransactionRequestDTO;
import com.nocountry.financeai.dto.TransactionResponseDTO;
import com.nocountry.financeai.entity.UserEntity;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    public TransactionController(TransactionRepository transactionRepository,
                                 UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<TransactionResponseDTO> listarTransacciones(){
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

    @PostMapping
    public TransactionResponseDTO crearTransaccion(@RequestBody TransactionRequestDTO request){

        UserEntity usuario = userRepository.findById(request.usuarioId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TransactionEntity transaction = TransactionEntity.builder()
                .monto(request.monto())
                .tipo(request.tipo())
                .categoria(request.categoria())
                .descripcion(request.descripcion())
                .fecha(request.fecha())
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
}
