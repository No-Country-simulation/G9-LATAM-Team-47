package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;


public interface AnalisisIAService {
    // metodo para hacer el analisis del usuario autenticado
    AnalisisResponse analizar(String email);

    // medodo para hacer el analisis por documento de identificacion
    AnalisisResponse analizarPorDocumento(String documento);

    // metodo para hacer el analisi de un usuario por Id
    AnalisisResponse analizarPorUsuarioId(Long usuarioId);
}
