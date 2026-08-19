package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.PerfilFinancieroRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.PerfilFinancieroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilFinancieroServiceImpl implements PerfilFinancieroService {
    private final PerfilFinancieroRepository perfilFinancieroRepository;
    private final UserRepository userRepository;

    @Override
    public PerfilFinancieroEntity obtenerPerfilPorUsuarioId(Long usuarioId) {
        return perfilFinancieroRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El usuario no tiene perfil financiero"
                ));
    }

    @Override
    public PerfilFinancieroResponse crearPerfil(String email, PerfilFinancieroRequest request) {
        UserEntity usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (perfilFinancieroRepository.findByUsuarioId(usuario.getId()).isPresent()) {
            throw new IllegalStateException("El usuario ya tiene un perfil financiero registrado");
        }

        PerfilFinancieroEntity perfil = PerfilFinancieroEntity.builder()
                .usuario(usuario)
                .empleoFormal(request.empleoFormal())
                .ingresoMensual(request.ingresoMensual())
                .lineaCredito(request.lineaCredito())
                .build();
        PerfilFinancieroEntity perfilGuardado = perfilFinancieroRepository.save(perfil);

        return new PerfilFinancieroResponse(
                perfilGuardado.getEmpleoFormal(),
                perfilGuardado.getIngresoMensual(),
                perfilGuardado.getLineaCredito()
        );
    }
    @Override
    public PerfilFinancieroResponse obtenerPerfilPorEmail(String email) {
        UserEntity usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        PerfilFinancieroEntity perfil = perfilFinancieroRepository.findByUsuarioId(usuario.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Perfil financiero no registrado"));

        return new PerfilFinancieroResponse(
                perfil.getEmpleoFormal(),
                perfil.getIngresoMensual(),
                perfil.getLineaCredito()
                // Añade otros campos si tu DTO los requiere
        );
    }
}
