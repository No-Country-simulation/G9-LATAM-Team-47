package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.entity.TransactionEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.entity.enums.MedioPago;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransaccionServiceImplTest {

    @Mock private TransactionRepository transactionRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks private TransaccionServiceImpl transaccionService;

    private UserEntity userEntity;
    private TransactionRequest transactionRequest;
    private TransactionEntity transactionEntity;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder().id(1L).email("carlos@example.com").build();
        
        transactionRequest = new TransactionRequest(
                "Supermercado", 
                new BigDecimal("150.50"), 
                MedioPago.DEBITO
        );

        transactionEntity = TransactionEntity.builder()
                .idTransaccion(1L)
                .usuario(userEntity)
                .nombreComercio("Supermercado")
                .montoTransaccion(new BigDecimal("150.50"))
                .medioPago(MedioPago.DEBITO)
                .fechaTransaccion(LocalDateTime.now())
                .build();
    }

    @Test
    void crearTransaccionAutenticado_Exito() {
        when(userRepository.findByEmail("carlos@example.com")).thenReturn(Optional.of(userEntity));
        when(transactionRepository.save(any(TransactionEntity.class))).thenReturn(transactionEntity);

        TransaccionResponse response = transaccionService.crearTransaccionAutenticado("carlos@example.com", transactionRequest);

        assertNotNull(response);
        assertEquals("Supermercado", response.nombreComercio());
        assertEquals(new BigDecimal("150.50"), response.montoTransaccion());
        verify(transactionRepository).save(any(TransactionEntity.class));
    }

    @Test
    void crearTransaccionAutenticado_Falla_UsuarioNoEncontrado() {
        when(userRepository.findByEmail("inexistente@example.com")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> 
            transaccionService.crearTransaccionAutenticado("inexistente@example.com", transactionRequest)
        );
    }
}
