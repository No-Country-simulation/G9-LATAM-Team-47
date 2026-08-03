package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;

public interface PerfilFinancieroService {
    PerfilFinancieroEntity obtenerPerfilPorUsuarioId(Long usuarioId);

    PerfilFinancieroResponse crearPerfil(String email, PerfilFinancieroRequest request);
}
