package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.client.IAClient;
import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.TransactionEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.repository.PerfilFinancieroRepository;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.AnalisisIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalisisIAServiceImpl implements AnalisisIAService {
    
    private final IAClient iaClient;
    private final UserRepository userRepository;
    private final PerfilFinancieroRepository  perfilFinancieroRepository;
    private final TransactionRepository transactionRepository;
    private final HistorialAnalisisRepository historialAnalisisRepository;

    @Override
    public AnalisisResponse analizar(String email) {
        // Su busca el usuario por email, se usa el Id para hacer el analisis
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        ));
        return analizarPorUsuarioId(usuario.getId());
    }

    @Override
    public AnalisisResponse analizarPorUsuarioId(Long usuarioId) {
        // Busca el usuario por el id y se guarda
        UserEntity usuario = userRepository
                .findById(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));
        // Calcula la edad del usuario
        Integer edad = Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears();
        // Busca el perfil financiero asociado al usuario, si no tiene envia exepcion
        PerfilFinancieroEntity perfil = perfilFinancieroRepository
                .findByUsuarioId(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "El usuario no tiene un perfil financiero registrado"));
        // Guarda las transacciones de un usuario en una lista
        List<TransactionRequest> transaccionesRequest = transactionRepository
                .findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertirTransaccion)
                .toList();

        if (transaccionesRequest.isEmpty()) {
            throw new IllegalStateException("El usuario debe tener al menos una transacción registrada para generar un análisis");
        }

        // Teniendo tadas las variable para el analisis crea el request
        AnalisisRequest request = new AnalisisRequest(
                edad,
                usuario.getSexo(),
                usuario.getEstadoCivil(),
                usuario.getNumeroHijos(),
                perfil.getEmpleoFormal(),
                perfil.getIngresoMensual(),
                perfil.getLineaCredito(),
                transaccionesRequest
        );

        // Envia la peticion para hacer el analisis y guarda la respuesta
        AnalisisResponse response = iaClient.analizar(request);

        // Guarda el analisis al usuario
        guardarHistorial(usuario, response);

        return response;
    }

    // metodo para convertir entidad en request
    private TransactionRequest convertirTransaccion(TransactionEntity entity) {
        return new TransactionRequest(
                entity.getNombreComercio(),
                entity.getMontoTransaccion(),
                entity.getMedioPago()
        );
    }

    // metodo para guarda el historial en la base de datos
    private void guardarHistorial(UserEntity usuario, AnalisisResponse response) {
        HistorialAnalisisEntity historial = HistorialAnalisisEntity.builder()
                .usuario(usuario)
                .perfilFinanciero(response.perfilFinanciero())
                .probabilidad(response.probabilidad())
                .nivelEndeudamiento(response.nivelEndeudamiento())
                .rangoAhorro(response.rangoAhorro())
                .resumenGastos(response.resumenGastos())
                .recomendaciones(response.recomendaciones())
                .build();

        historialAnalisisRepository.save(historial);
    }
}
