package br.com.jtech.tasklist.adapters.output.repositories.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum StatusEnum {
    PENDENTE("pendente"),
    CONCLUIDA("concluída");

    @JsonValue
    private final String description;


    public static StatusEnum fromDescription(String description) {
        return Arrays.stream(values())
                .filter(s -> s.getDescription().equalsIgnoreCase(description))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Status inválido: " + description));
    }
}

