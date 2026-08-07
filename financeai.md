This file is a merged representation of the entire codebase, combined into a single document by Repomix.

# File Summary

## Purpose
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.

## File Format
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  a. A header with the file path (## File: path/to/file)
  b. The full contents of the file in a code block

## Usage Guidelines
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.

## Notes
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)

# Directory Structure
````
backend/
  src/
    main/
      java/
        com/
          nocountry/
            financeai/
              client/
                IAClient.java
              config/
                .gitkeep
                CorsConfig.java
                RestClientConfig.java
              controller/
                .gitkeep
                AnalisisController.java
                AuthController.java
                HistorialAnalisisController.java
                TransactionController.java
              dto/
                request/
                  AnalisisRequest.java
                  RegisterRequest.java
                  TransactionRequest.java
                response/
                  AnalisisResponse.java
                  AuthResponse.java
                  HistorialAnalisisResponse.java
                .gitkeep
                ErrorResponseDto.java
              entity/
                enums/
                  PerfilFinanciero.java
                .gitkeep
                HistorialAnalisisEntity.java
                TransactionEntity.java
                UserEntity.java
              exception/
                .gitkeep
                ApiExceptionHandler.java
                ErrorResponse.java
                GlobalExceptionHandler.java
              repository/
                .gitkeep
                HistorialAnalisisRepository.java
                TransactionRepository.java
                UserRepository.java
              security/
                CustomUserDetailsService.java
                JwtAuthFilter.java
                JwtUtil.java
                SecurityConfig.java
              service/
                impl/
                  AnalisisIAServiceImpl.java
                  AuthServiceImpl.java
                  HistorialAnalisisServiceImpl.java
                .gitkeep
                AnalisisIAService.java
                AuthService.java
                HistorialAnalisisService.java
              FinanceaiApplication.java
      resources/
        db/
          migration/
            V1__create_users_table.sql
            V2__create_transactions_table.sql
            V3__create_analysis_table.sql
        application.yml
    test/
      java/
        com/
          nocountry/
            financeai/
              FinanceaiApplicationTests.java
  HELP.md
  mvnw
  mvnw.cmd
  pom.xml
  README.md
data-science/
  README.md
  requirements.txt
frontend/
  app.js
  index.html
  style.css
mock-api/
  app/
    models/
      __init__.py
      request.py
      response.py
    routers/
      __init__.py
      analisis.py
    services/
      __init__.py
      analisis_service.py
    __init__.py
    main.py
.gitattibutes
.gitignore
docker-compose.yml
README.md
````

# Files

## File: backend/src/main/java/com/nocountry/financeai/entity/enums/PerfilFinanciero.java
````java
package com.nocountry.financeai.entity.enums;

public enum PerfilFinanciero {
    SALUDABLE,
    EN_OBSERVACION,
    RIESGO
}
````

## File: backend/src/main/java/com/nocountry/financeai/entity/.gitkeep
````

````

## File: backend/src/main/java/com/nocountry/financeai/entity/HistorialAnalisisEntity.java
````java
package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "historial_analisis")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistorialAnalisisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_financiero", nullable = false)
    private PerfilFinanciero perfilFinanciero;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal probabilidad;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "resumen_gastos")
    private Map<String, BigDecimal> resumenGastos;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(nullable = false)
    private List<String> recomendaciones;

    @Column(name = "fecha_analisis", nullable = false, updatable = false)
    private LocalDateTime fechaAnalisis;

    @PrePersist
    protected void onCreate(){
        this.fechaAnalisis = LocalDateTime.now();
    }

}
````

## File: mock-api/app/models/__init__.py
````python

````

## File: mock-api/app/models/request.py
````python
from pydantic import BaseModel
from decimal import Decimal
from enum import Enum


class FrecuenciaAhorro(str, Enum):
    ALTA = "ALTA"
    MEDIA = "MEDIA"
    BAJA = "BAJA"

class TransaccionRequest(BaseModel):
    descripcion: str
    valor: Decimal

class AnalisisFinancieroRequest(BaseModel):
    ingresoMensual: Decimal
    nivelEndeudamiento: int
    frecuenciaAhorro: FrecuenciaAhorro
    transacciones: list[TransaccionRequest]
````

## File: mock-api/app/models/response.py
````python
from decimal import Decimal
from enum import Enum
from pydantic import BaseModel

class PerfilFinanciero(str, Enum):
    SALUDABLE = "SALUDABLE"
    EN_OBSERVACION = "EN_OBSERVACION"
    EN_RIESGO = "EN_RIESGO"

class ResumenGastosResponse(BaseModel):
    alimentacion: Decimal
    transporte: Decimal
    entretenimiento: Decimal
    salud: Decimal
    educacion: Decimal
    servicios: Decimal
    otros: Decimal

class AnalisisFinancieroResponse(BaseModel):
    perfilFinanciero: PerfilFinanciero
    probabilidad: Decimal
    resumenGastos: ResumenGastosResponse
    recomendaciones: list[str]
````

## File: mock-api/app/routers/__init__.py
````python

````

## File: mock-api/app/routers/analisis.py
````python
from fastapi import APIRouter
from app.models.response import AnalisisFinancieroResponse
from app.services.analisis_service import analizar

router = APIRouter(
    prefix="/predict",
    tags=["Analisis Financiero"]
)
@router.post("", response_model=AnalisisFinancieroResponse)
def analizar_financiero():
    return analizar()
````

## File: mock-api/app/services/__init__.py
````python

````

## File: mock-api/app/services/analisis_service.py
````python
from decimal import Decimal

from app.models.response import (
    AnalisisFinancieroResponse,
    PerfilFinanciero,
    ResumenGastosResponse,
)

def analizar():
    resumen = ResumenGastosResponse(
        alimentacion=Decimal("200.00"),
        transporte=Decimal("100.00"),
        entretenimiento=Decimal("50.00"),
        salud=Decimal("75.00"),
        educacion=Decimal("150.00"),
        servicios=Decimal("80.00"),
        otros=Decimal("30.00"),
    )

    return AnalisisFinancieroResponse(
        perfilFinanciero=PerfilFinanciero.EN_OBSERVACION,
        probabilidad=Decimal("0.75"),
        resumenGastos=resumen,
        recomendaciones=[
            "Considera aumentar tu ahorro mensual.",
            "Revisa tus gastos en entretenimiento para optimizar tu presupuesto.",
        ],
    )
````

## File: mock-api/app/__init__.py
````python

````

## File: mock-api/app/main.py
````python
from fastapi import FastAPI
from app.routers.analisis import router

app = FastAPI(
    tittle="Hackathton IA API",
    description="API de analisis financiero",
    version="1.0.0"
)

app.include_router(router)
````

