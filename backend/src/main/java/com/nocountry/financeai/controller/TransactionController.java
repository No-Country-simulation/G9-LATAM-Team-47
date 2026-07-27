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

import com.nocountry.financeai.service.TransactionService;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionResponseDTO> listarTransacciones(){
        return transactionService.listarTransacciones();
    }

    @PostMapping
    public TransactionResponseDTO crearTransaccion(@RequestBody TransactionRequestDTO request){
        return transactionService.crearTransaccion(request);
    }
}
