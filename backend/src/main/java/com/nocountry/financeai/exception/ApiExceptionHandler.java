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

import java.util.stream.Collectors;

@Slf4j
@Hidden // Evita el Error 500 en Swagger
@RestControllerAdvice
public class ApiExceptionHandler {

    // 1. Error Genérico (Corregido)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> gestionarErrorGeneral(Exception e) {
        log.error("Error interno del servidor", e);
        // Usamos el constructor: String error, String message, int status
        ErrorResponse error = new ErrorResponse(
                "Internal Server Error",
                "Error interno del servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    // 2. Errores de Validación (Corregido)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> gestionarErroresValidacion(MethodArgumentNotValidException ex) {
        log.warn("Se recibieron datos inválidos en la petición");

        // Convertimos la lista de errores en un solo String para que quepa en el record
        String errores = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        ErrorResponse error = new ErrorResponse(
                "Bad Request",
                "Error de validación: " + errores,
                HttpStatus.BAD_REQUEST.value()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    // 3. Error de Conexión a la IA (Corregido para devolver ResponseEntity)
    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> gestionarErrorConexionIA(ResourceAccessException ex) {
        log.error("No fue posible conectar con la API de análisis");

        ErrorResponse error = new ErrorResponse(
                "Service Unavailable",
                "El servicio de Análisis no está disponible",
                HttpStatus.SERVICE_UNAVAILABLE.value()
        );
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(error);
    }

    // 4. EL 401 QUE NECESITÁBAMOS HOY PARA EL SLICE 1
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException ex) {
        log.warn("Intento de login fallido");
        ErrorResponse error = new ErrorResponse(
                "Unauthorized",
                "Credenciales inválidas. Verifica tu correo y contraseña.",
                HttpStatus.UNAUTHORIZED.value()
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}


