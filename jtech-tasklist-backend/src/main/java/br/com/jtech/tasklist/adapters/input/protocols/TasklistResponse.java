/*
*  @(#)TasklistResponse.java
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
package br.com.jtech.tasklist.adapters.input.protocols;

import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.adapters.output.repositories.entities.TasklistEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
* class TasklistResponse 
* 
* user angelo.vicente 
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TasklistResponse implements Serializable {
    private UUID id;
    private String title;
    private String description;
    private String status;

    public static TasklistResponse of(TasklistEntity entity) {
        return TasklistResponse.builder()
                .id(entity.getId() != null ? entity.getId() : null)
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus() != null ? entity.getStatus().getDescription() : null)
                .build();
    }

    public static List<TasklistResponse> of(List<TasklistEntity> entities) {
        return entities.stream()
                .map(TasklistResponse::of) // agora compila, porque existe of(TasklistEntity)
                .toList();
    }


    public static TasklistResponse of(Tasklist tasklist) {
        return TasklistResponse.builder()
                .id(tasklist.getId())
                .title(tasklist.getTitle())
                .description(tasklist.getDescription())
                .status(tasklist.getStatus() != null ? tasklist.getStatus().getDescription() : null)
                .build();
    }
}