## File: backend/src/main/java/com/nocountry/financeai/client/IAClient.java
````java
package com.nocountry.financeai.client;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class IAClient {
    private final RestClient restClient;

    public AnalisisResponse analizar(AnalisisRequest request) {

        return restClient.post()
                .uri("/predict")
                .body(request)
                .retrieve()
                .body(AnalisisResponse.class);
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/config/.gitkeep
````

````

## File: backend/src/main/java/com/nocountry/financeai/config/CorsConfig.java
````java
package com.nocountry.financeai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/config/RestClientConfig.java
````java
package com.nocountry.financeai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Value("${ia.api.url}")
    private String iaApiUrl;
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(iaApiUrl)
                .build();
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/controller/.gitkeep
````

````

## File: backend/src/main/java/com/nocountry/financeai/controller/AuthController.java
````java
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;
import com.nocountry.financeai.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull; // <-- Nuevo import
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    // Agregamos @NotNull para eliminar la advertencia del IDE
    public ResponseEntity<AuthResponse> register(@Valid @NotNull @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/controller/HistorialAnalisisController.java
````java
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.service.HistorialAnalisisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analisis")
@RequiredArgsConstructor
public class HistorialAnalisisController {
    private final HistorialAnalisisService historialAnalisisService;


    @GetMapping("/usuario/{userId}")
    public List<HistorialAnalisisResponse> obtenerHistorialPorId(@Valid @PathVariable Long userId) {
        return historialAnalisisService.obtenerHistorialPorId(userId);
    }

    @GetMapping
    public List<HistorialAnalisisResponse> obtenerHistorial() {
        return historialAnalisisService.obtenerHistorial();
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/controller/TransactionController.java
````java
package com.nocountry.financeai.controller;

import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.entity.TransactionEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionRepository transactionRepository;

    public TransactionController(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @GetMapping
    public List<TransactionEntity> listarTransacciones(){
        return transactionRepository.findAll();
    }

    @PostMapping
    public TransactionEntity crearTransaccion(@RequestBody TransactionEntity transaction){
        return transactionRepository.save(transaction);
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/dto/request/RegisterRequest.java
````java
package com.nocountry.financeai.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record RegisterRequest(
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El formato del email no es válido")
        String email,

        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
        String password
) {}
````

## File: backend/src/main/java/com/nocountry/financeai/dto/response/AuthResponse.java
````java
package com.nocountry.financeai.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String message,
        String email
) {}
````

## File: backend/src/main/java/com/nocountry/financeai/dto/response/HistorialAnalisisResponse.java
````java
package com.nocountry.financeai.dto.response;

import com.nocountry.financeai.entity.enums.PerfilFinanciero;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record HistorialAnalisisResponse (
  Long id,
  Long usuarioId,
  PerfilFinanciero perfilFinanciero,
  BigDecimal probabilidad,
  Map<String, BigDecimal> resumenGastos,
  List<String> recomendaciones
){}
````

## File: backend/src/main/java/com/nocountry/financeai/dto/.gitkeep
````

````

## File: backend/src/main/java/com/nocountry/financeai/dto/ErrorResponseDto.java
````java
package com.nocountry.financeai.dto;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String error,
        String message,
        int status,
        LocalDateTime timestamp
) {
    public ErrorResponseDto(String error, String message, int status) {
        this(error, message, status, LocalDateTime.now());
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/entity/TransactionEntity.java
````java
package com.nocountry.financeai.entity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transacciones")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal monto;

    @Column(nullable = false, length = 10)
    private String tipo;

    @Column(nullable = false, length = 50)
    private String categoria;

    @Column(length = 255)
    private String descripcion;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;
}
````

## File: backend/src/main/java/com/nocountry/financeai/entity/UserEntity.java
````java
package com.nocountry.financeai.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder.Default
    @Column(length = 20)
    private String rol = "USER";

    @Builder.Default
    private Boolean activo = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/exception/.gitkeep
````

````

## File: backend/src/main/java/com/nocountry/financeai/exception/ApiExceptionHandler.java
````java
package com.nocountry.financeai.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> gestionarErrorGeneral(Exception e) {
        log.error("Error interno del servidor", e);
        ErrorResponse error = new ErrorResponse(
                500,
                "Error interno del servidor",
                List.of(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse gestionarErroresValidacion(MethodArgumentNotValidException ex) {
        log.warn("Se recibieron datos inválidos en la petición");

        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getDefaultMessage())
                .toList();
        return new ErrorResponse(
                400,
                "Error de validacion",
                errores,
                LocalDateTime.now()
        );
    }

    @ExceptionHandler(ResourceAccessException.class)
    public ErrorResponse gestionarErroresValidacion(Exception ex) {
        log.error( "No fue posible conectar con la API de analisis");

        ErrorResponse error = new ErrorResponse(
                503,
                "El servicio de Analisis no esta disponible",
                List.of(),
                LocalDateTime.now()
        );
        return error;
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/exception/ErrorResponse.java
````java
package com.nocountry.financeai.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private int codigo;
    private String mensaje;
    private List<String> errores;
    private LocalDateTime fechaError;
}
````

## File: backend/src/main/java/com/nocountry/financeai/exception/GlobalExceptionHandler.java
````java
package com.nocountry.financeai.exception;

import com.nocountry.financeai.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        ErrorResponseDto response = new ErrorResponseDto(
                "BAD_REQUEST",
                errors.toString(),
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception ex) {
        ErrorResponseDto response = new ErrorResponseDto(
                "INTERNAL_SERVER_ERROR",
                ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/repository/.gitkeep
````

````

## File: backend/src/main/java/com/nocountry/financeai/repository/HistorialAnalisisRepository.java
````java
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialAnalisisRepository extends JpaRepository<HistorialAnalisisEntity, Long> {
    List<HistorialAnalisisEntity> findByUsuarioId(Long id);
}
````

## File: backend/src/main/java/com/nocountry/financeai/repository/TransactionRepository.java
````java
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
````

## File: backend/src/main/java/com/nocountry/financeai/repository/UserRepository.java
````java
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Eliminamos findByUsername y dejamos EXCLUSIVAMENTE findByEmail
    Optional<UserEntity> findByEmail(String email);
}
````

## File: backend/src/main/java/com/nocountry/financeai/security/CustomUserDetailsService.java
````java
package com.nocountry.financeai.security;

import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con el email: " + email));

        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/security/JwtAuthFilter.java
````java
package com.nocountry.financeai.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    // Se usa @Lazy en UserDetailsService para romper la referencia circular en tiempo de inicio
    public JwtAuthFilter(JwtUtil jwtUtil, @Lazy UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(jwt);

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            if (jwtUtil.isTokenValid(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/security/JwtUtil.java
````java
package com.nocountry.financeai.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Clave secreta en HEX/Base64 (mínimo 256 bits para HMAC-SHA)
    @Value("${jwt.secret:404E635266556A586E3272357538782F413F4428472B4B6250655368566D5971}")
    private String secretKey;

    // Tiempo de expiración en milisegundos (24 horas por defecto)
    @Value("${jwt.expiration:86400000}")
    private long jwtExpiration;

    /**
     * Extrae el username (email) del token JWT.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae un Claim específico utilizando una función de resolución.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Genera un token básico a partir de las credenciales del usuario.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    /**
     * Genera un token incluyendo datos adicionales (claims personalizados).
     */
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }

    /**
     * Valida si el token pertenece al usuario y no ha expirado.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Comprueba si la fecha del token es previa a la fecha actual.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extrae la fecha de expiración almacenada en el token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Decodifica y valida la firma del token para obtener todos los Claims.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Obtiene la clave de firma decodificada en Base64/HEX.
     */
    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/service/impl/AuthServiceImpl.java
````java
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
````

## File: backend/src/main/java/com/nocountry/financeai/service/impl/HistorialAnalisisServiceImpl.java
````java
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.service.HistorialAnalisisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialAnalisisServiceImpl implements HistorialAnalisisService {
    private final HistorialAnalisisRepository historialAnalisisRepository;

    @Override
    public List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id) {
        return historialAnalisisRepository.findByUsuarioId(id)
                .stream()
                .map(historil -> new HistorialAnalisisResponse(
                        historil.getId(),
                        historil.getUsuarioId(),
                        historil.getPerfilFinanciero(),
                        historil.getProbabilidad(),
                        historil.getResumenGastos(),
                        historil.getRecomendaciones()
                ))
                .toList();
    }

    @Override
    public List<HistorialAnalisisResponse> obtenerHistorial() {

        return historialAnalisisRepository.findAll()
                .stream()
                .map(historial -> new HistorialAnalisisResponse(
                        historial.getId(),
                        historial.getUsuarioId(),
                        historial.getPerfilFinanciero(),
                        historial.getProbabilidad(),
                        historial.getResumenGastos(),
                        historial.getRecomendaciones()
                ))
                .toList();

    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/service/.gitkeep
````

````

## File: backend/src/main/java/com/nocountry/financeai/service/AuthService.java
````java
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
}
````

## File: backend/src/main/java/com/nocountry/financeai/service/HistorialAnalisisService.java
````java
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;

import java.util.List;

public interface HistorialAnalisisService {
    List<HistorialAnalisisResponse> obtenerHistorial();
    List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id);
}
````

## File: backend/src/main/resources/db/migration/V1__create_users_table.sql
````sql
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(20) DEFAULT 'USER',
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
````

## File: backend/src/main/resources/db/migration/V2__create_transactions_table.sql
````sql
CREATE TABLE transacciones (
id BIGSERIAL PRIMARY KEY,
usuario_id BIGINT NOT NULL,
monto NUMERIC(12, 2) NOT NULL,
tipo VARCHAR(10) NOT NULL,
categoria VARCHAR(50) NOT NULL,
descripcion VARCHAR(255),
fecha TIMESTAMP NOT NULL,
CONSTRAINT fk_transacciones_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE
);
````

## File: backend/src/main/resources/db/migration/V3__create_analysis_table.sql
````sql
CREATE TABLE historial_analisis (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT,
    perfil_financiero VARCHAR(50),
    probabilidad DECIMAL(4,2),
    resumen_gastos JSONB,
    recomendaciones JSONB,
    fecha_analisis TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
````

## File: backend/src/main/resources/application.yml
````yaml
spring:
  application:
    name: financeai
````

## File: backend/src/test/java/com/nocountry/financeai/FinanceaiApplicationTests.java
````java
package com.nocountry.financeai;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FinanceaiApplicationTests {

    @Test
    void contextLoads() {
        // Intencionadamente vacío: Este test sirve exclusivamente para verificar
        // que el contexto de Spring Boot se inicialice correctamente.
    }

}
````

## File: backend/HELP.md
````markdown
# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/4.1.0/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.1.0/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.1.0/reference/web/servlet.html)
* [Validation](https://docs.spring.io/spring-boot/4.1.0/reference/io/validation.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/4.1.0/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [Flyway Migration](https://docs.spring.io/spring-boot/4.1.0/how-to/data-initialization.html#howto.data-initialization.migration-tool.flyway)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.
````

## File: backend/mvnw
````
#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup batch script, version 3.3.4
#
# Optional ENV vars
# -----------------
#   JAVA_HOME - location of a JDK home dir, required when download maven via java source
#   MVNW_REPOURL - repo url base for downloading maven distribution
#   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
#   MVNW_VERBOSE - true: enable verbose log; debug: trace the mvnw script; others: silence the output
# ----------------------------------------------------------------------------

set -euf
[ "${MVNW_VERBOSE-}" != debug ] || set -x

# OS specific support.
native_path() { printf %s\\n "$1"; }
case "$(uname)" in
CYGWIN* | MINGW*)
  [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
  native_path() { cygpath --path --windows "$1"; }
  ;;
esac

# set JAVACMD and JAVACCMD
set_java_home() {
  # For Cygwin and MinGW, ensure paths are in Unix format before anything is touched
  if [ -n "${JAVA_HOME-}" ]; then
    if [ -x "$JAVA_HOME/jre/sh/java" ]; then
      # IBM's JDK on AIX uses strange locations for the executables
      JAVACMD="$JAVA_HOME/jre/sh/java"
      JAVACCMD="$JAVA_HOME/jre/sh/javac"
    else
      JAVACMD="$JAVA_HOME/bin/java"
      JAVACCMD="$JAVA_HOME/bin/javac"

      if [ ! -x "$JAVACMD" ] || [ ! -x "$JAVACCMD" ]; then
        echo "The JAVA_HOME environment variable is not defined correctly, so mvnw cannot run." >&2
        echo "JAVA_HOME is set to \"$JAVA_HOME\", but \"\$JAVA_HOME/bin/java\" or \"\$JAVA_HOME/bin/javac\" does not exist." >&2
        return 1
      fi
    fi
  else
    JAVACMD="$(
      'set' +e
      'unset' -f command 2>/dev/null
      'command' -v java
    )" || :
    JAVACCMD="$(
      'set' +e
      'unset' -f command 2>/dev/null
      'command' -v javac
    )" || :

    if [ ! -x "${JAVACMD-}" ] || [ ! -x "${JAVACCMD-}" ]; then
      echo "The java/javac command does not exist in PATH nor is JAVA_HOME set, so mvnw cannot run." >&2
      return 1
    fi
  fi
}

# hash string like Java String::hashCode
hash_string() {
  str="${1:-}" h=0
  while [ -n "$str" ]; do
    char="${str%"${str#?}"}"
    h=$(((h * 31 + $(LC_CTYPE=C printf %d "'$char")) % 4294967296))
    str="${str#?}"
  done
  printf %x\\n $h
}

verbose() { :; }
[ "${MVNW_VERBOSE-}" != true ] || verbose() { printf %s\\n "${1-}"; }

die() {
  printf %s\\n "$1" >&2
  exit 1
}

trim() {
  # MWRAPPER-139:
  #   Trims trailing and leading whitespace, carriage returns, tabs, and linefeeds.
  #   Needed for removing poorly interpreted newline sequences when running in more
  #   exotic environments such as mingw bash on Windows.
  printf "%s" "${1}" | tr -d '[:space:]'
}

scriptDir="$(dirname "$0")"
scriptName="$(basename "$0")"

# parse distributionUrl and optional distributionSha256Sum, requires .mvn/wrapper/maven-wrapper.properties
while IFS="=" read -r key value; do
  case "${key-}" in
  distributionUrl) distributionUrl=$(trim "${value-}") ;;
  distributionSha256Sum) distributionSha256Sum=$(trim "${value-}") ;;
  esac
done <"$scriptDir/.mvn/wrapper/maven-wrapper.properties"
[ -n "${distributionUrl-}" ] || die "cannot read distributionUrl property in $scriptDir/.mvn/wrapper/maven-wrapper.properties"

case "${distributionUrl##*/}" in
maven-mvnd-*bin.*)
  MVN_CMD=mvnd.sh _MVNW_REPO_PATTERN=/maven/mvnd/
  case "${PROCESSOR_ARCHITECTURE-}${PROCESSOR_ARCHITEW6432-}:$(uname -a)" in
  *AMD64:CYGWIN* | *AMD64:MINGW*) distributionPlatform=windows-amd64 ;;
  :Darwin*x86_64) distributionPlatform=darwin-amd64 ;;
  :Darwin*arm64) distributionPlatform=darwin-aarch64 ;;
  :Linux*x86_64*) distributionPlatform=linux-amd64 ;;
  *)
    echo "Cannot detect native platform for mvnd on $(uname)-$(uname -m), use pure java version" >&2
    distributionPlatform=linux-amd64
    ;;
  esac
  distributionUrl="${distributionUrl%-bin.*}-$distributionPlatform.zip"
  ;;
maven-mvnd-*) MVN_CMD=mvnd.sh _MVNW_REPO_PATTERN=/maven/mvnd/ ;;
*) MVN_CMD="mvn${scriptName#mvnw}" _MVNW_REPO_PATTERN=/org/apache/maven/ ;;
esac

# apply MVNW_REPOURL and calculate MAVEN_HOME
# maven home pattern: ~/.m2/wrapper/dists/{apache-maven-<version>,maven-mvnd-<version>-<platform>}/<hash>
[ -z "${MVNW_REPOURL-}" ] || distributionUrl="$MVNW_REPOURL$_MVNW_REPO_PATTERN${distributionUrl#*"$_MVNW_REPO_PATTERN"}"
distributionUrlName="${distributionUrl##*/}"
distributionUrlNameMain="${distributionUrlName%.*}"
distributionUrlNameMain="${distributionUrlNameMain%-bin}"
MAVEN_USER_HOME="${MAVEN_USER_HOME:-${HOME}/.m2}"
MAVEN_HOME="${MAVEN_USER_HOME}/wrapper/dists/${distributionUrlNameMain-}/$(hash_string "$distributionUrl")"

exec_maven() {
  unset MVNW_VERBOSE MVNW_USERNAME MVNW_PASSWORD MVNW_REPOURL || :
  exec "$MAVEN_HOME/bin/$MVN_CMD" "$@" || die "cannot exec $MAVEN_HOME/bin/$MVN_CMD"
}

if [ -d "$MAVEN_HOME" ]; then
  verbose "found existing MAVEN_HOME at $MAVEN_HOME"
  exec_maven "$@"
fi

case "${distributionUrl-}" in
*?-bin.zip | *?maven-mvnd-?*-?*.zip) ;;
*) die "distributionUrl is not valid, must match *-bin.zip or maven-mvnd-*.zip, but found '${distributionUrl-}'" ;;
esac

