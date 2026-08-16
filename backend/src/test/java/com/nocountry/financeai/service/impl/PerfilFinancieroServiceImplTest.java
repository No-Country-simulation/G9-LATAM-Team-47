package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.PerfilFinancieroRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.PerfilFinancieroServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PerfilFinancieroServiceImplTest {

    @Mock private PerfilFinancieroRepository perfilFinancieroRepository;
    @Mock private UserRepository userRepository;

    @InjectMocks private PerfilFinancieroServiceImpl perfilFinancieroService;

    private UserEntity userEntity;
    private PerfilFinancieroEntity perfilFinancieroEntity;

    @BeforeEach
    void setUp() {
        userEntity = UserEntity.builder().id(1L).email("test@example.com").build();
        perfilFinancieroEntity = PerfilFinancieroEntity.builder().idPerfilFinanciero(1L).build();
    }

    @Test
    void obtenerPerfilPorUsuarioId_Exito() {
        when(perfilFinancieroRepository.findByUsuarioId(1L)).thenReturn(Optional.of(perfilFinancieroEntity));
        
        PerfilFinancieroEntity result = perfilFinancieroService.obtenerPerfilPorUsuarioId(1L);
        assertNotNull(result);
        assertEquals(1L, result.getIdPerfilFinanciero());
    }

    @Test
    void obtenerPerfilPorUsuarioId_Falla() {
        when(perfilFinancieroRepository.findByUsuarioId(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> perfilFinancieroService.obtenerPerfilPorUsuarioId(1L));
    }
}
