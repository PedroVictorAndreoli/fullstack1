package br.com.jtech.tasklist.adapters.input.controllers;

import br.com.jtech.tasklist.adapters.input.protocols.TasklistResponse;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.input.GetTasklistInputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasklists/")
@RequiredArgsConstructor
public class GetTasklistController {
    @Qualifier("getTasklistUseCase")
    private final GetTasklistInputGateway getTasklistInputGateway;

    @GetMapping
    public ResponseEntity<List<TasklistResponse>> findAll() {
        List<Tasklist> tasklists = getTasklistInputGateway.findAll();
        List<TasklistResponse> responses = tasklists.stream()
                .map(TasklistResponse::of)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TasklistResponse> findById(@PathVariable String id) {
        Tasklist tasklist = getTasklistInputGateway.findById(id);
        return ResponseEntity.ok(TasklistResponse.of(tasklist.toEntity()));
    }
}
