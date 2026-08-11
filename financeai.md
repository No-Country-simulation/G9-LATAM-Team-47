This file is a merged representation of the entire codebase, combined into a single document by Repomix.

<file_summary>
This section contains a summary of this file.

<purpose>
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.
</purpose>

<file_format>
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  - File path as an attribute
  - Full contents of the file
</file_format>

<usage_guidelines>
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.
</usage_guidelines>

<notes>
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)
</notes>

</file_summary>

<directory_structure>
backend/
  .mvn/
    wrapper/
      maven-wrapper.properties
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
                IniciarAdmin.java
                JwtConfig.java
                OpenApiConfig.java
                OrdenOpenApi.java
                RestClientConfig.java
              controller/
                .gitkeep
                AdminController.java
                AnalisisController.java
                AuthController.java
                HistorialAnalisisController.java
                PerfilFinancieroController.java
                TestSecurityController.java
                TransactionController.java
                UserController.java
              dto/
                request/
                  AnalisisRequest.java
                  ChangePasswdRequest.java
                  LoginRequest.java
                  PerfilFinancieroRequest.java
                  RegisterRequest.java
                  TransactionRequest.java
                  UserRequest.java
                response/
                  AnalisisResponse.java
                  AuthResponse.java
                  ErrorResponse.java
                  HistorialAnalisisResponse.java
                  PerfilFinancieroResponse.java
                  TransaccionResponse.java
                  UserResponse.java
                .gitkeep
              entity/
                enums/
                  EstadoCivil.java
                  MedioPago.java
                  PerfilFinanciero.java
                  RangoAhorro.java
                  Rol.java
                  Sexo.java
                .gitkeep
                HistorialAnalisisEntity.java
                PerfilFinancieroEntity.java
                TransactionEntity.java
                UserEntity.java
              exception/
                .gitkeep
                ApiExceptionHandler.java
                ResourceNotFoundException.java
                UserAlreadyExistsException.java
              repository/
                .gitkeep
                HistorialAnalisisRepository.java
                PerfilFinancieroRepository.java
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
                  PerfilFinancieroServiceImpl.java
                  TransaccionServiceImpl.java
                  UserServiceImpl.java
                .gitkeep
                AnalisisIAService.java
                AuthService.java
                HistorialAnalisisService.java
                PerfilFinancieroService.java
                TransaccionService.java
                UserService.java
              FinanceaiApplication.java
      resources/
        db/
          migration/
            V1__create_users_table.sql
            V2__create_transactions_table.sql
            V3__create_analysis_table.sql
            V4__create_perfil_Financiero_table.sql
            V5__fix_historial_analisis_schema.sql
        application.yml
    test/
      java/
        com/
          nocountry/
            financeai/
              FinanceaiApplicationTests.java
  Dockerfile
  HELP.md
  mvnw
  mvnw.cmd
  pom.xml
  README.md
data-science/
  modeloFinanceAI/
    Dockerfile
    main.py
    modelo_clasificacion_transacciones.pkl
    modelo_perfil_financiero.pkl
    requirements.txt
  main.py
  modelo_clasificacion_transacciones.pkl
  modelo_perfil_financiero.pkl
  README.md
  requirements.txt
frontend/
  css/
    style.css
  js/
    api.js
    auth.js
    dashboard.js
  dashboard.html
  index.html
mock-api/
  app/
    models/
      __init__.py
      .response.py.kate-swp
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
  Dockerfile
  README.md
.gitattributes
.gitignore
docker-compose.yml
financeai.md
notamaestra_financeai.md
Protocolo de colaboracion.md
README.md
</directory_structure>

<files>
This section contains the contents of the repository's files.

<file path="backend/src/main/java/com/nocountry/financeai/entity/.gitkeep">

</file>

<file path="mock-api/app/models/__init__.py">

</file>

<file path="mock-api/app/routers/__init__.py">

</file>

<file path="mock-api/app/services/__init__.py">

</file>

<file path="mock-api/app/__init__.py">

</file>

<file path="mock-api/app/main.py">
from fastapi import FastAPI
from app.routers.analisis import router

app = FastAPI(
    tittle="Hackathton IA API",
    description="API de analisis financiero",
    version="1.0.0"
)

app.include_router(router)
</file>

<file path="backend/.mvn/wrapper/maven-wrapper.properties">
wrapperVersion=3.3.4
distributionType=only-script
distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.9/apache-maven-3.9.9-bin.zip
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/CorsConfig.java">
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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/IniciarAdmin.java">
package com.nocountry.financeai.config;

