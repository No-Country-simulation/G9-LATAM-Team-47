package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.HistorialAnalisisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialAnalisisServiceImpl implements HistorialAnalisisService {
    private final HistorialAnalisisRepository historialAnalisisRepository;
    private final UserRepository userRepository;
    @Override
    public List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id) {
        return historialAnalisisRepository.findByUsuarioId(id)
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public List<HistorialAnalisisResponse> obtenerHistorial() {

        return historialAnalisisRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();

    }

    @Override
    public List<HistorialAnalisisResponse> obtenerHistorialAutenticado(String email) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return historialAnalisisRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    public HistorialAnalisisResponse convertirRespuesta(HistorialAnalisisEntity historial) {
        return new HistorialAnalisisResponse(
                historial.getId(),
                historial.getUsuario().getId(),
                historial.getPerfilFinanciero(),
                historial.getProbabilidad(),
                historial.getResumenGastos(),
                historial.getRecomendaciones()
        );
    }
}


