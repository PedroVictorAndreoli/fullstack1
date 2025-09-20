package br.com.jtech.tasklist.adapters.input.controllers;

import br.com.jtech.tasklist.adapters.input.protocols.TasklistRequest;
import br.com.jtech.tasklist.adapters.input.protocols.TasklistResponse;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.input.UpdateTasklistInputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasklists/")
@RequiredArgsConstructor
public class UpdateTasklistController {

    @Qualifier("updateTasklistUseCase")
    private final UpdateTasklistInputGateway updateTasklistInputGateway;

    @PutMapping("/{id}")
    public ResponseEntity<TasklistResponse> update(
            @PathVariable UUID id,
            @RequestBody TasklistRequest request) {

        // Converte request para domain e seta o ID do path
        Tasklist tasklistToUpdate = Tasklist.of(request);
        tasklistToUpdate.setId(id);

        // Atualiza a tarefa
        Tasklist updatedTasklist = updateTasklistInputGateway.update(tasklistToUpdate);

        // Converte para response
        TasklistResponse response = TasklistResponse.of(updatedTasklist.toEntity());

        return ResponseEntity.ok(response);
    }
}
