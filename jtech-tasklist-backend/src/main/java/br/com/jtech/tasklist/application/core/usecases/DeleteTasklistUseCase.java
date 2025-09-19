package br.com.jtech.tasklist.application.core.usecases;

import br.com.jtech.tasklist.application.ports.input.DeleteTasklistInputGateway;
import br.com.jtech.tasklist.application.ports.input.GetTasklistInputGateway;
import br.com.jtech.tasklist.application.ports.output.DeleteTasklistOutputGateway;
import br.com.jtech.tasklist.application.ports.output.GetTasklistOutputGateway;
import br.com.jtech.tasklist.application.ports.output.UpdateTasklistOutputGateway;

import java.util.UUID;

public class DeleteTasklistUseCase implements DeleteTasklistInputGateway {
    private final DeleteTasklistOutputGateway deleteTasklistOutputGateway;
    public DeleteTasklistUseCase(DeleteTasklistOutputGateway deleteTasklistOutputGateway) {
        this.deleteTasklistOutputGateway = deleteTasklistOutputGateway;
    }

    @Override
    public void deleteById(UUID id) {
        deleteTasklistOutputGateway.deleteById(id);
    }
}