# prepare tmp dir
if TMP_DOWNLOAD_DIR="$(mktemp -d)" && [ -d "$TMP_DOWNLOAD_DIR" ]; then
  clean() { rm -rf -- "$TMP_DOWNLOAD_DIR"; }
  trap clean HUP INT TERM EXIT
else
  die "cannot create temp dir"
fi

mkdir -p -- "${MAVEN_HOME%/*}"

# Download and Install Apache Maven
verbose "Couldn't find MAVEN_HOME, downloading and installing it ..."
verbose "Downloading from: $distributionUrl"
verbose "Downloading to: $TMP_DOWNLOAD_DIR/$distributionUrlName"

# select .zip or .tar.gz
if ! command -v unzip >/dev/null; then
  distributionUrl="${distributionUrl%.zip}.tar.gz"
  distributionUrlName="${distributionUrl##*/}"
fi

# verbose opt
__MVNW_QUIET_WGET=--quiet __MVNW_QUIET_CURL=--silent __MVNW_QUIET_UNZIP=-q __MVNW_QUIET_TAR=''
[ "${MVNW_VERBOSE-}" != true ] || __MVNW_QUIET_WGET='' __MVNW_QUIET_CURL='' __MVNW_QUIET_UNZIP='' __MVNW_QUIET_TAR=v