import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.entity.enums.Rol;
import com.nocountry.financeai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
@RequiredArgsConstructor
public class IniciarAdmin {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner crearAdministrador(){
        return args ->{
            String emailAdmin = "admin@financeai.com";
            if ( userRepository.existsByEmail(emailAdmin)){
                return;
            }
            UserEntity admin = UserEntity.builder()
                    .nombre("Administrador")
                    .apellido("FinanceAI")
                    .documento("ADMIN_001")
                    .email(emailAdmin)
                    .password(passwordEncoder.encode("FinanceAdmin2026*"))
                    .fechaNacimiento(LocalDate.of(1990, 1, 1))
                    .rol(Rol.ADMIN)
                    .activo(true)
                    .build();

            userRepository.save(admin);
        };
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/JwtConfig.java">
package com.nocountry.financeai.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {

    private String secret;
    private long expiration;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public long getExpiration() {
        return expiration;
    }

    public void setExpiration(long expiration) {
        this.expiration = expiration;
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/RestClientConfig.java">
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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/UserController.java">
package com.nocountry.financeai.controller;
import com.nocountry.financeai.dto.request.ChangePasswdRequest;
import com.nocountry.financeai.dto.request.UserRequest;
import com.nocountry.financeai.dto.response.UserResponse;
import com.nocountry.financeai.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
@Tag(
        name = "Usuarios",
        description = "Gestion del perfil de usuario"
)
public class UserController {
    private final UserService userService;

    @GetMapping("/miPerfil")
    public UserResponse obtenerMiPerfil(Authentication authentication) {

        return userService.obtenerMiPerfil(authentication.getName());
    }

    @PatchMapping("/miPerfil")
    public UserResponse actualizarMiPerfil(Authentication authentication, @Valid @RequestBody UserRequest userRequest) {
        return userService.actualizarMiPerfil(authentication.getName(), userRequest);
    }

    @PutMapping("/miPerfil/passwd")
    public ResponseEntity<Map<String, String>> cambiarPasswd(Authentication autenticacion, @Valid @RequestBody ChangePasswdRequest request){
        userService.cambiarPasswd(autenticacion.getName(), request);
        return ResponseEntity.ok(Map.of("message", "Se actualizor correctamente la contraseña"));
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/ChangePasswdRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record ChangePasswdRequest (
        @Schema(
                description = "Solicita contraseña actual",
                example = "abc123456"
        )
        @JsonProperty("current_password")
        @NotBlank(message = "Contranseña actual puede estar vacio")
        String currentPasswd,
        @Schema(
                description = "Solicita nueva clave",
                example = "ABC123*"
        )
        @NotBlank(message = "Nueva contraseña no puede estar vacio")
        String newPasswd,
        @Schema(
                description = "Solitia confirmar la nueva clave",
                example = "ABC123*"
        )
        @NotBlank(message = "Confirmar contraseña no puede estar vacio")
        String confirmPasswd
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/PerfilFinancieroRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PerfilFinancieroRequest(
        @Schema(
                description = "Indica si el usuario tiene empleo formal (1) o no (0)",
                example = "1")
        @JsonProperty("empleo_formal")
        @NotNull(message = "Debe indicar si tiene empleo formal")
        @Min(value = 0, message = "El valor debe ser 0 o 1")
        Integer empleoFormal,

        @Schema(
                description = "Ingreso mensual del usuario",
                example = "3500.00")
        @JsonProperty("ingreso_mensual")
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @Schema(
                description = "Línea de crédito disponible del usuario",
                example = "1000.00")
        @JsonProperty("linea_credito")
        @NotNull(message = "La línea de crédito es obligatoria")
        @DecimalMin(value = "0.0", inclusive = true, message = "La línea de crédito no puede ser negativa")
        BigDecimal lineaCredito
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/UserResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public record UserResponse(
        @Schema(
                description = "Nombre del usuario",
                example = "carlos"
        )
        String nombre,

        @Schema(
                description = "Apellido del usuario",
                example = "gomez"
        )
        String apellido,

        @Schema(
                description = "Documento de identificacion del usuario",
                example = "1022332456"
        )
        String documento,

        @Schema(
                description = "Email registrado por el usuario",
                example = "carlosgomez@nocountry.com"
        )
        String email,

        @Schema(
                description = "Fecha de nacimiento del usuario",
                example = "1996-05-31"
        )
        @JsonProperty("fecha_nacimiento")
        LocalDate fechaNacimiento,

        @Schema(
                description = "Estado civil del usuario",
                example = "viudo"
        )
        @JsonProperty("estado_civil")
        EstadoCivil estadoCivil,

        @Schema(
                description = "Genero del usuario",
                example = "masculino"
        )
        Sexo sexo,

        @Schema(
                description = "Cantidad de hijos que tiene el usuario",
                example = "2"
        )
        @JsonProperty("numero_hijos")
        Integer numeroHijos


) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/PerfilFinanciero.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Resultado del perfil financiero despues del analisis",
        example = "SALUDABLE"
)
public enum PerfilFinanciero {
    SALUDABLE,
    EN_OBSERVACION,
    RIESGO;

    @JsonCreator
    public static PerfilFinanciero forString(String value) {
        return PerfilFinanciero.valueOf(value.trim().toUpperCase());
    }

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/ResourceNotFoundException.java">
package com.nocountry.financeai.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/UserAlreadyExistsException.java">
package com.nocountry.financeai.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/HistorialAnalisisRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialAnalisisRepository extends JpaRepository<HistorialAnalisisEntity, Long> {
    List<HistorialAnalisisEntity> findByUsuarioId(Long id);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/PerfilFinancieroRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilFinancieroRepository extends JpaRepository<PerfilFinancieroEntity, Long> {
    Optional<PerfilFinancieroEntity> findByUsuarioId(Long usuarioId);

}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/PerfilFinancieroServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.PerfilFinancieroRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.PerfilFinancieroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilFinancieroServiceImpl implements PerfilFinancieroService {
    private final PerfilFinancieroRepository perfilFinancieroRepository;
    private final UserRepository userRepository;

    @Override
    public PerfilFinancieroEntity obtenerPerfilPorUsuarioId(Long usuarioId) {
        return perfilFinancieroRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El usuario no tiene perfil financiero"
                ));
    }

    @Override
    public PerfilFinancieroResponse crearPerfil(String email, PerfilFinancieroRequest request) {
        UserEntity usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (perfilFinancieroRepository.findByUsuarioId(usuario.getId()).isPresent()) {
            throw new IllegalStateException("El usuario ya tiene un perfil financiero registrado");
        }

        PerfilFinancieroEntity perfil = PerfilFinancieroEntity.builder()
                .usuario(usuario)
                .empleoFormal(request.empleoFormal())
                .ingresoMensual(request.ingresoMensual())
                .lineaCredito(request.lineaCredito())
                .build();
        PerfilFinancieroEntity perfilGuardado = perfilFinancieroRepository.save(perfil);

        return new PerfilFinancieroResponse(
                perfilGuardado.getEmpleoFormal(),
                perfilGuardado.getIngresoMensual(),
                perfilGuardado.getLineaCredito()
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/UserServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.ChangePasswdRequest;
import com.nocountry.financeai.dto.request.UserRequest;
import com.nocountry.financeai.dto.response.UserResponse;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // metodos definidos en la interfaz

    @Override
    public List<UserResponse> obtenerUsuarios() {
        return userRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public UserResponse obtenerUsuarioPorDocumento(String documento) {
        UserEntity usuario = buscarUsuarioPorDocumento(documento);
        return convertirRespuesta(usuario);
    }

    @Override
    public UserResponse obtenerMiPerfil(String email) {
        UserEntity usuario = buscarUsuarioPorEmail(email);
        return convertirRespuesta(usuario);

    }

    @Override
    public void cambiarPasswd(String email, ChangePasswdRequest request) {
        UserEntity usuario = buscarUsuarioPorEmail(email);

        if(!passwordEncoder.matches(
                request.currentPasswd(),
                usuario.getPassword()
        )){
            throw new IllegalArgumentException("La contraseña actual es incorrecta");
        }
        if(!request.newPasswd().equals(request.confirmPasswd())){
            throw new IllegalArgumentException("La nueva contraseña no coincide");
        }

        usuario.setPassword(passwordEncoder.encode(request.newPasswd()));
        userRepository.save(usuario);
    }

    @Override
    public UserResponse actualizarMiPerfil(String email, UserRequest request) {
        UserEntity usuario = buscarUsuarioPorEmail(email);

        System.out.println(request.nombre());

        if(request.nombre() != null){
            usuario.setNombre(request.nombre());
        }
        if(request.apellido() != null){
            usuario.setApellido(request.apellido());
        }
        if(request.email() != null){
            usuario.setEmail(request.email());
        }
        if(request.estadoCivil() != null){
            usuario.setEstadoCivil(request.estadoCivil());
        }
        if(request.sexo() != null){
            usuario.setSexo(request.sexo());
        }
        if(request.numeroHijos() != null){
            usuario.setNumeroHijos(request.numeroHijos());
        }

        UserEntity usuarioActualizado = userRepository.save(usuario);

        return convertirRespuesta(usuarioActualizado);
    }

    // metodos privados de la clase

    private UserEntity buscarUsuarioPorEmail(String email) {
        return  userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(
                        "Usuario no encontrado"
                ));
    }

    private UserEntity buscarUsuarioPorDocumento(String documento){
        UserEntity usuario = userRepository.findByDocumento(documento)
                .orElseThrow(()-> new ResourceNotFoundException("Usuario no encontrado")
                );
        return usuario;
    }

    private UserResponse convertirRespuesta(UserEntity usuario) {
        return new UserResponse(
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDocumento(),
                usuario.getEmail(),
                usuario.getFechaNacimiento(),
                usuario.getEstadoCivil(),
                usuario.getSexo(),
                usuario.getNumeroHijos()
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/PerfilFinancieroService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;

public interface PerfilFinancieroService {
    PerfilFinancieroEntity obtenerPerfilPorUsuarioId(Long usuarioId);

    PerfilFinancieroResponse crearPerfil(String email, PerfilFinancieroRequest request);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/UserService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.ChangePasswdRequest;
import com.nocountry.financeai.dto.request.UserRequest;
import com.nocountry.financeai.dto.response.UserResponse;

import java.util.List;

public interface UserService  {
    // Lista los todos los usuarios
    List<UserResponse> obtenerUsuarios();

    //  Obtiene usuario por documento
    UserResponse obtenerUsuarioPorDocumento(String documento);

    //obtiene el perlfil del usuario autenticado
    UserResponse obtenerMiPerfil(String email);

    // Actualiza datos del usuario
    UserResponse actualizarMiPerfil(String email, UserRequest userRequest);

    // Actuliza contraseña de usuaria
    void cambiarPasswd(String email, ChangePasswdRequest changePasswdRequest);


}
</file>

<file path="backend/src/main/resources/db/migration/V4__create_perfil_Financiero_table.sql">
CREATE TABLE perfil_financiero (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL UNIQUE,
    empleo_formal INTEGER,
    ingreso_mensual DECIMAL(12,2),
    linea_credito DECIMAL(12,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_perfil_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios(id)
);
</file>

<file path="backend/src/test/java/com/nocountry/financeai/FinanceaiApplicationTests.java">
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
</file>

<file path="backend/HELP.md">
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
</file>

<file path="backend/mvnw">
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
</file>

<file path="data-science/modeloFinanceAI/Dockerfile">
FROM python:3.11-slim

WORKDIR /app

COPY . .

RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 8000

CMD ["uvicorn","main:app","--host","0.0.0.0","--port","8000"]
</file>

<file path="data-science/main.py">
from fastapi import FastAPI, HTTPException, status
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field
from typing import List, Dict
from contextlib import asynccontextmanager
import pandas as pd
import numpy as np
import joblib
import sklearn
import sklearn.compose._column_transformer

# ==============================================================================
# 1. PARCHE DE COMPATIBILIDAD SKLEARN
# ==============================================================================
if not hasattr(sklearn.compose._column_transformer, '_RemainderColsList'):
    class _RemainderColsList(list):
        pass
    sklearn.compose._column_transformer._RemainderColsList = _RemainderColsList

# ==============================================================================
# 2. CARGA SEGURA DE MODELOS (LIFESPAN)
# ==============================================================================
modelos = {}

@asynccontextmanager
async def lifespan(app: FastAPI):
    # Proceso de arranque (Startup)
    try:
        modelos['transacciones'] = joblib.load('modelo_clasificacion_transacciones.pkl')
        modelos['perfil'] = joblib.load('modelo_perfil_financiero.pkl')
        print("✅ [PROD] Modelos ML cargados exitosamente.")
    except Exception as e:
        print(f"❌ [ERROR CRÍTICO] Fallo al cargar modelos .pkl: {e}")
        raise RuntimeError(f"No se pudieron cargar los modelos en producción: {e}")
    yield
    # Proceso de apagado (Shutdown)
    modelos.clear()

# ==============================================================================
# 3. CREAR LA APLICACIÓN FASTAPI
# ==============================================================================
app = FastAPI(
    title="API Analítica Financiera",
    version="1.0.0",
    lifespan=lifespan
)

# Configuración de CORS para producción / Oracle Cloud
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # En prod estricto, reemplaza "*" por la IP/Dominio de tu Frontend
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# ==============================================================================
# 4. ESTRUCTURA DE DATOS DE ENTRADA (Pydantic Models)
# ==============================================================================
class TransaccionInput(BaseModel):
    nombre_comercio: str = Field(
        ..., 
        example="Uber", 
        description="Nombre del establecimiento o comercio"
    )
    monto_transaccion: float = Field(
        ..., 
        gt=0, 
        example=250.0, 
        description="Monto de la transacción (debe ser mayor a 0)"
    )
    medio_pago: str = Field(
        ..., 
        example="credito", 
        description="Medios aceptados: credito, debito, transaccion, efectivo"
    )

class EntradaUsuario(BaseModel):
    edad: int = Field(..., ge=18, le=120)
    sexo: str
    estado_civil: str
    numero_hijos: int = Field(..., ge=0)
    empleo_formal: int = Field(..., ge=0, le=1)
    ingreso_mensual: float = Field(..., ge=0)
    linea_credito: float = Field(..., ge=0)
    transacciones: List[TransaccionInput] = []

# ==============================================================================
# 5. ENDPOINTS DE PRODUCCIÓN
# ==============================================================================

@app.get("/health", status_code=status.HTTP_200_OK)
def health_check():
    """Endpoint para que Oracle Cloud / Docker verifique si la API está viva"""
    if 'transacciones' not in modelos or 'perfil' not in modelos:
        raise HTTPException(status_code=500, detail="Modelos no inicializados")
    return {"status": "ok", "models_loaded": True}

@app.post("/analisis-financiero")
def analizar_usuario(datos: EntradaUsuario):
    try:
        modelo_perfil = modelos.get('perfil')
        modelo_transacciones = modelos.get('transacciones')

        # ----------------------------------------------------------------------
        # A) CÁLCULO DE GASTOS Y MÉTRICAS FINANCIERAS
        # ----------------------------------------------------------------------
        gasto_total = 0.0
        if datos.transacciones:
            gasto_total = sum([float(tx.monto_transaccion) for tx in datos.transacciones])

        # 1. Nivel de Endeudamiento (escala float 0.0 a 1.0)
        denom_endeudamiento = datos.ingreso_mensual + datos.linea_credito
        if denom_endeudamiento > 0:
            nivel_endeudamiento = round(float(gasto_total / denom_endeudamiento), 2)
        else:
            nivel_endeudamiento = 0.0

        # 2. Rango de Ahorro (String)
        if datos.ingreso_mensual > 0:
            ahorro_bruto = max(datos.ingreso_mensual - gasto_total, 0.0)
            pct_ahorro = ahorro_bruto / datos.ingreso_mensual
        else:
            pct_ahorro = 0.0

        if pct_ahorro >= 0.40:
            rango_ahorro_str = "Alta"
        elif pct_ahorro >= 0.20:
            rango_ahorro_str = "Media"
        elif pct_ahorro > 0:
            rango_ahorro_str = "Baja"
        else:
            rango_ahorro_str = "Ninguna"

# ----------------------------------------------------------------------
        # B) PREDICCIÓN CON MODELO DE PERFIL (.pkl)
        # ----------------------------------------------------------------------
        df_cliente = pd.DataFrame([{
            'edad': int(datos.edad),
            'sexo': str(datos.sexo).lower().strip(),
            'estado_civil': str(datos.estado_civil).lower().strip(),
            'numero_hijos': int(datos.numero_hijos),
            'empleo_formal': int(datos.empleo_formal),
            'ingreso_mensual': float(datos.ingreso_mensual),
            'linea_credito': float(datos.linea_credito),
            'nivel_endeudamiento': float(nivel_endeudamiento),
            'rango_ahorro': float(pct_ahorro)  # Valor decimal menor a 1
        }])

        perfil_pred = modelo_perfil.predict(df_cliente)[0]
        perfil_str = str(perfil_pred).upper().replace(" ", "_")

        # Inicializamos la probabilidad por defecto por seguridad
        probabilidad = 0.85
        try:
            if hasattr(modelo_perfil, "predict_proba"):
                probs = modelo_perfil.predict_proba(df_cliente)[0]
                probabilidad = round(float(np.max(probs)), 2)
        except Exception:
            probabilidad = 0.85

        # ----------------------------------------------------------------------
        # C) CLASIFICACIÓN NLP DE TRANSACCIONES
        # ----------------------------------------------------------------------
        resumen_gastos: Dict[str, float] = {}
        
        if datos.transacciones and len(datos.transacciones) > 0:
            df_tx = pd.DataFrame([
                {
                    'nombre_comercio': str(t.nombre_comercio).lower().strip(),
                    'monto_transaccion': float(t.monto_transaccion)
                }
                for t in datos.transacciones
            ])
            
            # Evaluación defensiva de probabilidades o predicción directa
            try:
                probs_matriz = modelo_transacciones.predict_proba(df_tx)
                clases = modelo_transacciones.classes_
                categorias_finales = []

                for probs in probs_matriz:
                    prob_max = float(np.max(probs))
                    idx_max = int(np.argmax(probs))
                    
                    # Umbral de confianza al 60%
                    if prob_max <= 0.60:
                        categorias_finales.append("otros servicios")
                    else:
                        categorias_finales.append(str(clases[idx_max]))
                
                df_tx['categoria'] = categorias_finales
            except Exception:
                # Si el modelo no soporta predict_proba, realiza la predicción directa
                preds = modelo_transacciones.predict(df_tx)
                df_tx['categoria'] = [str(p) for p in preds]
            
            # Agrupar montos por categoría
            agrupar = df_tx.groupby('categoria')['monto_transaccion'].sum().to_dict()
            resumen_gastos = {str(k).lower(): round(float(v), 2) for k, v in agrupar.items()}

        # ----------------------------------------------------------------------
        # D) GENERACIÓN DE RECOMENDACIONES
        # ----------------------------------------------------------------------
        recomendaciones = []

        if perfil_str == "RIESGOSO" and datos.linea_credito > datos.ingreso_mensual:
            recomendaciones.append(
                "Para aumentar el score del perfil financiero, se recomienda reducir el gasto o incrementar el ingreso mensual"
            )

        if "entretenimiento" in resumen_gastos and resumen_gastos["entretenimiento"] > (datos.ingreso_mensual * 0.15):
            recomendaciones.append("Monitorear los gastos recurrentes de entretenimiento.")

        if nivel_endeudamiento > 0.50:
            recomendaciones.append("Reducir las gastos para bajar el nivel de endeudamiento.")

        if not recomendaciones:
            recomendaciones.append("Mantener los hábitos de gasto actuales y continuar monitoreando el presupuesto.")

        # ----------------------------------------------------------------------
        # E) SALIDA EN FORMATO ESTRICTO
        # ----------------------------------------------------------------------
        return {
            "perfilFinanciero": perfil_str,
            "probabilidad": probabilidad,
            "nivel_endeudamiento": nivel_endeudamiento,
            "rango_ahorro": rango_ahorro_str,
            "resumenGastos": resumen_gastos,
            "recomendaciones": recomendaciones
        }

    except Exception as e:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=f"Error interno en la inferencia del modelo: {str(e)}"
        )

####http://localhost:8000/docs####
</file>

<file path="data-science/README.md">
# Data Science
</file>

<file path="frontend/css/style.css">
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
</file>

<file path="mock-api/app/models/request.py">
from decimal import Decimal
from enum import Enum

from pydantic import BaseModel


class Sexo(str, Enum):
    MASCULINO = "MASCULINO"
    FEMENINO = "FEMENINO"


class EstadoCivil(str, Enum):
    SOLTERO = "SOLTERO"
    CASADO = "CASADO"
    DIVORCIADO = "DIVORCIADO"
    VIUDO = "VIUDO"


class MedioPago(str, Enum):
    EFECTIVO = "EFECTIVO"
    DEBITO = "DEBITO"
    CREDITO = "CREDITO"
    TRANSFERENCIA = "TRANSFERENCIA"

class TransaccionRequest(BaseModel):
    nombre_comercio: str
    monto_transaccion: Decimal
    medio_pago: MedioPago


class AnalisisFinancieroRequest(BaseModel):
    edad: int
    sexo: Sexo
    estado_civil: EstadoCivil
    numero_hijos: int
    empleo_formal: int
    ingreso_mensual: Decimal
    linea_credito: Decimal
    transacciones: list[TransaccionRequest]
</file>

<file path="mock-api/app/routers/analisis.py">
from fastapi import APIRouter

from app.models.request import AnalisisFinancieroRequest
from app.models.response import AnalisisFinancieroResponse
from app.services.analisis_service import analizar

router = APIRouter(
    prefix="/analisis-financiero",
    tags=["Analisis Financiero"]
)
@router.post("", response_model=AnalisisFinancieroResponse)
def analizar_usuario(
        request:AnalisisFinancieroRequest) -> AnalisisFinancieroResponse:
    return analizar()
</file>

<file path="mock-api/README.md">
# FinanceAI - Mock API

## Descripción

Microservicio desarrollado con FastAPI que simula el servicio de Inteligencia Artificial utilizado por FinanceAI.


---

## Tecnologías

- Python 3.13
- FastAPI
- Pydantic v2
- Uvicorn

---

## Crear entorno virtual

```bash
python -m venv .venv
```

### Linux

```bash
source .venv/bin/activate
```

### Windows

```bash
.venv\Scripts\activate
```

---

## Instalar dependencias

```bash
pip install fastapi uvicorn pydantic
```

o las que realmente estés usando (`scikit-learn`, `joblib`, etc., cuando ya entren al proyecto).

---

## Ejecutar

```bash
uvicorn app.main:app --reload
```

La API estará disponible en:

```text
http://localhost:8000
```

---

## Documentación

Swagger

```text
http://localhost:8000/docs
```

OpenAPI

```text
http://localhost:8000/openapi.json
```

---

## Endpoint disponible

### POST `/predict`

Genera un diagnóstico financiero simulado.

---

## Estado del proyecto

- ✔ Mock API implementada.
- ✔ Documentación OpenAPI.
- ✔ Lista para integración con Spring Boot.
- 🔄 Pendiente integración del modelo real.
</file>

<file path="Protocolo de colaboracion.md">
# 📌 Protocolo de Colaboración, Verificación y Control de Versionado (Actualizado)

**Proyecto:** FinanceAI - Backend  
**Propósito:** Definir el flujo de interacción estricto para la entrega de código optimizado, validación de compilación local, generación de comandos Git y actualización de la Nota Maestra.

---

### 🎯 Objetivo Principal
Garantizar que ningún commit de Git y ninguna actualización en la Nota Maestra se registren con código no probado. Todo cambio debe estar alineado con la arquitectura real del proyecto (paquete base `com.nocountry.financeai`) y ser compilado localmente antes de pasar a la fase de versionado y documentación.

---

### 🔄 Flujo de Trabajo en 5 Pasos (Paso 0 al Paso 4)

#### **Paso 0: Análisis Estricto de Contexto (Asistente IA)**
* Antes de generar cualquier fragmento de código o sugerencia, la IA **debe revisar obligatoriamente** las fuentes adjuntas en el cuaderno (como `financeai.md`, `pom.xml` o notas previas).
* Tiene prohibido inventar rutas, nombres de paquetes genéricos (*placeholders*) o versiones. Debe extraer el paquete base real (`com.nocountry.financeai`) para entregar una solución 100% *plug and play*.

#### **Paso 1: Entrega de Código (Asistente IA)**
* Se proporciona el código fuente completo en Java 21 / Spring Boot 3 (DTOs, Servicios, Controladores, etc.) con sus anotaciones (Lombok, Jakarta Validation, Spring Security) adaptado a la estructura del proyecto.
* **Restricción:** En este paso **no se generan** comandos Git ni bloques de actualización de la nota.

#### **Paso 2: Verificación Local (Desarrollador)**
* Se copia el código al IDE (IntelliJ / VS Code).
* Se ejecuta la compilación (`mvn clean compile` o build del IDE) y se verifica que no existan errores de sintaxis, dependencias o conflictos de contexto.

#### **Paso 3: Trigger de Confirmación (Desarrollador)**
* El usuario envía un mensaje en el chat confirmando que el módulo/código ha sido integrado y compilado exitosamente (ej. *"Listo, ya compiló correctamente"*).

#### **Paso 4: Artefactos Finales (Asistente IA)**
* Tras recibir el trigger, la IA genera inmediatamente:
  1. **Comando Git:** Formateado bajo el estándar *Conventional Commits* (ej. `feat(auth): ...`, `fix(security): ...`).
  2. **Snippet de Nota Maestra:** Fragmento Markdown listo para copiar y pegar en la documentación general del proyecto.

---

### 🏷️ Convención de Commits (Conventional Commits)

| Tipo | Uso | Ejemplo |
| :--- | :--- | :--- |
| `feat` | Nueva funcionalidad agregada | `feat(auth): implement RegisterRequest and LoginRequest DTOs` |
| `fix` | Corrección de un error o bug | `fix(security): resolve circular dependency in JwtAuthFilter` |
| `refactor` | Reestructuración de código sin alterar comportamiento | `refactor(config): update SecurityConfig to handle specific exceptions` |
| `docs` | Cambios exclusivos en documentación | `docs(readme): update backend technical notes` |
</file>

<file path="backend/src/main/java/com/nocountry/financeai/client/IAClient.java">
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
                .uri("/analisis-financiero")
                .body(request)
                .retrieve()
                .body(AnalisisResponse.class);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/OpenApiConfig.java">
package com.nocountry.financeai.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Value("${app.openapi.server-url}")
    private String serverUrl;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server().url(serverUrl)
                ))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));

    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/OrdenOpenApi.java">
package com.nocountry.financeai.config;

import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Clase para dar un orden a los endpoint en OpenApi-swagger
@Configuration
public class OrdenOpenApi {
    @Bean
    public OpenApiCustomizer ordenarTags() {
        return openApi -> {
            List<String> ordenDeseado = List.of(
                    "Analisis",
                    "Autenticacion",
                    "Perfil Financiero",
                    "Transacciones",
                    "Historial Resultado Analisis",
                    "Usuarios",
                    "Administradores",
                    "Test"
            );

            List<Tag> tagsOrdenados = new ArrayList<>(openApi.getTags());
            tagsOrdenados.sort(Comparator.comparingInt(tag -> {
                int idx = ordenDeseado.indexOf(tag.getName());
                return idx == -1 ? Integer.MAX_VALUE : idx;
            }));

            openApi.setTags(tagsOrdenados);
        };
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/AdminController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.dto.response.UserResponse;
import com.nocountry.financeai.service.AnalisisIAService;
import com.nocountry.financeai.service.TransaccionService;
import com.nocountry.financeai.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
@Tag(
        name = "Administradores",
        description = "Administra el sistema"
)
public class AdminController {
    private final UserService userService;
    private final TransaccionService transaccionService;
    private final AnalisisIAService analisisIAService;

    @GetMapping("/usuarios")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> obtenerUsuarios() {
        return userService.obtenerUsuarios();
    }

    @GetMapping("/usuarios/documento/{documento}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse obtenerUsuario(@PathVariable String documento) {
        return userService.obtenerUsuarioPorDocumento(documento);
    }

    @GetMapping("/transacciones")
    @PreAuthorize("hasRole('ADMIN')")
    public List<TransaccionResponse> listarTransacciones(){
        return transaccionService.obtenerTransacciones();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/transacciones/usuario/{usuarioId}")
    public List<TransaccionResponse> listarTransaccionesPorUsuario(@PathVariable Long usuarioId) {
        return transaccionService.obtenerTransaccionesPorUsuario(usuarioId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("transacciones/usuario/{usuarioId}")
    public TransaccionResponse crearTransaccion(
            @PathVariable Long usuarioId,
            @Valid @RequestBody TransactionRequest transactionRequest) {
        return transaccionService.crearTransaccion(usuarioId, transactionRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/usuario/{documento}/analizar")
    public AnalisisResponse analisisPorUsuario(
            @PathVariable String documento
    ) {
        return analisisIAService.analizarPorDocumento(documento);
    }


}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/PerfilFinancieroController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.service.PerfilFinancieroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/perfil")
@RequiredArgsConstructor
@Tag(
        name = "Perfil Financiero",
        description = "Gestión del perfil financiero del usuario")
public class PerfilFinancieroController {
    private final PerfilFinancieroService perfilFinancieroService;

    @PostMapping
    public PerfilFinancieroResponse crearPerfil(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody PerfilFinancieroRequest request
    ) {
        System.out.println("Request recibido: " + request);
        return perfilFinancieroService.crearPerfil(userDetails.getUsername(), request);

    }

}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/UserRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;


public record UserRequest(
        @Schema(
                description = "Nombre del usuario" ,
                example = "carlos"
        )
        @Size(max = 100, message = "Nombre no puede superar los 100 caracteres")
        String nombre,

        @Schema(
                description = "apellido del usuario" ,
                example = "gomez"
        )
        @Size(max = 100, message = "Apellido no puede superar los 100 caracteres")
        String apellido,
        @Schema(
                description = "Documento de identificacion del usuario" ,
                example = "PEMJ920323HJCZNN0"
        )
        String documento,

        @Schema(
                description = "Correo electronico del usuario" ,
                example = "carlosgomez@alura.com"
        )
        @Email(message = "El formato del correo no es valido")
        String email,

        @Schema(
                description = "Fecha de naciemiento del usuario" ,
                example = "1999-03-24"
        )
        @JsonProperty("fecha_nacimiento")
        LocalDate fechaNacimiento,

        @Schema(
                description = "Estado civil del usuario" ,
                example = "soltero"
        )

        @JsonProperty("estado_civil")
        EstadoCivil estadoCivil,

        @Schema(
                description = "Sexo de nacimiento del usuario" ,
                example = "masculino"
        )
        Sexo sexo,

        @Schema(
                description = "Cantidad de hijos del usuario" ,
                example = "1"
        )
        @JsonProperty("numero_hijos")
        @Min(value = 0, message = "El numero de hijos no puede ser negativo")
        Integer numeroHijos
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/HistorialAnalisisResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.entity.enums.RangoAhorro;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public record HistorialAnalisisResponse (
        @JsonProperty("perfil_financiero")
        PerfilFinanciero perfilFinanciero,

        BigDecimal probabilidad,
        @JsonProperty("nivel_endeudamiento")
        BigDecimal nivelEndeudamiento,
        @JsonProperty("rango_ahorro")
        RangoAhorro rangoAhorro,
        @JsonProperty("resumen_gastos")
        Map<String, BigDecimal> resumenGastos,
        @JsonProperty
        List<String> recomendaciones
){}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/PerfilFinancieroResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record PerfilFinancieroResponse (
        @Schema(
                description = "Cantidad de empleos que tiene un usuario",
                example = "1"
        )
        @JsonProperty("empleo_formal")
        Integer empleoFormal,

        @Schema(
                description = "Cantidad de ingresos que persibe un usuario",
                example = "5500"
        )
        @JsonProperty("ingreso_mensual")
        BigDecimal ingresoMensual,

        @Schema(
                description = "Monto de credito que tiene un usuario",
                example = "10000"
        )
        @JsonProperty("linea_credito")
        BigDecimal lineaCredito
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/TransaccionResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransaccionResponse(
        @Schema(
                description = "Nombre del comercio que aparece en la factura",
                example = "telcel"
        )
        @JsonProperty("nombre_comercio")
        String nombreComercio,

        @Schema(
                description = "Valor de la transaccion",
                example = "365"
        )
        @JsonProperty("monto_transaccion")
        BigDecimal montoTransaccion,

        @Schema(
                description = "Medio de pago en el que se pago/cancelo la transaccion",
                example = "EFECTIVO"
        )
        @JsonProperty("medio_pago")
        MedioPago medioPago,
        @Schema(
                description = "Fecha de la transaccion",
                example = "2026-08-06T20:06:38.692Z"
        )
        LocalDateTime fecha
) {}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/MedioPago.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Medio de pago que usa un usuario para cancelar una transaccion",
        example = "TRANSFERENCIA"
)
public enum MedioPago {
    EFECTIVO,
    DEBITO,
    CREDITO,
    TRANSFERENCIA;

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/Rol.java">
package com.nocountry.financeai.entity.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Permisos que tiene un usuario en el sistema",
        example = "USER"
)
public enum Rol {
    USER,
    ADMIN,
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/PerfilFinancieroEntity.java">
package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.RangoAhorro;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "perfil_financiero")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PerfilFinancieroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPerfilFinanciero;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private UserEntity usuario;

    @Column(name = "empleo_formal")
    private Integer empleoFormal;

    @Column(name = "ingreso_mensual", precision = 12, scale = 2)
    private BigDecimal ingresoMensual;

    @Column(name = "linea_credito",  precision = 12, scale = 2)
    private BigDecimal lineaCredito;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/TransactionRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByUsuarioId(Long usuarioId);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/CustomUserDetailsService.java">
package com.nocountry.financeai.security;

import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_" + user.getRol().name()
                        )
                )
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/JwtAuthFilter.java">
package com.nocountry.financeai.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
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
        log.info("JWT filter ejecutando{}", request.getRequestURI());
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(jwt);
        System.out.println("JWT recibido para: " + userEmail);

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
                System.out.println("JWT válido: autenticando usuario");
            }
        }

        filterChain.doFilter(request, response);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/JwtUtil.java">
package com.nocountry.financeai.security;

import com.nocountry.financeai.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;

    public JwtUtil(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtConfig.getExpiration()))
                .signWith(getSignInKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 🔑 IMPORTANTE: usando HEX (no Base64)
     */
    private SecretKey getSignInKey() {
        byte[] keyBytes = HexFormat.of().parseHex(jwtConfig.getSecret());
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/TransaccionServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.entity.TransactionEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public TransaccionResponse crearTransaccionAutenticado(String email, TransactionRequest transactionRequest) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        TransactionEntity transaccion = TransactionEntity.builder()
                        .nombreComercio(transactionRequest.nombreComercio())
                        .montoTransaccion(transactionRequest.montoTransaccion())
                        .medioPago(transactionRequest.medioPago())
                        .usuario(usuario)
                        .fecha(LocalDateTime.now())
                        .build();

        TransactionEntity transaccionGuardada = transactionRepository.save(transaccion);

        return convertirRespuesta(
                transaccionGuardada
        );
    }

    @Override
    public List<TransaccionResponse> obtenerTransaccionesAutenticado(String email) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return transactionRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public TransaccionResponse crearTransaccion(Long usuarioId, TransactionRequest request) {
        UserEntity usuario = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TransactionEntity transaccion = TransactionEntity.builder()
                .nombreComercio(request.nombreComercio())
                .montoTransaccion(request.montoTransaccion())
                .medioPago(request.medioPago())
                .usuario(usuario)
                .fecha(LocalDateTime.now())
                .build();

        TransactionEntity transaccionGuardada = transactionRepository.save(transaccion);

        return new TransaccionResponse(
                transaccionGuardada.getNombreComercio(),
                transaccionGuardada.getMontoTransaccion(),
                transaccionGuardada.getMedioPago(),
                transaccionGuardada.getFecha()
        );
    }

    @Override
    public List<TransaccionResponse> obtenerTransaccionesPorUsuario(Long idUsuario) {
        return transactionRepository.findByUsuarioId(idUsuario)
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public TransaccionResponse actualizarTransaccion(String email, Long idTransaccion, TransactionRequest request) {

        TransactionEntity transaccion = transactionRepository.findById(idTransaccion)
                .orElseThrow(()-> new ResourceNotFoundException("Transaccion no encontrada"));

        if(!transaccion.getUsuario().getEmail().equals(email)) {
            throw new AccessDeniedException("No tienes permiso para modificar esta transaccion");
        }

        if(request.nombreComercio() != null){
            transaccion.setNombreComercio(request.nombreComercio());
        }

        if(request.montoTransaccion() != null){
            transaccion.setMontoTransaccion(request.montoTransaccion());
        }

        if(request.medioPago() != null){
            transaccion.setMedioPago(request.medioPago());
        }

        TransactionEntity transaccionActualizada = transactionRepository.save(transaccion);
        return convertirRespuesta(transaccionActualizada);
    }

    @Override
    public void eliminarTransaccion(String email, Long idTransaccion) {
        TransactionEntity transaccion = transactionRepository.findById(idTransaccion)
                .orElseThrow(()-> new ResourceNotFoundException("Transaccion no encontrada"));

        if(!transaccion.getUsuario().getEmail().equals(email)) {
            throw new  AccessDeniedException("Transaccion no pertenece al usuario");
        }
        transactionRepository.delete(transaccion);
    }

    public List<TransaccionResponse> obtenerTransacciones() {
        return transactionRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    private TransaccionResponse convertirRespuesta(TransactionEntity transactionEntity) {
        return new TransaccionResponse(
                transactionEntity.getNombreComercio(),
                transactionEntity.getMontoTransaccion(),
                transactionEntity.getMedioPago(),
                transactionEntity.getFecha()
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/AuthService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.LoginRequest;
import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/HistorialAnalisisService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;

import java.util.List;

public interface HistorialAnalisisService {
    List<HistorialAnalisisResponse> obtenerHistorial();
    List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id);
    List<HistorialAnalisisResponse> obtenerHistorialAutenticado(String email);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/TransaccionService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.entity.TransactionEntity;
import jakarta.validation.Valid;

import java.util.List;

public interface TransaccionService {
    //crea transacciones de un usuario autenticado
    TransaccionResponse crearTransaccionAutenticado(String email,TransactionRequest transactionRequest);
    // Obtiene las transacciones de un usuario registrado
    List<TransaccionResponse> obtenerTransaccionesAutenticado(String email);
    // Crea transaccion por Id
    TransaccionResponse crearTransaccion(Long usuarioId, TransactionRequest transactionRequest);
    // Obtiene todas las transacciones de todos los usuarios
    List<TransaccionResponse> obtenerTransacciones();
    // Obtiene todas las transacciones de un usuario
    List<TransaccionResponse> obtenerTransaccionesPorUsuario(Long idUsuario);

    TransaccionResponse actualizarTransaccion(String email, Long idTransaccion, @Valid TransactionRequest transactionRequest);

    void eliminarTransaccion(String email, Long idTransaccion);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/FinanceaiApplication.java">
package com.nocountry.financeai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceaiApplication.class, args);
	}

}
</file>

<file path="backend/src/main/resources/db/migration/V2__create_transactions_table.sql">
CREATE TABLE transacciones (
id BIGSERIAL PRIMARY KEY,
usuario_id BIGINT NOT NULL,
monto_transaccion NUMERIC(12, 2) NOT NULL,
tipo VARCHAR(10),
categoria VARCHAR(50),
nombre_comercio VARCHAR(255),
medio_pago VARCHAR(20) NOT NULL,
fecha TIMESTAMP NOT NULL,
CONSTRAINT fk_transacciones_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE
);
</file>

<file path="backend/src/main/resources/db/migration/V5__fix_historial_analisis_schema.sql">
-- Renombrar la columna frecuencia_ahorro a rango_ahorro para coincidir con la entidad JPA
ALTER TABLE historial_analisis
RENAME COLUMN frecuencia_ahorro TO rango_ahorro;

-- Cambiar el tipo de dato de INTEGER a NUMERIC(4,2) para soportar BigDecimal
ALTER TABLE historial_analisis
ALTER COLUMN nivel_endeudamiento TYPE NUMERIC(4,2);
</file>

<file path="backend/mvnw.cmd">
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
</file>

<file path="backend/README.md">
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
</file>

<file path="data-science/requirements.txt">
fastapi
uvicorn
pandas
scikit-learn==1.3.2
joblib
pydantic
</file>

<file path="frontend/js/api.js">
// ==========================================
// Configuración y Utilidades Base de la API
// ==========================================
const BASE_URL = 'http://localhost:8080/api/v1';

/**
 * Función genérica (fetch wrapper) para consumir endpoints protegidos.
 * Inyecta automáticamente el token JWT en las cabeceras.
 */
async function fetchProtected(endpoint, options = {}) {
    const token = localStorage.getItem('jwtToken');

    if (!token) {
        console.warn("No hay sesión activa");
        return null;
    }

    const defaultHeaders = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
    };

    const config = {
        ...options,
        headers: {
            ...defaultHeaders,
            ...options.headers
        }
    };

    try {
        const response = await fetch(`${BASE_URL}${endpoint}`, config);

        // Si el token expiró o es inválido, Spring Boot devolverá 401 o 403
        if (response.status === 401 || response.status === 403) {
            localStorage.removeItem('jwtToken');
            window.location.href = 'index.html';
            throw new Error('Sesión expirada o no autorizada');
        }

        return response;
    } catch (error) {
        console.error('Error en fetchProtected:', error);
        throw error;
    }
}
</file>

<file path="frontend/js/dashboard.js">
// ==========================================
// Configuración e Inicio
// ==========================================
// Asumiendo que `fetchProtected` está en api.js. Si no, asegúrate de que agregue la URL base '/api/v1' y el Header de Autorización.

document.addEventListener('DOMContentLoaded', async () => {
    const token = localStorage.getItem('jwtToken');
    // AUD-01: Validamos que haya token
    if (!token || token === 'undefined') {
        window.location.href = 'index.html';
        return;
    }

    // AUD-19: Validar si el usuario ya tiene perfil financiero
    // Verificamos intentando consultar el perfil. (Asumiendo que existe un endpoint GET /perfil)
    // Si el backend aún no tiene GET /perfil, esto fallará y forzará a llenarlo.
    await verificarPerfilFinanciero();

    cargarTransacciones();
});

const btnLogout = document.getElementById('btnLogout');
if (btnLogout) {
    btnLogout.addEventListener('click', () => {
        localStorage.removeItem('jwtToken');
        // También limpiamos banderas locales
        localStorage.removeItem('perfilCompletado');
        window.location.href = 'index.html';
    });
}

// ==========================================
// Módulo de Perfil Financiero (AUD-19)
// ==========================================
async function verificarPerfilFinanciero() {
    // Si ya lo completó en esta sesión localmente, lo dejamos pasar
    if (localStorage.getItem('perfilCompletado') === 'true') return;

    // Aquí llamarías a tu API para validar. Por ahora, mostramos el modal directamente 
    // si no tenemos constancia local de que lo haya llenado.
    const modal = new bootstrap.Modal(document.getElementById('modalPerfilIncompleto'));
    modal.show();

    const formPerfil = document.getElementById('formPerfilFinanciero');
    formPerfil.addEventListener('submit', async (e) => {
        e.preventDefault();
        const btnGuardar = document.getElementById('btnGuardarPerfil');
        btnGuardar.disabled = true;
        btnGuardar.innerText = 'Guardando...';

        const payload = {
            ingresoMensual: parseFloat(document.getElementById('perfilIngreso').value),
            lineaCredito: parseFloat(document.getElementById('perfilCredito').value),
            empleoFormal: document.getElementById('perfilEmpleoFormal').checked
        };

        try {
            // Requisito: Endpoint para crear perfil
            const response = await fetchProtected('/perfil', {
                method: 'POST',
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                localStorage.setItem('perfilCompletado', 'true');
                modal.hide();
            } else {
                alert('Hubo un error al guardar tu perfil. Inténtalo de nuevo.');
                btnGuardar.disabled = false;
                btnGuardar.innerText = 'Guardar y Continuar';
            }
        } catch (error) {
            console.error('Error al guardar perfil:', error);
            btnGuardar.disabled = false;
        }
    });
}

// ==========================================
// Módulo de Transacciones (Slice 2)
// ==========================================
async function cargarTransacciones() {
    try {
        // AUD-03: Ruta correcta hacia el backend Java
        const response = await fetchProtected('/transacciones/usuario/transacciones', { method: 'GET' });
        if (response.ok) {
            const transacciones = await response.json();
            renderizarTablaTransacciones(transacciones);
        }
    } catch (error) {
        console.error('Error al cargar transacciones:', error);
    }
}

const formTransaccion = document.getElementById('formTransaccion');
if (formTransaccion) {
    formTransaccion.addEventListener('submit', async (e) => {
        e.preventDefault();

        // AUD-03: Contrato de payload exacto
        const payload = {
            nombre_comercio: document.getElementById('transComercio').value,
            monto_transaccion: parseFloat(document.getElementById('transMonto').value),
            medio_pago: document.getElementById('transMedioPago').value
        };

        try {
            const response = await fetchProtected('/transacciones/usuario/transacciones', {
                method: 'POST',
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                formTransaccion.reset();
                cargarTransacciones(); // Recargar la tabla
            } else {
                alert('Error al guardar la transacción');
            }
        } catch (error) {
            console.error('Error en el registro:', error);
        }
    });
}

function renderizarTablaTransacciones(transacciones) {
    const tbody = document.getElementById('tablaTransaccionesBody');
    if (!tbody) return;
    tbody.innerHTML = '';

    if (!transacciones || transacciones.length === 0) {
        tbody.innerHTML = '<tr><td colspan="3" class="text-center text-muted">Aún no hay transacciones registradas</td></tr>';
        return;
    }

    transacciones.forEach(t => {
        const tr = document.createElement('tr');
        // Usamos los nombres correctos del backend (monto_transaccion, nombre_comercio)
        tr.innerHTML = `
            <td>${t.nombre_comercio || 'Desconocido'}</td>
            <td><span class="badge bg-secondary">${t.medio_pago || 'N/A'}</span></td>
            <td class="text-end fw-bold">$${t.monto_transaccion ? t.monto_transaccion.toFixed(2) : '0.00'}</td>
        `;
        tbody.appendChild(tr);
    });
}

// ==========================================
// Módulo de Análisis IA (Slice 3)
// ==========================================
const btnAnalizar = document.getElementById('btnAnalizar');
if (btnAnalizar) {
    btnAnalizar.addEventListener('click', async () => {
        btnAnalizar.disabled = true;
        btnAnalizar.innerText = 'Consultando a la IA...';

        try {
            // AUD-03: Ajustado al endpoint correcto del backend
            const response = await fetchProtected('/analisis/predict', { method: 'POST' });

            if (response.ok) {
                const resultado = await response.json();
                mostrarResultadosIA(resultado);
            } else {
                alert('No se pudo completar el análisis. Verifica que tengas transacciones registradas.');
            }
        } catch (error) {
            console.error('Error al solicitar análisis:', error);
        } finally {
            btnAnalizar.disabled = false;
            btnAnalizar.innerText = 'Generar Análisis Inteligente';
        }
    });
}

function mostrarResultadosIA(data) {
    const contenedor = document.getElementById('resultadoContenedor');
    if (!contenedor) return;

    // AUD-02: Corregido de 'EN_RIESGO' a 'RIESGO' para alinear con el enum de Java
    let badgeClass = 'bg-secondary';
    if (data.perfil_financiero === 'SALUDABLE') badgeClass = 'bg-success';
    else if (data.perfil_financiero === 'EN_OBSERVACION') badgeClass = 'bg-warning text-dark';
    else if (data.perfil_financiero === 'RIESGO') badgeClass = 'bg-danger';

    contenedor.classList.remove('d-none');
    document.getElementById('iaPerfil').innerHTML = `<span class="badge ${badgeClass} p-2">${data.perfil_financiero || 'DESCONOCIDO'}</span>`;

    if (data.resumen_gastos && data.resumen_gastos.length > 0) {
        const listaRecomendaciones = data.resumen_gastos.map(r => `<li class="list-group-item bg-transparent text-start small">${r}</li>`).join('');
        document.getElementById('iaRecomendaciones').innerHTML = `<ul class="list-group list-group-flush">${listaRecomendaciones}</ul>`;
    } else {
        document.getElementById('iaRecomendaciones').innerHTML = '<p class="text-muted small">No hay datos suficientes para recomendaciones.</p>';
    }
}
</file>

<file path="frontend/dashboard.html">
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FinanceAI - Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <nav class="navbar navbar-dark bg-primary shadow-sm">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1 fw-bold">FinanceAI - Panel Principal</span>
            <button class="btn btn-outline-light btn-sm" id="btnLogout">Cerrar Sesión</button>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8 text-center">
                <div class="card shadow border-0 p-4">
                    <h2 class="text-success mb-3">¡Bienvenido al Dashboard!</h2>
                    <p class="text-muted">La interfaz ha cargado correctamente y la sesión está activa.</p>
                    <hr>
                    <div id="estadoConexion" class="alert alert-info">
                        Verificando conexión con el backend...
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        console.log("¡El dashboard.html se cargó y ejecutó correctamente!");
        
        // Validar si el token existe
        const token = localStorage.getItem('jwtToken');
        const estadoDiv = document.getElementById('estadoConexion');
        
        if (!token || token === 'undefined') {
            estadoDiv.className = "alert alert-danger";
            estadoDiv.innerText = "Advertencia: No se encontró un token JWT válido en el almacenamiento local.";
        } else {
            estadoDiv.className = "alert alert-success";
            estadoDiv.innerText = "Token JWT detectado con éxito. Listo para consumir la API.";
        }

        // Botón de salida
        document.getElementById('btnLogout').addEventListener('click', () => {
            localStorage.removeItem('jwtToken');
            window.location.href = 'index.html';
        });
    </script>
</body>
</html>
</file>

<file path="mock-api/app/models/response.py">
from decimal import Decimal
from enum import Enum

from pydantic import BaseModel

class PerfilFinanciero(str, Enum):
    SALUDABLE = "SALUDABLE"
    EN_OBSERVACION = "EN_OBSERVACION"
    EN_RIESGO = "EN_RIESGO"

class RangoAhorro(str, Enum):
    ALTA = "ALTA"
    MEDIA = "MEDIA"
    BAJA = "BAJA"
    NINGUNA = "NINGUNA"

class AnalisisFinancieroResponse(BaseModel):
    perfil_financiero: PerfilFinanciero
    probabilidad: Decimal
    nivel_endeudamiento: Decimal
    porcentaje_ahorro: RangoAhorro
    resumen_gastos: dict[str, Decimal]
    recomendaciones: list[str]
</file>

<file path="mock-api/app/services/analisis_service.py">
from decimal import Decimal

from app.models.response import (
    AnalisisFinancieroResponse,
    PerfilFinanciero,
    RangoAhorro,
)

def analizar() -> AnalisisFinancieroResponse:


    resumen = {
        "alimentacion": Decimal("500.00"),
        "transporte": Decimal("150.00"),
        "entretenimiento": Decimal("40.00"),
        "salud": Decimal("75.00"),
        "educacion": Decimal("450.00"),
        "servicios": Decimal("70.00"),
        "otros": Decimal("30.00"),
    }

    return AnalisisFinancieroResponse(
        perfil_financiero=PerfilFinanciero.SALUDABLE,
        probabilidad=Decimal("0.65"),
        nivel_endeudamiento=Decimal("0.45"),
        porcentaje_ahorro=RangoAhorro.MEDIA,
        resumen_gastos=resumen,
        recomendaciones=[
            "Considera aumentar tu ahorro mensual.",
            "Revisa tus gastos en entretenimiento para optimizar tu presupuesto.",
        ],
)
</file>

<file path=".gitattributes">
text=auto eol=lf
backend/mvnw text eol=lf
</file>

<file path="notamaestra_financeai.md">
# FinanceAI
## Nota Maestra del Proyecto
*Documentación Técnica, Auditoría de Código y Hoja de Ruta — Documento único de referencia para el equipo y para asistentes de IA*

**Versión 2 — Actualizado: 05 de agosto de 2026**
Stack objetivo: Java 21 + Spring Boot 4.1.0
Basado en re-auditoría cruzada entre la Nota Maestra v1 (30 de julio de 2026) y un nuevo snapshot del código real (financeai.md, 05 de agosto de 2026)
*Proyecto Hackathon No Country / ONE (Oracle Next Education – Alura)*
*Este documento reemplaza a la versión del 30 de julio de 2026 como fuente única de verdad del proyecto.*

---

## 0. Cómo Usar Este Documento
Este documento es la fuente única de verdad (single source of truth) del proyecto FinanceAI. Esta es la Versión 2, generada a partir de una nueva re-auditoría cruzada entre la Nota Maestra v1 (30 de julio de 2026) y un snapshot actualizado del código real (financeai.md, capturado el 05 de agosto de 2026 con Repomix). Reemplaza y consolida la versión anterior.

* Úsalo como contexto al iniciar una conversación con un asistente de IA (Claude u otro) para generar historias de usuario, tickets técnicos, revisiones de código o planificación de sprint.
* Cada hallazgo técnico tiene un identificador AUD-XX (Sección 6) y cada tarea propuesta tiene un identificador TASK-XXX (Sección 10), para poder referenciarlos sin ambigüedad en conversaciones futuras. Los identificadores AUD-01 a AUD-13 y TASK-001 a TASK-025 se conservan de la v1 para no romper referencias ya usadas por el equipo; los hallazgos y tareas nuevos de esta re-auditoría continúan la numeración desde AUD-14 / TASK-026.
* Las Secciones 6 (Auditoría Técnica) y 10 (Backlog Priorizado) son las de mayor prioridad de lectura para generar el próximo sprint de trabajo.
* Al pedirle tareas a una IA, cita la sección y el ID exacto (ej.: “Genera la tarea técnica detallada para TASK-026 / AUD-14”) para obtener resultados consistentes con este documento.
* Este documento debe actualizarse cada vez que se cierre un hallazgo o se complete una tarea del backlog, para que siga siendo confiable como fuente de contexto.

### 0.1 Convención de Identificadores
**AUD-XX:** hallazgo técnico detectado en la auditoría de código (Sección 6). Representa un bug, riesgo de seguridad o inconsistencia real, no una opinión de estilo.
**TASK-XXX:** tarea concreta del backlog priorizado (Sección 10), lista para convertirse en ticket. Cada TASK-XXX referencia el AUD-XX o la sección que la origina.
**Estados:** 🟢/✅ Completado · 🟡 Parcial / con deuda técnica · 🔴 Pendiente · ⚠️ Bloqueado o riesgoso.

### 0.2 Qué Cambió Desde la v1 (Resumen Ejecutivo)
El snapshot de código de esta re-auditoría es sustancialmente más reciente que el usado en la v1: aparecieron componentes enteros que no existían antes (gestión de perfil financiero, un motor de IA real con modelos entrenados, y una interfaz de login/registro funcional en el frontend). El balance neto:
* **6 hallazgos de la v1 quedaron resueltos 🟢** (AUD-04, AUD-06, AUD-07, AUD-08, AUD-10, AUD-12), incluyendo los dos hallazgos de seguridad más severos del backlog anterior: la fuga de datos en transacciones (AUD-07) y el IDOR de historial (AUD-08).
* **1 hallazgo es una regresión 🔴** (AUD-09: application.yml quedó peor que antes, no simplemente igual de incompleto).
* **5 hallazgos de la v1 siguen sin cambios 🔴** (AUD-01, AUD-02, AUD-03, AUD-05 en parte, AUD-11, AUD-13).
* **8 hallazgos son nuevos 🟣** (AUD-14 a AUD-21), producto directo de las nuevas funcionalidades incorporadas: el motor de IA real no está conectado al backend (tres desalineaciones independientes), el esquema de la tabla de historial no coincide con la entidad JPA, y el flujo de registro/login del frontend tiene puntos de quiebre nuevos.

Conclusión para el PM: el proyecto avanzó de forma real y significativa en dos frentes (seguridad de transacciones/historial, y existencia de un modelo de IA entrenado), pero el camino crítico para una demo end-to-end sigue bloqueado, ahora por una cadena de 6 dependencias secuenciales en lugar de un solo problema aislado. Ver Sección 7.4 para el detalle de esa cadena.

---

## 1. Visión General del Proyecto
### 1.1 Descripción
FinanceAI es una solución inteligente orientada al sector Fintech / Educación Financiera. Su propósito es transformar transacciones brutas en conocimiento útil y accionable para mejorar la salud financiera de los usuarios, mediante análisis de hábitos, clasificación de perfil financiero y recomendaciones automáticas generadas por un motor de IA.

### 1.2 Objetivos del MVP (Funcionalidades Obligatorias)
* Clasificación automática de transacciones: categorización en Alimentación, Transporte, Salud, Vivienda, Educación, Ocio, Servicios, etc.
* Análisis de perfil financiero: clasificación del usuario en Saludable, En observación o En riesgo.
* Recomendaciones personalizadas: consejos prácticos según patrones de consumo e indicadores financieros.
* Exposición RESTful: interfaz JSON documentada (Swagger/OpenAPI) para consumo de clientes y frontend.
* Despliegue OCI: integración obligatoria con al menos un servicio de Oracle Cloud Infrastructure.

### 1.3 Equipo y Stack Tecnológico
*Sin cambios respecto a la v1: se mantiene Java 21 sobre spring-boot-starter-parent 4.1.0 en todo el documento.*

| Rol / Área | Integrantes | Tecnologías principales |
| :--- | :--- | :--- |
| Backend Developers | 3 personas | Java 21, Spring Boot 4.1.0 (Web, Data JPA, Security), Hibernate/JPA, Flyway, PostgreSQL 16 Alpine, JJWT 0.12.6, springdoc-openapi 3.0.3, Docker |
| Data Science | 4 personas | Python, Pandas, Scikit-Learn (1.6.1 en el servicio productivo).<br>Novedad v2: ya existen dos modelos entrenados y serializados (modelo_perfil_financiero.pkl, modelo_clasificacion_transacciones.pkl) sirviendo desde un microservicio FastAPI propio (data-science/modeloFinanceAI). Ver AUD-15 a AUD-17: el modelo existe y funciona en aislamiento, pero no está correctamente conectado al backend todavía. |
| Frontend | No listado explícitamente en el equipo original | HTML5, Bootstrap 5, JavaScript vanilla (fetch API).<br>Novedad v2: ya existe una pantalla de login/registro funcional (index.html + auth.js) y un dashboard.js con lógica de transacciones y análisis, aunque ambos dependen de contratos de API y de una página (dashboard.html) que hoy no existen (ver AUD-03, AUD-18, AUD-19). |
| Project Management | 1 persona | Metodologías ágiles (Sprints / Kanban) |
| Infraestructura Cloud | Equipo general | Oracle Cloud Infrastructure (OCI) — Compute / Object Storage (pendiente, Semana 5) |

### 1.4 Contrato JSON Objetivo (Especificación Original del Producto)
**⚠️ Este es el contrato OBJETIVO original del producto (documentado en el README). NO coincide con la implementación actual del backend ni con lo que hoy envía/espera el frontend real (auth.js / dashboard.js). Ver Sección 3 y AUD-03 para el detalle completo, que en esta v2 se confirma también en runtime, no sólo en el diseño.**

**Request — POST /api/analisis-financiero**
```json
{ "ingreso_mensual": 4500, "nivel_endeudamiento": 25, "frecuencia_ahorro": "Media", "transacciones": [ { "descripcion": "Supermercado", "valor": 420 }, { "descripcion": "Combustible", "valor": 300 }, { "descripcion": "Streaming", "valor": 40 } ] }
```

**Response**
```json
{ "perfil_financiero": "En observacion", "probabilidad": 0.82, "resumen_gastos": { "alimentacion": 420, "transporte": 300, "entretenimiento": 40 }, "recomendaciones": [ "Monitorear gastos recurrentes de entretenimiento", "Aumentar reserva financiera mensual" ] }
```

---

## 2. Arquitectura Real del Sistema
### 2.1 Módulos del Monorepo
*Novedad v2: el motor de IA real vive en un módulo separado del mock-api original, y ambos coexisten hoy en el repositorio y en docker-compose.yml.*

| Módulo | Tecnología | Estado | Descripción |
| :--- | :--- | :--- | :--- |
| backend/ | Java 21 + Spring Boot 4.1.0 | 🟡 En desarrollo activo | API REST principal: autenticación, transacciones, perfil financiero, análisis y su historial. |
| mock-api/ | Python + FastAPI + Pydantic | ⚠️ Obsoleto, no conectado | Simulaba el motor de IA. Sigue en el repo y sigue siendo el destino teórico de ia.api.url, pero ya no aporta valor real ahora que existe un modelo entrenado (ver AUD-17: decidir su retiro). |
| data-science/modeloFinanceAI/ | Python (FastAPI, pandas, scikit-learn, joblib) | 🟡 Funcional en aislamiento, no conectado | Novedad v2. Sirve dos modelos .pkl reales (perfil financiero y clasificación de transacciones) vía POST /analisis-financiero. Responde bien probado de forma directa, pero el backend no le apunta correctamente (AUD-15/16). |
| data-science/ (raíz) | Python (pandas, scikit-learn) | 🟡 Copia duplicada | Contiene una segunda copia casi idéntica del mismo servicio y los mismos .pkl, con un formato de salida distinto (decimales vs. strings con “%”). No está referenciada en docker-compose.yml. Ver AUD-17. |
| frontend/ | HTML + Bootstrap 5 + JS vanilla | 🟡 Parcialmente construido, no integrado | Novedad v2: ya existe login/registro real (index.html, auth.js) con manejo de JWT en localStorage. dashboard.js también existe pero apunta a endpoints inexistentes y a una página dashboard.html que no está en el repo (AUD-03, AUD-18). |

### 2.2 Flujo de Comunicación (Previsto vs. Real)
Frontend  →  Backend (Spring Boot, puerto 8080)  →  Motor de IA (hoy con dos candidatos: mock-api en :8001, o modelo-financeai en :8000)  →  Backend persiste el historial en PostgreSQL. El backend también persiste usuarios, transacciones y perfil financiero directamente en PostgreSQL vía Spring Data JPA / Flyway.

**Nota de infraestructura (actualizada):** docker-compose.yml en la raíz del repo ya orquesta cuatro servicios: postgres-db, mock-api (puerto host 8001), modelo-financeai (puerto host 8000) y backend. Sin embargo, la variable IA_API_URL del backend sigue apuntando a http://mock-api:8000 — un host correcto pero con el puerto equivocado (mock-api escucha internamente en 8001, no 8000), y el servicio que sí escucha en el puerto 8000 es modelo-financeai, con otro nombre de host. Ver AUD-15.

### 2.3 Estructura de Paquetes del Backend (actualizada)
*com.nocountry.financeai*
```text
client/            IAClient (RestClient hacia el motor de IA)
config/            CorsConfig, OpenApiConfig, OrdenOpenApi, RestClientConfig
controller/        AnalisisController, AuthController, HistorialAnalisisController,
                   PerfilFinancieroController (nuevo), TestSecurityController (nuevo),
                   TransactionController
dto/request/       AnalisisRequest, LoginRequest, PerfilFinancieroRequest (nuevo),
                   RegisterRequest, TransactionRequest
dto/response/      AnalisisResponse, AuthResponse, ErrorResponse,
                   HistorialAnalisisResponse, PerfilFinancieroResponse (nuevo),
                   TransaccionResponse (nuevo)
entity/enums/      EstadoCivil, MedioPago, PerfilFinanciero, RangoAhorro, Rol, Sexo (todos nuevos salvo PerfilFinanciero)
entity/            HistorialAnalisisEntity, PerfilFinancieroEntity (nuevo), TransactionEntity, UserEntity
exception/         ApiExceptionHandler, ResourceNotFoundException (nuevo), UserAlreadyExistsException
repository/        HistorialAnalisisRepository, PerfilFinancieroRepository (nuevo),
                   TransactionRepository, UserRepository
security/          CustomUserDetailsService, JwtAuthFilter, JwtUtil, SecurityConfig
service/impl/      AnalisisIAServiceImpl, AuthServiceImpl, HistorialAnalisisServiceImpl,
                   PerfilFinancieroServiceImpl (nuevo), TransaccionServiceImpl (nuevo)
FinanceaiApplication
```
La incorporación más relevante desde la v1 es el módulo completo de Perfil Financiero (entidad, repositorio, service, controller y DTOs), que ahora es un prerrequisito obligatorio para poder generar un análisis (AnalisisIAServiceImpl.analizarPorUsuarioId lanza ResourceNotFoundException si el usuario no tiene perfil). El frontend actual no llama a este endpoint nuevo (ver AUD-19).

---

## 3. Contrato de API: Objetivo vs. Estado Actual
La v1 ya documentaba que el contrato README/frontend no coincidía con el backend real. En esta v2 se confirma que el problema no es sólo teórico: el frontend ya tiene código ejecutable (dashboard.js) que llama activamente a endpoints que no existen en el backend.

| Aspecto | Contrato objetivo (README) | Backend real (Java) | Frontend real (dashboard.js) |
| :--- | :--- | :--- | :--- |
| Endpoint transacciones | POST /api/analisis-financiero (todo en un solo request) | POST /api/v1/transacciones/usuario/transacciones | POST /transactions (con BASE_URL http://localhost:8080/api/v1) → URL final inexistente |
| Endpoint análisis | incluido en el mismo request | POST /api/v1/analisis/predict | POST /analisis → URL final inexistente |
| Payload transacción | { descripcion, valor } | { nombre_comercio, monto_transaccion, medio_pago } | { descripcion, valor } — coincide con el README, no con el backend |
| Autenticación | No especificada | Requiere JWT (Bearer) en casi todas las rutas | Sí envía Bearer token, pero contra las URLs incorrectas de arriba |
| Valores de perfil_financiero | Saludable / En observación / En riesgo (texto libre) | Enum Java: SALUDABLE, EN_OBSERVACION, RIESGO | dashboard.js ya contempla un cuarto valor: 'EN_RIESGO', que tampoco coincide con el enum Java (ver AUD-02) |

### 3.1 Recomendación del PM (sin cambios de fondo)
Se mantiene la recomendación de la v1: fijar un único contrato antes de continuar con nuevas features (TASK-003), conservando el versionado /api/v1/... del backend y evaluando snake_case global vía Jackson (spring.jackson.property-naming-strategy: SNAKE_CASE) para hablar el mismo idioma que el README y el frontend. Esta vez con un agravante: dashboard.js ya fue escrito asumiendo nombres de endpoint y de payload que no son ni el contrato README ni el contrato backend real (usa /transactions en inglés, que no aparece en ninguno de los dos documentos previos) — es un tercer dialecto a reconciliar, no sólo dos.

---

## 4. Configuración de Entorno
### 4.1 docker-compose.yml (raíz del repo, vigente)
*Novedad v2: ahora orquesta 4 servicios (antes sólo PostgreSQL). Ver AUD-15 sobre el desalineamiento de host/puerto que esto introdujo.*
*postgres-db (5432) · mock-api (host 8001 → contenedor 8001) · modelo-financeai (host 8000 → contenedor 8000) · backend (8080), con IA_API_URL=http://mock-api:8000 — combinación de host y puerto que no corresponde a ningún servicio real (ver AUD-15).*

### 4.2 application.yml del backend — ⚠️ Regresión detectada (AUD-09)
**Hallazgo nuevo relevante: 🔴** backend/src/main/resources/application.yml ya no contiene siquiera spring.application.name (que sí tenía en la v1). Su contenido actual es, literalmente, una copia del docker-compose.yml de la raíz (bloques services:, postgres-db:, volumes:, etc.), probablemente pegada por error o resultado de un merge mal resuelto. Un application.yml con sintaxis de docker-compose no es YAML de Spring Boot válido para configuración de la app; en el mejor caso Spring lo ignora silenciosamente, en el peor caso puede fallar el arranque según el classpath. Se prioriza como P0 por su bajo costo de arreglo y su alto impacto en el arranque local de cualquier integrante nuevo.

### 4.3 Variables de Entorno Requeridas
| Variable | Descripción | Dónde se usa | Estado |
| :--- | :--- | :--- | :--- |
| SPRING_DATASOURCE_URL / _USERNAME / _PASSWORD | Credenciales y cadena JDBC hacia PostgreSQL | application.properties (local, gitignored) / docker-compose | 🟡 Sólo local o vía compose, no versionada como plantilla (AUD-09 sigue relacionado) |
| ia.api.url (IA_API_URL) | URL base del motor de IA consumido por IAClient | RestClientConfig | 🔴 Configurada, pero apunta a un host:puerto que no sirve el modelo real (AUD-15) |
| jwt.secret | Clave HMAC para firmar los tokens | JwtUtil | ⚠️ Sigue con un valor por defecto embebido en el código fuente (AUD-13, sin cambios desde v1) |
| jwt.expiration | Tiempo de expiración del token en ms | JwtUtil | 🟢 Sin cambios, aceptable para desarrollo |
| server.port | Puerto del backend | Por defecto 8080 | 🟢 OK |

### 4.4 Historial de Diagnóstico y Resoluciones (Bitácora del Equipo)
Se conserva la bitácora completa de la v1 (ver documento anterior); se agrega la siguiente entrada correspondiente a esta re-auditoría:
* 2026-08-05 — Re-auditoría cruzada de PM: se contrastó la Nota Maestra v1 (30-jul) contra un nuevo snapshot Repomix del código real. Se confirmaron 6 hallazgos resueltos, 1 regresión (AUD-09) y se detectaron 8 hallazgos nuevos (AUD-14 a AUD-21), en su mayoría producto de la incorporación del motor de IA real y del módulo de perfil financiero. Esta nota maestra v2 reemplaza a la v1 como fuente de verdad.

---

## 5. Dependencias del Backend (pom.xml)
Parent: org.springframework.boot:spring-boot-starter-parent:4.1.0 · `<java.version>21</java.version>`.

| Dependencia | Versión | Propósito |
| :--- | :--- | :--- |
| spring-boot-starter-data-jpa | gestionada por el parent | Persistencia JPA/Hibernate sobre PostgreSQL. |
| spring-boot-starter-validation | gestionada por el parent | Bean Validation (Jakarta) para DTOs. |
| spring-boot-starter-web | gestionada por el parent | MVC / API REST. |
| spring-boot-starter-security | gestionada por el parent | Autenticación y autorización. |
| spring-boot-starter-flyway + flyway-core + flyway-database-postgresql | gestionada por el parent | Migraciones versionadas del esquema (V1–V4, con V5 propuesta en TASK-026). |
| postgresql (runtime) | gestionada por el parent | Driver JDBC. |
| lombok (optional) | gestionada por el parent | Reducción de boilerplate. |
| jjwt-api / jjwt-impl / jjwt-jackson | 0.12.6 | Emisión y validación de JWT. |
| springdoc-openapi-starter-webmvc-ui | 3.0.3 — corrección v2 | Swagger UI / documentación OpenAPI. |
| spring-boot-starter-test | gestionada por el parent | JUnit 5 + Mockito para testing. |

**Corrección aplicada en v2:** la Nota Maestra v1 documentaba springdoc-openapi 2.6.0; el pom.xml real hoy fija la versión 3.0.3. Además, el bloque `<configuration>` del maven-compiler-plugin en el pom.xml real está mal escrito como `<coniguration>` (falta la primera “f”), lo que probablemente hace que Maven ignore ese bloque completo y procese Lombok por su mecanismo por defecto en vez del explícito (ver AUD-21).

---

## 6. Auditoría Técnica — Re-auditoría sobre Snapshot Actualizado
Metodología: se contrastó línea por línea el nuevo snapshot financeai.md (05-ago-2026) contra la Nota Maestra v1 (30-jul-2026) y contra sí mismo entre módulos (backend, mock-api, motor de IA real, frontend). Los 13 hallazgos originales (AUD-01 a AUD-13) se re-verifican uno por uno; se agregan los hallazgos AUD-14 a AUD-21, detectados por primera vez en esta ronda.

### 6.1 Índice de Hallazgos (actualizado)
| ID | Severidad | Componente | Título | Estado en v2 |
| :--- | :--- | :--- | :--- | :--- |
| AUD-01 | Alta | Backend / Auth | AuthResponse: campos message/email invertidos | 🔴 Sin cambios |
| AUD-02 | Alta | Backend + Motor IA | Enum de perfil financiero inconsistente (RIESGO / EN_RIESGO / RIESGOSO) | 🔴 Sin cambios, ahora 3 variantes |
| AUD-03 | Alta | Backend + Frontend | Contrato de API desalineado (path y payload) | 🔴 Sin cambios, confirmado en runtime |
| AUD-04 | Media | Backend + Mock API | Campo transactions vs. transacciones | ✅ Resuelto |
| AUD-05 | Alta | Mock API | El endpoint /predict ignora el body recibido | 🟡 Superado en sustancia por AUD-15/16/17 |
| AUD-06 | Alta | Backend / Análisis | usuarioId nunca se asigna en HistorialAnalisisEntity | ✅ Resuelto |
| AUD-07 | Alta (seguridad) | Backend / Transactions | Sin autorización ni DTO propio: fuga de datos | ✅ Resuelto |
| AUD-08 | Alta (seguridad) | Backend / Historial | IDOR en historial de análisis | ✅ Resuelto |
| AUD-09 | Media | Backend / Config | application.yml casi vacío, sin plantilla | 🔴 Regresión: peor que en v1 |
| AUD-10 | Baja | Backend / Entity | UserEntity.apellido nunca se puebla | ✅ Resuelto |
| AUD-11 | Baja | Backend / Config | spring.jpa.open-in-view=false pendiente | 🔴 Sin cambios |
| AUD-12 | Media | Backend / DTO | TransactionRequest mal aprovechado | ✅ Resuelto |
| AUD-13 | Alta (seguridad) | Backend / Security | jwt.secret hardcodeado | 🔴 Sin cambios |
| AUD-14 | Alta — nuevo | Backend / Persistencia | Esquema de historial_analisis no coincide con la entidad JPA | 🔴 Nuevo |
| AUD-15 | Alta — nuevo | Infra / IAClient | Motor de IA real inalcanzable: host, puerto y ruta desalineados | 🔴 Nuevo |
| AUD-16 | Alta — nuevo | Backend + Data Science | Contrato de respuesta del motor real no coincide con AnalisisResponse | 🔴 Nuevo |
| AUD-17 | Media — nuevo | Data Science | Motor de IA duplicado, con salidas incompatibles entre sí | 🔴 Nuevo |
| AUD-18 | Media — nuevo | Frontend | dashboard.html no existe; scripts cargados en la página equivocada | 🔴 Nuevo |
| AUD-19 | Media — nuevo | Frontend + Backend | El registro no crea el perfil financiero, bloqueando el análisis | 🔴 Nuevo |
| AUD-20 | Baja — nuevo | Backend / Entity | Typo en enum Sexo (FEMININO) | 🔴 Nuevo |
| AUD-21 | Baja — nuevo | Backend / Config | Typo en pom.xml (<coniguration>) y versión de springdoc desactualizada en docs | 🔴 Nuevo |

*Balance de la re-auditoría: 6 hallazgos resueltos · 1 regresión · 5 sin cambios · 1 superado en sustancia · 8 nuevos. Total de hallazgos activos hoy: 15 de 21.*

### 6.2 Hallazgos Re-verificados de la v1 (AUD-01 a AUD-13)
*Se conserva el detalle original de cada hallazgo (ver Nota Maestra v1, Sección 6.2, para el texto completo de Hallazgo / Impacto / Acción recomendada); a continuación sólo se documenta el resultado de la re-verificación de cada uno contra el snapshot actualizado.*

#### AUD-01 — AuthResponse: campos invertidos — 🔴 Sin cambios
El record AuthResponse(String message, String email) sigue instanciándose como new AuthResponse(token, "mensaje...") en AuthServiceImpl. Con agravante nuevo: frontend/js/auth.js ya existe y hace localStorage.setItem('jwtToken', data.token) — un campo token que no existe en la respuesta real. El login/registro desde el navegador falla en silencio (localStorage guarda undefined) aunque el backend responda 200 OK.

#### AUD-02 — Enum de perfil financiero inconsistente — 🔴 Sin cambios, ahora con una tercera variante
Persisten los dos valores documentados en v1 (Java: RIESGO; mock-api antiguo: EN_RIESGO). Se detecta un tercer candidato: data-science/modeloFinanceAI/main.py compara perfil_str == "RIESGOSO", lo que sugiere que la clase real que produce el modelo entrenado es "RIESGOSO" — un tercer literal, distinto de los otros dos. dashboard.js, por su parte, ya contempla un cuarto: 'EN_RIESGO' para el color del badge. Antes de fijar el valor unificado hace falta inspeccionar model.classes_ del .pkl para saber cuál es la verdad de base.

#### AUD-03 — Contrato de API desalineado — 🔴 Sin cambios, confirmado en runtime
Ver detalle completo actualizado en la Sección 3. La novedad es que ya no es un desalineamiento teórico entre documentos: dashboard.js llama activamente a /transactions y /analisis, ninguno de los cuales existe en el backend real.

#### AUD-04 — Nombre del campo de transacciones — ✅ Resuelto
El componente del record AnalisisRequest ya se llama transacciones (antes transactions), coincidiendo con el campo requerido por el modelo Pydantic del mock. Sin acción adicional necesaria salvo si se retira el mock-api (ver AUD-17).

#### AUD-05 — El mock API ignora el body — 🟡 Superado en sustancia, pendiente de decisión formal
mock-api/app/routers/analisis.py no cambió: sigue sin declarar parámetro de request. Pero ya existe un motor de IA real (modelo-financeai) que sí procesa el body con un modelo entrenado. El hallazgo original queda parcialmente obsoleto: el problema ya no es “no hay lógica real” sino “hay que decidir si el mock se retira o se documenta como stub de desarrollo” (ver AUD-17, TASK-029).

#### AUD-06 — usuarioId nunca se asigna — ✅ Resuelto
AnalisisIAServiceImpl.guardarHistorial() ahora invoca .usuario(usuario) en el builder antes de guardar. El historial ya queda correctamente asociado al usuario autenticado. Nota: este arreglo expone un problema distinto y nuevo — AUD-14 — sobre el esquema de la tabla destino.

#### AUD-07 — TransactionController sin autorización ni DTO propio — ✅ Resuelto
Reescrito por completo: usa TransactionRequest (DTO, no la entidad JPA), separa las rutas administrativas (/usuario/{usuarioId}, protegidas con @PreAuthorize("hasRole('ADMIN')")) de las rutas del propio usuario (/usuario/transacciones, que derivan el usuario del @AuthenticationPrincipal). Ya no hay mass assignment ni fuga de datos entre cuentas. Es el arreglo de seguridad más significativo entre ambas versiones.

#### AUD-08 — IDOR en HistorialAnalisisController — ✅ Resuelto
Se agregó el endpoint /api/v1/analisis/usuario/historial, que deriva el usuario del token JWT vía @AuthenticationPrincipal. La ruta original por userId libre (/usuario/{userId}) se restringió con @PreAuthorize("hasRole('ADMIN')"). Ya no es posible leer el historial de otro usuario con un token válido propio.

#### AUD-09 — application.yml casi vacío — 🔴 Regresión: el archivo empeoró
Ver detalle completo en la Sección 4.2. En v1 el archivo al menos definía spring.application.name; hoy contiene contenido de docker-compose.yml pegado por error, sin ninguna propiedad válida de Spring Boot. Se reclasifica de Media a Alta por su impacto potencial en el arranque.

#### AUD-10 — UserEntity.apellido nunca se puebla — ✅ Resuelto
RegisterRequest ya incluye el campo apellido con validación @NotBlank, y AuthServiceImpl.register() lo asigna correctamente al construir la entidad.

#### AUD-11 — spring.jpa.open-in-view=false pendiente — 🔴 Sin cambios
Sigue sin aplicarse. No se puede verificar su efecto hasta resolver AUD-09, dado que hoy no hay un application.yml funcional donde colocarlo.

#### AUD-12 — TransactionRequest mal aprovechado — ✅ Resuelto
Como consecuencia directa del arreglo de AUD-07, TransactionRequest ya es el DTO real usado tanto en el alta de transacciones del usuario autenticado como en la ruta administrativa. Ya no hay ambigüedad sobre su propósito.

#### AUD-13 — jwt.secret con valor por defecto hardcodeado — 🔴 Sin cambios
JwtUtil conserva el mismo @Value("${jwt.secret:404E...}") con el fallback embebido y versionado en Git. Sigue siendo el hallazgo de seguridad abierto más severo del proyecto.

### 6.3 Hallazgos Nuevos (AUD-14 a AUD-21)

#### AUD-14 — Esquema de historial_analisis no coincide con la entidad JPA
**Severidad:** Alta   ·   **Componente:** V3__create_analysis_table.sql vs. HistorialAnalisisEntity
Hallazgo: la migración Flyway V3 define las columnas frecuencia_ahorro (VARCHAR) y nivel_endeudamiento (INTEGER). La entidad HistorialAnalisisEntity, en cambio, mapea @Column(name = "rango_ahorro") — una columna que la migración nunca creó — y define nivelEndeudamiento como BigDecimal(4,2), un tipo incompatible con INTEGER.
Impacto: cualquier intento de persistir un HistorialAnalisisEntity falla con un error SQL (columna inexistente / tipo incompatible), incluso después de que AUD-06 ya propaga correctamente el usuarioId. Este hallazgo bloquea en la práctica el mismo flujo que AUD-06 acababa de destrabar.
**Acción recomendada (TASK-026):** crear una migración Flyway V5 que renombre/ajuste frecuencia_ahorro → rango_ahorro (VARCHAR) y corrija el tipo de nivel_endeudamiento a NUMERIC(4,2), alineando el esquema real con lo que la entidad ya espera. No editar V3, que ya pudo haberse aplicado en ambientes existentes.

#### AUD-15 — Motor de IA real inalcanzable: host, puerto y ruta desalineados
**Severidad:** Alta   ·   **Componente:** docker-compose.yml, RestClientConfig, IAClient
Hallazgo: se detectan tres desalineaciones independientes, cualquiera de las cuales por sí sola ya rompe la integración: (a) IA_API_URL=http://mock-api:8000, pero el contenedor mock-api expone el puerto 8001, no 8000; (b) el servicio que sí escucha en el puerto 8000 es modelo-financeai, con otro nombre de host dentro de la red de Docker; (c) IAClient.analizar() llama siempre a .uri("/predict"), una ruta que existe en el mock-api antiguo pero no en el motor real, cuyo endpoint es POST /analisis-financiero.
Impacto: hoy, con la configuración actual, la llamada del backend al motor de IA fallará sin importar cuál de los dos servicios se pretenda usar, por al menos dos de las tres razones simultáneamente.
**Acción recomendada (TASK-027):** decidir explícitamente cuál motor es el canónico (recomendación: modelo-financeai, por ser el que tiene modelos entrenados reales), corregir IA_API_URL al host:puerto correcto de ese servicio, y actualizar la ruta en IAClient a /analisis-financiero.

#### AUD-16 — Contrato de respuesta del motor real no coincide con AnalisisResponse
**Severidad:** Alta   ·   **Componente:** AnalisisResponse (Java) vs. data-science/modeloFinanceAI/main.py
Hallazgo: incluso resolviendo AUD-15, el body de respuesta no es deserializable tal como está. El motor real devuelve perfilFinanciero y resumenGastos (camelCase, sin guion bajo), mientras que AnalisisResponse espera perfil_financiero y resumen_gastos vía @JsonProperty. Más grave aún: probabilidad y nivel_endeudamiento llegan como strings con formato porcentual (ej. "87.5%"), donde el DTO Java los tipa como BigDecimal — esto no es un desalineamiento de nombre, es un error de deserialización que Jackson no puede resolver solo.
Impacto: la llamada HTTP tendría éxito (200 OK), pero restClient.retrieve().body(AnalisisResponse.class) lanzará una excepción de conversión, resultando en un 500 genérico para el usuario final incluso con AUD-15 ya resuelto.
**Acción recomendada (TASK-028):** en el servicio de Data Science, emitir los campos con los nombres que el backend ya espera (o agregar @JsonAlias en el lado Java) y devolver probabilidad y nivel_endeudamiento como valores numéricos puros (sin el sufijo “%”), dejando el formato de presentación como responsabilidad del frontend.

#### AUD-17 — Motor de IA duplicado, con salidas incompatibles entre sí
**Severidad:** Media   ·   **Componente:** data-science/ (raíz) vs. data-science/modeloFinanceAI/
Hallazgo: existen dos copias casi idénticas del servicio de inferencia, cada una con su propio par de archivos .pkl. Ambas cargan los mismos modelos y calculan las mismas métricas, pero difieren en el formato de salida: la copia en la raíz devuelve nivel_endeudamiento y probabilidad como valores decimales puros; la copia en modeloFinanceAI/ (la que está conectada en docker-compose.yml) los devuelve como strings con “%”. Sólo esta última está referenciada en la infraestructura.
Impacto: riesgo de que un integrante del equipo edite la copia equivocada, o de que una futura re-auditoría compare AnalisisResponse contra el archivo que no está en producción. Genera confusión sobre cuál es la fuente de verdad del modelo.
**Acción recomendada (TASK-029):** eliminar la copia no referenciada (data-science/, raíz) o documentar explícitamente por qué se conserva (ej. como notebook de entrenamiento vs. servicio de inferencia), dejando una sola carpeta como canónica para servir el modelo.

#### AUD-18 — dashboard.html no existe; scripts cargados en la página equivocada
**Severidad:** Media   ·   **Componente:** frontend/index.html, auth.js, dashboard.js
Hallazgo: tras un login o registro exitoso, auth.js hace window.location.href = 'dashboard.html', un archivo que no existe en el repositorio (frontend/ sólo contiene index.html). Además, dashboard.js está cargado como <script> dentro de index.html —la propia pantalla de login— por lo que su lógica de “verificar sesión activa y cargar transacciones” se ejecuta sobre la pantalla de login antes de que exista una sesión, no sobre un dashboard real.
Impacto: incluso si AUD-01 se resolviera y el login funcionara correctamente, el usuario llegaría a una página en blanco (error 404 del navegador) en vez de a un dashboard.
**Acción recomendada (TASK-030):** crear frontend/dashboard.html como una página separada de index.html, mover ahí la carga de dashboard.js y api.js, y dejar en index.html únicamente auth.js.

#### AUD-19 — El registro no crea el perfil financiero, bloqueando el análisis
**Severidad:** Media   ·   **Componente:** frontend/js/auth.js, RegisterRequest, PerfilFinancieroController
Hallazgo: el formulario de registro en index.html sigue capturando y enviando ingresoMensual, lineaCredito y empleoFormal dentro del payload de POST /api/v1/auth/register. Pero esos campos ya no forman parte de RegisterRequest (se movieron a un endpoint independiente, POST /api/v1/perfil, agregado en esta misma iteración del backend). Jackson ignora en silencio los campos desconocidos: el registro se completa “con éxito” pero el perfil financiero nunca se crea, y el frontend nunca llama al endpoint nuevo.
Impacto: AnalisisIAServiceImpl.analizarPorUsuarioId() exige que exista un perfil financiero y lanza ResourceNotFoundException si no lo encuentra. Todo usuario registrado desde el frontend actual queda, sin saberlo, incapacitado para generar un análisis.
**Acción recomendada (TASK-031):** tras un registro exitoso, encadenar automáticamente una llamada a POST /api/v1/perfil con los datos ya capturados en el mismo formulario, antes de redirigir al dashboard.

#### AUD-20 — Typo en el enum Sexo (FEMININO)
**Severidad:** Baja   ·   **Componente:** entity/enums/Sexo.java
Hallazgo: el enum define FEMININO en lugar de FEMENINO. Bajo impacto funcional directo (el valor se usa de forma consistente en todo el backend), pero si el modelo de Data Science fue entrenado con la categoría “femenino” escrita correctamente, este valor cae fuera de vocabulario para cualquier encoder categórico que dependa del texto exacto.
**Acción recomendada (TASK-032):** corregir el nombre del enum a FEMENINO, coordinando con el equipo de Data Science para confirmar que no rompe el encoding usado al entrenar los modelos .pkl.

#### AUD-21 — Typo en pom.xml y versión de springdoc desactualizada en la documentación
**Severidad:** Baja   ·   **Componente:** backend/pom.xml
Hallazgo: el bloque de configuración del maven-compiler-plugin está escrito como <coniguration> (falta la primera “f”) en ambas aperturas y cierres. Es probable que Maven ignore silenciosamente ese bloque completo, dependiendo en cambio del procesamiento por defecto de anotaciones para Lombok. Adicionalmente, la Nota Maestra v1 documentaba springdoc-openapi 2.6.0, mientras que el pom.xml real ya fija 3.0.3 (corregido en la Sección 5 de esta v2).
**Acción recomendada (TASK-033):** corregir el typo a <configuration> y verificar con mvn clean package que Lombok se siga procesando correctamente; no se requiere acción adicional sobre la versión de springdoc, ya documentada.

---

## 7. Estado Real por Vertical Slice (actualizado)
Los estados siguientes corrigen el estatus reportado en la v1, cruzándolo contra el código real y los hallazgos actualizados de la Sección 6.

### 7.1 Slice 1 — Autenticación (Auth)
| Capa | Estado real | Nota |
| :--- | :--- | :--- |
| Backend: Seguridad base (SecurityFilterChain, JwtUtil, JwtAuthFilter) | ✅ Completo | Sin cambios respecto a v1. |
| Backend: Endpoints register / login | 🟡 Funcional con deuda | Bloqueado semánticamente por AUD-01 (sin cambios) y con riesgo de seguridad por AUD-13 (sin cambios). |
| Backend: Validación de funcionalidad (tests) | 🔴 Pendiente | Sin cambios (TASK-013). |
| Frontend: Login / Registro | 🟡 Existe, pero no funcional end-to-end | Novedad v2: index.html + auth.js ya implementan el flujo completo de UI, pero rompen en tres puntos: AUD-01 (token mal nombrado), AUD-18 (dashboard.html inexistente) y AUD-19 (perfil financiero nunca se crea). |

### 7.2 Slice 2 — Gestión de Transacciones
| Capa | Estado real | Nota |
| :--- | :--- | :--- |
| Backend: Persistencia (entidad, repositorio, migración) | ✅ Completo | Sin cambios. |
| Backend: Controlador y lógica de negocio | ✅ Reescrito y seguro | AUD-07 resuelto: ya no es una vulnerabilidad activa. Separación correcta entre rutas admin y rutas del usuario autenticado, con DTO propio. |
| Frontend: Dashboard de transacciones | 🔴 No funcional end-to-end | dashboard.js llama a /transactions con payload { descripcion, valor }; el backend real espera /api/v1/transacciones/usuario/transacciones con { nombre_comercio, monto_transaccion, medio_pago } (AUD-03). Además depende de una página que no existe (AUD-18). |

### 7.3 Slice 3 — Análisis Financiero e IA
| Capa | Estado real | Nota |
| :--- | :--- | :--- |
| Backend: DTOs, cliente REST, entidad de historial | 🟡 Completo estructuralmente, con un bug nuevo | AUD-02 (enum, sin cambios) y AUD-06 (resuelto) pero AUD-14 (nuevo) bloquea la persistencia del historial. |
| Backend: Integración de negocio real (perfil financiero requerido) | 🟡 Implementado, pero sin insumos del frontend | Novedad v2: el módulo de Perfil Financiero (entidad/servicio/controller) ya existe y funciona, pero el frontend nunca lo alimenta (AUD-19). |
| Motor de IA | 🟡 De 0% a modelo entrenado real, pero desconectado | Salto más grande del proyecto desde la v1: existen dos modelos .pkl funcionales en aislamiento (AUD-17), pero el backend no puede alcanzarlos por errores de configuración (AUD-15) y de contrato (AUD-16). |
| Frontend: Vista de diagnóstico e historial | 🔴 No existe en el repositorio | Sin cambios respecto a v1. |

### 7.4 Camino Crítico para una Demo End-to-End (nuevo análisis)
Para que un usuario pueda completar el flujo registrarse → iniciar sesión → ver su dashboard → cargar una transacción → pedir un análisis → ver una recomendación en pantalla, existen hoy seis bloqueos secuenciales. Cada uno oculta al siguiente hasta que se resuelve, por lo que no alcanza con arreglar uno o dos ítems aislados para tener una demo funcional:
* **1. AUD-01** — sin esto no hay token utilizable en el cliente; no se puede ni completar un login.
* **2. AUD-18** — sin dashboard.html, no hay a dónde navegar después del login.
* **3. AUD-19** — sin perfil financiero cargado, el análisis no puede ejecutarse más adelante.
* **4. AUD-03** — el dashboard llama URLs y payloads que no existen en el backend real.
* **5. AUD-15 + AUD-16** — aunque todo lo anterior se resuelva, la llamada al motor de IA falla por host/puerto/ruta y por incompatibilidad de formato de respuesta.
* **6. AUD-14** — incluso si el motor de IA respondiera correctamente, guardar el resultado en historial_analisis falla por el desalineamiento de esquema.

Recomendación de secuencia: los ítems 1, 2 y 3 son requisitos de UI/flujo y no dependen de infraestructura — pueden resolverse en paralelo por el equipo de frontend. Los ítems 5 y 6 requieren coordinación entre Backend y Data Science para acordar el contrato de datos. El ítem 4 es el más costoso en tiempo (reescritura de dashboard.js) y conviene abordarlo después de fijar el contrato definitivo en la Sección 3.1, para no reescribirlo dos veces.

---

## 8. Hoja de Ruta / Cronograma Ágil (Actualizado v2)
Estatus general (05 de agosto de 2026): el equipo avanzó sustancialmente en seguridad (Slice 2) y en el motor de IA (Slice 3, antes 0%), pero el Sprint de Estabilización propuesto en la v1 quedó parcialmente ejecutado: 4 de los 8 hallazgos de severidad Alta originales siguen abiertos, y se sumaron 3 hallazgos Alta nuevos. Se recomienda un segundo Sprint de Estabilización antes de continuar con features nuevas.

### Sprint de Estabilización v2 (bloqueante, antes de nuevas features)
Objetivo: cerrar los hallazgos de severidad Alta que siguen abiertos o son nuevos (AUD-01, 02, 03, 09, 13, 14, 15, 16) antes de intentar una demo end-to-end. Corresponde al grupo P0/P1 del backlog (Sección 10).
* ✅ Corregir AuthResponse (AUD-01 / TASK-001) — arrastrado de la v1, sigue sin resolver.
* ✅ Restaurar application.yml real (AUD-09 / TASK-010) — regresión, prioridad alta por su bajo costo.
* ✅ Eliminar jwt.secret hardcodeado (AUD-13 / TASK-009) — arrastrado de la v1.
* ✅ Crear dashboard.html y reordenar scripts (AUD-18 / TASK-030) — nuevo, bloquea toda navegación post-login.
* ✅ Nueva migración V5 para alinear historial_analisis (AUD-14 / TASK-026) — nuevo, bloquea la persistencia de análisis.
* 🔴 Alinear host/puerto/ruta del motor de IA (AUD-15 / TASK-027) — nuevo.
* 🔴 Alinear contrato de respuesta del motor de IA (AUD-16 / TASK-028) — nuevo.
* 🔴 Unificar el valor del enum de perfil de riesgo (AUD-02 / TASK-002) — arrastrado, requiere inspeccionar el modelo .pkl.
* 🔴 Fijar contrato único de API y conectar el flujo de perfil financiero (AUD-03 + AUD-19 / TASK-003 + TASK-031).

### ✅ Progreso desde la v1 (ya no requiere trabajo adicional)
* Rediseño seguro de TransactionController con DTO y autorización (AUD-07).
* Corrección del IDOR en HistorialAnalisisController (AUD-08).
* Propagación de usuarioId al guardar historial (AUD-06).
* Unificación del nombre de campo transacciones (AUD-04).
* Población de UserEntity.apellido (AUD-10).
* Aprovechamiento correcto de TransactionRequest como DTO (AUD-12).
* Existencia de un motor de IA real con modelos entrenados (antes 0% de integración con OCI/Data Science).
* Existencia de una pantalla de login/registro funcional en el frontend (antes “no existe en el repositorio”).

### Semanas 2 a 5 (sin cambios de fondo respecto a la v1)
El resto del cronograma original (Semana 2: Core Bancario, Semana 3: Motor de IA, Semana 4: Refinamiento, Semana 5: Despliegue OCI) se mantiene vigente en su estructura. Ver Sección 10 para el detalle de tareas actualizado con los nuevos identificadores TASK-026 a TASK-033.

---

## 9. Convenciones y Definition of Done
### 9.1 Convención de Contratos y Nombres (decisión pendiente del equipo)
Sin cambios respecto a la v1: conviven records de Java 21 y clases Lombok @Data sin una regla explícita. Se agrega a esta versión que los DTOs nuevos del módulo de Perfil Financiero (PerfilFinancieroRequest, PerfilFinancieroResponse) ya siguen el patrón recomendado (records inmutables), lo cual es una buena señal de consistencia hacia adelante. Se mantiene la recomendación: usar records para DTOs inmutables sin lógica adicional, reservando @Data sólo si se necesita mutabilidad real.

### 9.2 Checklist — Definition of Done
* El endpoint/feature respeta el contrato de API vigente (Sección 3) y no introduce un nuevo casing o path ad-hoc.
* Toda entrada de usuario pasa por un DTO validado con Jakarta Validation — nunca se expone una @Entity directamente en un @RequestBody o @ResponseBody.
* Toda consulta o mutación de datos sensibles filtra explícitamente por el usuario autenticado extraído del SecurityContext.
* No se introducen nuevos valores por defecto de secretos/credenciales en el código fuente (ver AUD-13, todavía abierto).
* Se agregó o actualizó al menos un test (JUnit/Mockito) que cubra el camino feliz y un camino de error relevante.
* Los cambios en el esquema de base de datos se realizan mediante una nueva migración Flyway (nunca editando una migración ya aplicada) — ver AUD-14 como ejemplo concreto de por qué esta regla importa.
* Swagger/OpenAPI (springdoc) refleja el endpoint nuevo o modificado con sus @Schema y ejemplos.
* Si la tarea cierra un hallazgo AUD-XX, se marca como resuelto en la Sección 6 al actualizar este documento.

### 9.3 Convención de Ramas y Commits
Sin cambios respecto a la v1: feature/slice-{n}-{descripcion-corta} · fix/AUD-{nn}-{descripcion-corta} · Conventional Commits (feat:, fix:, chore:, docs:, test:, refactor:) con referencia al TASK-XXX o AUD-XX en el cuerpo del commit.

### 9.4 Testing Mínimo Esperado
Sin cambios respecto a la v1. Se resalta que, a pesar del progreso funcional entre versiones, no se detectaron tests nuevos en el snapshot actualizado — TASK-013 sigue plenamente vigente y su ausencia es la razón por la cual varios de los hallazgos de esta sección se hubieran detectado antes con una suite mínima (por ejemplo, AUD-14 se habría detectado con un solo test de integración que persista un historial de análisis).

---

## 10. Backlog Priorizado — Próximas Tareas (actualizado v2)
Se conservan los identificadores TASK-001 a TASK-025 de la v1 (con su estado actualizado); las tareas nuevas de esta re-auditoría continúan la numeración desde TASK-026.

### P0 — Sprint de Estabilización v2 (bloqueante, antes de continuar)
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-001 | ✅ Corregir campos de AuthResponse (token / message) | AUD-01 | El JSON de login/registro expone un campo token explícito y uno message descriptivo, sin datos cruzados. |
| TASK-009 | ✅ Externalizar jwt.secret y eliminar el fallback hardcodeado | AUD-13 | La aplicación falla rápido si jwt.secret no está definido en el entorno. |
| TASK-010 | ✅ Restaurar application.yml del backend (revertir el contenido de docker-compose pegado por error) | AUD-09 | El backend arranca localmente usando application.yml + variables de entorno, sin depender de una plantilla accidental. |
| TASK-026 | ✅ Migración V5: alinear esquema de historial_analisis con la entidad JPA | AUD-14 | Un análisis se persiste sin error SQL; rango_ahorro y nivel_endeudamiento tienen el tipo y nombre correctos en BD. |
| TASK-030 | ✅ Crear dashboard.html real y reordenar la carga de scripts | AUD-18 | Tras un login exitoso, el usuario llega a una página real que carga dashboard.js y api.js correctamente. |

### P1 — Conectar el Motor de IA Real
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-002 | 🔴 Unificar valores del enum de perfil de riesgo (inspeccionar model.classes_) | AUD-02 | AnalisisIAServiceImpl no lanza excepción ante ninguna respuesta válida del motor real; cubierto por test unitario. |
| TASK-027 | 🔴 Alinear host, puerto y ruta del motor de IA en docker-compose e IAClient | AUD-15 | El backend puede invocar exitosamente al motor de IA elegido como canónico, en local y en docker-compose. |
| TASK-028 | 🔴 Alinear contrato de respuesta del motor de IA con AnalisisResponse | AUD-16 | AnalisisResponse se deserializa sin error a partir de la respuesta real del motor (nombres de campo y tipos numéricos correctos). |
| TASK-031 | 🔴 Conectar la creación de perfil financiero al flujo de registro del frontend | AUD-19 | Tras registrarse desde el navegador, el usuario tiene un perfil financiero persistido y puede solicitar un análisis sin error 404. |

### P2 — Integración Frontend ↔ Backend Restante
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-003 | 🔴 Definir y documentar el contrato único de API (path + casing + payload de transacciones) | AUD-03 | dashboard.js puede llamar exitosamente al backend real sin transformar el payload a mano. |
| TASK-018 | 🔴 Dashboard de transacciones funcional conectado al backend real | Slice 2 (depende de TASK-003) | El formulario crea transacciones visibles de inmediato en la tabla, usando el token de sesión y el payload correcto. |
| TASK-029 | 🔴 Retirar o documentar formalmente el motor de IA duplicado / mock-api obsoleto | AUD-17 + AUD-05 | Sólo queda un servicio de inferencia canónico referenciado en docker-compose y documentado como tal. |
| TASK-016 | 🟡 Completar paginación en el listado de transacciones (el filtrado por usuario ya está resuelto) | Slice 2 | GET soporta parámetros de página/tamaño además del filtrado por usuario autenticado ya existente. |
| TASK-021 | 🔴 Vista de historial de diagnósticos en el frontend | Slice 3 | El usuario puede ver sus análisis previos ordenados por fecha, una vez AUD-14 esté resuelto. |

### P3 — Deuda Técnica y Limpieza
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-011 | 🔴 Aplicar spring.jpa.open-in-view=false | AUD-11 | Aplicable recién después de TASK-010 (application.yml restaurado); los tests de integración siguen pasando. |
| TASK-013 | 🔴 Suite de pruebas de integración de Auth (colección versionada en el repo) | Slice 1 | Registro exitoso, registro duplicado (400), login exitoso y login inválido (401) cubiertos y documentados. |
| TASK-020 | 🟡 Completar resiliencia ante caída del motor de IA (ya existe el manejo de ResourceAccessException → 503) | Slice 3 | Se agregan tests que cubran explícitamente el escenario de caída del servicio de IA. |
| TASK-032 | ✅ Corregir typo Sexo.FEMININO → FEMENINO | AUD-20 | El enum usa la ortografía correcta; se confirma con Data Science que no rompe el encoding del modelo entrenado. |
| TASK-033 | ✅ Corregir <coniguration> en pom.xml | AUD-21 | mvn clean package procesa Lombok correctamente con el bloque de configuración corregido. |

### P4 — Infraestructura y Cierre (Semana 5, sin cambios de fondo)
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-023 | 🔴 docker-compose.prod.yml con backend, motor de IA real y PostgreSQL contenerizados | Semana 5 | docker compose -f docker-compose.prod.yml up levanta el stack completo end-to-end. |
| TASK-024 | 🔴 Despliegue en OCI (Compute u Object Storage) | Semana 5 | La API es accesible públicamente vía HTTPS y documentada con la URL final. |
| TASK-025 | 🔴 QA end-to-end + revisión final de esta nota maestra | Semana 5 | Los hallazgos AUD-01 a AUD-21 están cerrados o explícitamente diferidos con justificación. |
**Tareas ya completadas (sin acción pendiente)**
*TASK-004 (AUD-04), TASK-006 (AUD-06), TASK-007 (AUD-07), TASK-008 (AUD-08), TASK-012 (AUD-10), TASK-015 (alta de transacciones con userId desde JWT), TASK-017 (validaciones Jakarta sobre TransactionRequest) y TASK-019 (endpoint disparador reutilizando transacciones persistidas) ya están resueltas en el snapshot actual y no requieren trabajo adicional salvo verificación en QA final (TASK-025).*

---

## 11. Anexo: Prompts Guía para Sesiones de IA
Bloques de prompt listos para copiar y pegar al iniciar una conversación con un asistente de IA, adjuntando o pegando esta nota maestra v2 como contexto.

### Prompt base (inicio de cualquier sesión)
*Actúa como Tech Lead senior de Java 21 / Spring Boot 4.1.0 del proyecto FinanceAI. Te comparto la Nota Maestra v2 del proyecto (documento adjunto). Antes de proponer código, confirma en qué archivo(s) reales del repositorio (Sección 2) impacta el cambio, y respeta las convenciones de la Sección 9 (Definition of Done).*

### Prompt para una tarea puntual del backlog
*Con base en TASK-0XX de la Sección 10 de la Nota Maestra v2 de FinanceAI, redacta la historia de usuario en formato Gherkin (Given/When/Then) y la lista de archivos a modificar o crear, siguiendo la arquitectura descrita en la Sección 2.3.*

### Prompt para planificación de sprint
*Tomando el Backlog Priorizado (Sección 10) y el Camino Crítico para Demo (Sección 7.4) de la Nota Maestra v2 de FinanceAI, arma el plan del próximo sprint de 1 semana. Respeta que el grupo P0 debe cerrarse antes de continuar con features nuevas de los grupos P1 en adelante.*

### Prompt para la próxima re-auditoría
*Actúa como auditor técnico senior. Te comparto la Nota Maestra v2 de FinanceAI y un nuevo snapshot Repomix del código real. Contrasta línea por línea el estado de los hallazgos AUD-01 a AUD-21 contra el nuevo snapshot, marca cuáles se resolvieron, cuáles siguen abiertos y cuáles son regresiones, y detecta hallazgos nuevos continuando la numeración desde AUD-22.*
</file>

<file path="README.md">
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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/HistorialAnalisisController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.service.HistorialAnalisisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analisis")
@RequiredArgsConstructor
@Tag(
        name = "Historial Resultado Analisis",
        description = "Listado de historiales realizados de un usuario"
)
public class HistorialAnalisisController {
    private final HistorialAnalisisService historialAnalisisService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuario/{userId}")
    public List<HistorialAnalisisResponse> obtenerHistorialPorId(@PathVariable Long userId) {
        return historialAnalisisService.obtenerHistorialPorId(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<HistorialAnalisisResponse> obtenerHistorial() {
        return historialAnalisisService.obtenerHistorial();
    }

    @GetMapping("/usuario/historial")
    public List<HistorialAnalisisResponse> obtenerMiHistorial(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return historialAnalisisService.obtenerHistorialAutenticado(userDetails.getUsername());
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/TestSecurityController.java">
package com.nocountry.financeai.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(
        name = "Test",
        description = "Prebas preeliminares del sistema "
)
public class TestSecurityController {

    // Ruta pública (dentro de /auth/**)
    @GetMapping("/auth/ping")
    public ResponseEntity<String> publicPing() {
        return ResponseEntity.ok("Ruta pública OK - Acceso permitido sin token");
    }

    // Ruta protegida
    @GetMapping("/test/protected")
    public ResponseEntity<String> protectedPing() {
        return ResponseEntity.ok("Ruta protegida OK - Requiere token JWT válido");
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/AuthResponse.java">
package com.nocountry.financeai.dto.response;

public record AuthResponse(
        String token,
        String message
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/EstadoCivil.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoCivil {
    SOLTERO("SOLTERO"),
    CASADO("CASADO"),
    DIVORCIADO("DIVORCIADO"),
    VIUDO("VIUDO");

    private final String valor;

    EstadoCivil(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static EstadoCivil fromValor(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        String normalized = valor.trim().toUpperCase();
        for (EstadoCivil ec : EstadoCivil.values()) {
            if (ec.name().equalsIgnoreCase(normalized) || ec.valor.equalsIgnoreCase(normalized)) {
                return ec;
            }
        }
        // Fallback flexible para evitar errores 400 por tildes o variaciones
        if (normalized.contains("SOLTERO")) return SOLTERO;
        if (normalized.contains("CASADO")) return CASADO;
        if (normalized.contains("DIVORCIADO")) return DIVORCIADO;
        if (normalized.contains("VIUDO")) return VIUDO;

        throw new IllegalArgumentException("Valor no aceptado para EstadoCivil: " + valor);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/RangoAhorro.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        description = "Rango de ahorro del usuario",
        example = "Alta"
)
public enum RangoAhorro {
    ALTA,
    MEDIA,
    BAJA,
    NINGUNA;

    @JsonCreator
    public static RangoAhorro forString(String value) {
        return RangoAhorro.valueOf(value.trim().toUpperCase());
    }

    @JsonValue
    public String toValue(){
        return this.name().toLowerCase();
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/Sexo.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {
    MASCULINO("M"),
    FEMENINO("F"); // Corregido el typo AUD-20 (antes FEMININO)

    private final String codigo;

    Sexo(String codigo) {
        this.codigo = codigo;
    }

    // @JsonValue indica que al convertir este Enum a JSON,
    // se debe usar el valor de este metodo ("M" o "F")
    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    // @JsonCreator intercepta el JSON entrante y lo convierte al Enum correcto
    @JsonCreator
    public static Sexo fromCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Sexo sexo : Sexo.values()) {
            if (sexo.codigo.equalsIgnoreCase(codigo.trim())) {
                return sexo;
            }
        }
        throw new IllegalArgumentException("Valor no aceptado para Sexo. Se esperaba 'M' o 'F', pero se recibió: " + codigo);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/HistorialAnalisisEntity.java">
package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.RangoAhorro;
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
@ToString
public class HistorialAnalisisEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_financiero", nullable = false)
    private PerfilFinanciero perfilFinanciero;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal probabilidad;

    @Column(name = "nivel_endeudamiento", nullable = false, precision = 4, scale = 2)
    private BigDecimal nivelEndeudamiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "rango_ahorro", nullable = false, length = 20)
    private RangoAhorro rangoAhorro;

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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/TransactionEntity.java">
package com.nocountry.financeai.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;


@Entity
@Table(name = "transacciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String tipo;

    @Column(length = 50)
    private String categoria;

    @Column(name = "nombre_comercio", nullable = false, length = 255)
    private String nombreComercio;

    @Column(name ="monto_transaccion", nullable = false)
    private BigDecimal montoTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "medio_pago", nullable = false, length = 20)
    private MedioPago medioPago;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/HistorialAnalisisServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.HistorialAnalisisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialAnalisisServiceImpl implements HistorialAnalisisService {
    private final HistorialAnalisisRepository historialAnalisisRepository;
    private final UserRepository userRepository;
    @Override
    public List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id) {
        return historialAnalisisRepository.findByUsuarioId(id)
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public List<HistorialAnalisisResponse> obtenerHistorial() {

        return historialAnalisisRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();

    }

    @Override
    public List<HistorialAnalisisResponse> obtenerHistorialAutenticado(String email) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return historialAnalisisRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    public HistorialAnalisisResponse convertirRespuesta(HistorialAnalisisEntity historial) {
        return new HistorialAnalisisResponse(
                historial.getPerfilFinanciero(),
                historial.getProbabilidad(),
                historial.getNivelEndeudamiento(),
                historial.getRangoAhorro(),
                historial.getResumenGastos(),
                historial.getRecomendaciones()
        );
    }
}
</file>

<file path="data-science/modeloFinanceAI/main.py">
from fastapi import FastAPI, HTTPException, status
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field
from typing import List, Dict
from contextlib import asynccontextmanager
import pandas as pd
import numpy as np
import joblib
import sklearn
import sklearn.compose._column_transformer

# ==============================================================================
# 1. PARCHE DE COMPATIBILIDAD SKLEARN
# ==============================================================================
if not hasattr(sklearn.compose._column_transformer, '_RemainderColsList'):
    class _RemainderColsList(list):
        pass
    sklearn.compose._column_transformer._RemainderColsList = _RemainderColsList

# ==============================================================================
# 2. CARGA SEGURA DE MODELOS (LIFESPAN)
# ==============================================================================
modelos = {}

@asynccontextmanager
async def lifespan(app: FastAPI):
    # Proceso de arranque (Startup)
    try:
        modelos['transacciones'] = joblib.load('modelo_clasificacion_transacciones.pkl')
        modelos['perfil'] = joblib.load('modelo_perfil_financiero.pkl')
        print("✅ [PROD] Modelos ML cargados exitosamente.")
    except Exception as e:
        print(f"❌ [ERROR CRÍTICO] Fallo al cargar modelos .pkl: {e}")
        raise RuntimeError(f"No se pudieron cargar los modelos en producción: {e}")
    yield
    # Proceso de apagado (Shutdown)
    modelos.clear()

# ==============================================================================
# 3. CREAR LA APLICACIÓN FASTAPI
# ==============================================================================
app = FastAPI(
    title="API Analítica Financiera",
    version="1.0.0",
    lifespan=lifespan
)

# Configuración de CORS para producción / Oracle Cloud
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # En prod estricto, reemplaza "*" por la IP/Dominio de tu Frontend
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# ==============================================================================
# 4. ESTRUCTURA DE DATOS DE ENTRADA (Pydantic Models)
# ==============================================================================
class TransaccionInput(BaseModel):
    nombre_comercio: str = Field(
        ..., 
        example="Uber", 
        description="Nombre del establecimiento o comercio"
    )
    monto_transaccion: float = Field(
        ..., 
        gt=0, 
        example=250.0, 
        description="Monto de la transacción (debe ser mayor a 0)"
    )
    medio_pago: str = Field(
        ..., 
        example="credito", 
        description="Medios aceptados: credito, debito, transaccion, efectivo"
    )

class EntradaUsuario(BaseModel):
    edad: int = Field(..., ge=18, le=120)
    sexo: str
    estado_civil: str
    numero_hijos: int = Field(..., ge=0)
    empleo_formal: int = Field(..., ge=0, le=1)
    ingreso_mensual: float = Field(..., ge=0)
    linea_credito: float = Field(..., ge=0)
    transacciones: List[TransaccionInput] = []

# ==============================================================================
# 5. ENDPOINTS DE PRODUCCIÓN
# ==============================================================================

@app.get("/health", status_code=status.HTTP_200_OK)
def health_check():
    """Endpoint para que Oracle Cloud / Docker verifique si la API está viva"""
    if 'transacciones' not in modelos or 'perfil' not in modelos:
        raise HTTPException(status_code=500, detail="Modelos no inicializados")
    return {"status": "ok", "models_loaded": True}

@app.post("/analisis-financiero")
def analizar_usuario(datos: EntradaUsuario):
    try:
        modelo_perfil = modelos.get('perfil')
        modelo_transacciones = modelos.get('transacciones')

        # ----------------------------------------------------------------------
        # A) CÁLCULO DE GASTOS Y MÉTRICAS FINANCIERAS
        # ----------------------------------------------------------------------
        gasto_total = 0.0
        if datos.transacciones:
            gasto_total = sum([float(tx.monto_transaccion) for tx in datos.transacciones])

        # 1. Nivel de Endeudamiento (escala float 0.0 a 1.0)
        denom_endeudamiento = datos.ingreso_mensual + datos.linea_credito
        if denom_endeudamiento > 0:
            nivel_endeudamiento = round(float(gasto_total / denom_endeudamiento), 2)
        else:
            nivel_endeudamiento = 0.0

        # 2. Rango de Ahorro (String)
        if datos.ingreso_mensual > 0:
            ahorro_bruto = max(datos.ingreso_mensual - gasto_total, 0.0)
            pct_ahorro = ahorro_bruto / datos.ingreso_mensual
        else:
            pct_ahorro = 0.0

        if pct_ahorro >= 0.40:
            rango_ahorro_str = "Alta"
        elif pct_ahorro >= 0.20:
            rango_ahorro_str = "Media"
        elif pct_ahorro > 0:
            rango_ahorro_str = "Baja"
        else:
            rango_ahorro_str = "Ninguna"

# ----------------------------------------------------------------------
        # B) PREDICCIÓN CON MODELO DE PERFIL (.pkl)
        # ----------------------------------------------------------------------
        df_cliente = pd.DataFrame([{
            'edad': int(datos.edad),
            'sexo': str(datos.sexo).lower().strip(),
            'estado_civil': str(datos.estado_civil).lower().strip(),
            'numero_hijos': int(datos.numero_hijos),
            'empleo_formal': int(datos.empleo_formal),
            'ingreso_mensual': float(datos.ingreso_mensual),
            'linea_credito': float(datos.linea_credito),
            'nivel_endeudamiento': float(nivel_endeudamiento),
            'rango_ahorro': float(pct_ahorro)  # Valor decimal menor a 1
        }])

        perfil_pred = modelo_perfil.predict(df_cliente)[0]
        perfil_str = str(perfil_pred).upper().replace(" ", "_")

        # Inicializamos la probabilidad por defecto por seguridad
        probabilidad = 0.85
        try:
            if hasattr(modelo_perfil, "predict_proba"):
                probs = modelo_perfil.predict_proba(df_cliente)[0]
                probabilidad = round(float(np.max(probs)), 2)
        except Exception:
            probabilidad = 0.85

        # ----------------------------------------------------------------------
        # C) CLASIFICACIÓN NLP DE TRANSACCIONES
        # ----------------------------------------------------------------------
        resumen_gastos: Dict[str, float] = {}
        
        if datos.transacciones and len(datos.transacciones) > 0:
            df_tx = pd.DataFrame([
                {
                    'nombre_comercio': str(t.nombre_comercio).lower().strip(),
                    'monto_transaccion': float(t.monto_transaccion)
                }
                for t in datos.transacciones
            ])
            
            # Evaluación defensiva de probabilidades o predicción directa
            try:
                probs_matriz = modelo_transacciones.predict_proba(df_tx)
                clases = modelo_transacciones.classes_
                categorias_finales = []

                for probs in probs_matriz:
                    prob_max = float(np.max(probs))
                    idx_max = int(np.argmax(probs))
                    
                    # Umbral de confianza al 60%
                    if prob_max <= 0.60:
                        categorias_finales.append("otros servicios")
                    else:
                        categorias_finales.append(str(clases[idx_max]))
                
                df_tx['categoria'] = categorias_finales
            except Exception:
                # Si el modelo no soporta predict_proba, realiza la predicción directa
                preds = modelo_transacciones.predict(df_tx)
                df_tx['categoria'] = [str(p) for p in preds]
            
            # Agrupar montos por categoría
            agrupar = df_tx.groupby('categoria')['monto_transaccion'].sum().to_dict()
            resumen_gastos = {str(k).lower(): round(float(v), 2) for k, v in agrupar.items()}

        # ----------------------------------------------------------------------
        # D) GENERACIÓN DE RECOMENDACIONES
        # ----------------------------------------------------------------------
        recomendaciones = []

        if perfil_str == "RIESGOSO" and datos.linea_credito > datos.ingreso_mensual:
            recomendaciones.append(
                "Para aumentar el score del perfil financiero, se recomienda reducir el gasto o incrementar el ingreso mensual"
            )

        if "entretenimiento" in resumen_gastos and resumen_gastos["entretenimiento"] > (datos.ingreso_mensual * 0.15):
            recomendaciones.append("Monitorear los gastos recurrentes de entretenimiento.")

        if nivel_endeudamiento > 0.50:
            recomendaciones.append("Reducir las gastos para bajar el nivel de endeudamiento.")

        if not recomendaciones:
            recomendaciones.append("Mantener los hábitos de gasto actuales y continuar monitoreando el presupuesto.")

        # ----------------------------------------------------------------------
        # E) SALIDA EN FORMATO ESTRICTO
        # ----------------------------------------------------------------------
        return {
            "perfil_financiero": perfil_str,
            "probabilidad": probabilidad,
            "nivel_endeudamiento": nivel_endeudamiento,
            "rango_ahorro": rango_ahorro_str,
            "resumen_gastos": resumen_gastos,
            "recomendaciones": recomendaciones
        }

    except Exception as e:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=f"Error interno en la inferencia del modelo: {str(e)}"
        )

####http://localhost:8000/docs####
</file>

<file path="data-science/modeloFinanceAI/requirements.txt">
fastapi
uvicorn
pandas
scikit-learn==1.6.1
joblib
pydantic
</file>

<file path="mock-api/Dockerfile">
FROM python:3.13-slim

WORKDIR /app

COPY . .

RUN pip install --no-cache-dir fastapi uvicorn

EXPOSE 8001

CMD ["uvicorn","app.main:app","--host","0.0.0.0","--port","8001"]
</file>

<file path="financeai.md">
This file is a merged representation of the entire codebase, combined into a single document by Repomix.

<file_summary>
This section contains a summary of this file.

<purpose>
This file contains a packed representation of the entire repository's contents.
It is designed to be easily consumable by AI systems for analysis, code review,
or other automated processes.
</purpose>

<file_format>
The content is organized as follows:
1. This summary section
2. Repository information
3. Directory structure
4. Repository files (if enabled)
5. Multiple file entries, each consisting of:
  - File path as an attribute
  - Full contents of the file
</file_format>

<usage_guidelines>
- This file should be treated as read-only. Any changes should be made to the
  original repository files, not this packed version.
- When processing this file, use the file path to distinguish
  between different files in the repository.
- Be aware that this file may contain sensitive information. Handle it with
  the same level of security as you would the original repository.
</usage_guidelines>

<notes>
- Some files may have been excluded based on .gitignore rules and Repomix's configuration
- Binary files are not included in this packed representation. Please refer to the Repository Structure section for a complete list of file paths, including binary files
- Files matching patterns in .gitignore are excluded
- Files matching default ignore patterns are excluded
- Files are sorted by Git change count (files with more changes are at the bottom)
</notes>

</file_summary>

<directory_structure>
backend/
  .mvn/
    wrapper/
      maven-wrapper.properties
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
                OpenApiConfig.java
                OrdenOpenApi.java
                RestClientConfig.java
              controller/
                .gitkeep
                AnalisisController.java
                AuthController.java
                HistorialAnalisisController.java
                PerfilFinancieroController.java
                TestSecurityController.java
                TransactionController.java
              dto/
                request/
                  AnalisisRequest.java
                  LoginRequest.java
                  PerfilFinancieroRequest.java
                  RegisterRequest.java
                  TransactionRequest.java
                response/
                  AnalisisResponse.java
                  AuthResponse.java
                  ErrorResponse.java
                  HistorialAnalisisResponse.java
                  PerfilFinancieroResponse.java
                  TransaccionResponse.java
                .gitkeep
              entity/
                enums/
                  EstadoCivil.java
                  MedioPago.java
                  PerfilFinanciero.java
                  RangoAhorro.java
                  Rol.java
                  Sexo.java
                .gitkeep
                HistorialAnalisisEntity.java
                PerfilFinancieroEntity.java
                TransactionEntity.java
                UserEntity.java
              exception/
                .gitkeep
                ApiExceptionHandler.java
                ResourceNotFoundException.java
                UserAlreadyExistsException.java
              repository/
                .gitkeep
                HistorialAnalisisRepository.java
                PerfilFinancieroRepository.java
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
                  PerfilFinancieroServiceImpl.java
                  TransaccionServiceImpl.java
                .gitkeep
                AnalisisIAService.java
                AuthService.java
                HistorialAnalisisService.java
                PerfilFinancieroService.java
                TransaccionService.java
              FinanceaiApplication.java
      resources/
        db/
          migration/
            V1__create_users_table.sql
            V2__create_transactions_table.sql
            V3__create_analysis_table.sql
            V4__create_perfil_Financiero_table.sql
            V5__fix_historial_analisis_schema.sql
        application.yml
    test/
      java/
        com/
          nocountry/
            financeai/
              FinanceaiApplicationTests.java
  Dockerfile
  HELP.md
  mvnw
  mvnw.cmd
  pom.xml
  README.md
data-science/
  modeloFinanceAI/
    Dockerfile
    main.py
    modelo_clasificacion_transacciones.pkl
    modelo_perfil_financiero.pkl
    requirements.txt
  main.py
  modelo_clasificacion_transacciones.pkl
  modelo_perfil_financiero.pkl
  README.md
  requirements.txt
frontend/
  css/
    style.css
  js/
    api.js
    auth.js
    dashboard.js
  dashboard.html
  index.html
mock-api/
  app/
    models/
      __init__.py
      .response.py.kate-swp
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
  Dockerfile
  README.md
.gitattributes
.gitignore
docker-compose.yml
notamaestra_financeai.md
Protocolo de colaboracion.md
README.md
</directory_structure>

<files>
This section contains the contents of the repository's files.

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/PerfilFinanciero.java">
package com.nocountry.financeai.entity.enums;

public enum PerfilFinanciero {
    SALUDABLE,
    EN_OBSERVACION,
    RIESGO
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/.gitkeep">

</file>

<file path="mock-api/app/models/__init__.py">

</file>

<file path="mock-api/app/models/request.py">
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
</file>

<file path="mock-api/app/routers/__init__.py">

</file>

<file path="mock-api/app/routers/analisis.py">
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
</file>

<file path="mock-api/app/services/__init__.py">

</file>

<file path="mock-api/app/__init__.py">

</file>

<file path="mock-api/app/main.py">
from fastapi import FastAPI
from app.routers.analisis import router

app = FastAPI(
    tittle="Hackathton IA API",
    description="API de analisis financiero",
    version="1.0.0"
)

app.include_router(router)
</file>

<file path="backend/.mvn/wrapper/maven-wrapper.properties">
wrapperVersion=3.3.4
distributionType=only-script
distributionUrl=https://repo.maven.apache.org/maven2/org/apache/maven/apache-maven/3.9.9/apache-maven-3.9.9-bin.zip
</file>

<file path="backend/src/main/java/com/nocountry/financeai/client/IAClient.java">
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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/CorsConfig.java">
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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/OpenApiConfig.java">
package com.nocountry.financeai.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"));

    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/OrdenOpenApi.java">
package com.nocountry.financeai.config;

import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Clase para dar un orden a los endpoint en OpenApi-swagger
@Configuration
public class OrdenOpenApi {
    @Bean
    public OpenApiCustomizer ordenarTags() {
        return openApi -> {
            List<String> ordenDeseado = List.of(
                    "Analisis",
                    "Autenticacion",
                    "Perfil Financiero",
                    "Transacciones",
                    "Historial Resultado Analisis",
                    "Test"
            );

            List<Tag> tagsOrdenados = new ArrayList<>(openApi.getTags());
            tagsOrdenados.sort(Comparator.comparingInt(tag -> {
                int idx = ordenDeseado.indexOf(tag.getName());
                return idx == -1 ? Integer.MAX_VALUE : idx;
            }));

            openApi.setTags(tagsOrdenados);
        };
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/config/RestClientConfig.java">
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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/PerfilFinancieroController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.service.PerfilFinancieroService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/perfil")
@RequiredArgsConstructor
@Tag(name = "Perfil Financiero", description = "Gestión del perfil financiero del usuario")
public class PerfilFinancieroController {
    private final PerfilFinancieroService perfilFinancieroService;

    @PostMapping
    public PerfilFinancieroResponse crearPerfil(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody PerfilFinancieroRequest request
    ) {
        System.out.println("Request recibido: " + request);
        return perfilFinancieroService.crearPerfil(userDetails.getUsername(), request);

    }

}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/PerfilFinancieroRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PerfilFinancieroRequest(
        @Schema(
                description = "Indica si el usuario tiene empleo formal (1) o no (0)",
                example = "1")
        @JsonProperty("empleo_formal")
        @NotNull(message = "Debe indicar si tiene empleo formal")
        @Min(value = 0, message = "El valor debe ser 0 o 1")
        Integer empleoFormal,

        @Schema(
                description = "Ingreso mensual del usuario",
                example = "3500.00")
        @JsonProperty("ingreso_mensual")
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @Schema(
                description = "Línea de crédito disponible del usuario",
                example = "1000.00")
        @JsonProperty("linea_credito")
        @NotNull(message = "La línea de crédito es obligatoria")
        @DecimalMin(value = "0.0", inclusive = true, message = "La línea de crédito no puede ser negativa")
        BigDecimal lineaCredito
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/HistorialAnalisisResponse.java">
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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/PerfilFinancieroResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record PerfilFinancieroResponse (
        @JsonProperty("empleo_formal")
        Integer empleoFormal,
        @JsonProperty("ingreso_mensual")
        BigDecimal ingresoMensual,
        @JsonProperty("linea_credito")
        BigDecimal lineaCredito
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/TransaccionResponse.java">
package com.nocountry.financeai.dto.response;

import com.nocountry.financeai.entity.enums.MedioPago;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransaccionResponse(
        String nombreComercio,
        BigDecimal montoTransaccion,
        MedioPago medioPago,
        LocalDateTime fecha
) {}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/MedioPago.java">
package com.nocountry.financeai.entity.enums;

public enum MedioPago {
    EFECTIVO,
    DEBITO,
    CREDITO,
    TRANSFERENCIA,
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/RangoAhorro.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum RangoAhorro {
    ALTA,
    MEDIA,
    BAJA,
    NINGUNA;

    @JsonCreator
    public static RangoAhorro fromString(String value) {
        return RangoAhorro.valueOf(value.trim().toUpperCase());
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/Rol.java">
package com.nocountry.financeai.entity.enums;

public enum Rol {
    USER,
    ADMIN,
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/PerfilFinancieroEntity.java">
package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.RangoAhorro;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "perfil_financiero")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerfilFinancieroEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idPerfilFinanciero;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private UserEntity usuario;

    @Column(name = "empleo_formal")
    private Integer empleoFormal;

    @Column(name = "ingreso_mensual", precision = 12, scale = 2)
    private BigDecimal ingresoMensual;

    @Column(name = "linea_credito",  precision = 12, scale = 2)
    private BigDecimal lineaCredito;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/ResourceNotFoundException.java">
package com.nocountry.financeai.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/UserAlreadyExistsException.java">
package com.nocountry.financeai.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/HistorialAnalisisRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialAnalisisRepository extends JpaRepository<HistorialAnalisisEntity, Long> {
    List<HistorialAnalisisEntity> findByUsuarioId(Long id);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/PerfilFinancieroRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilFinancieroRepository extends JpaRepository<PerfilFinancieroEntity, Long> {
    Optional<PerfilFinancieroEntity> findByUsuarioId(Long usuarioId);

}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/JwtUtil.java">
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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/PerfilFinancieroServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.PerfilFinancieroRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.PerfilFinancieroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PerfilFinancieroServiceImpl implements PerfilFinancieroService {
    private final PerfilFinancieroRepository perfilFinancieroRepository;
    private final UserRepository userRepository;

    @Override
    public PerfilFinancieroEntity obtenerPerfilPorUsuarioId(Long usuarioId) {
        return perfilFinancieroRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "El usuario no tiene perfil financiero"
                ));
    }

    @Override
    public PerfilFinancieroResponse crearPerfil(String email, PerfilFinancieroRequest request) {
        UserEntity usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        if (perfilFinancieroRepository.findByUsuarioId(usuario.getId()).isPresent()) {
            throw new IllegalStateException("El usuario ya tiene un perfil financiero registrado");
        }

        PerfilFinancieroEntity perfil = PerfilFinancieroEntity.builder()
                .usuario(usuario)
                .empleoFormal(request.empleoFormal())
                .ingresoMensual(request.ingresoMensual())
                .lineaCredito(request.lineaCredito())
                .build();
        PerfilFinancieroEntity perfilGuardado = perfilFinancieroRepository.save(perfil);

        return new PerfilFinancieroResponse(
                perfilGuardado.getEmpleoFormal(),
                perfilGuardado.getIngresoMensual(),
                perfilGuardado.getLineaCredito()
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/TransaccionServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.entity.TransactionEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.TransaccionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransaccionServiceImpl implements TransaccionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Override
    public TransaccionResponse crearTransaccionAutenticado(String email, TransactionRequest transactionRequest) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        )
                );

        TransactionEntity transaccion = TransactionEntity.builder()
                        .nombreComercio(transactionRequest.nombreComercio())
                        .montoTransaccion(transactionRequest.montoTransaccion())
                        .medioPago(transactionRequest.mediopago())
                        .usuario(usuario)
                        .fecha(LocalDateTime.now())
                        .build();

        TransactionEntity transaccionGuardada = transactionRepository.save(transaccion);

        return convertirRespuesta(
                transaccionGuardada
        );
    }

    @Override
    public List<TransaccionResponse> obtenerTransaccionesAutenticado(String email) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return transactionRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public TransaccionResponse crearTransaccion(Long usuarioId, TransactionRequest transactionRequest) {
        UserEntity usuario = userRepository.findById(usuarioId).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        TransactionEntity transaccion = TransactionEntity.builder()
                .nombreComercio(transactionRequest.nombreComercio())
                .montoTransaccion(transactionRequest.montoTransaccion())
                .medioPago(transactionRequest.mediopago())
                .usuario(usuario)
                .fecha(LocalDateTime.now())
                .build();

        TransactionEntity transaccionGuardada = transactionRepository.save(transaccion);

        return new TransaccionResponse(
                transaccionGuardada.getNombreComercio(),
                transaccionGuardada.getMontoTransaccion(),
                transaccionGuardada.getMedioPago(),
                transaccionGuardada.getFecha()
        );
    }

    @Override
    public List<TransaccionResponse> obtenerTransaccionesPorUsuario(Long idUsuario) {
        return transactionRepository.findByUsuarioId(idUsuario)
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    public List<TransaccionResponse> obtenerTransacciones() {
        return transactionRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    private TransaccionResponse convertirRespuesta(TransactionEntity transactionEntity) {
        return new TransaccionResponse(
                transactionEntity.getNombreComercio(),
                transactionEntity.getMontoTransaccion(),
                transactionEntity.getMedioPago(),
                transactionEntity.getFecha()
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/.gitkeep">

</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/PerfilFinancieroService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.PerfilFinancieroRequest;
import com.nocountry.financeai.dto.response.PerfilFinancieroResponse;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;

public interface PerfilFinancieroService {
    PerfilFinancieroEntity obtenerPerfilPorUsuarioId(Long usuarioId);

    PerfilFinancieroResponse crearPerfil(String email, PerfilFinancieroRequest request);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/TransaccionService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.entity.TransactionEntity;

import java.util.List;

public interface TransaccionService {
    //crea transacciones de un usuario autenticado
    TransaccionResponse crearTransaccionAutenticado(String email,TransactionRequest transactionRequest);
    // Obtiene las transacciones de un usuario registrado
    List<TransaccionResponse> obtenerTransaccionesAutenticado(String email);
    // Crea transaccion por Id
    TransaccionResponse crearTransaccion(Long usuarioId, TransactionRequest transactionRequest);
    // Obtiene todas las transacciones de todos los usuarios
    List<TransaccionResponse> obtenerTransacciones();
    // Obtiene todas las transacciones de un usuario
    List<TransaccionResponse> obtenerTransaccionesPorUsuario(Long idUsuario);
}
</file>

<file path="backend/src/main/resources/db/migration/V4__create_perfil_Financiero_table.sql">
CREATE TABLE perfil_financiero (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL UNIQUE,
    empleo_formal INTEGER,
    ingreso_mensual DECIMAL(12,2),
    linea_credito DECIMAL(12,2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_perfil_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuarios(id)
);
</file>

<file path="backend/src/test/java/com/nocountry/financeai/FinanceaiApplicationTests.java">
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
</file>

<file path="backend/HELP.md">
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
</file>

<file path="backend/mvnw">
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
</file>

<file path="data-science/modeloFinanceAI/Dockerfile">
FROM python:3.11-slim

WORKDIR /app

COPY . .

RUN pip install --no-cache-dir -r requirements.txt

EXPOSE 8000

CMD ["uvicorn","main:app","--host","0.0.0.0","--port","8000"]
</file>

<file path="data-science/modeloFinanceAI/main.py">
from fastapi import FastAPI, HTTPException, status
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field
from typing import List, Dict
from contextlib import asynccontextmanager
import pandas as pd
import numpy as np
import joblib
import sklearn
import sklearn.compose._column_transformer

# ==============================================================================
# 1. PARCHE DE COMPATIBILIDAD SKLEARN
# ==============================================================================
if not hasattr(sklearn.compose._column_transformer, '_RemainderColsList'):
    class _RemainderColsList(list):
        pass
    sklearn.compose._column_transformer._RemainderColsList = _RemainderColsList

# ==============================================================================
# 2. CARGA SEGURA DE MODELOS (LIFESPAN)
# ==============================================================================
modelos = {}

@asynccontextmanager
async def lifespan(app: FastAPI):
    # Proceso de arranque (Startup)
    try:
        modelos['transacciones'] = joblib.load('modelo_clasificacion_transacciones.pkl')
        modelos['perfil'] = joblib.load('modelo_perfil_financiero.pkl')
        print("✅ [PROD] Modelos ML cargados exitosamente.")
    except Exception as e:
        print(f"❌ [ERROR CRÍTICO] Fallo al cargar modelos .pkl: {e}")
        raise RuntimeError(f"No se pudieron cargar los modelos en producción: {e}")
    yield
    # Proceso de apagado (Shutdown)
    modelos.clear()

# ==============================================================================
# 3. CREAR LA APLICACIÓN FASTAPI
# ==============================================================================
app = FastAPI(
    title="API Analítica Financiera",
    version="1.0.0",
    lifespan=lifespan
)

# Configuración de CORS para producción / Oracle Cloud
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # En prod estricto, reemplaza "*" por la IP/Dominio de tu Frontend
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# ==============================================================================
# 4. ESTRUCTURA DE DATOS DE ENTRADA (Pydantic Models)
# ==============================================================================
class TransaccionInput(BaseModel):
    nombre_comercio: str = Field(
        ..., 
        example="Uber", 
        description="Nombre del establecimiento o comercio"
    )
    monto_transaccion: float = Field(
        ..., 
        gt=0, 
        example=250.0, 
        description="Monto de la transacción (debe ser mayor a 0)"
    )
    medio_pago: str = Field(
        ..., 
        example="credito", 
        description="Medios aceptados: credito, debito, transaccion, efectivo"
    )

class EntradaUsuario(BaseModel):
    edad: int = Field(..., ge=18, le=120)
    sexo: str
    estado_civil: str
    numero_hijos: int = Field(..., ge=0)
    empleo_formal: int = Field(..., ge=0, le=1)
    ingreso_mensual: float = Field(..., ge=0)
    linea_credito: float = Field(..., ge=0)
    transacciones: List[TransaccionInput] = []

# ==============================================================================
# 5. ENDPOINTS DE PRODUCCIÓN
# ==============================================================================

@app.get("/health", status_code=status.HTTP_200_OK)
def health_check():
    """Endpoint para que Oracle Cloud / Docker verifique si la API está viva"""
    if 'transacciones' not in modelos or 'perfil' not in modelos:
        raise HTTPException(status_code=500, detail="Modelos no inicializados")
    return {"status": "ok", "models_loaded": True}

@app.post("/analisis-financiero")
def analizar_usuario(datos: EntradaUsuario):
    try:
        modelo_perfil = modelos.get('perfil')
        modelo_transacciones = modelos.get('transacciones')

        # ----------------------------------------------------------------------
        # A) CÁLCULO DE GASTOS Y MÉTRICAS FINANCIERAS
        # ----------------------------------------------------------------------
        gasto_total = 0.0
        if datos.transacciones:
            gasto_total = sum([float(tx.monto_transaccion) for tx in datos.transacciones])

# 1. Nivel de Endeudamiento
        denom_endeudamiento = datos.ingreso_mensual + datos.linea_credito
        if denom_endeudamiento > 0:
            nivel_endeudamiento_val = round(float((gasto_total / denom_endeudamiento) * 100), 2)
            nivel_endeudamiento = f"{nivel_endeudamiento_val}%"
        else:
            nivel_endeudamiento = "0.0%"

        # 2. Porcentaje y Frecuencia de Ahorro
        if datos.ingreso_mensual > 0:
            ahorro_bruto = max(datos.ingreso_mensual - gasto_total, 0.0)
            pct_ahorro = ahorro_bruto / datos.ingreso_mensual
        else:
            pct_ahorro = 0.0

        if pct_ahorro >= 0.40:
            porcentaje_ahorro_str = "Alta"
        elif pct_ahorro >= 0.20:
            porcentaje_ahorro_str = "Media"
        elif pct_ahorro > 0:
            porcentaje_ahorro_str = "Baja"
        else:
            porcentaje_ahorro_str = "Ninguna"

# ----------------------------------------------------------------------
        # B) PREDICCIÓN CON MODELO DE PERFIL (.pkl)
        # ----------------------------------------------------------------------
        # 1. Escala decimal para endeudamiento (0.0 a 1.0) para hacer match con Colab
        nivel_endeudamiento_decimal = (gasto_total / denom_endeudamiento) if denom_endeudamiento > 0 else 0.0

        # 2. DataFrame con las 9 variables exactas del modelo
        df_cliente = pd.DataFrame([{
            'edad': int(datos.edad),
            'sexo': str(datos.sexo).lower().strip(),
            'estado_civil': str(datos.estado_civil).lower().strip(),
            'numero_hijos': int(datos.numero_hijos),
            'empleo_formal': int(datos.empleo_formal),
            'ingreso_mensual': float(datos.ingreso_mensual),
            'linea_credito': float(datos.linea_credito),
            'nivel_endeudamiento': float(nivel_endeudamiento_decimal),
            'porcentaje_ahorro': float(pct_ahorro)
        }])

        # 3. Predicción de perfil
        perfil_pred = modelo_perfil.predict(df_cliente)[0]
        perfil_str = str(perfil_pred).upper().replace(" ", "_")

        # 4. Cálculo seguro de la probabilidad en porcentaje
        probabilidad = "85.0%"  # Valor por defecto de respaldo
        if hasattr(modelo_perfil, "predict_proba"):
            probs = modelo_perfil.predict_proba(df_cliente)[0]
            prob_val = round(float(np.max(probs)) * 100, 2)
            probabilidad = f"{prob_val}%"
        # ----------------------------------------------------------------------
        # C) CLASIFICACIÓN NLP DE TRANSACCIONES (Protección lista vacía)
        # ----------------------------------------------------------------------
        resumen_gastos: Dict[str, float] = {}
        
        if datos.transacciones and len(datos.transacciones) > 0:
            df_tx = pd.DataFrame([
                {
                    'nombre_comercio': str(t.nombre_comercio).lower().strip(),
                    'monto_transaccion': float(t.monto_transaccion)
                }
                for t in datos.transacciones
            ])
            
            if hasattr(modelo_transacciones, "predict_proba"):
                probs_matriz = modelo_transacciones.predict_proba(df_tx)
                clases = modelo_transacciones.classes_
                categorias_finales = []

                for probs in probs_matriz:
                    prob_max = float(np.max(probs))
                    idx_max = int(np.argmax(probs))
                    
                    # Umbral del 60%
                    if prob_max <= 0.60:
                        categorias_finales.append("otros servicios")
                    else:
                        categorias_finales.append(str(clases[idx_max]))
                
                df_tx['categoria'] = categorias_finales
            else:
                preds = modelo_transacciones.predict(df_tx)
                df_tx['categoria'] = [str(p) for p in preds]
            
            # Agrupar y formatear
            agrupado = df_tx.groupby('categoria')['monto_transaccion'].sum().to_dict()
            resumen_gastos = {str(k).lower(): round(float(v), 2) for k, v in agrupado.items()}

# ----------------------------------------------------------------------
        # D) GENERACIÓN DE RECOMENDACIONES
        # ----------------------------------------------------------------------
        recomendaciones = []

        # 1. Recomendación por sobreapalancamiento en perfil RIESGOSO
        if perfil_str == "RIESGOSO" and datos.linea_credito > datos.ingreso_mensual:
            recomendaciones.append(
                "Para aumentar el score del perfil financiero, se recomienda incrementar el ingreso mensual de modo que supere la línea de crédito asignada"
            )

        # 2. Recomendaciones por comportamiento de gasto y ahorro
        if "entretenimiento" in resumen_gastos and resumen_gastos["entretenimiento"] > (datos.ingreso_mensual * 0.15):
            recomendaciones.append("Monitorear los gastos recurrentes de entretenimiento.")
            
        if nivel_endeudamiento_val > 50:
            recomendaciones.append("Reducir el uso de tarjetas de crédito para bajar el nivel de endeudamiento.")

        # Recomendación por defecto si no aplica ninguna de las anteriores
        if not recomendaciones:
            recomendaciones.append("Mantener los hábitos de gasto actuales y continuar monitoreando el presupuesto.")
        # ----------------------------------------------------------------------
        # E) SALIDA EN FORMATO ESTRICTO DEL BACK-END
        # ----------------------------------------------------------------------
        return {
            "perfilFinanciero": perfil_str,
            "probabilidad": probabilidad,
            "nivel_endeudamiento": nivel_endeudamiento,
            "porcentaje_ahorro": porcentaje_ahorro_str,
            "resumenGastos": resumen_gastos,
            "recomendaciones": recomendaciones
        }

    except Exception as e:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=f"Error interno en la inferencia del modelo: {str(e)}"
        )





####http://localhost:8000/docs####
</file>

<file path="data-science/modeloFinanceAI/requirements.txt">
fastapi
uvicorn
pandas
scikit-learn==1.6.1
joblib
pydantic
</file>

<file path="data-science/main.py">
from fastapi import FastAPI, HTTPException, status
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field
from typing import List, Dict
from contextlib import asynccontextmanager
import pandas as pd
import numpy as np
import joblib
import sklearn
import sklearn.compose._column_transformer

# ==============================================================================
# 1. PARCHE DE COMPATIBILIDAD SKLEARN
# ==============================================================================
if not hasattr(sklearn.compose._column_transformer, '_RemainderColsList'):
    class _RemainderColsList(list):
        pass
    sklearn.compose._column_transformer._RemainderColsList = _RemainderColsList

# ==============================================================================
# 2. CARGA SEGURA DE MODELOS (LIFESPAN)
# ==============================================================================
modelos = {}

@asynccontextmanager
async def lifespan(app: FastAPI):
    # Proceso de arranque (Startup)
    try:
        modelos['transacciones'] = joblib.load('modelo_clasificacion_transacciones.pkl')
        modelos['perfil'] = joblib.load('modelo_perfil_financiero.pkl')
        print("✅ [PROD] Modelos ML cargados exitosamente.")
    except Exception as e:
        print(f"❌ [ERROR CRÍTICO] Fallo al cargar modelos .pkl: {e}")
        raise RuntimeError(f"No se pudieron cargar los modelos en producción: {e}")
    yield
    # Proceso de apagado (Shutdown)
    modelos.clear()

# ==============================================================================
# 3. CREAR LA APLICACIÓN FASTAPI
# ==============================================================================
app = FastAPI(
    title="API Analítica Financiera",
    version="1.0.0",
    lifespan=lifespan
)

# Configuración de CORS para producción / Oracle Cloud
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # En prod estricto, reemplaza "*" por la IP/Dominio de tu Frontend
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# ==============================================================================
# 4. ESTRUCTURA DE DATOS DE ENTRADA (Pydantic Models)
# ==============================================================================
class TransaccionInput(BaseModel):
    nombre_comercio: str = Field(
        ..., 
        example="Uber", 
        description="Nombre del establecimiento o comercio"
    )
    monto_transaccion: float = Field(
        ..., 
        gt=0, 
        example=250.0, 
        description="Monto de la transacción (debe ser mayor a 0)"
    )
    medio_pago: str = Field(
        ..., 
        example="credito", 
        description="Medios aceptados: credito, debito, transaccion, efectivo"
    )

class EntradaUsuario(BaseModel):
    edad: int = Field(..., ge=18, le=120)
    sexo: str
    estado_civil: str
    numero_hijos: int = Field(..., ge=0)
    empleo_formal: int = Field(..., ge=0, le=1)
    ingreso_mensual: float = Field(..., ge=0)
    linea_credito: float = Field(..., ge=0)
    transacciones: List[TransaccionInput] = []

# ==============================================================================
# 5. ENDPOINTS DE PRODUCCIÓN
# ==============================================================================

@app.get("/health", status_code=status.HTTP_200_OK)
def health_check():
    """Endpoint para que Oracle Cloud / Docker verifique si la API está viva"""
    if 'transacciones' not in modelos or 'perfil' not in modelos:
        raise HTTPException(status_code=500, detail="Modelos no inicializados")
    return {"status": "ok", "models_loaded": True}

@app.post("/analisis-financiero")
def analizar_usuario(datos: EntradaUsuario):
    try:
        modelo_perfil = modelos.get('perfil')
        modelo_transacciones = modelos.get('transacciones')

        # ----------------------------------------------------------------------
        # A) CÁLCULO DE GASTOS Y MÉTRICAS FINANCIERAS
        # ----------------------------------------------------------------------
        gasto_total = 0.0
        if datos.transacciones:
            gasto_total = sum([float(tx.monto_transaccion) for tx in datos.transacciones])

        # 1. Nivel de Endeudamiento (escala float 0.0 a 1.0)
        denom_endeudamiento = datos.ingreso_mensual + datos.linea_credito
        if denom_endeudamiento > 0:
            nivel_endeudamiento = round(float(gasto_total / denom_endeudamiento), 2)
        else:
            nivel_endeudamiento = 0.0

        # 2. Rango de Ahorro (String)
        if datos.ingreso_mensual > 0:
            ahorro_bruto = max(datos.ingreso_mensual - gasto_total, 0.0)
            pct_ahorro = ahorro_bruto / datos.ingreso_mensual
        else:
            pct_ahorro = 0.0

        if pct_ahorro >= 0.40:
            rango_ahorro_str = "Alta"
        elif pct_ahorro >= 0.20:
            rango_ahorro_str = "Media"
        elif pct_ahorro > 0:
            rango_ahorro_str = "Baja"
        else:
            rango_ahorro_str = "Ninguna"

# ----------------------------------------------------------------------
        # B) PREDICCIÓN CON MODELO DE PERFIL (.pkl)
        # ----------------------------------------------------------------------
        df_cliente = pd.DataFrame([{
            'edad': int(datos.edad),
            'sexo': str(datos.sexo).lower().strip(),
            'estado_civil': str(datos.estado_civil).lower().strip(),
            'numero_hijos': int(datos.numero_hijos),
            'empleo_formal': int(datos.empleo_formal),
            'ingreso_mensual': float(datos.ingreso_mensual),
            'linea_credito': float(datos.linea_credito),
            'nivel_endeudamiento': float(nivel_endeudamiento),
            'rango_ahorro': float(pct_ahorro)  # Valor decimal menor a 1
        }])

        perfil_pred = modelo_perfil.predict(df_cliente)[0]
        perfil_str = str(perfil_pred).upper().replace(" ", "_")

        # Inicializamos la probabilidad por defecto por seguridad
        probabilidad = 0.85
        try:
            if hasattr(modelo_perfil, "predict_proba"):
                probs = modelo_perfil.predict_proba(df_cliente)[0]
                probabilidad = round(float(np.max(probs)), 2)
        except Exception:
            probabilidad = 0.85

        # ----------------------------------------------------------------------
        # C) CLASIFICACIÓN NLP DE TRANSACCIONES
        # ----------------------------------------------------------------------
        resumen_gastos: Dict[str, float] = {}
        
        if datos.transacciones and len(datos.transacciones) > 0:
            df_tx = pd.DataFrame([
                {
                    'nombre_comercio': str(t.nombre_comercio).lower().strip(),
                    'monto_transaccion': float(t.monto_transaccion)
                }
                for t in datos.transacciones
            ])
            
            # Evaluación defensiva de probabilidades o predicción directa
            try:
                probs_matriz = modelo_transacciones.predict_proba(df_tx)
                clases = modelo_transacciones.classes_
                categorias_finales = []

                for probs in probs_matriz:
                    prob_max = float(np.max(probs))
                    idx_max = int(np.argmax(probs))
                    
                    # Umbral de confianza al 60%
                    if prob_max <= 0.60:
                        categorias_finales.append("otros servicios")
                    else:
                        categorias_finales.append(str(clases[idx_max]))
                
                df_tx['categoria'] = categorias_finales
            except Exception:
                # Si el modelo no soporta predict_proba, realiza la predicción directa
                preds = modelo_transacciones.predict(df_tx)
                df_tx['categoria'] = [str(p) for p in preds]
            
            # Agrupar montos por categoría
            agrupar = df_tx.groupby('categoria')['monto_transaccion'].sum().to_dict()
            resumen_gastos = {str(k).lower(): round(float(v), 2) for k, v in agrupar.items()}

        # ----------------------------------------------------------------------
        # D) GENERACIÓN DE RECOMENDACIONES
        # ----------------------------------------------------------------------
        recomendaciones = []

        if perfil_str == "RIESGOSO" and datos.linea_credito > datos.ingreso_mensual:
            recomendaciones.append(
                "Para aumentar el score del perfil financiero, se recomienda reducir el gasto o incrementar el ingreso mensual"
            )

        if "entretenimiento" in resumen_gastos and resumen_gastos["entretenimiento"] > (datos.ingreso_mensual * 0.15):
            recomendaciones.append("Monitorear los gastos recurrentes de entretenimiento.")

        if nivel_endeudamiento > 0.50:
            recomendaciones.append("Reducir las gastos para bajar el nivel de endeudamiento.")

        if not recomendaciones:
            recomendaciones.append("Mantener los hábitos de gasto actuales y continuar monitoreando el presupuesto.")

        # ----------------------------------------------------------------------
        # E) SALIDA EN FORMATO ESTRICTO
        # ----------------------------------------------------------------------
        return {
            "perfilFinanciero": perfil_str,
            "probabilidad": probabilidad,
            "nivel_endeudamiento": nivel_endeudamiento,
            "rango_ahorro": rango_ahorro_str,
            "resumenGastos": resumen_gastos,
            "recomendaciones": recomendaciones
        }

    except Exception as e:
        raise HTTPException(
            status_code=status.HTTP_500_INTERNAL_SERVER_ERROR,
            detail=f"Error interno en la inferencia del modelo: {str(e)}"
        )

####http://localhost:8000/docs####
</file>

<file path="data-science/README.md">
# Data Science
</file>

<file path="frontend/css/style.css">
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
</file>

<file path="frontend/js/api.js">
// ==========================================
// Configuración y Utilidades Base de la API
// ==========================================
const BASE_URL = 'http://localhost:8080/api/v1';

/**
 * Función genérica (fetch wrapper) para consumir endpoints protegidos.
 * Inyecta automáticamente el token JWT en las cabeceras.
 */
async function fetchProtected(endpoint, options = {}) {
    const token = localStorage.getItem('jwtToken');

    if (!token) {
        console.warn("No hay sesión activa. Redirigiendo...");
        window.location.href = 'index.html';
        return null;
    }

    const defaultHeaders = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
    };

    const config = {
        ...options,
        headers: {
            ...defaultHeaders,
            ...options.headers
        }
    };

    try {
        const response = await fetch(`${BASE_URL}${endpoint}`, config);

        // Si el token expiró o es inválido, Spring Boot devolverá 401 o 403
        if (response.status === 401 || response.status === 403) {
            localStorage.removeItem('jwtToken');
            window.location.href = 'index.html';
            throw new Error('Sesión expirada o no autorizada');
        }

        return response;
    } catch (error) {
        console.error('Error en fetchProtected:', error);
        throw error;
    }
}
</file>

<file path="frontend/dashboard.html">
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FinanceAI - Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

    <nav class="navbar navbar-dark bg-primary shadow-sm">
        <div class="container-fluid">
            <span class="navbar-brand mb-0 h1 fw-bold">FinanceAI - Panel Principal</span>
            <button class="btn btn-outline-light btn-sm" id="btnLogout">Cerrar Sesión</button>
        </div>
    </nav>

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8 text-center">
                <div class="card shadow border-0 p-4">
                    <h2 class="text-success mb-3">¡Bienvenido al Dashboard!</h2>
                    <p class="text-muted">La interfaz ha cargado correctamente y la sesión está activa.</p>
                    <hr>
                    <div id="estadoConexion" class="alert alert-info">
                        Verificando conexión con el backend...
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        console.log("¡El dashboard.html se cargó y ejecutó correctamente!");
        
        // Validar si el token existe
        const token = localStorage.getItem('jwtToken');
        const estadoDiv = document.getElementById('estadoConexion');
        
        if (!token || token === 'undefined') {
            estadoDiv.className = "alert alert-danger";
            estadoDiv.innerText = "Advertencia: No se encontró un token JWT válido en el almacenamiento local.";
        } else {
            estadoDiv.className = "alert alert-success";
            estadoDiv.innerText = "Token JWT detectado con éxito. Listo para consumir la API.";
        }

        // Botón de salida
        document.getElementById('btnLogout').addEventListener('click', () => {
            localStorage.removeItem('jwtToken');
            window.location.href = 'index.html';
        });
    </script>
</body>
</html>
</file>

<file path="mock-api/app/models/response.py">
from decimal import Decimal
from enum import Enum
from pydantic import BaseModel, ConfigDict

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
    probabilidad: float
    resumenGastos: ResumenGastosResponse
    recomendaciones: list[str]

    model_config = ConfigDict(
        json_schema_extra={
            "example": {
                "perfilFinanciero": "SALUDABLE",
                "probabilidad": 0.87,
                "resumenGastos": 0 ,
                "recomendaciones": [
                    "Reducir gastos en entretenimiento.",
                    "Incrementar el ahorro mensual."
                ]
            }
        }
    )
</file>

<file path="mock-api/app/services/analisis_service.py">
from decimal import Decimal

from app.models.response import (
    AnalisisFinancieroResponse,
    PerfilFinanciero,
    ResumenGastosResponse,
)

def analizar():
    resumen = ResumenGastosResponse(
        alimentacion=Decimal("300.00"),
        transporte=Decimal("100.00"),
        entretenimiento=Decimal("50.00"),
        salud=Decimal("75.00"),
        educacion=Decimal("550.00"),
        servicios=Decimal("80.00"),
        otros=Decimal("30.00"),
    )

    return AnalisisFinancieroResponse(
        perfilFinanciero=PerfilFinanciero.SALUDABLE,
        probabilidad=Decimal("0.75"),
        resumenGastos=resumen,
        recomendaciones=[
            "Considera aumentar tu ahorro mensual.",
            "Revisa tus gastos en entretenimiento para optimizar tu presupuesto.",
        ],
    )
</file>

<file path="mock-api/README.md">
# FinanceAI - Mock API

## Descripción

Microservicio desarrollado con FastAPI que simula el servicio de Inteligencia Artificial utilizado por FinanceAI.


---

## Tecnologías

- Python 3.13
- FastAPI
- Pydantic v2
- Uvicorn

---

## Crear entorno virtual

```bash
python -m venv .venv
```

### Linux

```bash
source .venv/bin/activate
```

### Windows

```bash
.venv\Scripts\activate
```

---

## Instalar dependencias

```bash
pip install fastapi uvicorn pydantic
```

o las que realmente estés usando (`scikit-learn`, `joblib`, etc., cuando ya entren al proyecto).

---

## Ejecutar

```bash
uvicorn app.main:app --reload
```

La API estará disponible en:

```text
http://localhost:8000
```

---

## Documentación

Swagger

```text
http://localhost:8000/docs
```

OpenAPI

```text
http://localhost:8000/openapi.json
```

---

## Endpoint disponible

### POST `/predict`

Genera un diagnóstico financiero simulado.

---

## Estado del proyecto

- ✔ Mock API implementada.
- ✔ Documentación OpenAPI.
- ✔ Lista para integración con Spring Boot.
- 🔄 Pendiente integración del modelo real.
</file>

<file path="Protocolo de colaboracion.md">
# 📌 Protocolo de Colaboración, Verificación y Control de Versionado (Actualizado)

**Proyecto:** FinanceAI - Backend  
**Propósito:** Definir el flujo de interacción estricto para la entrega de código optimizado, validación de compilación local, generación de comandos Git y actualización de la Nota Maestra.

---

### 🎯 Objetivo Principal
Garantizar que ningún commit de Git y ninguna actualización en la Nota Maestra se registren con código no probado. Todo cambio debe estar alineado con la arquitectura real del proyecto (paquete base `com.nocountry.financeai`) y ser compilado localmente antes de pasar a la fase de versionado y documentación.

---

### 🔄 Flujo de Trabajo en 5 Pasos (Paso 0 al Paso 4)

#### **Paso 0: Análisis Estricto de Contexto (Asistente IA)**
* Antes de generar cualquier fragmento de código o sugerencia, la IA **debe revisar obligatoriamente** las fuentes adjuntas en el cuaderno (como `financeai.md`, `pom.xml` o notas previas).
* Tiene prohibido inventar rutas, nombres de paquetes genéricos (*placeholders*) o versiones. Debe extraer el paquete base real (`com.nocountry.financeai`) para entregar una solución 100% *plug and play*.

#### **Paso 1: Entrega de Código (Asistente IA)**
* Se proporciona el código fuente completo en Java 21 / Spring Boot 3 (DTOs, Servicios, Controladores, etc.) con sus anotaciones (Lombok, Jakarta Validation, Spring Security) adaptado a la estructura del proyecto.
* **Restricción:** En este paso **no se generan** comandos Git ni bloques de actualización de la nota.

#### **Paso 2: Verificación Local (Desarrollador)**
* Se copia el código al IDE (IntelliJ / VS Code).
* Se ejecuta la compilación (`mvn clean compile` o build del IDE) y se verifica que no existan errores de sintaxis, dependencias o conflictos de contexto.

#### **Paso 3: Trigger de Confirmación (Desarrollador)**
* El usuario envía un mensaje en el chat confirmando que el módulo/código ha sido integrado y compilado exitosamente (ej. *"Listo, ya compiló correctamente"*).

#### **Paso 4: Artefactos Finales (Asistente IA)**
* Tras recibir el trigger, la IA genera inmediatamente:
  1. **Comando Git:** Formateado bajo el estándar *Conventional Commits* (ej. `feat(auth): ...`, `fix(security): ...`).
  2. **Snippet de Nota Maestra:** Fragmento Markdown listo para copiar y pegar en la documentación general del proyecto.

---

### 🏷️ Convención de Commits (Conventional Commits)

| Tipo | Uso | Ejemplo |
| :--- | :--- | :--- |
| `feat` | Nueva funcionalidad agregada | `feat(auth): implement RegisterRequest and LoginRequest DTOs` |
| `fix` | Corrección de un error o bug | `fix(security): resolve circular dependency in JwtAuthFilter` |
| `refactor` | Reestructuración de código sin alterar comportamiento | `refactor(config): update SecurityConfig to handle specific exceptions` |
| `docs` | Cambios exclusivos en documentación | `docs(readme): update backend technical notes` |
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/TestSecurityController.java">
package com.nocountry.financeai.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Test")
public class TestSecurityController {

    // Ruta pública (dentro de /auth/**)
    @GetMapping("/auth/ping")
    public ResponseEntity<String> publicPing() {
        return ResponseEntity.ok("Ruta pública OK - Acceso permitido sin token");
    }

    // Ruta protegida
    @GetMapping("/test/protected")
    public ResponseEntity<String> protectedPing() {
        return ResponseEntity.ok("Ruta protegida OK - Requiere token JWT válido");
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/TransactionController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.entity.TransactionEntity;

import com.nocountry.financeai.service.TransaccionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transacciones")
@Tag(name = "Transacciones", description = "Registro y consulta de transacciones")
public class TransactionController {

    private final TransaccionService transaccionService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<TransaccionResponse> listarTransacciones(){
        return transaccionService.obtenerTransacciones();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuario/{usuarioId}")
    public List<TransaccionResponse> listarTransaccionesPorUsuario(@PathVariable Long usuarioId) {
        return transaccionService.obtenerTransaccionesPorUsuario(usuarioId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/usuario/{usuarioId}")
    public TransaccionResponse crearTransaccion(
            @PathVariable Long usuarioId,
            @Valid @RequestBody TransactionRequest transactionRequest) {
        return transaccionService.crearTransaccion(usuarioId, transactionRequest);
    }

    @PostMapping("/usuario/transacciones")
    public TransaccionResponse crearTransaccionAutenticado(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody TransactionRequest transactionRequest
    ) {
        return transaccionService.crearTransaccionAutenticado(
                userDetails.getUsername(),
                transactionRequest
        );
    }

    @GetMapping("/usuario/transacciones")
    public List<TransaccionResponse> obtenerMisTransacciones(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return transaccionService.obtenerTransaccionesAutenticado(userDetails.getUsername());
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/LoginRequest.java">
package com.nocountry.financeai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Schema(
            description = "email del usuario",
            example = "carlosgomez@gmail.com"
    )
    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "El formato del correo es inválido")
    private String email;

    @Schema(
            description = "clave del usuario",
            example = "abc123456"
    )
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/AuthResponse.java">
package com.nocountry.financeai.dto.response;

public record AuthResponse(
        String token,
        String message
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/EstadoCivil.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum EstadoCivil {
    SOLTERO("SOLTERO"),
    CASADO("CASADO"),
    DIVORCIADO("DIVORCIADO"),
    VIUDO("VIUDO");

    private final String valor;

    EstadoCivil(String valor) {
        this.valor = valor;
    }

    @JsonValue
    public String getValor() {
        return valor;
    }

    @JsonCreator
    public static EstadoCivil fromValor(String valor) {
        if (valor == null || valor.isBlank()) {
            return null;
        }
        String normalized = valor.trim().toUpperCase();
        for (EstadoCivil ec : EstadoCivil.values()) {
            if (ec.name().equalsIgnoreCase(normalized) || ec.valor.equalsIgnoreCase(normalized)) {
                return ec;
            }
        }
        // Fallback flexible para evitar errores 400 por tildes o variaciones
        if (normalized.contains("SOLTERO")) return SOLTERO;
        if (normalized.contains("CASADO")) return CASADO;
        if (normalized.contains("DIVORCIADO")) return DIVORCIADO;
        if (normalized.contains("VIUDO")) return VIUDO;

        throw new IllegalArgumentException("Valor no aceptado para EstadoCivil: " + valor);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/enums/Sexo.java">
package com.nocountry.financeai.entity.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Sexo {
    MASCULINO("M"),
    FEMENINO("F"); // Corregido el typo AUD-20 (antes FEMININO)

    private final String codigo;

    Sexo(String codigo) {
        this.codigo = codigo;
    }

    // @JsonValue indica que al convertir este Enum a JSON,
    // se debe usar el valor de este metodo ("M" o "F")
    @JsonValue
    public String getCodigo() {
        return codigo;
    }

    // @JsonCreator intercepta el JSON entrante y lo convierte al Enum correcto
    @JsonCreator
    public static Sexo fromCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (Sexo sexo : Sexo.values()) {
            if (sexo.codigo.equalsIgnoreCase(codigo.trim())) {
                return sexo;
            }
        }
        throw new IllegalArgumentException("Valor no aceptado para Sexo. Se esperaba 'M' o 'F', pero se recibió: " + codigo);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/HistorialAnalisisEntity.java">
package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.RangoAhorro;
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

    @ManyToOne(fetch= FetchType.LAZY, optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;

    @Enumerated(EnumType.STRING)
    @Column(name = "perfil_financiero", nullable = false)
    private PerfilFinanciero perfilFinanciero;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal probabilidad;

    @Column(name = "nivel_endeudamiento", nullable = false, precision = 4, scale = 2)
    private BigDecimal nivelEndeudamiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "rango_ahorro", nullable = false, length = 20)
    private RangoAhorro rangoAhorro;

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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/TransactionEntity.java">
package com.nocountry.financeai.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
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

    @Column(nullable = false, length = 10)
    private String tipo;

    @Column(length = 50)
    private String categoria;

    @Column(name = "nombre_comercio", nullable = false, length = 255)
    private String nombreComercio;

    @Column(name ="monto_transaccion", nullable = false)
    private BigDecimal montoTransaccion;

    @Enumerated(EnumType.STRING)
    @Column(name = "medio_pago", nullable = false, length = 20)
    private MedioPago medioPago;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private UserEntity usuario;
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/TransactionRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    List<TransactionEntity> findByUsuarioId(Long usuarioId);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/UserRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Utilizado en el Login para buscar al usuario
    Optional<UserEntity> findByEmail(String email);

    // Utilizado en el Registro para evitar correos duplicados
    boolean existsByEmail(String email);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/CustomUserDetailsService.java">
package com.nocountry.financeai.security;

import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
                List.of(
                        new SimpleGrantedAuthority(
                                "ROLE_" + user.getRol().name()
                        )
                )
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/JwtAuthFilter.java">
package com.nocountry.financeai.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Slf4j
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
        log.info("JWT filter ejecutando{}", request.getRequestURI());
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtUtil.extractUsername(jwt);
        System.out.println("JWT recibido para: " + userEmail);

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
                System.out.println("JWT válido: autenticando usuario");
            }
        }

        filterChain.doFilter(request, response);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/HistorialAnalisisServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.HistorialAnalisisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistorialAnalisisServiceImpl implements HistorialAnalisisService {
    private final HistorialAnalisisRepository historialAnalisisRepository;
    private final UserRepository userRepository;
    @Override
    public List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id) {
        return historialAnalisisRepository.findByUsuarioId(id)
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    @Override
    public List<HistorialAnalisisResponse> obtenerHistorial() {

        return historialAnalisisRepository.findAll()
                .stream()
                .map(this::convertirRespuesta)
                .toList();

    }

    @Override
    public List<HistorialAnalisisResponse> obtenerHistorialAutenticado(String email) {
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return historialAnalisisRepository.findByUsuarioId(usuario.getId())
                .stream()
                .map(this::convertirRespuesta)
                .toList();
    }

    public HistorialAnalisisResponse convertirRespuesta(HistorialAnalisisEntity historial) {
        return new HistorialAnalisisResponse(
                historial.getId(),
                historial.getUsuario().getId(),
                historial.getPerfilFinanciero(),
                historial.getProbabilidad(),
                historial.getResumenGastos(),
                historial.getRecomendaciones()
        );
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/AuthService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.LoginRequest;
import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/HistorialAnalisisService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;

import java.util.List;

public interface HistorialAnalisisService {
    List<HistorialAnalisisResponse> obtenerHistorial();
    List<HistorialAnalisisResponse> obtenerHistorialPorId(Long id);
    List<HistorialAnalisisResponse> obtenerHistorialAutenticado(String email);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/FinanceaiApplication.java">
package com.nocountry.financeai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinanceaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanceaiApplication.class, args);
	}

}
</file>

<file path="backend/src/main/resources/db/migration/V2__create_transactions_table.sql">
CREATE TABLE transacciones (
id BIGSERIAL PRIMARY KEY,
usuario_id BIGINT NOT NULL,
monto_transaccion NUMERIC(12, 2) NOT NULL,
tipo VARCHAR(10),
categoria VARCHAR(50),
nombre_comercio VARCHAR(255),
medio_pago VARCHAR(20) NOT NULL,
fecha TIMESTAMP NOT NULL,
CONSTRAINT fk_transacciones_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuarios(id)
ON DELETE CASCADE
);
</file>

<file path="backend/src/main/resources/db/migration/V5__fix_historial_analisis_schema.sql">
-- Renombrar la columna frecuencia_ahorro a rango_ahorro para coincidir con la entidad JPA
ALTER TABLE historial_analisis
RENAME COLUMN frecuencia_ahorro TO rango_ahorro;

-- Cambiar el tipo de dato de INTEGER a NUMERIC(4,2) para soportar BigDecimal
ALTER TABLE historial_analisis
ALTER COLUMN nivel_endeudamiento TYPE NUMERIC(4,2);
</file>

<file path="backend/mvnw.cmd">
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
</file>

<file path="backend/README.md">
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
</file>

<file path="data-science/requirements.txt">
fastapi
uvicorn
pandas
scikit-learn==1.3.2
joblib
pydantic
</file>

<file path="frontend/js/dashboard.js">
// ==========================================
// Configuración e Inicio
// ==========================================
// Asumiendo que `fetchProtected` está en api.js. Si no, asegúrate de que agregue la URL base '/api/v1' y el Header de Autorización.

document.addEventListener('DOMContentLoaded', async () => {
    const token = localStorage.getItem('jwtToken');
    // AUD-01: Validamos que haya token
    if (!token || token === 'undefined') {
        window.location.href = 'index.html';
        return;
    }

    // AUD-19: Validar si el usuario ya tiene perfil financiero
    // Verificamos intentando consultar el perfil. (Asumiendo que existe un endpoint GET /perfil)
    // Si el backend aún no tiene GET /perfil, esto fallará y forzará a llenarlo.
    await verificarPerfilFinanciero();

    cargarTransacciones();
});

const btnLogout = document.getElementById('btnLogout');
if (btnLogout) {
    btnLogout.addEventListener('click', () => {
        localStorage.removeItem('jwtToken');
        // También limpiamos banderas locales
        localStorage.removeItem('perfilCompletado');
        window.location.href = 'index.html';
    });
}

// ==========================================
// Módulo de Perfil Financiero (AUD-19)
// ==========================================
async function verificarPerfilFinanciero() {
    // Si ya lo completó en esta sesión localmente, lo dejamos pasar
    if (localStorage.getItem('perfilCompletado') === 'true') return;

    // Aquí llamarías a tu API para validar. Por ahora, mostramos el modal directamente 
    // si no tenemos constancia local de que lo haya llenado.
    const modal = new bootstrap.Modal(document.getElementById('modalPerfilIncompleto'));
    modal.show();

    const formPerfil = document.getElementById('formPerfilFinanciero');
    formPerfil.addEventListener('submit', async (e) => {
        e.preventDefault();
        const btnGuardar = document.getElementById('btnGuardarPerfil');
        btnGuardar.disabled = true;
        btnGuardar.innerText = 'Guardando...';

        const payload = {
            ingresoMensual: parseFloat(document.getElementById('perfilIngreso').value),
            lineaCredito: parseFloat(document.getElementById('perfilCredito').value),
            empleoFormal: document.getElementById('perfilEmpleoFormal').checked
        };

        try {
            // Requisito: Endpoint para crear perfil
            const response = await fetchProtected('/perfil', {
                method: 'POST',
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                localStorage.setItem('perfilCompletado', 'true');
                modal.hide();
            } else {
                alert('Hubo un error al guardar tu perfil. Inténtalo de nuevo.');
                btnGuardar.disabled = false;
                btnGuardar.innerText = 'Guardar y Continuar';
            }
        } catch (error) {
            console.error('Error al guardar perfil:', error);
            btnGuardar.disabled = false;
        }
    });
}

// ==========================================
// Módulo de Transacciones (Slice 2)
// ==========================================
async function cargarTransacciones() {
    try {
        // AUD-03: Ruta correcta hacia el backend Java
        const response = await fetchProtected('/transacciones/usuario/transacciones', { method: 'GET' });
        if (response.ok) {
            const transacciones = await response.json();
            renderizarTablaTransacciones(transacciones);
        }
    } catch (error) {
        console.error('Error al cargar transacciones:', error);
    }
}

const formTransaccion = document.getElementById('formTransaccion');
if (formTransaccion) {
    formTransaccion.addEventListener('submit', async (e) => {
        e.preventDefault();

        // AUD-03: Contrato de payload exacto
        const payload = {
            nombre_comercio: document.getElementById('transComercio').value,
            monto_transaccion: parseFloat(document.getElementById('transMonto').value),
            medio_pago: document.getElementById('transMedioPago').value
        };

        try {
            const response = await fetchProtected('/transacciones/usuario/transacciones', {
                method: 'POST',
                body: JSON.stringify(payload)
            });

            if (response.ok) {
                formTransaccion.reset();
                cargarTransacciones(); // Recargar la tabla
            } else {
                alert('Error al guardar la transacción');
            }
        } catch (error) {
            console.error('Error en el registro:', error);
        }
    });
}

function renderizarTablaTransacciones(transacciones) {
    const tbody = document.getElementById('tablaTransaccionesBody');
    if (!tbody) return;
    tbody.innerHTML = '';

    if (!transacciones || transacciones.length === 0) {
        tbody.innerHTML = '<tr><td colspan="3" class="text-center text-muted">Aún no hay transacciones registradas</td></tr>';
        return;
    }

    transacciones.forEach(t => {
        const tr = document.createElement('tr');
        // Usamos los nombres correctos del backend (monto_transaccion, nombre_comercio)
        tr.innerHTML = `
            <td>${t.nombre_comercio || 'Desconocido'}</td>
            <td><span class="badge bg-secondary">${t.medio_pago || 'N/A'}</span></td>
            <td class="text-end fw-bold">$${t.monto_transaccion ? t.monto_transaccion.toFixed(2) : '0.00'}</td>
        `;
        tbody.appendChild(tr);
    });
}

// ==========================================
// Módulo de Análisis IA (Slice 3)
// ==========================================
const btnAnalizar = document.getElementById('btnAnalizar');
if (btnAnalizar) {
    btnAnalizar.addEventListener('click', async () => {
        btnAnalizar.disabled = true;
        btnAnalizar.innerText = 'Consultando a la IA...';

        try {
            // AUD-03: Ajustado al endpoint correcto del backend
            const response = await fetchProtected('/analisis/predict', { method: 'POST' });

            if (response.ok) {
                const resultado = await response.json();
                mostrarResultadosIA(resultado);
            } else {
                alert('No se pudo completar el análisis. Verifica que tengas transacciones registradas.');
            }
        } catch (error) {
            console.error('Error al solicitar análisis:', error);
        } finally {
            btnAnalizar.disabled = false;
            btnAnalizar.innerText = 'Generar Análisis Inteligente';
        }
    });
}

function mostrarResultadosIA(data) {
    const contenedor = document.getElementById('resultadoContenedor');
    if (!contenedor) return;

    // AUD-02: Corregido de 'EN_RIESGO' a 'RIESGO' para alinear con el enum de Java
    let badgeClass = 'bg-secondary';
    if (data.perfil_financiero === 'SALUDABLE') badgeClass = 'bg-success';
    else if (data.perfil_financiero === 'EN_OBSERVACION') badgeClass = 'bg-warning text-dark';
    else if (data.perfil_financiero === 'RIESGO') badgeClass = 'bg-danger';

    contenedor.classList.remove('d-none');
    document.getElementById('iaPerfil').innerHTML = `<span class="badge ${badgeClass} p-2">${data.perfil_financiero || 'DESCONOCIDO'}</span>`;

    if (data.resumen_gastos && data.resumen_gastos.length > 0) {
        const listaRecomendaciones = data.resumen_gastos.map(r => `<li class="list-group-item bg-transparent text-start small">${r}</li>`).join('');
        document.getElementById('iaRecomendaciones').innerHTML = `<ul class="list-group list-group-flush">${listaRecomendaciones}</ul>`;
    } else {
        document.getElementById('iaRecomendaciones').innerHTML = '<p class="text-muted small">No hay datos suficientes para recomendaciones.</p>';
    }
}
</file>

<file path=".gitattributes">
text=auto eol=lf
backend/mvnw text eol=lf
</file>

<file path="notamaestra_financeai.md">
# FinanceAI
## Nota Maestra del Proyecto
*Documentación Técnica, Auditoría de Código y Hoja de Ruta — Documento único de referencia para el equipo y para asistentes de IA*

**Versión 2 — Actualizado: 05 de agosto de 2026**
Stack objetivo: Java 21 + Spring Boot 4.1.0
Basado en re-auditoría cruzada entre la Nota Maestra v1 (30 de julio de 2026) y un nuevo snapshot del código real (financeai.md, 05 de agosto de 2026)
*Proyecto Hackathon No Country / ONE (Oracle Next Education – Alura)*
*Este documento reemplaza a la versión del 30 de julio de 2026 como fuente única de verdad del proyecto.*

---

## 0. Cómo Usar Este Documento
Este documento es la fuente única de verdad (single source of truth) del proyecto FinanceAI. Esta es la Versión 2, generada a partir de una nueva re-auditoría cruzada entre la Nota Maestra v1 (30 de julio de 2026) y un snapshot actualizado del código real (financeai.md, capturado el 05 de agosto de 2026 con Repomix). Reemplaza y consolida la versión anterior.

* Úsalo como contexto al iniciar una conversación con un asistente de IA (Claude u otro) para generar historias de usuario, tickets técnicos, revisiones de código o planificación de sprint.
* Cada hallazgo técnico tiene un identificador AUD-XX (Sección 6) y cada tarea propuesta tiene un identificador TASK-XXX (Sección 10), para poder referenciarlos sin ambigüedad en conversaciones futuras. Los identificadores AUD-01 a AUD-13 y TASK-001 a TASK-025 se conservan de la v1 para no romper referencias ya usadas por el equipo; los hallazgos y tareas nuevos de esta re-auditoría continúan la numeración desde AUD-14 / TASK-026.
* Las Secciones 6 (Auditoría Técnica) y 10 (Backlog Priorizado) son las de mayor prioridad de lectura para generar el próximo sprint de trabajo.
* Al pedirle tareas a una IA, cita la sección y el ID exacto (ej.: “Genera la tarea técnica detallada para TASK-026 / AUD-14”) para obtener resultados consistentes con este documento.
* Este documento debe actualizarse cada vez que se cierre un hallazgo o se complete una tarea del backlog, para que siga siendo confiable como fuente de contexto.

### 0.1 Convención de Identificadores
**AUD-XX:** hallazgo técnico detectado en la auditoría de código (Sección 6). Representa un bug, riesgo de seguridad o inconsistencia real, no una opinión de estilo.
**TASK-XXX:** tarea concreta del backlog priorizado (Sección 10), lista para convertirse en ticket. Cada TASK-XXX referencia el AUD-XX o la sección que la origina.
**Estados:** 🟢/✅ Completado · 🟡 Parcial / con deuda técnica · 🔴 Pendiente · ⚠️ Bloqueado o riesgoso.

### 0.2 Qué Cambió Desde la v1 (Resumen Ejecutivo)
El snapshot de código de esta re-auditoría es sustancialmente más reciente que el usado en la v1: aparecieron componentes enteros que no existían antes (gestión de perfil financiero, un motor de IA real con modelos entrenados, y una interfaz de login/registro funcional en el frontend). El balance neto:
* **6 hallazgos de la v1 quedaron resueltos 🟢** (AUD-04, AUD-06, AUD-07, AUD-08, AUD-10, AUD-12), incluyendo los dos hallazgos de seguridad más severos del backlog anterior: la fuga de datos en transacciones (AUD-07) y el IDOR de historial (AUD-08).
* **1 hallazgo es una regresión 🔴** (AUD-09: application.yml quedó peor que antes, no simplemente igual de incompleto).
* **5 hallazgos de la v1 siguen sin cambios 🔴** (AUD-01, AUD-02, AUD-03, AUD-05 en parte, AUD-11, AUD-13).
* **8 hallazgos son nuevos 🟣** (AUD-14 a AUD-21), producto directo de las nuevas funcionalidades incorporadas: el motor de IA real no está conectado al backend (tres desalineaciones independientes), el esquema de la tabla de historial no coincide con la entidad JPA, y el flujo de registro/login del frontend tiene puntos de quiebre nuevos.

Conclusión para el PM: el proyecto avanzó de forma real y significativa en dos frentes (seguridad de transacciones/historial, y existencia de un modelo de IA entrenado), pero el camino crítico para una demo end-to-end sigue bloqueado, ahora por una cadena de 6 dependencias secuenciales en lugar de un solo problema aislado. Ver Sección 7.4 para el detalle de esa cadena.

---

## 1. Visión General del Proyecto
### 1.1 Descripción
FinanceAI es una solución inteligente orientada al sector Fintech / Educación Financiera. Su propósito es transformar transacciones brutas en conocimiento útil y accionable para mejorar la salud financiera de los usuarios, mediante análisis de hábitos, clasificación de perfil financiero y recomendaciones automáticas generadas por un motor de IA.

### 1.2 Objetivos del MVP (Funcionalidades Obligatorias)
* Clasificación automática de transacciones: categorización en Alimentación, Transporte, Salud, Vivienda, Educación, Ocio, Servicios, etc.
* Análisis de perfil financiero: clasificación del usuario en Saludable, En observación o En riesgo.
* Recomendaciones personalizadas: consejos prácticos según patrones de consumo e indicadores financieros.
* Exposición RESTful: interfaz JSON documentada (Swagger/OpenAPI) para consumo de clientes y frontend.
* Despliegue OCI: integración obligatoria con al menos un servicio de Oracle Cloud Infrastructure.

### 1.3 Equipo y Stack Tecnológico
*Sin cambios respecto a la v1: se mantiene Java 21 sobre spring-boot-starter-parent 4.1.0 en todo el documento.*

| Rol / Área | Integrantes | Tecnologías principales |
| :--- | :--- | :--- |
| Backend Developers | 3 personas | Java 21, Spring Boot 4.1.0 (Web, Data JPA, Security), Hibernate/JPA, Flyway, PostgreSQL 16 Alpine, JJWT 0.12.6, springdoc-openapi 3.0.3, Docker |
| Data Science | 4 personas | Python, Pandas, Scikit-Learn (1.6.1 en el servicio productivo).<br>Novedad v2: ya existen dos modelos entrenados y serializados (modelo_perfil_financiero.pkl, modelo_clasificacion_transacciones.pkl) sirviendo desde un microservicio FastAPI propio (data-science/modeloFinanceAI). Ver AUD-15 a AUD-17: el modelo existe y funciona en aislamiento, pero no está correctamente conectado al backend todavía. |
| Frontend | No listado explícitamente en el equipo original | HTML5, Bootstrap 5, JavaScript vanilla (fetch API).<br>Novedad v2: ya existe una pantalla de login/registro funcional (index.html + auth.js) y un dashboard.js con lógica de transacciones y análisis, aunque ambos dependen de contratos de API y de una página (dashboard.html) que hoy no existen (ver AUD-03, AUD-18, AUD-19). |
| Project Management | 1 persona | Metodologías ágiles (Sprints / Kanban) |
| Infraestructura Cloud | Equipo general | Oracle Cloud Infrastructure (OCI) — Compute / Object Storage (pendiente, Semana 5) |

### 1.4 Contrato JSON Objetivo (Especificación Original del Producto)
**⚠️ Este es el contrato OBJETIVO original del producto (documentado en el README). NO coincide con la implementación actual del backend ni con lo que hoy envía/espera el frontend real (auth.js / dashboard.js). Ver Sección 3 y AUD-03 para el detalle completo, que en esta v2 se confirma también en runtime, no sólo en el diseño.**

**Request — POST /api/analisis-financiero**
```json
{ "ingreso_mensual": 4500, "nivel_endeudamiento": 25, "frecuencia_ahorro": "Media", "transacciones": [ { "descripcion": "Supermercado", "valor": 420 }, { "descripcion": "Combustible", "valor": 300 }, { "descripcion": "Streaming", "valor": 40 } ] }
```

**Response**
```json
{ "perfil_financiero": "En observacion", "probabilidad": 0.82, "resumen_gastos": { "alimentacion": 420, "transporte": 300, "entretenimiento": 40 }, "recomendaciones": [ "Monitorear gastos recurrentes de entretenimiento", "Aumentar reserva financiera mensual" ] }
```

---

## 2. Arquitectura Real del Sistema
### 2.1 Módulos del Monorepo
*Novedad v2: el motor de IA real vive en un módulo separado del mock-api original, y ambos coexisten hoy en el repositorio y en docker-compose.yml.*

| Módulo | Tecnología | Estado | Descripción |
| :--- | :--- | :--- | :--- |
| backend/ | Java 21 + Spring Boot 4.1.0 | 🟡 En desarrollo activo | API REST principal: autenticación, transacciones, perfil financiero, análisis y su historial. |
| mock-api/ | Python + FastAPI + Pydantic | ⚠️ Obsoleto, no conectado | Simulaba el motor de IA. Sigue en el repo y sigue siendo el destino teórico de ia.api.url, pero ya no aporta valor real ahora que existe un modelo entrenado (ver AUD-17: decidir su retiro). |
| data-science/modeloFinanceAI/ | Python (FastAPI, pandas, scikit-learn, joblib) | 🟡 Funcional en aislamiento, no conectado | Novedad v2. Sirve dos modelos .pkl reales (perfil financiero y clasificación de transacciones) vía POST /analisis-financiero. Responde bien probado de forma directa, pero el backend no le apunta correctamente (AUD-15/16). |
| data-science/ (raíz) | Python (pandas, scikit-learn) | 🟡 Copia duplicada | Contiene una segunda copia casi idéntica del mismo servicio y los mismos .pkl, con un formato de salida distinto (decimales vs. strings con “%”). No está referenciada en docker-compose.yml. Ver AUD-17. |
| frontend/ | HTML + Bootstrap 5 + JS vanilla | 🟡 Parcialmente construido, no integrado | Novedad v2: ya existe login/registro real (index.html, auth.js) con manejo de JWT en localStorage. dashboard.js también existe pero apunta a endpoints inexistentes y a una página dashboard.html que no está en el repo (AUD-03, AUD-18). |

### 2.2 Flujo de Comunicación (Previsto vs. Real)
Frontend  →  Backend (Spring Boot, puerto 8080)  →  Motor de IA (hoy con dos candidatos: mock-api en :8001, o modelo-financeai en :8000)  →  Backend persiste el historial en PostgreSQL. El backend también persiste usuarios, transacciones y perfil financiero directamente en PostgreSQL vía Spring Data JPA / Flyway.

**Nota de infraestructura (actualizada):** docker-compose.yml en la raíz del repo ya orquesta cuatro servicios: postgres-db, mock-api (puerto host 8001), modelo-financeai (puerto host 8000) y backend. Sin embargo, la variable IA_API_URL del backend sigue apuntando a http://mock-api:8000 — un host correcto pero con el puerto equivocado (mock-api escucha internamente en 8001, no 8000), y el servicio que sí escucha en el puerto 8000 es modelo-financeai, con otro nombre de host. Ver AUD-15.

### 2.3 Estructura de Paquetes del Backend (actualizada)
*com.nocountry.financeai*
```text
client/            IAClient (RestClient hacia el motor de IA)
config/            CorsConfig, OpenApiConfig, OrdenOpenApi, RestClientConfig
controller/        AnalisisController, AuthController, HistorialAnalisisController,
                   PerfilFinancieroController (nuevo), TestSecurityController (nuevo),
                   TransactionController
dto/request/       AnalisisRequest, LoginRequest, PerfilFinancieroRequest (nuevo),
                   RegisterRequest, TransactionRequest
dto/response/      AnalisisResponse, AuthResponse, ErrorResponse,
                   HistorialAnalisisResponse, PerfilFinancieroResponse (nuevo),
                   TransaccionResponse (nuevo)
entity/enums/      EstadoCivil, MedioPago, PerfilFinanciero, RangoAhorro, Rol, Sexo (todos nuevos salvo PerfilFinanciero)
entity/            HistorialAnalisisEntity, PerfilFinancieroEntity (nuevo), TransactionEntity, UserEntity
exception/         ApiExceptionHandler, ResourceNotFoundException (nuevo), UserAlreadyExistsException
repository/        HistorialAnalisisRepository, PerfilFinancieroRepository (nuevo),
                   TransactionRepository, UserRepository
security/          CustomUserDetailsService, JwtAuthFilter, JwtUtil, SecurityConfig
service/impl/      AnalisisIAServiceImpl, AuthServiceImpl, HistorialAnalisisServiceImpl,
                   PerfilFinancieroServiceImpl (nuevo), TransaccionServiceImpl (nuevo)
FinanceaiApplication
```
La incorporación más relevante desde la v1 es el módulo completo de Perfil Financiero (entidad, repositorio, service, controller y DTOs), que ahora es un prerrequisito obligatorio para poder generar un análisis (AnalisisIAServiceImpl.analizarPorUsuarioId lanza ResourceNotFoundException si el usuario no tiene perfil). El frontend actual no llama a este endpoint nuevo (ver AUD-19).

---

## 3. Contrato de API: Objetivo vs. Estado Actual
La v1 ya documentaba que el contrato README/frontend no coincidía con el backend real. En esta v2 se confirma que el problema no es sólo teórico: el frontend ya tiene código ejecutable (dashboard.js) que llama activamente a endpoints que no existen en el backend.

| Aspecto | Contrato objetivo (README) | Backend real (Java) | Frontend real (dashboard.js) |
| :--- | :--- | :--- | :--- |
| Endpoint transacciones | POST /api/analisis-financiero (todo en un solo request) | POST /api/v1/transacciones/usuario/transacciones | POST /transactions (con BASE_URL http://localhost:8080/api/v1) → URL final inexistente |
| Endpoint análisis | incluido en el mismo request | POST /api/v1/analisis/predict | POST /analisis → URL final inexistente |
| Payload transacción | { descripcion, valor } | { nombre_comercio, monto_transaccion, medio_pago } | { descripcion, valor } — coincide con el README, no con el backend |
| Autenticación | No especificada | Requiere JWT (Bearer) en casi todas las rutas | Sí envía Bearer token, pero contra las URLs incorrectas de arriba |
| Valores de perfil_financiero | Saludable / En observación / En riesgo (texto libre) | Enum Java: SALUDABLE, EN_OBSERVACION, RIESGO | dashboard.js ya contempla un cuarto valor: 'EN_RIESGO', que tampoco coincide con el enum Java (ver AUD-02) |

### 3.1 Recomendación del PM (sin cambios de fondo)
Se mantiene la recomendación de la v1: fijar un único contrato antes de continuar con nuevas features (TASK-003), conservando el versionado /api/v1/... del backend y evaluando snake_case global vía Jackson (spring.jackson.property-naming-strategy: SNAKE_CASE) para hablar el mismo idioma que el README y el frontend. Esta vez con un agravante: dashboard.js ya fue escrito asumiendo nombres de endpoint y de payload que no son ni el contrato README ni el contrato backend real (usa /transactions en inglés, que no aparece en ninguno de los dos documentos previos) — es un tercer dialecto a reconciliar, no sólo dos.

---

## 4. Configuración de Entorno
### 4.1 docker-compose.yml (raíz del repo, vigente)
*Novedad v2: ahora orquesta 4 servicios (antes sólo PostgreSQL). Ver AUD-15 sobre el desalineamiento de host/puerto que esto introdujo.*
*postgres-db (5432) · mock-api (host 8001 → contenedor 8001) · modelo-financeai (host 8000 → contenedor 8000) · backend (8080), con IA_API_URL=http://mock-api:8000 — combinación de host y puerto que no corresponde a ningún servicio real (ver AUD-15).*

### 4.2 application.yml del backend — ⚠️ Regresión detectada (AUD-09)
**Hallazgo nuevo relevante: 🔴** backend/src/main/resources/application.yml ya no contiene siquiera spring.application.name (que sí tenía en la v1). Su contenido actual es, literalmente, una copia del docker-compose.yml de la raíz (bloques services:, postgres-db:, volumes:, etc.), probablemente pegada por error o resultado de un merge mal resuelto. Un application.yml con sintaxis de docker-compose no es YAML de Spring Boot válido para configuración de la app; en el mejor caso Spring lo ignora silenciosamente, en el peor caso puede fallar el arranque según el classpath. Se prioriza como P0 por su bajo costo de arreglo y su alto impacto en el arranque local de cualquier integrante nuevo.

### 4.3 Variables de Entorno Requeridas
| Variable | Descripción | Dónde se usa | Estado |
| :--- | :--- | :--- | :--- |
| SPRING_DATASOURCE_URL / _USERNAME / _PASSWORD | Credenciales y cadena JDBC hacia PostgreSQL | application.properties (local, gitignored) / docker-compose | 🟡 Sólo local o vía compose, no versionada como plantilla (AUD-09 sigue relacionado) |
| ia.api.url (IA_API_URL) | URL base del motor de IA consumido por IAClient | RestClientConfig | 🔴 Configurada, pero apunta a un host:puerto que no sirve el modelo real (AUD-15) |
| jwt.secret | Clave HMAC para firmar los tokens | JwtUtil | ⚠️ Sigue con un valor por defecto embebido en el código fuente (AUD-13, sin cambios desde v1) |
| jwt.expiration | Tiempo de expiración del token en ms | JwtUtil | 🟢 Sin cambios, aceptable para desarrollo |
| server.port | Puerto del backend | Por defecto 8080 | 🟢 OK |

### 4.4 Historial de Diagnóstico y Resoluciones (Bitácora del Equipo)
Se conserva la bitácora completa de la v1 (ver documento anterior); se agrega la siguiente entrada correspondiente a esta re-auditoría:
* 2026-08-05 — Re-auditoría cruzada de PM: se contrastó la Nota Maestra v1 (30-jul) contra un nuevo snapshot Repomix del código real. Se confirmaron 6 hallazgos resueltos, 1 regresión (AUD-09) y se detectaron 8 hallazgos nuevos (AUD-14 a AUD-21), en su mayoría producto de la incorporación del motor de IA real y del módulo de perfil financiero. Esta nota maestra v2 reemplaza a la v1 como fuente de verdad.

---

## 5. Dependencias del Backend (pom.xml)
Parent: org.springframework.boot:spring-boot-starter-parent:4.1.0 · `<java.version>21</java.version>`.

| Dependencia | Versión | Propósito |
| :--- | :--- | :--- |
| spring-boot-starter-data-jpa | gestionada por el parent | Persistencia JPA/Hibernate sobre PostgreSQL. |
| spring-boot-starter-validation | gestionada por el parent | Bean Validation (Jakarta) para DTOs. |
| spring-boot-starter-web | gestionada por el parent | MVC / API REST. |
| spring-boot-starter-security | gestionada por el parent | Autenticación y autorización. |
| spring-boot-starter-flyway + flyway-core + flyway-database-postgresql | gestionada por el parent | Migraciones versionadas del esquema (V1–V4, con V5 propuesta en TASK-026). |
| postgresql (runtime) | gestionada por el parent | Driver JDBC. |
| lombok (optional) | gestionada por el parent | Reducción de boilerplate. |
| jjwt-api / jjwt-impl / jjwt-jackson | 0.12.6 | Emisión y validación de JWT. |
| springdoc-openapi-starter-webmvc-ui | 3.0.3 — corrección v2 | Swagger UI / documentación OpenAPI. |
| spring-boot-starter-test | gestionada por el parent | JUnit 5 + Mockito para testing. |

**Corrección aplicada en v2:** la Nota Maestra v1 documentaba springdoc-openapi 2.6.0; el pom.xml real hoy fija la versión 3.0.3. Además, el bloque `<configuration>` del maven-compiler-plugin en el pom.xml real está mal escrito como `<coniguration>` (falta la primera “f”), lo que probablemente hace que Maven ignore ese bloque completo y procese Lombok por su mecanismo por defecto en vez del explícito (ver AUD-21).

---

## 6. Auditoría Técnica — Re-auditoría sobre Snapshot Actualizado
Metodología: se contrastó línea por línea el nuevo snapshot financeai.md (05-ago-2026) contra la Nota Maestra v1 (30-jul-2026) y contra sí mismo entre módulos (backend, mock-api, motor de IA real, frontend). Los 13 hallazgos originales (AUD-01 a AUD-13) se re-verifican uno por uno; se agregan los hallazgos AUD-14 a AUD-21, detectados por primera vez en esta ronda.

### 6.1 Índice de Hallazgos (actualizado)
| ID | Severidad | Componente | Título | Estado en v2 |
| :--- | :--- | :--- | :--- | :--- |
| AUD-01 | Alta | Backend / Auth | AuthResponse: campos message/email invertidos | 🔴 Sin cambios |
| AUD-02 | Alta | Backend + Motor IA | Enum de perfil financiero inconsistente (RIESGO / EN_RIESGO / RIESGOSO) | 🔴 Sin cambios, ahora 3 variantes |
| AUD-03 | Alta | Backend + Frontend | Contrato de API desalineado (path y payload) | 🔴 Sin cambios, confirmado en runtime |
| AUD-04 | Media | Backend + Mock API | Campo transactions vs. transacciones | ✅ Resuelto |
| AUD-05 | Alta | Mock API | El endpoint /predict ignora el body recibido | 🟡 Superado en sustancia por AUD-15/16/17 |
| AUD-06 | Alta | Backend / Análisis | usuarioId nunca se asigna en HistorialAnalisisEntity | ✅ Resuelto |
| AUD-07 | Alta (seguridad) | Backend / Transactions | Sin autorización ni DTO propio: fuga de datos | ✅ Resuelto |
| AUD-08 | Alta (seguridad) | Backend / Historial | IDOR en historial de análisis | ✅ Resuelto |
| AUD-09 | Media | Backend / Config | application.yml casi vacío, sin plantilla | 🔴 Regresión: peor que en v1 |
| AUD-10 | Baja | Backend / Entity | UserEntity.apellido nunca se puebla | ✅ Resuelto |
| AUD-11 | Baja | Backend / Config | spring.jpa.open-in-view=false pendiente | 🔴 Sin cambios |
| AUD-12 | Media | Backend / DTO | TransactionRequest mal aprovechado | ✅ Resuelto |
| AUD-13 | Alta (seguridad) | Backend / Security | jwt.secret hardcodeado | 🔴 Sin cambios |
| AUD-14 | Alta — nuevo | Backend / Persistencia | Esquema de historial_analisis no coincide con la entidad JPA | 🔴 Nuevo |
| AUD-15 | Alta — nuevo | Infra / IAClient | Motor de IA real inalcanzable: host, puerto y ruta desalineados | 🔴 Nuevo |
| AUD-16 | Alta — nuevo | Backend + Data Science | Contrato de respuesta del motor real no coincide con AnalisisResponse | 🔴 Nuevo |
| AUD-17 | Media — nuevo | Data Science | Motor de IA duplicado, con salidas incompatibles entre sí | 🔴 Nuevo |
| AUD-18 | Media — nuevo | Frontend | dashboard.html no existe; scripts cargados en la página equivocada | 🔴 Nuevo |
| AUD-19 | Media — nuevo | Frontend + Backend | El registro no crea el perfil financiero, bloqueando el análisis | 🔴 Nuevo |
| AUD-20 | Baja — nuevo | Backend / Entity | Typo en enum Sexo (FEMININO) | 🔴 Nuevo |
| AUD-21 | Baja — nuevo | Backend / Config | Typo en pom.xml (<coniguration>) y versión de springdoc desactualizada en docs | 🔴 Nuevo |

*Balance de la re-auditoría: 6 hallazgos resueltos · 1 regresión · 5 sin cambios · 1 superado en sustancia · 8 nuevos. Total de hallazgos activos hoy: 15 de 21.*

### 6.2 Hallazgos Re-verificados de la v1 (AUD-01 a AUD-13)
*Se conserva el detalle original de cada hallazgo (ver Nota Maestra v1, Sección 6.2, para el texto completo de Hallazgo / Impacto / Acción recomendada); a continuación sólo se documenta el resultado de la re-verificación de cada uno contra el snapshot actualizado.*

#### AUD-01 — AuthResponse: campos invertidos — 🔴 Sin cambios
El record AuthResponse(String message, String email) sigue instanciándose como new AuthResponse(token, "mensaje...") en AuthServiceImpl. Con agravante nuevo: frontend/js/auth.js ya existe y hace localStorage.setItem('jwtToken', data.token) — un campo token que no existe en la respuesta real. El login/registro desde el navegador falla en silencio (localStorage guarda undefined) aunque el backend responda 200 OK.

#### AUD-02 — Enum de perfil financiero inconsistente — 🔴 Sin cambios, ahora con una tercera variante
Persisten los dos valores documentados en v1 (Java: RIESGO; mock-api antiguo: EN_RIESGO). Se detecta un tercer candidato: data-science/modeloFinanceAI/main.py compara perfil_str == "RIESGOSO", lo que sugiere que la clase real que produce el modelo entrenado es "RIESGOSO" — un tercer literal, distinto de los otros dos. dashboard.js, por su parte, ya contempla un cuarto: 'EN_RIESGO' para el color del badge. Antes de fijar el valor unificado hace falta inspeccionar model.classes_ del .pkl para saber cuál es la verdad de base.

#### AUD-03 — Contrato de API desalineado — 🔴 Sin cambios, confirmado en runtime
Ver detalle completo actualizado en la Sección 3. La novedad es que ya no es un desalineamiento teórico entre documentos: dashboard.js llama activamente a /transactions y /analisis, ninguno de los cuales existe en el backend real.

#### AUD-04 — Nombre del campo de transacciones — ✅ Resuelto
El componente del record AnalisisRequest ya se llama transacciones (antes transactions), coincidiendo con el campo requerido por el modelo Pydantic del mock. Sin acción adicional necesaria salvo si se retira el mock-api (ver AUD-17).

#### AUD-05 — El mock API ignora el body — 🟡 Superado en sustancia, pendiente de decisión formal
mock-api/app/routers/analisis.py no cambió: sigue sin declarar parámetro de request. Pero ya existe un motor de IA real (modelo-financeai) que sí procesa el body con un modelo entrenado. El hallazgo original queda parcialmente obsoleto: el problema ya no es “no hay lógica real” sino “hay que decidir si el mock se retira o se documenta como stub de desarrollo” (ver AUD-17, TASK-029).

#### AUD-06 — usuarioId nunca se asigna — ✅ Resuelto
AnalisisIAServiceImpl.guardarHistorial() ahora invoca .usuario(usuario) en el builder antes de guardar. El historial ya queda correctamente asociado al usuario autenticado. Nota: este arreglo expone un problema distinto y nuevo — AUD-14 — sobre el esquema de la tabla destino.

#### AUD-07 — TransactionController sin autorización ni DTO propio — ✅ Resuelto
Reescrito por completo: usa TransactionRequest (DTO, no la entidad JPA), separa las rutas administrativas (/usuario/{usuarioId}, protegidas con @PreAuthorize("hasRole('ADMIN')")) de las rutas del propio usuario (/usuario/transacciones, que derivan el usuario del @AuthenticationPrincipal). Ya no hay mass assignment ni fuga de datos entre cuentas. Es el arreglo de seguridad más significativo entre ambas versiones.

#### AUD-08 — IDOR en HistorialAnalisisController — ✅ Resuelto
Se agregó el endpoint /api/v1/analisis/usuario/historial, que deriva el usuario del token JWT vía @AuthenticationPrincipal. La ruta original por userId libre (/usuario/{userId}) se restringió con @PreAuthorize("hasRole('ADMIN')"). Ya no es posible leer el historial de otro usuario con un token válido propio.

#### AUD-09 — application.yml casi vacío — 🔴 Regresión: el archivo empeoró
Ver detalle completo en la Sección 4.2. En v1 el archivo al menos definía spring.application.name; hoy contiene contenido de docker-compose.yml pegado por error, sin ninguna propiedad válida de Spring Boot. Se reclasifica de Media a Alta por su impacto potencial en el arranque.

#### AUD-10 — UserEntity.apellido nunca se puebla — ✅ Resuelto
RegisterRequest ya incluye el campo apellido con validación @NotBlank, y AuthServiceImpl.register() lo asigna correctamente al construir la entidad.

#### AUD-11 — spring.jpa.open-in-view=false pendiente — 🔴 Sin cambios
Sigue sin aplicarse. No se puede verificar su efecto hasta resolver AUD-09, dado que hoy no hay un application.yml funcional donde colocarlo.

#### AUD-12 — TransactionRequest mal aprovechado — ✅ Resuelto
Como consecuencia directa del arreglo de AUD-07, TransactionRequest ya es el DTO real usado tanto en el alta de transacciones del usuario autenticado como en la ruta administrativa. Ya no hay ambigüedad sobre su propósito.

#### AUD-13 — jwt.secret con valor por defecto hardcodeado — 🔴 Sin cambios
JwtUtil conserva el mismo @Value("${jwt.secret:404E...}") con el fallback embebido y versionado en Git. Sigue siendo el hallazgo de seguridad abierto más severo del proyecto.

### 6.3 Hallazgos Nuevos (AUD-14 a AUD-21)

#### AUD-14 — Esquema de historial_analisis no coincide con la entidad JPA
**Severidad:** Alta   ·   **Componente:** V3__create_analysis_table.sql vs. HistorialAnalisisEntity
Hallazgo: la migración Flyway V3 define las columnas frecuencia_ahorro (VARCHAR) y nivel_endeudamiento (INTEGER). La entidad HistorialAnalisisEntity, en cambio, mapea @Column(name = "rango_ahorro") — una columna que la migración nunca creó — y define nivelEndeudamiento como BigDecimal(4,2), un tipo incompatible con INTEGER.
Impacto: cualquier intento de persistir un HistorialAnalisisEntity falla con un error SQL (columna inexistente / tipo incompatible), incluso después de que AUD-06 ya propaga correctamente el usuarioId. Este hallazgo bloquea en la práctica el mismo flujo que AUD-06 acababa de destrabar.
**Acción recomendada (TASK-026):** crear una migración Flyway V5 que renombre/ajuste frecuencia_ahorro → rango_ahorro (VARCHAR) y corrija el tipo de nivel_endeudamiento a NUMERIC(4,2), alineando el esquema real con lo que la entidad ya espera. No editar V3, que ya pudo haberse aplicado en ambientes existentes.

#### AUD-15 — Motor de IA real inalcanzable: host, puerto y ruta desalineados
**Severidad:** Alta   ·   **Componente:** docker-compose.yml, RestClientConfig, IAClient
Hallazgo: se detectan tres desalineaciones independientes, cualquiera de las cuales por sí sola ya rompe la integración: (a) IA_API_URL=http://mock-api:8000, pero el contenedor mock-api expone el puerto 8001, no 8000; (b) el servicio que sí escucha en el puerto 8000 es modelo-financeai, con otro nombre de host dentro de la red de Docker; (c) IAClient.analizar() llama siempre a .uri("/predict"), una ruta que existe en el mock-api antiguo pero no en el motor real, cuyo endpoint es POST /analisis-financiero.
Impacto: hoy, con la configuración actual, la llamada del backend al motor de IA fallará sin importar cuál de los dos servicios se pretenda usar, por al menos dos de las tres razones simultáneamente.
**Acción recomendada (TASK-027):** decidir explícitamente cuál motor es el canónico (recomendación: modelo-financeai, por ser el que tiene modelos entrenados reales), corregir IA_API_URL al host:puerto correcto de ese servicio, y actualizar la ruta en IAClient a /analisis-financiero.

#### AUD-16 — Contrato de respuesta del motor real no coincide con AnalisisResponse
**Severidad:** Alta   ·   **Componente:** AnalisisResponse (Java) vs. data-science/modeloFinanceAI/main.py
Hallazgo: incluso resolviendo AUD-15, el body de respuesta no es deserializable tal como está. El motor real devuelve perfilFinanciero y resumenGastos (camelCase, sin guion bajo), mientras que AnalisisResponse espera perfil_financiero y resumen_gastos vía @JsonProperty. Más grave aún: probabilidad y nivel_endeudamiento llegan como strings con formato porcentual (ej. "87.5%"), donde el DTO Java los tipa como BigDecimal — esto no es un desalineamiento de nombre, es un error de deserialización que Jackson no puede resolver solo.
Impacto: la llamada HTTP tendría éxito (200 OK), pero restClient.retrieve().body(AnalisisResponse.class) lanzará una excepción de conversión, resultando en un 500 genérico para el usuario final incluso con AUD-15 ya resuelto.
**Acción recomendada (TASK-028):** en el servicio de Data Science, emitir los campos con los nombres que el backend ya espera (o agregar @JsonAlias en el lado Java) y devolver probabilidad y nivel_endeudamiento como valores numéricos puros (sin el sufijo “%”), dejando el formato de presentación como responsabilidad del frontend.

#### AUD-17 — Motor de IA duplicado, con salidas incompatibles entre sí
**Severidad:** Media   ·   **Componente:** data-science/ (raíz) vs. data-science/modeloFinanceAI/
Hallazgo: existen dos copias casi idénticas del servicio de inferencia, cada una con su propio par de archivos .pkl. Ambas cargan los mismos modelos y calculan las mismas métricas, pero difieren en el formato de salida: la copia en la raíz devuelve nivel_endeudamiento y probabilidad como valores decimales puros; la copia en modeloFinanceAI/ (la que está conectada en docker-compose.yml) los devuelve como strings con “%”. Sólo esta última está referenciada en la infraestructura.
Impacto: riesgo de que un integrante del equipo edite la copia equivocada, o de que una futura re-auditoría compare AnalisisResponse contra el archivo que no está en producción. Genera confusión sobre cuál es la fuente de verdad del modelo.
**Acción recomendada (TASK-029):** eliminar la copia no referenciada (data-science/, raíz) o documentar explícitamente por qué se conserva (ej. como notebook de entrenamiento vs. servicio de inferencia), dejando una sola carpeta como canónica para servir el modelo.

#### AUD-18 — dashboard.html no existe; scripts cargados en la página equivocada
**Severidad:** Media   ·   **Componente:** frontend/index.html, auth.js, dashboard.js
Hallazgo: tras un login o registro exitoso, auth.js hace window.location.href = 'dashboard.html', un archivo que no existe en el repositorio (frontend/ sólo contiene index.html). Además, dashboard.js está cargado como <script> dentro de index.html —la propia pantalla de login— por lo que su lógica de “verificar sesión activa y cargar transacciones” se ejecuta sobre la pantalla de login antes de que exista una sesión, no sobre un dashboard real.
Impacto: incluso si AUD-01 se resolviera y el login funcionara correctamente, el usuario llegaría a una página en blanco (error 404 del navegador) en vez de a un dashboard.
**Acción recomendada (TASK-030):** crear frontend/dashboard.html como una página separada de index.html, mover ahí la carga de dashboard.js y api.js, y dejar en index.html únicamente auth.js.

#### AUD-19 — El registro no crea el perfil financiero, bloqueando el análisis
**Severidad:** Media   ·   **Componente:** frontend/js/auth.js, RegisterRequest, PerfilFinancieroController
Hallazgo: el formulario de registro en index.html sigue capturando y enviando ingresoMensual, lineaCredito y empleoFormal dentro del payload de POST /api/v1/auth/register. Pero esos campos ya no forman parte de RegisterRequest (se movieron a un endpoint independiente, POST /api/v1/perfil, agregado en esta misma iteración del backend). Jackson ignora en silencio los campos desconocidos: el registro se completa “con éxito” pero el perfil financiero nunca se crea, y el frontend nunca llama al endpoint nuevo.
Impacto: AnalisisIAServiceImpl.analizarPorUsuarioId() exige que exista un perfil financiero y lanza ResourceNotFoundException si no lo encuentra. Todo usuario registrado desde el frontend actual queda, sin saberlo, incapacitado para generar un análisis.
**Acción recomendada (TASK-031):** tras un registro exitoso, encadenar automáticamente una llamada a POST /api/v1/perfil con los datos ya capturados en el mismo formulario, antes de redirigir al dashboard.

#### AUD-20 — Typo en el enum Sexo (FEMININO)
**Severidad:** Baja   ·   **Componente:** entity/enums/Sexo.java
Hallazgo: el enum define FEMININO en lugar de FEMENINO. Bajo impacto funcional directo (el valor se usa de forma consistente en todo el backend), pero si el modelo de Data Science fue entrenado con la categoría “femenino” escrita correctamente, este valor cae fuera de vocabulario para cualquier encoder categórico que dependa del texto exacto.
**Acción recomendada (TASK-032):** corregir el nombre del enum a FEMENINO, coordinando con el equipo de Data Science para confirmar que no rompe el encoding usado al entrenar los modelos .pkl.

#### AUD-21 — Typo en pom.xml y versión de springdoc desactualizada en la documentación
**Severidad:** Baja   ·   **Componente:** backend/pom.xml
Hallazgo: el bloque de configuración del maven-compiler-plugin está escrito como <coniguration> (falta la primera “f”) en ambas aperturas y cierres. Es probable que Maven ignore silenciosamente ese bloque completo, dependiendo en cambio del procesamiento por defecto de anotaciones para Lombok. Adicionalmente, la Nota Maestra v1 documentaba springdoc-openapi 2.6.0, mientras que el pom.xml real ya fija 3.0.3 (corregido en la Sección 5 de esta v2).
**Acción recomendada (TASK-033):** corregir el typo a <configuration> y verificar con mvn clean package que Lombok se siga procesando correctamente; no se requiere acción adicional sobre la versión de springdoc, ya documentada.

---

## 7. Estado Real por Vertical Slice (actualizado)
Los estados siguientes corrigen el estatus reportado en la v1, cruzándolo contra el código real y los hallazgos actualizados de la Sección 6.

### 7.1 Slice 1 — Autenticación (Auth)
| Capa | Estado real | Nota |
| :--- | :--- | :--- |
| Backend: Seguridad base (SecurityFilterChain, JwtUtil, JwtAuthFilter) | ✅ Completo | Sin cambios respecto a v1. |
| Backend: Endpoints register / login | 🟡 Funcional con deuda | Bloqueado semánticamente por AUD-01 (sin cambios) y con riesgo de seguridad por AUD-13 (sin cambios). |
| Backend: Validación de funcionalidad (tests) | 🔴 Pendiente | Sin cambios (TASK-013). |
| Frontend: Login / Registro | 🟡 Existe, pero no funcional end-to-end | Novedad v2: index.html + auth.js ya implementan el flujo completo de UI, pero rompen en tres puntos: AUD-01 (token mal nombrado), AUD-18 (dashboard.html inexistente) y AUD-19 (perfil financiero nunca se crea). |

### 7.2 Slice 2 — Gestión de Transacciones
| Capa | Estado real | Nota |
| :--- | :--- | :--- |
| Backend: Persistencia (entidad, repositorio, migración) | ✅ Completo | Sin cambios. |
| Backend: Controlador y lógica de negocio | ✅ Reescrito y seguro | AUD-07 resuelto: ya no es una vulnerabilidad activa. Separación correcta entre rutas admin y rutas del usuario autenticado, con DTO propio. |
| Frontend: Dashboard de transacciones | 🔴 No funcional end-to-end | dashboard.js llama a /transactions con payload { descripcion, valor }; el backend real espera /api/v1/transacciones/usuario/transacciones con { nombre_comercio, monto_transaccion, medio_pago } (AUD-03). Además depende de una página que no existe (AUD-18). |

### 7.3 Slice 3 — Análisis Financiero e IA
| Capa | Estado real | Nota |
| :--- | :--- | :--- |
| Backend: DTOs, cliente REST, entidad de historial | 🟡 Completo estructuralmente, con un bug nuevo | AUD-02 (enum, sin cambios) y AUD-06 (resuelto) pero AUD-14 (nuevo) bloquea la persistencia del historial. |
| Backend: Integración de negocio real (perfil financiero requerido) | 🟡 Implementado, pero sin insumos del frontend | Novedad v2: el módulo de Perfil Financiero (entidad/servicio/controller) ya existe y funciona, pero el frontend nunca lo alimenta (AUD-19). |
| Motor de IA | 🟡 De 0% a modelo entrenado real, pero desconectado | Salto más grande del proyecto desde la v1: existen dos modelos .pkl funcionales en aislamiento (AUD-17), pero el backend no puede alcanzarlos por errores de configuración (AUD-15) y de contrato (AUD-16). |
| Frontend: Vista de diagnóstico e historial | 🔴 No existe en el repositorio | Sin cambios respecto a v1. |

### 7.4 Camino Crítico para una Demo End-to-End (nuevo análisis)
Para que un usuario pueda completar el flujo registrarse → iniciar sesión → ver su dashboard → cargar una transacción → pedir un análisis → ver una recomendación en pantalla, existen hoy seis bloqueos secuenciales. Cada uno oculta al siguiente hasta que se resuelve, por lo que no alcanza con arreglar uno o dos ítems aislados para tener una demo funcional:
* **1. AUD-01** — sin esto no hay token utilizable en el cliente; no se puede ni completar un login.
* **2. AUD-18** — sin dashboard.html, no hay a dónde navegar después del login.
* **3. AUD-19** — sin perfil financiero cargado, el análisis no puede ejecutarse más adelante.
* **4. AUD-03** — el dashboard llama URLs y payloads que no existen en el backend real.
* **5. AUD-15 + AUD-16** — aunque todo lo anterior se resuelva, la llamada al motor de IA falla por host/puerto/ruta y por incompatibilidad de formato de respuesta.
* **6. AUD-14** — incluso si el motor de IA respondiera correctamente, guardar el resultado en historial_analisis falla por el desalineamiento de esquema.

Recomendación de secuencia: los ítems 1, 2 y 3 son requisitos de UI/flujo y no dependen de infraestructura — pueden resolverse en paralelo por el equipo de frontend. Los ítems 5 y 6 requieren coordinación entre Backend y Data Science para acordar el contrato de datos. El ítem 4 es el más costoso en tiempo (reescritura de dashboard.js) y conviene abordarlo después de fijar el contrato definitivo en la Sección 3.1, para no reescribirlo dos veces.

---

## 8. Hoja de Ruta / Cronograma Ágil (Actualizado v2)
Estatus general (05 de agosto de 2026): el equipo avanzó sustancialmente en seguridad (Slice 2) y en el motor de IA (Slice 3, antes 0%), pero el Sprint de Estabilización propuesto en la v1 quedó parcialmente ejecutado: 4 de los 8 hallazgos de severidad Alta originales siguen abiertos, y se sumaron 3 hallazgos Alta nuevos. Se recomienda un segundo Sprint de Estabilización antes de continuar con features nuevas.

### Sprint de Estabilización v2 (bloqueante, antes de nuevas features)
Objetivo: cerrar los hallazgos de severidad Alta que siguen abiertos o son nuevos (AUD-01, 02, 03, 09, 13, 14, 15, 16) antes de intentar una demo end-to-end. Corresponde al grupo P0/P1 del backlog (Sección 10).
* ✅ Corregir AuthResponse (AUD-01 / TASK-001) — arrastrado de la v1, sigue sin resolver.
* ✅ Restaurar application.yml real (AUD-09 / TASK-010) — regresión, prioridad alta por su bajo costo.
* ✅ Eliminar jwt.secret hardcodeado (AUD-13 / TASK-009) — arrastrado de la v1.
* ✅ Crear dashboard.html y reordenar scripts (AUD-18 / TASK-030) — nuevo, bloquea toda navegación post-login.
* ✅ Nueva migración V5 para alinear historial_analisis (AUD-14 / TASK-026) — nuevo, bloquea la persistencia de análisis.
* 🔴 Alinear host/puerto/ruta del motor de IA (AUD-15 / TASK-027) — nuevo.
* 🔴 Alinear contrato de respuesta del motor de IA (AUD-16 / TASK-028) — nuevo.
* 🔴 Unificar el valor del enum de perfil de riesgo (AUD-02 / TASK-002) — arrastrado, requiere inspeccionar el modelo .pkl.
* 🔴 Fijar contrato único de API y conectar el flujo de perfil financiero (AUD-03 + AUD-19 / TASK-003 + TASK-031).

### ✅ Progreso desde la v1 (ya no requiere trabajo adicional)
* Rediseño seguro de TransactionController con DTO y autorización (AUD-07).
* Corrección del IDOR en HistorialAnalisisController (AUD-08).
* Propagación de usuarioId al guardar historial (AUD-06).
* Unificación del nombre de campo transacciones (AUD-04).
* Población de UserEntity.apellido (AUD-10).
* Aprovechamiento correcto de TransactionRequest como DTO (AUD-12).
* Existencia de un motor de IA real con modelos entrenados (antes 0% de integración con OCI/Data Science).
* Existencia de una pantalla de login/registro funcional en el frontend (antes “no existe en el repositorio”).

### Semanas 2 a 5 (sin cambios de fondo respecto a la v1)
El resto del cronograma original (Semana 2: Core Bancario, Semana 3: Motor de IA, Semana 4: Refinamiento, Semana 5: Despliegue OCI) se mantiene vigente en su estructura. Ver Sección 10 para el detalle de tareas actualizado con los nuevos identificadores TASK-026 a TASK-033.

---

## 9. Convenciones y Definition of Done
### 9.1 Convención de Contratos y Nombres (decisión pendiente del equipo)
Sin cambios respecto a la v1: conviven records de Java 21 y clases Lombok @Data sin una regla explícita. Se agrega a esta versión que los DTOs nuevos del módulo de Perfil Financiero (PerfilFinancieroRequest, PerfilFinancieroResponse) ya siguen el patrón recomendado (records inmutables), lo cual es una buena señal de consistencia hacia adelante. Se mantiene la recomendación: usar records para DTOs inmutables sin lógica adicional, reservando @Data sólo si se necesita mutabilidad real.

### 9.2 Checklist — Definition of Done
* El endpoint/feature respeta el contrato de API vigente (Sección 3) y no introduce un nuevo casing o path ad-hoc.
* Toda entrada de usuario pasa por un DTO validado con Jakarta Validation — nunca se expone una @Entity directamente en un @RequestBody o @ResponseBody.
* Toda consulta o mutación de datos sensibles filtra explícitamente por el usuario autenticado extraído del SecurityContext.
* No se introducen nuevos valores por defecto de secretos/credenciales en el código fuente (ver AUD-13, todavía abierto).
* Se agregó o actualizó al menos un test (JUnit/Mockito) que cubra el camino feliz y un camino de error relevante.
* Los cambios en el esquema de base de datos se realizan mediante una nueva migración Flyway (nunca editando una migración ya aplicada) — ver AUD-14 como ejemplo concreto de por qué esta regla importa.
* Swagger/OpenAPI (springdoc) refleja el endpoint nuevo o modificado con sus @Schema y ejemplos.
* Si la tarea cierra un hallazgo AUD-XX, se marca como resuelto en la Sección 6 al actualizar este documento.

### 9.3 Convención de Ramas y Commits
Sin cambios respecto a la v1: feature/slice-{n}-{descripcion-corta} · fix/AUD-{nn}-{descripcion-corta} · Conventional Commits (feat:, fix:, chore:, docs:, test:, refactor:) con referencia al TASK-XXX o AUD-XX en el cuerpo del commit.

### 9.4 Testing Mínimo Esperado
Sin cambios respecto a la v1. Se resalta que, a pesar del progreso funcional entre versiones, no se detectaron tests nuevos en el snapshot actualizado — TASK-013 sigue plenamente vigente y su ausencia es la razón por la cual varios de los hallazgos de esta sección se hubieran detectado antes con una suite mínima (por ejemplo, AUD-14 se habría detectado con un solo test de integración que persista un historial de análisis).

---

## 10. Backlog Priorizado — Próximas Tareas (actualizado v2)
Se conservan los identificadores TASK-001 a TASK-025 de la v1 (con su estado actualizado); las tareas nuevas de esta re-auditoría continúan la numeración desde TASK-026.

### P0 — Sprint de Estabilización v2 (bloqueante, antes de continuar)
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-001 | ✅ Corregir campos de AuthResponse (token / message) | AUD-01 | El JSON de login/registro expone un campo token explícito y uno message descriptivo, sin datos cruzados. |
| TASK-009 | ✅ Externalizar jwt.secret y eliminar el fallback hardcodeado | AUD-13 | La aplicación falla rápido si jwt.secret no está definido en el entorno. |
| TASK-010 | ✅ Restaurar application.yml del backend (revertir el contenido de docker-compose pegado por error) | AUD-09 | El backend arranca localmente usando application.yml + variables de entorno, sin depender de una plantilla accidental. |
| TASK-026 | ✅ Migración V5: alinear esquema de historial_analisis con la entidad JPA | AUD-14 | Un análisis se persiste sin error SQL; rango_ahorro y nivel_endeudamiento tienen el tipo y nombre correctos en BD. |
| TASK-030 | ✅ Crear dashboard.html real y reordenar la carga de scripts | AUD-18 | Tras un login exitoso, el usuario llega a una página real que carga dashboard.js y api.js correctamente. |

### P1 — Conectar el Motor de IA Real
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-002 | 🔴 Unificar valores del enum de perfil de riesgo (inspeccionar model.classes_) | AUD-02 | AnalisisIAServiceImpl no lanza excepción ante ninguna respuesta válida del motor real; cubierto por test unitario. |
| TASK-027 | 🔴 Alinear host, puerto y ruta del motor de IA en docker-compose e IAClient | AUD-15 | El backend puede invocar exitosamente al motor de IA elegido como canónico, en local y en docker-compose. |
| TASK-028 | 🔴 Alinear contrato de respuesta del motor de IA con AnalisisResponse | AUD-16 | AnalisisResponse se deserializa sin error a partir de la respuesta real del motor (nombres de campo y tipos numéricos correctos). |
| TASK-031 | 🔴 Conectar la creación de perfil financiero al flujo de registro del frontend | AUD-19 | Tras registrarse desde el navegador, el usuario tiene un perfil financiero persistido y puede solicitar un análisis sin error 404. |

### P2 — Integración Frontend ↔ Backend Restante
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-003 | 🔴 Definir y documentar el contrato único de API (path + casing + payload de transacciones) | AUD-03 | dashboard.js puede llamar exitosamente al backend real sin transformar el payload a mano. |
| TASK-018 | 🔴 Dashboard de transacciones funcional conectado al backend real | Slice 2 (depende de TASK-003) | El formulario crea transacciones visibles de inmediato en la tabla, usando el token de sesión y el payload correcto. |
| TASK-029 | 🔴 Retirar o documentar formalmente el motor de IA duplicado / mock-api obsoleto | AUD-17 + AUD-05 | Sólo queda un servicio de inferencia canónico referenciado en docker-compose y documentado como tal. |
| TASK-016 | 🟡 Completar paginación en el listado de transacciones (el filtrado por usuario ya está resuelto) | Slice 2 | GET soporta parámetros de página/tamaño además del filtrado por usuario autenticado ya existente. |
| TASK-021 | 🔴 Vista de historial de diagnósticos en el frontend | Slice 3 | El usuario puede ver sus análisis previos ordenados por fecha, una vez AUD-14 esté resuelto. |

### P3 — Deuda Técnica y Limpieza
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-011 | 🔴 Aplicar spring.jpa.open-in-view=false | AUD-11 | Aplicable recién después de TASK-010 (application.yml restaurado); los tests de integración siguen pasando. |
| TASK-013 | 🔴 Suite de pruebas de integración de Auth (colección versionada en el repo) | Slice 1 | Registro exitoso, registro duplicado (400), login exitoso y login inválido (401) cubiertos y documentados. |
| TASK-020 | 🟡 Completar resiliencia ante caída del motor de IA (ya existe el manejo de ResourceAccessException → 503) | Slice 3 | Se agregan tests que cubran explícitamente el escenario de caída del servicio de IA. |
| TASK-032 | ✅ Corregir typo Sexo.FEMININO → FEMENINO | AUD-20 | El enum usa la ortografía correcta; se confirma con Data Science que no rompe el encoding del modelo entrenado. |
| TASK-033 | ✅ Corregir <coniguration> en pom.xml | AUD-21 | mvn clean package procesa Lombok correctamente con el bloque de configuración corregido. |

### P4 — Infraestructura y Cierre (Semana 5, sin cambios de fondo)
| ID | Título | Ref. | Criterio de aceptación |
| :--- | :--- | :--- | :--- |
| TASK-023 | 🔴 docker-compose.prod.yml con backend, motor de IA real y PostgreSQL contenerizados | Semana 5 | docker compose -f docker-compose.prod.yml up levanta el stack completo end-to-end. |
| TASK-024 | 🔴 Despliegue en OCI (Compute u Object Storage) | Semana 5 | La API es accesible públicamente vía HTTPS y documentada con la URL final. |
| TASK-025 | 🔴 QA end-to-end + revisión final de esta nota maestra | Semana 5 | Los hallazgos AUD-01 a AUD-21 están cerrados o explícitamente diferidos con justificación. |
**Tareas ya completadas (sin acción pendiente)**
*TASK-004 (AUD-04), TASK-006 (AUD-06), TASK-007 (AUD-07), TASK-008 (AUD-08), TASK-012 (AUD-10), TASK-015 (alta de transacciones con userId desde JWT), TASK-017 (validaciones Jakarta sobre TransactionRequest) y TASK-019 (endpoint disparador reutilizando transacciones persistidas) ya están resueltas en el snapshot actual y no requieren trabajo adicional salvo verificación en QA final (TASK-025).*

---

## 11. Anexo: Prompts Guía para Sesiones de IA
Bloques de prompt listos para copiar y pegar al iniciar una conversación con un asistente de IA, adjuntando o pegando esta nota maestra v2 como contexto.

### Prompt base (inicio de cualquier sesión)
*Actúa como Tech Lead senior de Java 21 / Spring Boot 4.1.0 del proyecto FinanceAI. Te comparto la Nota Maestra v2 del proyecto (documento adjunto). Antes de proponer código, confirma en qué archivo(s) reales del repositorio (Sección 2) impacta el cambio, y respeta las convenciones de la Sección 9 (Definition of Done).*

### Prompt para una tarea puntual del backlog
*Con base en TASK-0XX de la Sección 10 de la Nota Maestra v2 de FinanceAI, redacta la historia de usuario en formato Gherkin (Given/When/Then) y la lista de archivos a modificar o crear, siguiendo la arquitectura descrita en la Sección 2.3.*

### Prompt para planificación de sprint
*Tomando el Backlog Priorizado (Sección 10) y el Camino Crítico para Demo (Sección 7.4) de la Nota Maestra v2 de FinanceAI, arma el plan del próximo sprint de 1 semana. Respeta que el grupo P0 debe cerrarse antes de continuar con features nuevas de los grupos P1 en adelante.*

### Prompt para la próxima re-auditoría
*Actúa como auditor técnico senior. Te comparto la Nota Maestra v2 de FinanceAI y un nuevo snapshot Repomix del código real. Contrasta línea por línea el estado de los hallazgos AUD-01 a AUD-21 contra el nuevo snapshot, marca cuáles se resolvieron, cuáles siguen abiertos y cuáles son regresiones, y detecta hallazgos nuevos continuando la numeración desde AUD-22.*
</file>

<file path="README.md">
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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/AuthController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.LoginRequest;
import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;
import com.nocountry.financeai.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticacion", description = "Registro y login de usuarios")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/HistorialAnalisisController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.response.HistorialAnalisisResponse;
import com.nocountry.financeai.service.HistorialAnalisisService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/analisis")
@RequiredArgsConstructor
@Tag(
        name = "Historial Resultado Analisis",
        description = "Listado de historiales realizados de un usuario"
)
public class HistorialAnalisisController {
    private final HistorialAnalisisService historialAnalisisService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/usuario/{userId}")
    public List<HistorialAnalisisResponse> obtenerHistorialPorId(@PathVariable Long userId) {
        return historialAnalisisService.obtenerHistorialPorId(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<HistorialAnalisisResponse> obtenerHistorial() {
        return historialAnalisisService.obtenerHistorial();
    }

    @GetMapping("/usuario/historial")
    public List<HistorialAnalisisResponse> obtenerMiHistorial(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return historialAnalisisService.obtenerHistorialAutenticado(userDetails.getUsername());
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/RegisterRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RegisterRequest(

        @Schema(
                description = "Nombre del usuario",
                example = "Carlos"
        )
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @Schema(
                description = "Apellido del usuario",
                example = "Gómez"
        )
        @NotBlank(message = "El apellido no puede estar vacío")
        String apellido,

        @Schema(
                description = "Email del usuario",
                example = "carlosgomez@gmail.com"
        )
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El formato del email no es válido")
        String email,

        @Schema(
                description = "Contraseña del usuario",
                example = "abc123456"
        )
        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(
                min = 8,
                message = "La contraseña debe tener al menos 8 caracteres"
        )
        String password,

        @Schema(
                description = "Fecha de nacimiento del usuario",
                example = "1995-06-15"
        )
        @JsonProperty("fecha_nacimiento")
        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe estar en el pasado")
        LocalDate fechaNacimiento,

        @Schema(
                description = "Sexo del usuario",
                example = "MASCULINO"
        )
        @NotNull(message = "El sexo es obligatorio")
        Sexo sexo,

        @Schema(
                description = "Estado civil del usuario",
                example = "SOLTERO"
        )
        @JsonProperty("estado_civil")
        @NotNull(message = "El estado civil es obligatorio")
        EstadoCivil estadoCivil,

        @Schema(
                description = "Número de hijos del usuario",
                example = "0"
        )
        @JsonProperty("numero_hijos")
        @NotNull(message = "El número de hijos es obligatorio")
        @Min(
                value = 0,
                message = "El número de hijos no puede ser negativo"
        )
        Integer numeroHijos

) {
    @AssertTrue(
            message = "El usuario debe ser mayor de 18 años"
    )
    public boolean esMayorDeEdad() {
        return fechaNacimiento != null
                && !fechaNacimiento.isAfter(
                LocalDate.now().minusYears(18)
        );
    }


}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/TransactionRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(

        @Schema(
                description = "Nombre del comercio donde se realizó la transacción",
                example = "Supermercado Éxito"
        )
        @JsonProperty("nombre_comercio")
        @NotBlank(message = "El nombre del comercio es obligatorio")
        String nombreComercio,

        @Schema(
                description = "Monto de la transacción",
                example = "210.00"
        )
        @JsonProperty("monto_transaccion")
        @NotNull(message = "El monto de la transacción es obligatorio")
        @Positive(message = "El monto debe ser mayor a cero")
        BigDecimal montoTransaccion,

        @Schema(
                description = "Medio de pago utilizado",
                example = "DEBITO"
        )

        @JsonProperty("medio_pago")
        @NotNull(message = "El medio de pago es obligatorio")
        MedioPago mediopago

) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/AnalisisResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.entity.enums.RangoAhorro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

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
        @JsonProperty("perfil_financiero")
        PerfilFinanciero perfilFinanciero,

        @Schema(
                description = "Probabilidad o nivel de confianza del modelo asociad al perlfil asignado, rango de 0 a 1",
                example = "0.82"
        )
        BigDecimal probabilidad,


        @Schema(
                description = "Porcentaje del nivel de endeudamiento de un usuario",
                example = "0.45"
        )
        @JsonProperty("nivel_endeudamiento")
        BigDecimal nivelEndeudamiento,

        @Schema(
                description = "Clasificacion del nivel de ahorro del usuario (Alta, Media, Baja, Ninguna)",
                example = "ALTA"
        )
        @JsonProperty("porcentaje_ahorro")
        RangoAhorro rangoAhorro,

        @Schema(
                description ="Resumen de gastos agrupados por categoria. Las claves del mapa son las categorias detectadas por el modelo",
                example ="{\"alimentacion\": 650, \"transporte\": 360, \"entretenimiento\":70}"
        )
        @JsonProperty("resumen_gastos")
        Map<String, BigDecimal> resumenGastos,

        @Schema(
                description = "lista de recomendaciones financieras generadas por el modelo, para el usuario",
                example = "[\"Monitorear los gastos recurrentes de entretenimiento\", \"Aumentar la reserva financiera mensual\"]"
        )
        List<String> recomendaciones
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/UserEntity.java">
package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Rol;
import com.nocountry.financeai.entity.enums.Sexo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", length = 20)
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Sexo sexo;

    @Column(name = "numero_hijos")
    private Integer numeroHijos;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Rol rol = Rol.USER;

    @Builder.Default
    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PerfilFinancieroEntity perfilFinanciero;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TransactionEntity> transacciones;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistorialAnalisisEntity> historialAnalisis;

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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/AnalisisIAServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.client.IAClient;
import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.TransactionEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.repository.PerfilFinancieroRepository;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.AnalisisIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalisisIAServiceImpl implements AnalisisIAService {
    
    private final IAClient iaClient;
    private final UserRepository userRepository;
    private final PerfilFinancieroRepository  perfilFinancieroRepository;
    private final TransactionRepository transactionRepository;
    private final HistorialAnalisisRepository historialAnalisisRepository;

    @Override
    public AnalisisResponse analizar(String email) {
        // Su busca el usuario por email, se usa el Id para hacer el analisis
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Usuario no encontrado"
                        ));
        return analizarPorUsuarioId(usuario.getId());
    }

    @Override
    public AnalisisResponse analizarPorUsuarioId(Long usuarioId) {
        // Busca el usuario por el id y se guarda
        UserEntity usuario = userRepository
                .findById(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));
        // Calcula la edad del usuario
        Integer edad = Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears();
        // Busca el perfil financiero asociado al usuario, si no tiene envia exepcion
        PerfilFinancieroEntity perfil = perfilFinancieroRepository
                .findByUsuarioId(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "El usuario no tiene un perfil financiero registrado"));
        // Guarda las transacciones de un usuario en una lista
        List<TransactionRequest> transaccionesRequest = transactionRepository
                .findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertirTransaccion)
                .toList();

        if (transaccionesRequest.isEmpty()) {
            throw new IllegalStateException("El usuario debe tener al menos una transacción registrada para generar un análisis");
        }

        // Teniendo tadas las variable para el analisis crea el request
        AnalisisRequest request = new AnalisisRequest(
                edad,
                usuario.getSexo(),
                usuario.getEstadoCivil(),
                usuario.getNumeroHijos(),
                perfil.getEmpleoFormal(),
                perfil.getIngresoMensual(),
                perfil.getLineaCredito(),
                transaccionesRequest
        );

        // Envia la peticion para hacer el analisis y guarda la respuesta
        AnalisisResponse response = iaClient.analizar(request);

        // Guarda el analisis al usuario
        guardarHistorial(usuario, response);

        return response;
    }

    // metodo para convertir entidad en request
    private TransactionRequest convertirTransaccion(TransactionEntity entity) {
        return new TransactionRequest(
                entity.getNombreComercio(),
                entity.getMontoTransaccion(),
                entity.getMedioPago()
        );
    }

    // metodo para guarda el historial en la base de datos
    private void guardarHistorial(UserEntity usuario, AnalisisResponse response) {
        HistorialAnalisisEntity historial = HistorialAnalisisEntity.builder()
                .usuario(usuario)
                .perfilFinanciero(response.perfilFinanciero())
                .probabilidad(response.probabilidad())
                .nivelEndeudamiento(response.nivelEndeudamiento())
                .rangoAhorro(response.rangoAhorro())
                .resumenGastos(response.resumenGastos())
                .recomendaciones(response.recomendaciones())
                .build();

        historialAnalisisRepository.save(historial);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/AnalisisIAService.java">
package com.nocountry.financeai.service;

import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;


public interface AnalisisIAService {
    // metodo para hacer el analisis del usuario autenticado
    AnalisisResponse analizar(String email);

    // metodo para hacer el analisi de un usuario por Id
    AnalisisResponse analizarPorUsuarioId(Long usuarioId);
}
</file>

<file path="backend/src/main/resources/db/migration/V1__create_users_table.sql">
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    sexo VARCHAR(20),
    estado_civil VARCHAR(20),
    numero_hijos INTEGER,
    rol VARCHAR(20) DEFAULT 'USER',
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
</file>

<file path="backend/src/main/resources/db/migration/V3__create_analysis_table.sql">
CREATE TABLE historial_analisis (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    perfil_financiero VARCHAR(50) NOT NULL,
    probabilidad DECIMAL(4,2) NOT NULL,
    nivel_endeudamiento INTEGER NOT NULL,
    frecuencia_ahorro VARCHAR(20) NOT NULL,
    resumen_gastos JSONB,
    recomendaciones JSONB NOT NULL,
    fecha_analisis TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_historial_analisis_usuario
        FOREIGN KEY (usuario_id)
            REFERENCES usuarios(id)
);
</file>

<file path="frontend/js/auth.js">
// ==========================================
// Módulo de Autenticación (Login y Registro)
// ==========================================
const BASE_URL = 'http://localhost:8080/api/v1';

// ==========================================
// 1. Manejo de Inicio de Sesión (Login)
// ==========================================
const formLogin = document.getElementById('formLogin');
if (formLogin) {
    formLogin.addEventListener('submit', async (e) => {
        e.preventDefault();

        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;

        try {
            const response = await fetch(`${BASE_URL}/auth/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const data = await response.json();
                
                // AUD-01: El backend Java devuelve el JWT dentro del campo 'message' debido al diseño del record AuthResponse
                const token = data.message || data.token;
                
                if (token) {
                    localStorage.setItem('jwtToken', token);
                    // AUD-18: Redirigir al dashboard unificado
                    window.location.href = 'dashboard.html';
                } else {
                    alert('Error crítico: No se encontró el token de acceso en la respuesta.');
                }
            } else {
                alert('Credenciales inválidas o error en el servidor.');
            }
        } catch (error) {
            console.error('Error de red en login:', error);
            alert('No se pudo conectar con el servidor backend.');
        }
    });
}

// ==========================================
// 2. Manejo de Registro y Perfil (AUD-19)
// ==========================================
const formRegister = document.getElementById('formRegister');
if (formRegister) {
    formRegister.addEventListener('submit', async (e) => {
        e.preventDefault();

        // Estructura exigida por RegisterRequest en el backend Java
        const registerPayload = {
            nombre: document.getElementById('regNombre').value,
            apellido: document.getElementById('regApellido').value,
            email: document.getElementById('regEmail').value,
            password: document.getElementById('regPassword').value,
            fechaNacimiento: document.getElementById('regFechaNacimiento').value,
            sexo: document.getElementById('regSexo').value,
            estadoCivil: document.getElementById('regEstadoCivil').value,
            numeroHijos: parseInt(document.getElementById('regNumeroHijos').value || 0)
        };

        // Datos del perfil financiero capturados en el mismo formulario (AUD-19)
        const perfilPayload = {
            ingresoMensual: parseFloat(document.getElementById('regIngresoMensual').value || 0),
            lineaCredito: parseFloat(document.getElementById('regLineaCredito').value || 0),
            empleoFormal: document.getElementById('regEmpleoFormal').checked
        };

        try {
            // Paso A: Registrar usuario en el backend
            const responseReg = await fetch(`${BASE_URL}/auth/register`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(registerPayload)
            });

            if (!responseReg.ok) {
                alert('Error al registrar el usuario. Es posible que el correo ya esté en uso.');
                return;
            }

            const dataReg = await responseReg.json();
            const token = dataReg.message || dataReg.token;

            if (token) {
                // Guardar token temporalmente para autenticar la petición de perfil
                localStorage.setItem('jwtToken', token);

                // Paso B: Crear automáticamente el perfil financiero (Solución a AUD-19)
                try {
                    const responsePerfil = await fetch(`${BASE_URL}/perfil`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        },
                        body: JSON.stringify(perfilPayload)
                    });

                    if (responsePerfil.ok) {
                        localStorage.setItem('perfilCompletado', 'true');
                    } else {
                        console.warn('El usuario se creó pero hubo un problema al guardar el perfil financiero inicial.');
                    }
                } catch (perfilError) {
                    console.error('Error de red al crear perfil financiero:', perfilError);
                }

                // Paso C: Redirigir al Dashboard (AUD-18)
                window.location.href = 'dashboard.html';
            } else {
                alert('Registro exitoso, pero no se obtuvo el token. Inicia sesión manualmente.');
                window.location.href = 'index.html';
            }
        } catch (error) {
            console.error('Error general en el registro:', error);
            alert('Ocurrió un error inesperado durante el proceso de registro.');
        }
    });
}
</file>

<file path="mock-api/Dockerfile">
FROM python:3.13-slim

WORKDIR /app

COPY . .

RUN pip install --no-cache-dir fastapi uvicorn

EXPOSE 8001

CMD ["uvicorn","app.main:app","--host","0.0.0.0","--port","8001"]
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/AnalisisController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.service.AnalisisIAService;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analisis/predict")
@RequiredArgsConstructor
@Tag(name = "Analisis",description = "Generacion de diagnosticos financieros generados por modelo dataScience"
)
public class AnalisisController {
    private final AnalisisIAService  analisisIAService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/usuario/{usuarioId}")
    public AnalisisResponse analisisPorUsuario(
            @PathVariable Long usuarioId
    ) {
        return analisisIAService.analizarPorUsuarioId(usuarioId);
    }

    @PostMapping
    public AnalisisResponse  analizar(@AuthenticationPrincipal UserDetails userDetails) {
        return analisisIAService.analizar(userDetails.getUsername());

    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/ErrorResponse.java">
package com.nocountry.financeai.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        List<String> message,
        LocalDateTime timestamp
) {
    // Constructor secundario inteligente para asignar la fecha y hora automáticamente
    public ErrorResponse(
            int status,
            String error,
            List<String> message
    ) {
        this(status, error, message, LocalDateTime.now());
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/AuthServiceImpl.java">
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
            throw new UserAlreadyExistsException("El correo ya está registrado");
        }

        // 2. Usamos request.nombre() tal cual lo definiste en tu record
        UserEntity user = UserEntity.builder()
                .nombre(request.nombre())
                .apellido(request.apellido())
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
</file>

<file path="backend/src/main/resources/application.yml">
spring:
  application:
    name: financeai

  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
    show-sql: true
    open-in-view: false # Resuelve el hallazgo AUD-11

  flyway:
    enabled: true
    baseline-on-migrate: true

jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXPIRATION}

ia:
  api:
    url: ${IA_API_URL}

# Configuración de Logs para depuración local
logging:
  level:
    org.springframework.web: INFO
    com.nocountry.financeai: DEBUG
    # Muestra los valores reales inyectados en las sentencias SQL de Hibernate
    org.hibernate.orm.jdbc.bind: TRACE
    org.hibernate.orm.jdbc.extract: TRACE
</file>

<file path="backend/Dockerfile">
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

# NUEVA LÍNEA: Limpieza de saltos de línea de Windows (CRLF a LF)
RUN sed -i 's/\r$//' mvnw

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
FROM eclipse-temurin:21-jre
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
</file>

<file path="frontend/index.html">
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FinanceAI - Acceso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="bg-light d-flex align-items-center" style="min-height: 100vh;">

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-5">
                <div class="card shadow-sm border-0">
                    <div class="card-body p-4 p-md-5">
                        <div class="text-center mb-4">
                            <h2 class="text-primary fw-bold">FinanceAI</h2>
                            <p class="text-muted">Tu asistente inteligente de salud financiera</p>
                        </div>
                        
                        <div id="alertPlaceholder"></div>    
                        <ul class="nav nav-pills nav-justified mb-4" id="authTabs" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="login-tab" data-bs-toggle="pill" data-bs-target="#login" type="button" role="tab">Iniciar Sesión</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="register-tab" data-bs-toggle="pill" data-bs-target="#register" type="button" role="tab">Registrarse</button>
                            </li>
                        </ul>

                        <div class="tab-content" id="authTabsContent">
                            <div class="tab-pane fade show active" id="login" role="tabpanel">
                                <form id="formLogin">
                                    <div class="mb-3">
                                        <label class="form-label text-secondary">Correo Electrónico</label>
                                        <input type="email" id="loginEmail" class="form-control" autocomplete="email" required>
                                    </div>
                                    <div class="mb-4">
                                        <label class="form-label text-secondary">Contraseña</label>
                                        <input type="password" id="loginPassword" class="form-control" autocomplete="current-password" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary w-100 fw-bold">Ingresar</button>
                                </form>
                            </div>

                            <div class="tab-pane fade" id="register" role="tabpanel">
                                <form id="formRegister">
                                    
                                    <h6 class="text-primary border-bottom pb-2 mb-3 mt-2">Credenciales y Datos Personales</h6>
                                    
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Nombre</label>
                                            <input type="text" id="regNombre" class="form-control form-control-sm" required>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">Apellido</label>
                                            <input type="text" id="regApellido" class="form-control form-control-sm" required>
                                        </div>
                                    </div>
                                    
                                    <div class="mb-2">
                                        <label class="form-label text-secondary small">Correo Electrónico</label>
                                        <input type="email" id="regEmail" class="form-control form-control-sm" autocomplete="email" required>
                                    </div>
                                    
                                    <div class="mb-3">
                                        <label class="form-label text-secondary small">Contraseña</label>
                                        <input type="password" id="regPassword" class="form-control form-control-sm" autocomplete="new-password" required>
                                    </div>

                                    <h6 class="text-primary border-bottom pb-2 mb-3">Perfil Demográfico Inicial</h6>
                                    
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Fecha Nacimiento</label>
                                            <input type="date" id="regFechaNacimiento" class="form-control form-control-sm" required>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">Sexo</label>
                                            <select id="regSexo" class="form-select form-select-sm" required>
                                                <option value="MASCULINO">Masculino</option>
                                                <option value="FEMININO">Femenino</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Estado Civil</label>
                                            <select id="regEstadoCivil" class="form-select form-select-sm" required>
                                                <option value="SOLTERO">Soltero/a</option>
                                                <option value="CASADO">Casado/a</option>
                                                <option value="DIVORCIADO">Divorciado/a</option>
                                                <option value="VIUDO">Viudo/a</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">N° Hijos</label>
                                            <input type="number" id="regNumeroHijos" class="form-control form-control-sm" value="0" min="0" required>
                                        </div>
                                    </div>

                                    <h6 class="text-primary border-bottom pb-2 mb-3">Perfil Financiero Inicial (AUD-19)</h6>
                                    
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Ingreso Mensual ($)</label>
                                            <input type="number" step="0.01" id="regIngresoMensual" class="form-control form-control-sm" required>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">Línea de Crédito ($)</label>
                                            <input type="number" step="0.01" id="regLineaCredito" class="form-control form-control-sm" required>
                                        </div>
                                    </div>

                                    <div class="mb-4 form-check">
                                        <input type="checkbox" class="form-check-input" id="regEmpleoFormal">
                                        <label class="form-check-label small text-secondary">¿Tienes empleo formal?</label>
                                    </div>

                                    <button type="submit" class="btn btn-success w-100 fw-bold">Crear Cuenta y Continuar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/auth.js"></script>
</body>
</html>
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/AnalisisRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
@Schema(description = "Datos solicitodos al cliente, necesarios para realizar el analisis")
public record AnalisisRequest (
        @NotNull(message = "Edad es obligatoria")
        @Min(value = 18, message = "La edad minima es 18 años")
        Integer edad,

        @NotNull(message = "El sexo es obligatorio")
        Sexo sexo,

        @JsonProperty("estado_civil")
        @NotNull(message = "Estado civil es obligatorio")
        EstadoCivil estadoCivil,

        @JsonProperty("numero_hijos")
        @NotNull(message = "si tiene hijos, indicar cuantos" )
        @Min(value = 0, message = "numero de hijos no puede ser negativo")
        Integer numeroHijos,

        @JsonProperty("empleo_formal")
        @NotNull(message = "si tiene empleo, indicar cuantos")
        @Min(value = 0, message = "El numero de empleos no puede ser negativo")
        Integer empleoFormal,

        @JsonProperty("ingreso_mensual")
        @Schema(
                description = "Ingreso mensual del usuario",
                example = "4500"
        )
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @JsonProperty("linea_credito")
        @NotNull(message = "La línea de crédito es obligatoria")
        @DecimalMin(
                value = "0.0",
                inclusive = true,
                message = "La línea de crédito no puede ser negativa"
        )
        BigDecimal lineaCredito,

        @Schema(
                description = "Lista de transacciones que un usuario realiza, Debe incluir minimo una"
        )
        @NotEmpty(message = "Se debe enviar al menos una transaccion")
        List<@Valid TransactionRequest> transacciones
) {}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/ApiExceptionHandler.java">
package com.nocountry.financeai.exception;

import com.nocountry.financeai.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Manejo centralizado de excepciones de la API.
 *
 * Ajustado para mantener respuestas HTTP consistentes mediante ResponseEntity
 * y conservar información detallada de errores para el cliente.
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    // Maneja los recursos que no existen y devuelve HTTP 404.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> gestionarRecursoNoEncontrado(
            ResourceNotFoundException ex
    ) {
        log.warn("Recurso no encontrado: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                404,
                "Recurso no encontrado",
                List.of(ex.getMessage()),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    // Maneja conflictos cuando el usuario intenta crear un perfil financiero que ya existe.
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> gestionarEstadoInvalido(
            IllegalStateException ex
    ) {
        log.warn("Conflicto en el estado de la solicitud: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    // Manejo general de errores no controlados de la aplicación.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> gestionarErrorGeneral(Exception e) {
        log.error("Error interno del servidor", e);

        ErrorResponse error = new ErrorResponse(
                500,
                "Error interno del servidor",
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    // Ajustado para devolver errores de validación detallados por campo, manteniendo el código HTTP correcto (400 Bad Request).
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> gestionarErroresValidacion(
            MethodArgumentNotValidException ex
    ) {
        log.warn("Se recibieron datos inválidos en la petición");

        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorResponse error = new ErrorResponse(
                400,
                "Error de validacion",
                errores,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    // Ajustado para diferenciar fallos de disponibilidad del serivicio de analisis(mock-api/modelo-dataScienc) mediante respuesta HTTP 503 Service Unavailable.
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> gestionarErrorConexionIA(
            ResourceAccessException ex
    ) {
        log.error("No fue posible conectar con la API de analisis", ex);

        ErrorResponse error = new ErrorResponse(
                503,
                "El servicio de Analisis no esta disponible",
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(error);
    }

    // Manejo de credenciales con autenticiacion JWT
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException ex
    ) {
        log.warn("Intento de login fallido");

        ErrorResponse error = new ErrorResponse(
                401,
                "Credenciales inválidas. Verifica tu correo y contraseña.",
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }
}
</file>

<file path="docker-compose.yml">
services:
  postgres-db:
    image: postgres:16-alpine
    container_name: financeai_postgres
    environment:
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  # Mock obsoleto desplazado al puerto 8001
  mock-api:
    build: ./mock-api
    container_name: financeai_mock_api
    ports:
      - "8001:8001"

  # Nuevo motor de IA real (Canónico)
  modelo-financeai:
    build: ./data-science/modeloFinanceAI
    container_name: financeai_modelo
    ports:
      - "8000:8000"

  backend:
    build: ./backend
    container_name: financeai_backend
    ports:
      - "8080:8080"
    depends_on:
      - postgres-db
      - modelo-financeai
    environment:
      SPRING_DATASOURCE_URL: ${SPRING_DATASOURCE_URL}
      SPRING_DATASOURCE_USERNAME: ${POSTGRES_USER}
      SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
      IA_API_URL: ${IA_API_URL}
      JWT_SECRET: ${JWT_SECRET}
      JWT_EXPIRATION: ${JWT_EXPIRATION}

volumes:
  postgres_data:
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/SecurityConfig.java">
package com.nocountry.financeai.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
                            .requestMatchers(
                                    "/api/v1/auth/**"
                            ).permitAll()

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
</file>

<file path=".gitignore">
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
__pycache__/
*.pyc
repomix.config.json
__pycache__/
*.pyc
</file>

<file path="backend/pom.xml">
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

        <!-- Base de Datos y Migraciones -->
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

        <!-- Utilidades -->
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
            <version>3.0.3</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId> <!-- CORREGIDO: Unificado en el starter oficial -->
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
</file>

</files>
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/AuthController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.LoginRequest;
import com.nocountry.financeai.dto.request.RegisterRequest;
import com.nocountry.financeai.dto.response.AuthResponse;
import com.nocountry.financeai.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(
        name = "Autenticacion",
        description = "Registro y login de usuarios")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/LoginRequest.java">
package com.nocountry.financeai.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @Schema(
                description = "email del usuario",
                example = "carlosgomez@gmail.com"
        )
        @NotBlank(message = "El correo electrónico es obligatorio")
        @Email(message = "El formato del correo es inválido")
        String email,

        @Schema(
                description = "clave del usuario",
                example = "Passwd123*"
        )
        @NotBlank(message = "La contraseña es obligatoria")
       String password
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/ErrorResponse.java">
package com.nocountry.financeai.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        int status,
        String error,
        List<String> message,
        LocalDateTime timestamp
) {
    // Constructor secundario inteligente para asignar la fecha y hora automáticamente
    public ErrorResponse(
            int status,
            String error,
            List<String> message
    ) {
        this(status, error, message, LocalDateTime.now());
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/entity/UserEntity.java">
package com.nocountry.financeai.entity;

import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Rol;
import com.nocountry.financeai.entity.enums.Sexo;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"relationLazy"})
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(name = "documento", nullable = false, unique = true, length = 30)
    private String documento;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_civil", length = 20)
    private EstadoCivil estadoCivil;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Sexo sexo;

    @Column(name = "numero_hijos")
    private Integer numeroHijos;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Rol rol = Rol.USER;

    @Builder.Default
    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PerfilFinancieroEntity perfilFinanciero;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TransactionEntity> transacciones;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HistorialAnalisisEntity> historialAnalisis;

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
</file>

<file path="backend/src/main/java/com/nocountry/financeai/repository/UserRepository.java">
package com.nocountry.financeai.repository;

import com.nocountry.financeai.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Utilizado en el Login para buscar al usuario
    Optional<UserEntity> findByEmail(String email);

    // Busca usuario por documeneto de identidad
    Optional<UserEntity> findByDocumento(String documento);

    // Utilizado en el Registro para evitar correos duplicados
    boolean existsByEmail(String email);

    // verifica si existe un documento
    boolean existsByDocumento(String documento);
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/AnalisisIAServiceImpl.java">
package com.nocountry.financeai.service.impl;

import com.nocountry.financeai.client.IAClient;
import com.nocountry.financeai.dto.request.AnalisisRequest;
import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.entity.HistorialAnalisisEntity;
import com.nocountry.financeai.entity.PerfilFinancieroEntity;
import com.nocountry.financeai.entity.TransactionEntity;
import com.nocountry.financeai.entity.UserEntity;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.exception.ResourceNotFoundException;
import com.nocountry.financeai.repository.HistorialAnalisisRepository;
import com.nocountry.financeai.repository.PerfilFinancieroRepository;
import com.nocountry.financeai.repository.TransactionRepository;
import com.nocountry.financeai.repository.UserRepository;
import com.nocountry.financeai.service.AnalisisIAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalisisIAServiceImpl implements AnalisisIAService {

    private final IAClient iaClient;
    private final UserRepository userRepository;
    private final PerfilFinancieroRepository perfilFinancieroRepository;
    private final TransactionRepository transactionRepository;
    private final HistorialAnalisisRepository historialAnalisisRepository;

    @Override
    public AnalisisResponse analizar(String email) {
        // Su busca el usuario por email, se usa el Id para hacer el analisis
        UserEntity usuario = userRepository
                .findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"
                        ));
        return analizarPorUsuarioId(usuario.getId());
    }

    @Override
    public AnalisisResponse analizarPorDocumento(String documento) {
        UserEntity usuario = userRepository
                .findByDocumento(documento)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));
        return analizarPorUsuarioId(usuario.getId());
    }

    @Override
    public AnalisisResponse analizarPorUsuarioId(Long usuarioId) {
        // Busca el usuario por el id y se guarda
        UserEntity usuario = userRepository
                .findById(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario no encontrado"));

        // Calcula la edad del usuario
        Integer edad = Period.between(usuario.getFechaNacimiento(), LocalDate.now()).getYears();

        // Busca el perfil financiero asociado al usuario, si no tiene envia exepcion
        PerfilFinancieroEntity perfil = perfilFinancieroRepository
                .findByUsuarioId(usuarioId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "El usuario no tiene un perfil financiero registrado"));

        // Guarda las transacciones de un usuario en una lista
        List<TransactionRequest> transaccionesRequest = transactionRepository
                .findByUsuarioId(usuarioId)
                .stream()
                .map(this::convertirTransaccion)
                .toList();

        if (transaccionesRequest.isEmpty()) {
            throw new IllegalStateException("El usuario debe tener al menos una transacción registrada para generar un análisis");
        }

        // Teniendo tadas las variable para el analisis crea el request
        AnalisisRequest request = convertirAnalisis(edad, usuario, perfil, transaccionesRequest);

        // Envia la peticion para hacer el analisis y guarda la respuesta
        AnalisisResponse response = iaClient.analizar(request);

        // Guarda el analisis al usuario
        guardarHistorial(usuario, response);

        return response;
    }


    // metodos privados para convertir entidad en request

    private TransactionRequest convertirTransaccion(TransactionEntity entity) {
        return new TransactionRequest(
                entity.getNombreComercio(),
                entity.getMontoTransaccion(),
                entity.getMedioPago()
        );
    }

    private AnalisisRequest convertirAnalisis(Integer edad, UserEntity usuario, PerfilFinancieroEntity perfil, List<TransactionRequest> transaccionRequest) {
        return new AnalisisRequest(edad,
                usuario.getSexo(),
                usuario.getEstadoCivil(),
                usuario.getNumeroHijos(),
                perfil.getEmpleoFormal(),
                perfil.getIngresoMensual(),
                perfil.getLineaCredito(),
                transaccionRequest
        );
    }


    // metodo privado de la clase para guarda el historial en la base de datos
    private void guardarHistorial(UserEntity usuario, AnalisisResponse response) {
        HistorialAnalisisEntity historial = HistorialAnalisisEntity.builder()
                .usuario(usuario)
                .perfilFinanciero(response.perfilFinanciero())
                .probabilidad(response.probabilidad())
                .nivelEndeudamiento(response.nivelEndeudamiento())
                .rangoAhorro(response.rangoAhorro())
                .resumenGastos(response.resumenGastos())
                .recomendaciones(response.recomendaciones())
                .build();

        historialAnalisisRepository.save(historial);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/AnalisisIAService.java">
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
</file>

<file path="backend/src/main/resources/db/migration/V1__create_users_table.sql">
CREATE TABLE usuarios (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100),
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    sexo VARCHAR(20),
    estado_civil VARCHAR(20),
    numero_hijos INTEGER,
    rol VARCHAR(20) DEFAULT 'USER',
    activo BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
</file>

<file path="backend/src/main/resources/application.yml">
spring:
  application:
    name: financeai

  datasource:
    url: ${SPRING_DATASOURCE_URL}
    username: ${SPRING_DATASOURCE_USERNAME}
    password: ${SPRING_DATASOURCE_PASSWORD}
    driver-class-name: org.postgresql.Driver

  jpa:
    hibernate:
      ddl-auto: validate
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
    show-sql: true
    open-in-view: false # Resuelve el hallazgo AUD-11

  flyway:
    enabled: true
    baseline-on-migrate: true

jwt:
  secret: ${JWT_SECRET}
  expiration: ${JWT_EXPIRATION}

ia:
  api:
    url: ${IA_API_URL}

# Configuración de Logs para depuración local
logging:
  level:
    org.springframework.web: INFO
    com.nocountry.financeai: DEBUG
    # Muestra los valores reales inyectados en las sentencias SQL de Hibernate
    org.hibernate.orm.jdbc.bind: TRACE
    org.hibernate.orm.jdbc.extract: TRACE
</file>

<file path="backend/Dockerfile">
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

# NUEVA LÍNEA: Limpieza de saltos de línea de Windows (CRLF a LF)
RUN sed -i 's/\r$//' mvnw

RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
FROM eclipse-temurin:21-jre
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
</file>

<file path="frontend/js/auth.js">
// ==========================================
// Módulo de Autenticación (Login y Registro)
// ==========================================
const BASE_URL = 'http://localhost:8080/api/v1';

// ==========================================
// 1. Manejo de Inicio de Sesión (Login)
// ==========================================
const formLogin = document.getElementById('formLogin');
if (formLogin) {
    formLogin.addEventListener('submit', async (e) => {
        e.preventDefault();

        const email = document.getElementById('loginEmail').value;
        const password = document.getElementById('loginPassword').value;

        try {
            const response = await fetch(`${BASE_URL}/auth/login`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ email, password })
            });

            if (response.ok) {
                const data = await response.json();

                // El backend devuelve el JWT real en el campo 'token' (AuthResponse.token)
                const token = data.token;

                if (token) {
                    localStorage.setItem('jwtToken', token);
                    // AUD-18: Redirigir al dashboard unificado
                    window.location.href = 'dashboard.html';
                } else {
                    alert('Error crítico: No se encontró el token de acceso en la respuesta.');
                }
            } else {
                alert('Credenciales inválidas o error en el servidor.');
            }
        } catch (error) {
            console.error('Error de red en login:', error);
            alert('No se pudo conectar con el servidor backend.');
        }
    });
}

// ==========================================
// 2. Manejo de Registro y Perfil (AUD-19)
// ==========================================
const formRegister = document.getElementById('formRegister');
if (formRegister) {
    formRegister.addEventListener('submit', async (e) => {
        e.preventDefault();

        // Estructura exigida por RegisterRequest en el backend Java
        const registerPayload = {
            nombre: document.getElementById('regNombre').value,
            apellido: document.getElementById('regApellido').value,
            email: document.getElementById('regEmail').value,
            password: document.getElementById('regPassword').value,
            fechaNacimiento: document.getElementById('regFechaNacimiento').value,
            sexo: document.getElementById('regSexo').value,
            estadoCivil: document.getElementById('regEstadoCivil').value,
            numeroHijos: parseInt(document.getElementById('regNumeroHijos').value || 0)
        };

        // Datos del perfil financiero capturados en el mismo formulario (AUD-19)
        const perfilPayload = {
            ingresoMensual: parseFloat(document.getElementById('regIngresoMensual').value || 0),
            lineaCredito: parseFloat(document.getElementById('regLineaCredito').value || 0),
            empleoFormal: document.getElementById('regEmpleoFormal').checked
        };

        try {
            // Paso A: Registrar usuario en el backend
            const responseReg = await fetch(`${BASE_URL}/auth/register`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(registerPayload)
            });

            if (!responseReg.ok) {
                alert('Error al registrar el usuario. Es posible que el correo ya esté en uso.');
                return;
            }

            const dataReg = await responseReg.json();

            // El backend devuelve el JWT real en el campo 'token' (AuthResponse.token)
            const token = dataReg.token;

            if (token) {
                // Guardar token temporalmente para autenticar la petición de perfil
                localStorage.setItem('jwtToken', token);

                // Paso B: Crear automáticamente el perfil financiero (Solución a AUD-19)
                try {
                    const responsePerfil = await fetch(`${BASE_URL}/perfil`, {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json',
                            'Authorization': `Bearer ${token}`
                        },
                        body: JSON.stringify(perfilPayload)
                    });

                    if (responsePerfil.ok) {
                        localStorage.setItem('perfilCompletado', 'true');
                    } else {
                        console.warn('El usuario se creó pero hubo un problema al guardar el perfil financiero inicial.');
                    }
                } catch (perfilError) {
                    console.error('Error de red al crear perfil financiero:', perfilError);
                }

                // Paso C: Redirigir al Dashboard (AUD-18)
                window.location.href = 'dashboard.html';
            } else {
                alert('Registro exitoso, pero no se obtuvo el token. Inicia sesión manualmente.');
                window.location.href = 'index.html';
            }
        } catch (error) {
            console.error('Error general en el registro:', error);
            alert('Ocurrió un error inesperado durante el proceso de registro.');
        }
    });
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/AnalisisController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.response.AnalisisResponse;
import com.nocountry.financeai.service.AnalisisIAService;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/analisis/predict")
@RequiredArgsConstructor
@Tag(
        name = "Analisis",
        description = "Generacion de diagnosticos financieros generados por modelo dataScience"
)
public class AnalisisController {
    private final AnalisisIAService  analisisIAService;

    @PostMapping
    public AnalisisResponse  analizar(@AuthenticationPrincipal UserDetails userDetails) {
        return analisisIAService.analizar(userDetails.getUsername());

    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/AnalisisRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Enumerated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
@Schema(description = "Datos solicitodos al cliente, necesarios para realizar el analisis")
public record AnalisisRequest (
        @NotNull(message = "Edad es obligatoria")
        @Min(value = 18, message = "La edad minima es 18 años")
        Integer edad,

        @NotNull(message = "El sexo es obligatorio")
        Sexo sexo,

        @JsonProperty("estado_civil")
        @NotNull(message = "Estado civil es obligatorio")
        EstadoCivil estadoCivil,

        @JsonProperty("numero_hijos")
        @NotNull(message = "si tiene hijos, indicar cuantos" )
        @Min(value = 0, message = "numero de hijos no puede ser negativo")
        Integer numeroHijos,

        @JsonProperty("empleo_formal")
        @NotNull(message = "si tiene empleo, indicar cuantos")
        @Min(value = 0, message = "El numero de empleos no puede ser negativo")
        Integer empleoFormal,

        @JsonProperty("ingreso_mensual")
        @Schema(
                description = "Ingreso mensual del usuario",
                example = "4500"
        )
        @NotNull(message = "El ingreso mensual es obligatorio")
        @Positive(message = "El ingreso mensual debe ser mayor a cero")
        BigDecimal ingresoMensual,

        @JsonProperty("linea_credito")
        @NotNull(message = "La línea de crédito es obligatoria")
        @DecimalMin(
                value = "0.0",
                inclusive = true,
                message = "La línea de crédito no puede ser negativa"
        )
        BigDecimal lineaCredito,

        @Schema(
                description = "Lista de transacciones que un usuario realiza, Debe incluir minimo una"
        )
        @NotEmpty(message = "Se debe enviar al menos una transaccion")
        List<@Valid TransactionRequest> transacciones
) {}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/RegisterRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.EstadoCivil;
import com.nocountry.financeai.entity.enums.Sexo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record RegisterRequest(

        @Schema(
                description = "Nombre del usuario",
                example = "Carlos"
        )
        @NotBlank(message = "El nombre no puede estar vacío")
        String nombre,

        @Schema(
                description = "Apellido del usuario",
                example = "Gómez"
        )
        @NotBlank(message = "El apellido no puede estar vacío")
        String apellido,

        @Schema(
                description = "Documento o Identificacion del usuario",
                example = "PEMJ920323HJCZNN0"
        )
        @NotBlank(message = "El documento es obligatorio")
        @Size(
                min = 5,
                max = 30,
                message = "El documento debe tener minimo 5 y 30 caracteres"
        )
        String documento,

        @Schema(
                description = "Email del usuario",
                example = "carlosgomez@gmail.com"
        )
        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "El formato del email no es válido")
        String email,

        @Schema(
                description = "Contraseña del usuario",
                example = "Passwd123*"
        )
        @NotBlank(message = "La contraseña no puede estar vacía")
        @Size(
                min = 8,
                message = "La contraseña debe tener al menos 8 caracteres"
        )
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._-]).+$",
                message = "La contraseña debe contener al menos una mayúscula, una minúscula, un número y un carácter especial."
        )
        String password,

        @Schema(
                description = "Fecha de nacimiento del usuario",
                example = "1995-06-15"
        )
        @JsonProperty("fecha_nacimiento")
        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe estar en el pasado")
        LocalDate fechaNacimiento,

        @Schema(
                description = "Sexo del usuario",
                example = "masculino"
        )
        @NotNull(message = "El sexo es obligatorio")
        Sexo sexo,

        @Schema(
                description = "Estado civil del usuario",
                example = "soltero"
        )
        @JsonProperty("estado_civil")
        @NotNull(message = "El estado civil es obligatorio")
        EstadoCivil estadoCivil,

        @Schema(
                description = "Número de hijos del usuario",
                example = "0"
        )
        @JsonProperty("numero_hijos")
        @NotNull(message = "El número de hijos es obligatorio")
        @Min(
                value = 0,
                message = "El número de hijos no puede ser negativo"
        )
        Integer numeroHijos

) {
    @AssertTrue(
            message = "El usuario debe ser mayor de 18 años"
    )
    public boolean esMayorDeEdad() {
        return fechaNacimiento != null
                && !fechaNacimiento.isAfter(
                LocalDate.now().minusYears(18)
        );
    }


}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/request/TransactionRequest.java">
package com.nocountry.financeai.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.MedioPago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record TransactionRequest(

        @Schema(
                description = "Nombre del comercio donde se realizó la transacción",
                example = "Supermercado Éxito"
        )
        @JsonProperty("nombre_comercio")
        @NotBlank(message = "El nombre del comercio es obligatorio")
        String nombreComercio,

        @Schema(
                description = "Monto de la transacción",
                example = "210.00"
        )
        @JsonProperty("monto_transaccion")
        @NotNull(message = "El monto de la transacción es obligatorio")
        @Positive(message = "El monto debe ser mayor a cero")
        BigDecimal montoTransaccion,

        @Schema(
                description = "Medio de pago utilizado",
                example = "debito"
        )

        @JsonProperty("medio_pago")
        @NotNull(message = "El medio de pago es obligatorio")
        MedioPago medioPago

) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/dto/response/AnalisisResponse.java">
package com.nocountry.financeai.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nocountry.financeai.entity.enums.PerfilFinanciero;
import com.nocountry.financeai.entity.enums.RangoAhorro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@Schema(description = "Resultado del analisis financiero generado a partir de los datos del usuario")
public record AnalisisResponse(

        @Schema(
                description = "Clasificacion del perfil financiero del usuario segun analisis",
                example = "En_Observacion",
                allowableValues = {"Saludable", "En observacion", "En riesgo" }
        )
        @JsonProperty("perfil_financiero")
        PerfilFinanciero perfilFinanciero,

        @Schema(
                description = "Probabilidad o nivel de confianza del modelo asociad al perlfil asignado, rango de 0 a 1",
                example = "0.82"
        )
        BigDecimal probabilidad,


        @Schema(
                description = "Porcentaje del nivel de endeudamiento de un usuario",
                example = "0.45"
        )
        @JsonProperty("nivel_endeudamiento")
        BigDecimal nivelEndeudamiento,

        @Schema(
                description = "Clasificacion del nivel de ahorro del usuario (Alta, Media, Baja, Ninguna)",
                example = "ALTA"
        )
        @JsonProperty("rango_ahorro")
        RangoAhorro rangoAhorro,

        @Schema(
                description ="Resumen de gastos agrupados por categoria. Las claves del mapa son las categorias detectadas por el modelo",
                example ="{\"alimentacion\": 650, \"transporte\": 360, \"entretenimiento\":70}"
        )
        @JsonProperty("resumen_gastos")
        Map<String, BigDecimal> resumenGastos,

        @Schema(
                description = "lista de recomendaciones financieras generadas por el modelo, para el usuario",
                example = "[\"Monitorear los gastos recurrentes de entretenimiento\", \"Aumentar la reserva financiera mensual\"]"
        )
        List<String> recomendaciones
) {
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/service/impl/AuthServiceImpl.java">
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
</file>

<file path="backend/src/main/resources/db/migration/V3__create_analysis_table.sql">
CREATE TABLE historial_analisis (
    id BIGSERIAL PRIMARY KEY,
    usuario_id BIGINT NOT NULL,
    perfil_financiero VARCHAR(50) NOT NULL,
    probabilidad DECIMAL(4,2) NOT NULL,
    nivel_endeudamiento NUMERIC(4,2) NOT NULL,
    rango_ahorro VARCHAR(20) NOT NULL,
    resumen_gastos JSONB,
    recomendaciones JSONB NOT NULL,
    fecha_analisis TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_historial_analisis_usuario
        FOREIGN KEY (usuario_id)
            REFERENCES usuarios(id)
);
</file>

<file path="frontend/index.html">
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FinanceAI - Acceso</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body class="bg-light d-flex align-items-center" style="min-height: 100vh;">

    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 col-lg-5">
                <div class="card shadow-sm border-0">
                    <div class="card-body p-4 p-md-5">
                        <div class="text-center mb-4">
                            <h2 class="text-primary fw-bold">FinanceAI</h2>
                            <p class="text-muted">Tu asistente inteligente de salud financiera</p>
                        </div>
                        
                        <div id="alertPlaceholder"></div>    
                        <ul class="nav nav-pills nav-justified mb-4" id="authTabs" role="tablist">
                            <li class="nav-item" role="presentation">
                                <button class="nav-link active" id="login-tab" data-bs-toggle="pill" data-bs-target="#login" type="button" role="tab">Iniciar Sesión</button>
                            </li>
                            <li class="nav-item" role="presentation">
                                <button class="nav-link" id="register-tab" data-bs-toggle="pill" data-bs-target="#register" type="button" role="tab">Registrarse</button>
                            </li>
                        </ul>

                        <div class="tab-content" id="authTabsContent">
                            <div class="tab-pane fade show active" id="login" role="tabpanel">
                                <form id="formLogin">
                                    <div class="mb-3">
                                        <label class="form-label text-secondary">Correo Electrónico</label>
                                        <input type="email" id="loginEmail" class="form-control" autocomplete="email" required>
                                    </div>
                                    <div class="mb-4">
                                        <label class="form-label text-secondary">Contraseña</label>
                                        <input type="password" id="loginPassword" class="form-control" autocomplete="current-password" required>
                                    </div>
                                    <button type="submit" class="btn btn-primary w-100 fw-bold">Ingresar</button>
                                </form>
                            </div>

                            <div class="tab-pane fade" id="register" role="tabpanel">
                                <form id="formRegister">
                                    
                                    <h6 class="text-primary border-bottom pb-2 mb-3 mt-2">Credenciales y Datos Personales</h6>
                                    
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Nombre</label>
                                            <input type="text" id="regNombre" class="form-control form-control-sm" required>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">Apellido</label>
                                            <input type="text" id="regApellido" class="form-control form-control-sm" required>
                                        </div>
                                    </div>
                                    
                                    <div class="mb-2">
                                        <label class="form-label text-secondary small">Correo Electrónico</label>
                                        <input type="email" id="regEmail" class="form-control form-control-sm" autocomplete="email" required>
                                    </div>
                                    
                                    <div class="mb-3">
                                        <label class="form-label text-secondary small">Contraseña</label>
                                        <input type="password" id="regPassword" class="form-control form-control-sm" autocomplete="new-password" required>
                                    </div>

                                    <h6 class="text-primary border-bottom pb-2 mb-3">Perfil Demográfico Inicial</h6>
                                    
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Fecha Nacimiento</label>
                                            <input type="date" id="regFechaNacimiento" class="form-control form-control-sm" required>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">Sexo</label>
                                            <select id="regSexo" class="form-select form-select-sm" required>
                                                <option value="MASCULINO">Masculino</option>
                                                <option value="FEMININO">Femenino</option>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="row mb-3">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Estado Civil</label>
                                            <select id="regEstadoCivil" class="form-select form-select-sm" required>
                                                <option value="SOLTERO">Soltero/a</option>
                                                <option value="CASADO">Casado/a</option>
                                                <option value="DIVORCIADO">Divorciado/a</option>
                                                <option value="VIUDO">Viudo/a</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">N° Hijos</label>
                                            <input type="number" id="regNumeroHijos" class="form-control form-control-sm" value="0" min="0" required>
                                        </div>
                                    </div>

                                    <h6 class="text-primary border-bottom pb-2 mb-3">Perfil Financiero Inicial (AUD-19)</h6>
                                    
                                    <div class="row mb-2">
                                        <div class="col">
                                            <label class="form-label text-secondary small">Ingreso Mensual ($)</label>
                                            <input type="number" step="0.01" id="regIngresoMensual" class="form-control form-control-sm" required>
                                        </div>
                                        <div class="col">
                                            <label class="form-label text-secondary small">Línea de Crédito ($)</label>
                                            <input type="number" step="0.01" id="regLineaCredito" class="form-control form-control-sm" required>
                                        </div>
                                    </div>

                                    <div class="mb-4 form-check">
                                        <input type="checkbox" class="form-check-input" id="regEmpleoFormal">
                                        <label class="form-check-label small text-secondary">¿Tienes empleo formal?</label>
                                    </div>

                                    <button type="submit" class="btn btn-success w-100 fw-bold">Crear Cuenta y Continuar</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="js/auth.js"></script>
</body>
</html>
</file>

<file path="backend/src/main/java/com/nocountry/financeai/controller/TransactionController.java">
package com.nocountry.financeai.controller;

import com.nocountry.financeai.dto.request.TransactionRequest;
import com.nocountry.financeai.dto.response.TransaccionResponse;
import com.nocountry.financeai.service.TransaccionService;
import com.nocountry.financeai.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transacciones")
@Tag(
        name = "Transacciones",
        description = "Registro y consulta de transacciones")
public class TransactionController {
    private final UserService userService;
    private final TransaccionService transaccionService;

    @PostMapping("/usuario/transacciones")
    public TransaccionResponse crearTransaccionAutenticado(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody TransactionRequest transactionRequest
    ) {
        return transaccionService.crearTransaccionAutenticado(
                userDetails.getUsername(),
                transactionRequest
        );
    }

    @GetMapping("/usuario/transacciones")
    public List<TransaccionResponse> obtenerMisTransacciones(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        return transaccionService.obtenerTransaccionesAutenticado(userDetails.getUsername());
    }

    @PatchMapping("/usuario/transacciones/{idTransaccion}")
    public TransaccionResponse actualizarTransaccionAutenticado(
            Authentication authentication,
            @PathVariable Long idTransaccion,
            @Valid @RequestBody TransactionRequest transactionRequest) {
        return transaccionService.actualizarTransaccion(authentication.getName(), idTransaccion, transactionRequest);
    }

    @DeleteMapping("/usuario/transacciones/{idTransaccion}")
    public ResponseEntity<Map<String,String>> eliminarTransaccionAutenticado(
            Authentication authentication,
            @PathVariable Long idTransaccion){
        transaccionService.eliminarTransaccion(authentication.getName(), idTransaccion);
        return ResponseEntity.ok(Map.of("message", "Transaccion eliminada correctamente"));
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/exception/ApiExceptionHandler.java">
package com.nocountry.financeai.exception;

import com.nocountry.financeai.dto.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Manejo centralizado de excepciones de la API.
 *
 * Ajustado para mantener respuestas HTTP consistentes mediante ResponseEntity
 * y conservar información detallada de errores para el cliente.
 */
@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {
    // Maneja los recursos que no existen y devuelve HTTP 404.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> gestionarRecursoNoEncontrado(
            ResourceNotFoundException ex
    ) {
        log.warn("Recurso no encontrado: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                404,
                "Recurso no encontrado",
                List.of(ex.getMessage()),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    // Maneja conflictos cuando el usuario intenta crear un perfil financiero que ya existe.
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> gestionarEstadoInvalido(
            IllegalStateException ex
    ) {
        log.warn("Conflicto en el estado de la solicitud: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> gestionarArgumentoInvalido(IllegalArgumentException ex){
        log.warn("Argumento invalido: {}", ex.getMessage());

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                List.of(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    // Maneja errores de acceso por falta de permisos
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> gestionarAccesoInvalido(AccessDeniedException ex){
        log.warn("Acceso invalido: {}", ex.getMessage());
        ErrorResponse error = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                List.of(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(error);
    }

    // Manejo general de errores no controlados de la aplicación.
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> gestionarErrorGeneral(Exception e) {
        log.error("Error interno del servidor", e);

        ErrorResponse error = new ErrorResponse(
                500,
                "Error interno del servidor",
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }

    // Ajustado para devolver errores de validación detallados por campo, manteniendo el código HTTP correcto (400 Bad Request).
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> gestionarErroresValidacion(
            MethodArgumentNotValidException ex
    ) {
        log.warn("Se recibieron datos inválidos en la petición");

        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        ErrorResponse error = new ErrorResponse(
                400,
                "Error de validacion",
                errores,
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    // Ajustado para diferenciar fallos de disponibilidad del serivicio de analisis(mock-api/modelo-dataScienc) mediante respuesta HTTP 503 Service Unavailable.
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> gestionarErrorConexionIA(
            ResourceAccessException ex
    ) {
        log.error("No fue posible conectar con la API de analisis", ex);

        ErrorResponse error = new ErrorResponse(
                503,
                "El servicio de Analisis no esta disponible",
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(error);
    }

    // Manejo de credenciales con autenticiacion JWT
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(
            BadCredentialsException ex
    ) {
        log.warn("Intento de login fallido");

        ErrorResponse error = new ErrorResponse(
                401,
                "Credenciales inválidas. Verifica tu correo y contraseña.",
                List.of(),
                LocalDateTime.now()
        );

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(error);
    }
}
</file>

<file path="backend/src/main/java/com/nocountry/financeai/security/SecurityConfig.java">
package com.nocountry.financeai.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
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
                            .requestMatchers(
                                    "/api/v1/auth/**"
                            ).permitAll()

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
</file>

<file path=".gitignore">
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
__pycache__/
*.pyc
</file>

<file path="docker-compose.yml">
services:
  postgres-db:
    image: postgres:16-alpine
    container_name: financeai_postgres
    environment:
      POSTGRES_DB: ${POSTGRES_DB}
      POSTGRES_USER: ${POSTGRES_USER}
      POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data
    networks:
      - financeai-net

  # Mock obsoleto desplazado al puerto 8001
  mock-api:
    build: ./mock-api
    container_name: financeai_mock_api
    ports:
      - "8001:8001"
    networks:
      - financeai-net

  # Nuevo motor de IA real (Canónico)
  modelo-financeai:
    build: ./data-science/modeloFinanceAI
    container_name: financeai_modelo
    ports:
      - "8000:8000"
    networks:
      - financeai-net

  backend:
    build: ./backend
    container_name: financeai_backend
    ports:
      - "8080:8080"
    depends_on:
      - postgres-db
      - modelo-financeai
    environment:
      SPRING_DATASOURCE_URL: ${SPRING_DATASOURCE_URL}
      SPRING_DATASOURCE_USERNAME: ${POSTGRES_USER}
      SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
      IA_API_URL: ${IA_API_URL}
      JWT_SECRET: ${JWT_SECRET}
      JWT_EXPIRATION: ${JWT_EXPIRATION}
    networks:
      - financeai-net

volumes:
  postgres_data:

networks:
  financeai-net:
    driver: bridge
</file>

<file path="backend/pom.xml">
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

        <!-- Base de Datos y Migraciones -->
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

        <!-- Utilidades -->
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
            <version>3.0.3</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId> <!-- CORREGIDO: Unificado en el starter oficial -->
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
</file>

</files>
