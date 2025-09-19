package br.com.jtech.tasklist.application.core.usecases;

import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.input.UpdateTasklistInputGateway;
import br.com.jtech.tasklist.application.ports.output.CreateTasklistOutputGateway;
import br.com.jtech.tasklist.application.ports.output.UpdateTasklistOutputGateway;

public class UpdateTasklistUseCase implements UpdateTasklistInputGateway{
    private final UpdateTasklistOutputGateway updateTasklistOutputGateway;

    public UpdateTasklistUseCase(UpdateTasklistOutputGateway updateTasklistOutputGateway) {
        this.updateTasklistOutputGateway = updateTasklistOutputGateway;
    }

    public Tasklist update(Tasklist tasklist) {
        return updateTasklistOutputGateway.update(tasklist);
    }
}
