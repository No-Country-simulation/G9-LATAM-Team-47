package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
}