# normalize http auth
case "${MVNW_PASSWORD:+has-password}" in
'') MVNW_USERNAME='' MVNW_PASSWORD='' ;;
has-password) [ -n "${MVNW_USERNAME-}" ] || MVNW_USERNAME='' MVNW_PASSWORD='' ;;
esac

if [ -z "${MVNW_USERNAME-}" ] && command -v wget >/dev/null; then
  verbose "Found wget ... using wget"
  wget ${__MVNW_QUIET_WGET:+"$__MVNW_QUIET_WGET"} "$distributionUrl" -O "$TMP_DOWNLOAD_DIR/$distributionUrlName" || die "wget: Failed to fetch $distributionUrl"
elif [ -z "${MVNW_USERNAME-}" ] && command -v curl >/dev/null; then
  verbose "Found curl ... using curl"
  curl ${__MVNW_QUIET_CURL:+"$__MVNW_QUIET_CURL"} -f -L -o "$TMP_DOWNLOAD_DIR/$distributionUrlName" "$distributionUrl" || die "curl: Failed to fetch $distributionUrl"
elif set_java_home; then
  verbose "Falling back to use Java to download"
  javaSource="$TMP_DOWNLOAD_DIR/Downloader.java"
  targetZip="$TMP_DOWNLOAD_DIR/$distributionUrlName"
  cat >"$javaSource" <<-END
	public class Downloader extends java.net.Authenticator
	{
	  protected java.net.PasswordAuthentication getPasswordAuthentication()
	  {
	    return new java.net.PasswordAuthentication( System.getenv( "MVNW_USERNAME" ), System.getenv( "MVNW_PASSWORD" ).toCharArray() );
	  }
	  public static void main( String[] args ) throws Exception
	  {
	    setDefault( new Downloader() );
	    java.nio.file.Files.copy( java.net.URI.create( args[0] ).toURL().openStream(), java.nio.file.Paths.get( args[1] ).toAbsolutePath().normalize() );
	  }
	}
	END
  # For Cygwin/MinGW, switch paths to Windows format before running javac and java
  verbose " - Compiling Downloader.java ..."
  "$(native_path "$JAVACCMD")" "$(native_path "$javaSource")" || die "Failed to compile Downloader.java"
  verbose " - Running Downloader.java ..."
  "$(native_path "$JAVACMD")" -cp "$(native_path "$TMP_DOWNLOAD_DIR")" Downloader "$distributionUrl" "$(native_path "$targetZip")"
fi

# If specified, validate the SHA-256 sum of the Maven distribution zip file
if [ -n "${distributionSha256Sum-}" ]; then
  distributionSha256Result=false
  if [ "$MVN_CMD" = mvnd.sh ]; then
    echo "Checksum validation is not supported for maven-mvnd." >&2
    echo "Please disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties." >&2
    exit 1
  elif command -v sha256sum >/dev/null; then
    if echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | sha256sum -c - >/dev/null 2>&1; then
      distributionSha256Result=true
    fi
  elif command -v shasum >/dev/null; then
    if echo "$distributionSha256Sum  $TMP_DOWNLOAD_DIR/$distributionUrlName" | shasum -a 256 -c >/dev/null 2>&1; then
      distributionSha256Result=true
    fi
  else
    echo "Checksum validation was requested but neither 'sha256sum' or 'shasum' are available." >&2
    echo "Please install either command, or disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties." >&2
    exit 1
  fi
  if [ $distributionSha256Result = false ]; then
    echo "Error: Failed to validate Maven distribution SHA-256, your Maven distribution might be compromised." >&2
    echo "If you updated your Maven version, you need to update the specified distributionSha256Sum property." >&2
    exit 1
  fi
fi

# unzip and move
if command -v unzip >/dev/null; then
  unzip ${__MVNW_QUIET_UNZIP:+"$__MVNW_QUIET_UNZIP"} "$TMP_DOWNLOAD_DIR/$distributionUrlName" -d "$TMP_DOWNLOAD_DIR" || die "failed to unzip"
else
  tar xzf${__MVNW_QUIET_TAR:+"$__MVNW_QUIET_TAR"} "$TMP_DOWNLOAD_DIR/$distributionUrlName" -C "$TMP_DOWNLOAD_DIR" || die "failed to untar"
fi

# Find the actual extracted directory name (handles snapshots where filename != directory name)
actualDistributionDir=""

# First try the expected directory name (for regular distributions)
if [ -d "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain" ]; then
  if [ -f "$TMP_DOWNLOAD_DIR/$distributionUrlNameMain/bin/$MVN_CMD" ]; then
    actualDistributionDir="$distributionUrlNameMain"
  fi
