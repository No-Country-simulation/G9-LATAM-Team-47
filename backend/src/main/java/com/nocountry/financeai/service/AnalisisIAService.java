package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;

import java.util.List;

public interface AnalisisIAService {
    AnalisisResponse analizar(AnalisisRequest analisisRequest);

}
