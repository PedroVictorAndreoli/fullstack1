package br.com.jtech.tasklist.config.infra.handlers;

import br.com.jtech.tasklist.config.infra.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import br.com.jtech.tasklist.config.infra.handlers.GlobalExceptionHandler;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleValidationErrors_ShouldReturnBadRequestWithSubErrors() {

        FieldError fieldError = new FieldError("task", "title", "Título é obrigatório");
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
        when(ex.getBindingResult()).thenReturn(bindingResult);
        when(ex.getObjectName()).thenReturn("task");
        when(ex.getLocalizedMessage()).thenReturn("Erro de validação");

        ResponseEntity<ApiError> response = handler.handleValidationErrors(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Erro de validação", response.getBody().getMessage());
        assertEquals("Erro de validação", response.getBody().getDebugMessage());
        List<ApiSubError> subErrors = response.getBody().getSubErrors();
        assertNotNull(subErrors);
        assertEquals(1, subErrors.size());
        assertTrue(subErrors.get(0) instanceof ApiValidationError);
        ApiValidationError validationError = (ApiValidationError) subErrors.get(0);
        assertEquals("task", validationError.getObject());
        assertEquals("title", validationError.getField());
        assertEquals("Título é obrigatório", validationError.getMessage());
    }

    @Test
    void handleRuntimeException_ShouldReturnBadRequestInPortuguese() {
        RuntimeException ex = new RuntimeException("Erro de execução");
        ResponseEntity<ApiError> response = handler.handleRuntimeException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Erro de execução: Erro de execução", response.getBody().getMessage());
        assertEquals("Erro de execução", response.getBody().getDebugMessage());
    }

    @Test
    void handleIllegalArgumentException_ShouldReturnBadRequestInPortuguese() {
        IllegalArgumentException ex = new IllegalArgumentException("Argumento inválido");
        ResponseEntity<ApiError> response = handler.handleIllegalArgumentException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Argumento inválido: Argumento inválido", response.getBody().getMessage());
        assertEquals("Argumento inválido", response.getBody().getDebugMessage());
    }

    @Test
    void handleAllExceptions_ShouldReturnInternalServerErrorInPortuguese() {
        Exception ex = new Exception("Erro inesperado");
        ResponseEntity<ApiError> response = handler.handleAllExceptions(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Erro inesperado", response.getBody().getDebugMessage());
        assertEquals("Erro inesperado", response.getBody().getMessage());
    }

}