fi

# If not found, search for any directory with the Maven executable (for snapshots)
if [ -z "$actualDistributionDir" ]; then
  # enable globbing to iterate over items
  set +f
  for dir in "$TMP_DOWNLOAD_DIR"/*; do
    if [ -d "$dir" ]; then
      if [ -f "$dir/bin/$MVN_CMD" ]; then
        actualDistributionDir="$(basename "$dir")"
        break
      fi
    fi
  done
  set -f
fi

if [ -z "$actualDistributionDir" ]; then
  verbose "Contents of $TMP_DOWNLOAD_DIR:"
  verbose "$(ls -la "$TMP_DOWNLOAD_DIR")"
  die "Could not find Maven distribution directory in extracted archive"
fi

verbose "Found extracted Maven distribution directory: $actualDistributionDir"
printf %s\\n "$distributionUrl" >"$TMP_DOWNLOAD_DIR/$actualDistributionDir/mvnw.url"
mv -- "$TMP_DOWNLOAD_DIR/$actualDistributionDir" "$MAVEN_HOME" || [ -d "$MAVEN_HOME" ] || die "fail to move MAVEN_HOME"

clean || :
exec_maven "$@"
````

## File: backend/mvnw.cmd
````batch
<# : batch portion
@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.3.4
@REM
@REM Optional ENV vars
@REM   MVNW_REPOURL - repo url base for downloading maven distribution
@REM   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
@REM   MVNW_VERBOSE - true: enable verbose log; others: silence the output
@REM ----------------------------------------------------------------------------

@IF "%__MVNW_ARG0_NAME__%"=="" (SET __MVNW_ARG0_NAME__=%~nx0)
@SET __MVNW_CMD__=
@SET __MVNW_ERROR__=
@SET __MVNW_PSMODULEP_SAVE=%PSModulePath%
@SET PSModulePath=
@FOR /F "usebackq tokens=1* delims==" %%A IN (`powershell -noprofile "& {$scriptDir='%~dp0'; $script='%__MVNW_ARG0_NAME__%'; icm -ScriptBlock ([Scriptblock]::Create((Get-Content -Raw '%~f0'))) -NoNewScope}"`) DO @(
  IF "%%A"=="MVN_CMD" (set __MVNW_CMD__=%%B) ELSE IF "%%B"=="" (echo %%A) ELSE (echo %%A=%%B)
)
@SET PSModulePath=%__MVNW_PSMODULEP_SAVE%
@SET __MVNW_PSMODULEP_SAVE=
@SET __MVNW_ARG0_NAME__=
@SET MVNW_USERNAME=
@SET MVNW_PASSWORD=
@IF NOT "%__MVNW_CMD__%"=="" ("%__MVNW_CMD__%" %*)
@echo Cannot start maven from wrapper >&2 && exit /b 1
@GOTO :EOF
: end batch / begin powershell #>

$ErrorActionPreference = "Stop"
if ($env:MVNW_VERBOSE -eq "true") {
  $VerbosePreference = "Continue"
}

# calculate distributionUrl, requires .mvn/wrapper/maven-wrapper.properties
$distributionUrl = (Get-Content -Raw "$scriptDir/.mvn/wrapper/maven-wrapper.properties" | ConvertFrom-StringData).distributionUrl
if (!$distributionUrl) {
  Write-Error "cannot read distributionUrl property in $scriptDir/.mvn/wrapper/maven-wrapper.properties"
}

switch -wildcard -casesensitive ( $($distributionUrl -replace '^.*/','') ) {
  "maven-mvnd-*" {
    $USE_MVND = $true
    $distributionUrl = $distributionUrl -replace '-bin\.[^.]*$',"-windows-amd64.zip"
    $MVN_CMD = "mvnd.cmd"
    break
  }
  default {
    $USE_MVND = $false
    $MVN_CMD = $script -replace '^mvnw','mvn'
    break
  }
}

# apply MVNW_REPOURL and calculate MAVEN_HOME
# maven home pattern: ~/.m2/wrapper/dists/{apache-maven-<version>,maven-mvnd-<version>-<platform>}/<hash>
if ($env:MVNW_REPOURL) {
  $MVNW_REPO_PATTERN = if ($USE_MVND -eq $False) { "/org/apache/maven/" } else { "/maven/mvnd/" }
  $distributionUrl = "$env:MVNW_REPOURL$MVNW_REPO_PATTERN$($distributionUrl -replace "^.*$MVNW_REPO_PATTERN",'')"
}
$distributionUrlName = $distributionUrl -replace '^.*/',''
$distributionUrlNameMain = $distributionUrlName -replace '\.[^.]*$','' -replace '-bin$',''

$MAVEN_M2_PATH = "$HOME/.m2"
if ($env:MAVEN_USER_HOME) {
  $MAVEN_M2_PATH = "$env:MAVEN_USER_HOME"
}

if (-not (Test-Path -Path $MAVEN_M2_PATH)) {
    New-Item -Path $MAVEN_M2_PATH -ItemType Directory | Out-Null
}

$MAVEN_WRAPPER_DISTS = $null
if ((Get-Item $MAVEN_M2_PATH).Target[0] -eq $null) {
  $MAVEN_WRAPPER_DISTS = "$MAVEN_M2_PATH/wrapper/dists"
} else {
  $MAVEN_WRAPPER_DISTS = (Get-Item $MAVEN_M2_PATH).Target[0] + "/wrapper/dists"
}

$MAVEN_HOME_PARENT = "$MAVEN_WRAPPER_DISTS/$distributionUrlNameMain"
$MAVEN_HOME_NAME = ([System.Security.Cryptography.SHA256]::Create().ComputeHash([byte[]][char[]]$distributionUrl) | ForEach-Object {$_.ToString("x2")}) -join ''
$MAVEN_HOME = "$MAVEN_HOME_PARENT/$MAVEN_HOME_NAME"

if (Test-Path -Path "$MAVEN_HOME" -PathType Container) {
  Write-Verbose "found existing MAVEN_HOME at $MAVEN_HOME"
  Write-Output "MVN_CMD=$MAVEN_HOME/bin/$MVN_CMD"
  exit $?
}

if (! $distributionUrlNameMain -or ($distributionUrlName -eq $distributionUrlNameMain)) {
  Write-Error "distributionUrl is not valid, must end with *-bin.zip, but found $distributionUrl"
}

# prepare tmp dir
$TMP_DOWNLOAD_DIR_HOLDER = New-TemporaryFile
$TMP_DOWNLOAD_DIR = New-Item -Itemtype Directory -Path "$TMP_DOWNLOAD_DIR_HOLDER.dir"
$TMP_DOWNLOAD_DIR_HOLDER.Delete() | Out-Null
trap {
  if ($TMP_DOWNLOAD_DIR.Exists) {
    try { Remove-Item $TMP_DOWNLOAD_DIR -Recurse -Force | Out-Null }
    catch { Write-Warning "Cannot remove $TMP_DOWNLOAD_DIR" }
  }
}

New-Item -Itemtype Directory -Path "$MAVEN_HOME_PARENT" -Force | Out-Null

# Download and Install Apache Maven
Write-Verbose "Couldn't find MAVEN_HOME, downloading and installing it ..."
Write-Verbose "Downloading from: $distributionUrl"
Write-Verbose "Downloading to: $TMP_DOWNLOAD_DIR/$distributionUrlName"

