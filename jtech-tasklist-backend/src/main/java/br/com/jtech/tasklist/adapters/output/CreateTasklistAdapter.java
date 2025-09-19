/*
*  @(#)TasklistAdapter.java
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
package br.com.jtech.tasklist.adapters.output;

import br.com.jtech.tasklist.adapters.output.repositories.TasklistRepository;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.output.CreateTasklistOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
* class TasklistAdapter 
* 
* user angelo.vicente  
*/
@Component
@RequiredArgsConstructor
public class CreateTasklistAdapter implements CreateTasklistOutputGateway {

    private final TasklistRepository repository;

    @Override
    public Tasklist create(Tasklist tasklist) {
        // Converte domain -> entity
        var entity = tasklist.toEntity();

        // Salva no banco
        var saved = repository.save(entity);

        // Converte entity -> domain
        return Tasklist.of(saved);
    }
}
