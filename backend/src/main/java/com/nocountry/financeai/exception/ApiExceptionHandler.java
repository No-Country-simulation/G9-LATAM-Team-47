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