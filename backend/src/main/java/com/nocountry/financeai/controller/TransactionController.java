package com.nocountry.financeai.controller;

import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.entity.TransactionEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public List<TransactionEntity> listarTransacciones(){
        return transactionRepository.findAll();
    }

    @PostMapping
    public TransactionEntity crearTransaccion(@RequestBody TransactionEntity transaction){
        return transactionRepository.save(transaction);
    }
}
