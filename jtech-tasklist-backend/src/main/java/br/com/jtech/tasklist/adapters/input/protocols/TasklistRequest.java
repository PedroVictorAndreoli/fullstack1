/*
 *  @(#)TasklistRequest.java
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

import br.com.jtech.tasklist.adapters.output.repositories.enums.StatusEnum;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;
import java.util.List;

/**
* class TasklistRequest 
* 
* user angelo.vicente 
*/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TasklistRequest implements Serializable {
    private String title;
    private String description;
    private String status;

    public Tasklist toDomain() {
        return Tasklist.builder()
                .title(title)
                .description(description)
                .status(StatusEnum.valueOf(status))
                .build();
    }
}