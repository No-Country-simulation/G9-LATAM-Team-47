package com.nocountry.financeai.exception;

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