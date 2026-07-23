package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;

public interface AnalisisIAService {
    AnalisisResponse analizar(AnalisisRequest analisisRequest);
}
