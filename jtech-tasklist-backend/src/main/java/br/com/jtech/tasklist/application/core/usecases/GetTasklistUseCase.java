package br.com.jtech.tasklist.application.core.usecases;

import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.input.GetTasklistInputGateway;
import br.com.jtech.tasklist.application.ports.output.GetTasklistOutputGateway;
import br.com.jtech.tasklist.application.ports.output.UpdateTasklistOutputGateway;

import java.util.List;

public class GetTasklistUseCase implements GetTasklistInputGateway {
    private final GetTasklistOutputGateway getTasklistOutputGateway;

    public GetTasklistUseCase(GetTasklistOutputGateway getTasklistOutputGateway) {
        this.getTasklistOutputGateway = getTasklistOutputGateway;
    }

    @Override
    public List<Tasklist> findAll() {
        return getTasklistOutputGateway.findAll();
    }

    @Override
    public Tasklist findById(String id) {
        return getTasklistOutputGateway.findById(id);
    }
}