$webclient = New-Object System.Net.WebClient
if ($env:MVNW_USERNAME -and $env:MVNW_PASSWORD) {
  $webclient.Credentials = New-Object System.Net.NetworkCredential($env:MVNW_USERNAME, $env:MVNW_PASSWORD)
}
[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12
$webclient.DownloadFile($distributionUrl, "$TMP_DOWNLOAD_DIR/$distributionUrlName") | Out-Null

# If specified, validate the SHA-256 sum of the Maven distribution zip file
$distributionSha256Sum = (Get-Content -Raw "$scriptDir/.mvn/wrapper/maven-wrapper.properties" | ConvertFrom-StringData).distributionSha256Sum
if ($distributionSha256Sum) {
  if ($USE_MVND) {
    Write-Error "Checksum validation is not supported for maven-mvnd. `nPlease disable validation by removing 'distributionSha256Sum' from your maven-wrapper.properties."
  }
  Import-Module $PSHOME\Modules\Microsoft.PowerShell.Utility -Function Get-FileHash
  if ((Get-FileHash "$TMP_DOWNLOAD_DIR/$distributionUrlName" -Algorithm SHA256).Hash.ToLower() -ne $distributionSha256Sum) {
    Write-Error "Error: Failed to validate Maven distribution SHA-256, your Maven distribution might be compromised. If you updated your Maven version, you need to update the specified distributionSha256Sum property."
  }
}

# unzip and move
Expand-Archive "$TMP_DOWNLOAD_DIR/$distributionUrlName" -DestinationPath "$TMP_DOWNLOAD_DIR" | Out-Null

# Find the actual extracted directory name (handles snapshots where filename != directory name)
$actualDistributionDir = ""

# First try the expected directory name (for regular distributions)
$expectedPath = Join-Path "$TMP_DOWNLOAD_DIR" "$distributionUrlNameMain"
$expectedMvnPath = Join-Path "$expectedPath" "bin/$MVN_CMD"
if ((Test-Path -Path $expectedPath -PathType Container) -and (Test-Path -Path $expectedMvnPath -PathType Leaf)) {
  $actualDistributionDir = $distributionUrlNameMain
}

# If not found, search for any directory with the Maven executable (for snapshots)
if (!$actualDistributionDir) {
  Get-ChildItem -Path "$TMP_DOWNLOAD_DIR" -Directory | ForEach-Object {
    $testPath = Join-Path $_.FullName "bin/$MVN_CMD"
    if (Test-Path -Path $testPath -PathType Leaf) {
      $actualDistributionDir = $_.Name
    }
  }
}

if (!$actualDistributionDir) {
  Write-Error "Could not find Maven distribution directory in extracted archive"
}

Write-Verbose "Found extracted Maven distribution directory: $actualDistributionDir"
Rename-Item -Path "$TMP_DOWNLOAD_DIR/$actualDistributionDir" -NewName $MAVEN_HOME_NAME | Out-Null
try {
  Move-Item -Path "$TMP_DOWNLOAD_DIR/$MAVEN_HOME_NAME" -Destination $MAVEN_HOME_PARENT | Out-Null
} catch {
  if (! (Test-Path -Path "$MAVEN_HOME" -PathType Container)) {
    Write-Error "fail to move MAVEN_HOME"
  }
} finally {
  try { Remove-Item $TMP_DOWNLOAD_DIR -Recurse -Force | Out-Null }
  catch { Write-Warning "Cannot remove $TMP_DOWNLOAD_DIR" }
}

Write-Output "MVN_CMD=$MAVEN_HOME/bin/$MVN_CMD"
````

## File: data-science/README.md
````markdown
# Data Science
````

## File: data-science/requirements.txt
````
fastapi
uvicorn
pandas
scikit-learn
numpy
joblib
pydantic
````

## File: frontend/app.js
````javascript
const API_URL = 'http://localhost:8080/api/analisis-financiero';

// Agregar transacción
document.getElementById('btnAgregarTransaccion').addEventListener('click', () => {
    const contenedor = document.getElementById('listaTransacciones');

    const div = document.createElement('div');
    div.innerHTML = `
        <input class="desc" placeholder="Descripción">
        <input class="valor" type="number" placeholder="Monto">
    `;

    contenedor.appendChild(div);
});

// Submit
document.getElementById('financeForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const transacciones = [];

    document.querySelectorAll('#listaTransacciones div').forEach(item => {
        const desc = item.querySelector('.desc').value;
        const valor = parseFloat(item.querySelector('.valor').value);

        if (desc && valor) {
            transacciones.push({ descripcion: desc, valor });
        }
    });

    const payload = {
        ingreso_mensual: parseFloat(document.getElementById('ingreso').value),
        nivel_endeudamiento: parseFloat(document.getElementById('endeudamiento').value),
        frecuencia_ahorro: document.getElementById('ahorro').value,
        transacciones
    };

    try {
        const res = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        const data = await res.json();
        mostrarResultados(data);

    } catch (err) {
        alert('Error conectando al backend');
    }
});

// Render
function mostrarResultados(data) {
    document.getElementById('resultadoContenedor').classList.remove('d-none');

    const badge = document.getElementById('badgePerfil');
    badge.innerText = data.perfil_financiero;
    badge.className = `badge badge-${data.perfil_financiero.replace(/\s/g, '')}`;

    document.getElementById('txtProbabilidad').innerText =
        (data.probabilidad * 100).toFixed(1) + '%';

    const lista = document.getElementById('listaGastos');
    lista.innerHTML = '';

    Object.entries(data.resumen_gastos).forEach(([cat, monto]) => {
        lista.innerHTML += `<li>${cat}: $${monto}</li>`;
    });

    const rec = document.getElementById('contenedorRecomendaciones');
    rec.innerHTML = '';

    data.recomendaciones.forEach(r => {
        rec.innerHTML += `<p>💡 ${r}</p>`;
    });
}
````

## File: frontend/index.html
````html
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FinanceAI - Panel de Control</title>

    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- CSS propio -->
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <nav class="navbar navbar-dark bg-dark mb-4">
        <div class="container">
            <span class="navbar-brand mb-0 h1">🚀 FinanceAI MVP</span>
        </div>
    </nav>

    <div class="container mb-5">
        <div class="row">

            <!-- FORM -->
            <div class="col-lg-6 mb-4">
                <div class="card p-4">
                    <h3 class="mb-3 text-primary">Datos Financieros</h3>

                    <form id="financeForm">
                        <div class="mb-3">
                            <label>Ingreso Mensual</label>
                            <input type="number" id="ingreso" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label>Endeudamiento (%)</label>
                            <input type="number" id="endeudamiento" class="form-control" required>
                        </div>

                        <div class="mb-3">
                            <label>Ahorro</label>
                            <select id="ahorro" class="form-select">
                                <option>Baja</option>
                                <option>Media</option>
                                <option>Alta</option>
                            </select>
                        </div>

                        <h5>Transacciones</h5>
                        <div id="listaTransacciones"></div>

                        <button type="button" id="btnAgregarTransaccion" class="btn btn-secondary mt-2">
                            + Añadir
                        </button>

                        <button type="submit" class="btn btn-primary w-100 mt-3">
                            Analizar
                        </button>
                    </form>
                </div>
            </div>

            <!-- RESULTADOS -->
            <div class="col-lg-6">
                <div class="card p-4">
                    <h3>Resultado</h3>

                    <div id="loading" class="d-none">Procesando...</div>

                    <div id="resultadoContenedor" class="d-none">
                        <span id="badgePerfil" class="badge"></span>
                        <p>Confianza: <span id="txtProbabilidad"></span></p>

                        <ul id="listaGastos"></ul>
                        <div id="contenedorRecomendaciones"></div>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <script src="app.js"></script>
