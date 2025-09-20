
/*
*  @(#)TasklistController.java
*
*  Copyright (c) J-Tech Solucoes em Informatica.
*  All Rights Reserved.
*
*  This software is the confidential and proprietary information of J-Tech.
*  ("Confidential Information"). You shall not disclose such Confidential
*  Information and shall use it only in accordance with the terms of the
*  license agreement you entered into with J-Tech.
*
*/
package br.com.jtech.tasklist.adapters.input.controllers;

import br.com.jtech.tasklist.adapters.input.protocols.TasklistRequest;
import br.com.jtech.tasklist.adapters.input.protocols.TasklistResponse;
import br.com.jtech.tasklist.adapters.output.repositories.entities.TasklistEntity;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.input.CreateTasklistInputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

import static br.com.jtech.tasklist.application.core.domains.Tasklist.of;

/**
* class TasklistController
*
* user angelo.vicente
*/
@RestController
@RequestMapping("/api/v1/tasklists")
@RequiredArgsConstructor
public class CreateTasklistController {
    @Qualifier("createTasklistUseCase")
    private final CreateTasklistInputGateway createTasklistInputGateway;

    @PostMapping
    public ResponseEntity<TasklistResponse> create(@RequestBody TasklistRequest request) {
        // Converte request -> domain
        var taskDomain = Tasklist.of(request);

        // Chama caso de uso
        var created = createTasklistInputGateway.create(taskDomain);

        // Converte domain -> response
        var response = TasklistResponse.of(created.toEntity());

        // Retorna 201 Created + Location header
        return ResponseEntity
                .created(URI.create("/api/v1/tasklists/" + response.getId()))
                .body(response);
    }
}

