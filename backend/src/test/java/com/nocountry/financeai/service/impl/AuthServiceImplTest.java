package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.LoginRequest;
import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Rol;
import com.nocountry.financeai.entity.enums.Sexo;
import com.nocountry.financeai.exception.UserAlreadyExistsException;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.security.JwtUtil;
import com.nocountry.financeai.service.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class AuthServiceImplTest {

    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private JwtUtil jwtUtil;
    @Mock private AuthenticationManager authenticationManager;

    @InjectMocks private AuthServiceImpl authService;

    private RegisterRequest registerRequest;
    private LoginRequest loginRequest;
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        registerRequest = RegisterRequest.builder()
                .nombre("Carlos")
                .apellido("Gomez")
                .documento("12345678")
                .email("carlos@example.com")
                .password("Password123!")
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .sexo(Sexo.MASCULINO)
                .estadoCivil(EstadoCivil.SOLTERO)
                .numeroHijos(0)
                .build();

        loginRequest = new LoginRequest("carlos@example.com", "Password123!");

        userEntity = UserEntity.builder()
                .id(1L)
                .nombre("Carlos")
                .email("carlos@example.com")
                .password("encodedPassword")
                .rol(Rol.USER)
                .build();
    }

    @Test
    void register_Exito() {
        when(userRepository.findByEmail(registerRequest.email())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(registerRequest.password())).thenReturn("encodedPassword");
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);
        when(jwtUtil.generateToken(any())).thenReturn("mockJwtToken");

        AuthResponse response = authService.register(registerRequest);

        assertNotNull(response);
        assertEquals("mockJwtToken", response.token());
        verify(userRepository).save(any(UserEntity.class));
    }

    @Test
    void register_Falla_UsuarioYaExiste() {
        when(userRepository.existsByEmail(any())).thenReturn(true);
        when(userRepository.findByEmail(registerRequest.email())).thenReturn(Optional.of(userEntity));
        assertThrows(UserAlreadyExistsException.class, () -> authService.register(registerRequest));
        verify(userRepository, never()).save(any(UserEntity.class));
    }

    @Test
    void login_Exito() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(userEntity.getEmail(), userEntity.getPassword()));
        when(userRepository.findByEmail(loginRequest.email())).thenReturn(Optional.of(userEntity));
        when(jwtUtil.generateToken(any())).thenReturn("mockJwtToken");

        AuthResponse response = authService.login(loginRequest);

        assertNotNull(response);
        assertEquals("mockJwtToken", response.token());
    }

    @Test
    void login_Falla_CredencialesInvalidas() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Credenciales inválidas"));

        assertThrows(BadCredentialsException.class, () -> authService.login(loginRequest));
    }
}
