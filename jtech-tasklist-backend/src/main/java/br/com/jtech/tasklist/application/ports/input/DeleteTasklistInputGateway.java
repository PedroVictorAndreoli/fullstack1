package br.com.jtech.tasklist.application.ports.input;

import java.util.UUID;

public interface DeleteTasklistInputGateway {
    void deleteById(UUID id);
}
