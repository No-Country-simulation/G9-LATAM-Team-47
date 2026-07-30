package com.nocountry.financeai.exception;

import com.nocountry.financeai.dto.response.ErrorResponse;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.ResourceAccessException;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@Hidden // Evita el Error 500 en Swagger
@RestControllerAdvice
public class ApiExceptionHandler {

    // 1. Error Genérico (500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> gestionarErrorGeneral(Exception e) {
        log.error("Error interno del servidor", e);

        ErrorResponse error = new ErrorResponse(
                "Internal Server Error",                  // error (Título)
                "Error interno del servidor",             // message (Detalle)
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // status (Ej: 500)
                LocalDateTime.now()                       // timestamp
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // 2. Errores de Validación (400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> gestionarErroresValidacion(MethodArgumentNotValidException ex) {
        log.warn("Se recibieron datos inválidos en la petición");

        // Convertimos la lista de errores en un solo String
        String errores = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse error = new ErrorResponse(
                "Bad Request",                            // error (Título)
                "Error de validación: " + errores,        // message (Detalle)
                HttpStatus.BAD_REQUEST.value(),           // status (Ej: 400)
                LocalDateTime.now()                       // timestamp
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 3. Error de Conexión a la IA (503)
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> gestionarErrorConexionIA(ResourceAccessException ex) {
        log.error("No fue posible conectar con la API de análisis");

        ErrorResponse error = new ErrorResponse(
                "Service Unavailable",                            // error (Título)
                "El servicio de Análisis no está disponible",     // message (Detalle)
                HttpStatus.SERVICE_UNAVAILABLE.value(),           // status (Ej: 503)
                LocalDateTime.now()                               // timestamp
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    // 4. Intento de Login Fallido (401)
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        log.warn("Intento de login fallido");

        ErrorResponse error = new ErrorResponse(
                "Unauthorized",                                             // error (Título)
                "Credenciales inválidas. Verifica tu correo y contraseña.", // message (Detalle)
                HttpStatus.UNAUTHORIZED.value(),                            // status (Ej: 401)
                LocalDateTime.now()                                         // timestamp
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}