</body>
</html>
````

## File: frontend/style.css
````css
body {
    background-color: #f8f9fa;
}

.card {
    border: none;
    box-shadow: 0 4px 6px rgba(0,0,0,0.1);
}

.badge-Saludable {
    background-color: #198754;
}

.badge-Observacion {
    background-color: #ffc107;
    color: black;
}

.badge-Riesgo {
    background-color: #dc3545;
}
````

## File: .gitattibutes
````
* text=auto eol=lf
````

## File: backend/src/main/java/com/nocountry/financeai/dto/request/TransactionRequest.java
````java
package com.nocountry.financeai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(
        @Schema(
                description = "Descripción de la transacción",
                example = "Recreación"
        )
        @NotBlank(message = "La descripcion es obligatoria")
        String descripcion,

        @Schema(
                description = "Valor de la transacción",
                example = "210"
        )
        @NotNull(message = "El valor es obligatorio")
        @Positive(message = "El valor debe ser mayor a cero")
        BigDecimal valor
) {}
````

## File: backend/src/main/java/com/nocountry/financeai/dto/response/AnalisisResponse.java
````java
package com.nocountry.financeai.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Schema(description = "Resultado del analisis financiero generado a partir de los datos del usuario")
public record AnalisisResponse(
        @Schema(
                description = "Clasificacion del perfil financiero del usuario segun analisis",
                example = "EN_OBSERVACION",
                allowableValues = {"Saludable", "En observacion", "En riesgo" }
        )
        String perfilFinanciero,

        @Schema(
                description = "Probabilidad o nivel de confianza del modelo asociad al perlfil asignado, rango de 0 a 1",
                example = "0.82"
        )
        BigDecimal probabilidad,

        @Schema(
                description ="Resumen de gastos agrupados por categoria. Las claves del mapa son las categorias detectadas por el modelo",
                example ="{\"alimentacion\": 650, \"transporte\": 360, \"entretenimiento\":70}"
        )
        Map<String, BigDecimal> resumenGastos,

        @Schema(
                description = "lista de recomendaciones financieras generadas por el modelo, para el usuario",
                example = "[\"Monitorear los gastos recurrentes de entretenimiento\", \"Aumentar la reserva financiera mensual\"]"
        )
        List<String> recomendaciones
) {
}
````

## File: backend/src/main/java/com/nocountry/financeai/service/impl/AnalisisIAServiceImpl.java
````java
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.client.IAClient;
import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.service.AnalisisIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class AnalisisIAServiceImpl implements AnalisisIAService {
    
    private final IAClient iaClient;
    private final HistorialAnalisisRepository historialAnalisisRepository;
    @Override
    public AnalisisResponse analizar(AnalisisRequest request) {
    AnalisisResponse response = iaClient.analizar(request);

        HistorialAnalisisEntity historialAnalisisEntity = HistorialAnalisisEntity.builder()
                .perfilFinanciero(PerfilFinanciero.valueOf(response.perfilFinanciero()))
                .probabilidad(response.probabilidad())
                .resumenGastos(response.resumenGastos())
                .recomendaciones(response.recomendaciones())
                .build();

        historialAnalisisRepository.save(historialAnalisisEntity);

        return response;
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/service/AnalisisIAService.java
````java
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;


public interface AnalisisIAService {
    AnalisisResponse analizar(AnalisisRequest analisisRequest);

}
````

## File: backend/src/main/java/com/nocountry/financeai/FinanceaiApplication.java
````java
package com.nocountry.financeai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceaiApplication.class, args);
	}

}
````

## File: backend/README.md
````markdown
## 💻 Módulo Backend & Infraestructura

El backend de **FinanceAI** está estructurado bajo una arquitectura limpia, desacoplada y orientada a capas utilizando **Java 21** y **Spring Boot 3.x/4.x**. El sistema ha sido diseñado bajo un enfoque "camaleónico", permitiendo un desarrollo local ágil pero completamente preparado para un despliegue seguro y transparente en **Oracle Cloud Infrastructure (OCI)**.

### 🛠️ Stack Tecnológico
* **Lenguaje:** Java 21 (LTS) - Implementación de *Records* inmutables para DTOs y compatibilidad nativa con *Virtual Threads*.
* **Framework:** Spring Boot (Spring Web, Spring Data JPA, Jakarta Validation).
* **Base de Datos:** PostgreSQL - Elegido por su estricta precisión matemática (`NUMERIC`) en transacciones financieras y madurez analítica.
* **Evolución de Datos:** Flyway - Control de versiones y migraciones automatizadas del esquema de base de datos.
* **Virtualización Local:** Docker Compose - Para la réplica exacta y aislada del entorno de base de datos en el equipo.
* **Calidad de Código:** Configurado bajo estándares estrictos de **SonarQube** (Clean Code y prevención de código muerto).

---

### 📂 Estructura de Arquitectura (Capas)
Dentro del directorio `/backend/src/main/java/com/nocountry/financeai/`, el código se organiza bajo el principio de responsabilidad única:

* **`controller/`**: Expone los endpoints REST públicos. Administra las validaciones automáticas de payloads (`@Valid`) y el manejo de políticas CORS para la integración fluida con el frontend.
* **`service/` & `service.impl/`**: Capa pura de lógica de negocio. Utiliza abstracción por interfaces para aislar los procesos internos, dejando el esqueleto preparado para orquestar las llamadas HTTP externas hacia la API de FastAPI del equipo de Data Science.
* **`dto/`**: Objetos de Transferencia de Datos desarrollados mediante *Java 21 Records*, reduciendo el código basura (*boilerplate*) y asegurando la inmutabilidad de los datos transferidos.
* **`model/`**: Aloja las entidades JPA de base de datos y Enums tipados (ej: `CategoriaGasto`, `MedioPago`) mapeados estrictamente en minúsculas mediante Jackson (`@JsonValue`), garantizando una sintonía del 100% con los requerimientos del dataset limpio de Data Science.
* **`repository/`**: Interfaces de persistencia segura que heredan de `JpaRepository`.

---

### 🐳 Réplica de Entorno Local (Docker Compose)
Para eliminar el problema de *"en mi máquina no funciona"*, la infraestructura local de base de datos está completamente automatizada.

