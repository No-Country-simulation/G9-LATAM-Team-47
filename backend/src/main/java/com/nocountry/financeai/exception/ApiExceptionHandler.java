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


