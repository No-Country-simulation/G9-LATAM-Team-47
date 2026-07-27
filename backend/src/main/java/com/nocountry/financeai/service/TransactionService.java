package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.TransactionRequestDTO;
import com.nocountry.financeai.dto.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {

    List<TransactionResponseDTO> listarTransacciones();

    TransactionResponseDTO crearTransaccion(TransactionRequestDTO dto);
}