**Instrucciones para el equipo de desarrollo:**
1. Asegúrate de tener Docker instalado en tu sistema operativo Linux.
2. Abre una terminal en la raíz del monorepo (donde se ubica el archivo `docker-compose.yml`).
3. Ejecuta el siguiente comando para levantar el entorno en segundo plano:
   ```bash
   docker compose up -d
````

## File: docker-compose.yml
````yaml
services:
  postgres-db:
    image: postgres:16-alpine
    container_name: financeai_postgres
    environment:
      POSTGRES_DB: financeai_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
````

## File: README.md
````markdown
# FinanceAI
# 🚀 FinanceAI – Asistente Inteligente de Salud Financiera

📋 ## Índice
- [Estado del proyecto](#-estado-del-proyecto)
- [Descripción del proyecto](#-descripción-del-proyecto)
- [Objetivos](#-objetivos)
- [Sector empresarial](#-sector-empresarial)
- [Tecnologías](#%EF%B8%8F-tecnologías)
- [Arquitectura](#-arquitectura)
- [Ejemplo de uso](#-ejemplo-de-uso)
- [Equipo](#-equipo)

---

## 🚧 Estado del proyecto
Actualmente el proyecto se encuentra en fase de planificación y diseño de arquitectura. La implementación se desarrollará durante el Hackathon ONE.

## 📖 Descripción del proyecto
FinanceAI es una solución inteligente orientada a mejorar la salud financiera de los usuarios mediante el análisis automático de sus transacciones y hábitos financieros.
A partir de la información proporcionada por el usuario, el sistema será capaz de analizar su comportamiento financiero y generar información útil que facilite una mejor toma de decisiones.

Entre la información procesada se encuentran:
* Ingreso mensual.
* Nivel de endeudamiento.
* Frecuencia de ahorro.
* Historial de transacciones.
* Descripción y monto de cada gasto.

## 🎯 Objetivos
El proyecto busca desarrollar un MVP capaz de:
* Clasificar automáticamente las transacciones financieras.
* Identificar patrones de consumo.
* Analizar el perfil financiero del usuario.
* Generar recomendaciones personalizadas.
* Exponer los resultados mediante una API REST.
* Integrar al menos un servicio de Oracle Cloud Infrastructure (OCI).

## 🏢 Sector Empresarial
**Fintech · Educación Financiera · Carteras Digitales**  
FinanceAI está dirigido a personas que desean comprender mejor sus hábitos financieros, organizar sus gastos y tomar decisiones más informadas sobre el manejo de su dinero.

## 🛠️ Tecnologías
Actualmente el proyecto contempla el uso de las siguientes tecnologías:

### Backend
* Java 21
* Spring Boot
* Spring Data JPA
* Maven
* Flyway
* Lombok
* Swagger / OpenAPI

### Ciencia de Datos
* Python
* Pandas
* Scikit-Learn
* Jupyter Notebook

### Frontend
* Vue.js

### Infraestructura
La infraestructura del proyecto se encuentra actualmente en definición. Durante el desarrollo del hackathon se seleccionarán los servicios de Oracle Cloud Infrastructure (OCI) que mejor se adapten a las necesidades del proyecto.

## 🏗️ Arquitectura
La solución estará organizada en cuatro módulos principales:
1. **Frontend**, encargado de la interacción con el usuario.
2. **Backend**, responsable de la lógica de negocio y la API REST.
3. **Ciencia de Datos**, donde se desarrollarán y entrenarán los modelos de clasificación y análisis financiero.
4. **Oracle Cloud Infrastructure (OCI)**, utilizado para el almacenamiento, procesamiento o despliegue de la solución.

La arquitectura podrá evolucionar conforme avance el desarrollo del proyecto.

## 💻 Ejemplo de uso

### Endpoint
`POST /api/analisis-financiero`

### Solicitud
```json
{
  "ingreso_mensual": 4500,
  "nivel_endeudamiento": 25,
  "frecuencia_ahorro": "Media",
  "transacciones": [
    {
      "descripcion": "Supermercado",
      "valor": 420
    },
    {
      "descripcion": "Combustible",
      "valor": 300
    },
    {
      "descripcion": "Streaming",
      "valor": 40
    }
  ]
}
Respuesta
JSON
{
  "perfil_financiero": "En observación",
  "probabilidad": 0.82,
  "resumen_gastos": {
    "alimentacion": 420,
    "transporte": 300,
    "entretenimiento": 40
  },
  "recomendaciones": [
    "Monitorear gastos recurrentes de entretenimiento.",
    "Aumentar la reserva financiera mensual."
  ]
}
👥 Equipo
Proyecto desarrollado por el equipo G9-LATAM-Team 47 FinanceAI durante el Hackathon Oracle Next Education (ONE).
````

## File: backend/src/main/java/com/nocountry/financeai/controller/AnalisisController.java
````java
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.service.AnalisisIAService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/analisis/predict")
@RequiredArgsConstructor
@Tag(
        name = "FinanceAI",
        description = "Generacion de diagnosticos financieros simulado por AI,a partir de los transacciones de un usuario"
)
public class AnalisisController {
    private final AnalisisIAService  analisisIAService;

    @PostMapping
    public AnalisisResponse  predict(@Valid @RequestBody AnalisisRequest analisisRequest) {
        return analisisIAService.analizar(analisisRequest);
    }
}
````

## File: backend/src/main/java/com/nocountry/financeai/dto/request/AnalisisRequest.java
````java
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
@Schema(description = "Datos solicitodos al cliente, necesarios para realizar el analisis")
public record AnalisisRequest (
        @Schema(
                description = "Ingreso mensual del usuario",
                example = "4500"
        )
        @JsonProperty("ingreso_mensual")
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @Schema(
                description = "Nivel de porcentaje de endeudamiento de un cliente (0 a 100",
                example = "35"
        )
        @JsonProperty("nivel_endeudamiento")
        @NotNull(message = "El nivel de endeudamiento es obligatorio")
        @Min(value = 0, message = "El endeudamiento no puede ser negativo")
        @Max(value = 100, message ="El endeudamiento no puede pasar el 100%")
        Integer nivelEndeudamiento,

        @Schema(
                description = "Frecuencia con que el usuario ahorra parte de su ingreso",
                example = "Alta",
                allowableValues = {"Baja", "Media", "Alta"}
        )
        @NotNull(message = "La frecuencia de ahorro es obligatoria")
        @JsonProperty("frecuencia_ahorro")
        String frecuenciaAhorro,

        @Schema(
                description = "Lista de transacciones que un usuario realiza, Debe incluir minimo una"
        )
        @NotEmpty(message = "Se debe enviar al menos una transaccion")
        List<TransactionRequest> transactions
) {}
````

## File: .gitignore
````
# --- IntelliJ e IDEs ---
.idea/
*.iml
*.iws
*.ipr
out/

# --- Java y Sistemas de Construcción (Maven/Gradle) ---
target/
build/
.gradle/
*.jar
*.war

# --- Sistema Operativo (Linux/Mac) ---
.DS_Store
Thumbs.db
*.log

# --- Infraestructura y Seguridad ---
.env
*.local
application.properties
application-dev.properties
repomix.config.json
````

## File: backend/src/main/java/com/nocountry/financeai/security/SecurityConfig.java
````java
package com.nocountry.financeai.security;

import com.nocountry.financeai.security.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        try {
            return http
                    .csrf(csrf -> csrf.disable())
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authorizeHttpRequests(auth -> auth
                            // 1. Endpoints Públicos de Autenticación
                            .requestMatchers("/api/v1/auth/**").permitAll()

                            // 2. Endpoints Públicos de Documentación Swagger / OpenAPI
                            .requestMatchers(
                                    "/v3/api-docs/**",
                                    "/v3/api-docs",
                                    "/swagger-ui/**",
                                    "/swagger-ui.html",
                                    "/swagger-resources/**",
                                    "/webjars/**"
                            ).permitAll()

                            // 3. Cualquier otra ruta requiere Token JWT
                            .anyRequest().authenticated()
                    )
                    // Interceptar peticiones con JwtAuthFilter antes del filtro por defecto de Spring
                    .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                    .build();
        } catch (Exception e) {
            throw new IllegalStateException("Error al configurar el SecurityFilterChain de Spring Security", e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
````

## File: backend/pom.xml
````xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>4.1.0</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <groupId>com.nocountry</groupId>
    <artifactId>financeai</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>financeai</name>
    <description>Proyecto FinanceAI</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters Core -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-flyway</artifactId>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-database-postgresql</artifactId>
        </dependency>
        <dependency>
            <groupId>org.flywaydb</groupId>
            <artifactId>flyway-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>0.12.6</version>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>

        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>0.12.6</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>2.5.0</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
````
