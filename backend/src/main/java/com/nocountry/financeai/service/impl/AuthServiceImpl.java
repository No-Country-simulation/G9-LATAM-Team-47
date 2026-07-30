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
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("El correo ya está registrado");
        }

        // 2. Usamos request.nombre() tal cual lo definiste en tu record
        var user = UserEntity.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .edad(request.getEdad())
                .sexo(request.getSexo())
                .estadoCivil(request.getEstadoCivil())
                .numeroHijos(request.getNumeroHijos())
                .empleoFormal(request.getEmpleoFormal())
                .ingresoMensual(request.getIngresoMensual())
                .lineaCredito(request.getLineaCredito())
                // .role(Role.USER) (si lo estás manejando)
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
        // NOTA: Si también convertiste LoginRequest a record, debes cambiar
        // request.getEmail() por request.email() y request.getPassword() por request.password()

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        UserEntity user = userRepository.findByEmail(request.getEmail())
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
