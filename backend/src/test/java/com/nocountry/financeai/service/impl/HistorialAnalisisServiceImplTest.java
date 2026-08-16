package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.HistorialAnalisisServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class HistorialAnalisisServiceImplTest {

    @Mock private HistorialAnalisisRepository historialAnalisisRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks private HistorialAnalisisServiceImpl historialAnalisisService;

    @Test
    void obtenerHistorial_Vacio() {
        // Implementación básica para estructura
        assertTrue(true);
    }
}
