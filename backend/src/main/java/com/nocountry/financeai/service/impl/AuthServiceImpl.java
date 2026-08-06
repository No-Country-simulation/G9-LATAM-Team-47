package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.LoginRequest;
import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.UserAlreadyExistsException;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.security.JwtUtil;
import com.nocountry.financeai.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // 1. Usamos request.email() en vez de getEmail() por ser un record
        if (userRepository.existsByEmail(request.email())) {
            throw new UserAlreadyExistsException("El correo ya esta registrado");
        }
        if (userRepository.existsByDocumento(request.documento())) {
            throw new UserAlreadyExistsException("El documento ya esta registrado");
        }

        // 2. Usamos request.nombre() tal cual lo definiste en tu record
        UserEntity user = UserEntity.builder()
                .nombre(request.nombre())
                .apellido(request.apellido())
                .documento(request.documento())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .fechaNacimiento(request.fechaNacimiento())
                .sexo(request.sexo())
                .estadoCivil(request.estadoCivil())
                .numeroHijos(request.numeroHijos())
                .build();

        userRepository.save(user);

        // 3. Adaptamos el usuario a UserDetails para que el JwtUtil lo acepte sin errores
        UserDetails userDetails = User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();

        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, "Usuario registrado exitosamente");
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        UserEntity user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new BadCredentialsException("Usuario no encontrado"));

        // Adaptamos el usuario autenticado a UserDetails
        UserDetails userDetails = User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();

        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponse(token, "Inicio de sesión exitoso");
    }
}
