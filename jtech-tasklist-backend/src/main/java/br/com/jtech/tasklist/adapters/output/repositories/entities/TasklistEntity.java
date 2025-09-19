/*
*  @(#)TasklistEntity.java
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
package br.com.jtech.tasklist.adapters.output.repositories.entities;

import br.com.jtech.tasklist.adapters.output.repositories.enums.StatusEnum;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

/**
* class TasklistEntity 
* 
* @author angelo.vicente
*/
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "TasklistEntity")
public class TasklistEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

}