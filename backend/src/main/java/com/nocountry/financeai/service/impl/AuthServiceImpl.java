package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // <--- ¡ESTA ANOTACIÓN ES LA QUE RESUELVE TU ERROR!
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService { // <--- DEBE IMPLEMENTAR LA INTERFAZ

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.findByEmail(request.email()).isPresent()) {
            throw new IllegalArgumentException("El email ya se encuentra registrado.");
        }

        UserEntity newUser = new UserEntity();
        newUser.setNombre(request.nombre());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(newUser);

        return AuthResponse.builder()
                .message("Usuario registrado con éxito")
                .email(newUser.getEmail())
                .build();
    }
}
