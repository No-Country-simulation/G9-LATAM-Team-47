package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.entity.TransactionEntity;

import java.util.List;

public interface TransaccionService {
    //crea transacciones de un usuario autenticado
    TransaccionResponse crearTransaccionAutenticado(String email,TransactionRequest transactionRequest);
    // Obtiene las transacciones de un usuario registrado
    List<TransaccionResponse> obtenerTransaccionesAutenticado(String email);
    // Crea transaccion por Id
    TransaccionResponse crearTransaccion(Long usuarioId, TransactionRequest transactionRequest);
    // Obtiene todas las transacciones de todos los usuarios
    List<TransaccionResponse> obtenerTransacciones();
    // Obtiene todas las transacciones de un usuario
    List<TransaccionResponse> obtenerTransaccionesPorUsuario(Long idUsuario);
}
