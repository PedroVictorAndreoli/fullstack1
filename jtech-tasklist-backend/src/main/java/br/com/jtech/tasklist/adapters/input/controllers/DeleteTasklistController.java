package br.com.jtech.tasklist.adapters.input.controllers;

import br.com.jtech.tasklist.application.ports.input.DeleteTasklistInputGateway;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasklists/")
@RequiredArgsConstructor
public class DeleteTasklistController {
    @Qualifier("deleteTasklistUseCase")
    private final DeleteTasklistInputGateway deleteTasklistInputGateway;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteTasklistInputGateway.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
