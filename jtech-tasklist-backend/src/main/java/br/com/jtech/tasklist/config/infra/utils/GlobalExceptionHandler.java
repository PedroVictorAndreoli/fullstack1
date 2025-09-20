package br.com.jtech.tasklist.config.infra.handlers;

import br.com.jtech.tasklist.config.infra.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Gerenciador global de exceções da API.
 *
 * Fornece respostas consistentes de erros em português.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationErrors(MethodArgumentNotValidException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST);
        error.setMessage("Erro de validação");
        error.setTimestamp(LocalDateTime.now());
        error.setDebugMessage(ex.getLocalizedMessage());
        error.setSubErrors(buildSubErrors(ex));

        return buildResponseEntity(error);
    }


    private List<ApiSubError> buildSubErrors(MethodArgumentNotValidException ex) {
        List<ApiSubError> errors = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.add(new ApiValidationError(
                    fieldError.getObjectName(),
                    fieldError.getField(),
                    fieldError.getRejectedValue(),
                    "Campo inválido: " + fieldError.getDefaultMessage()
            ));
        }
        return errors;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "Erro de execução: " + ex.getMessage(), ex);
        error.setTimestamp(LocalDateTime.now());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, "Argumento inválido: " + ex.getMessage(), ex);
        error.setTimestamp(LocalDateTime.now());
        return buildResponseEntity(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllExceptions(Exception ex) {
        ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Erro inesperado", ex);
        error.setTimestamp(LocalDateTime.now());
        return buildResponseEntity(error);
    }



    private ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }


}
