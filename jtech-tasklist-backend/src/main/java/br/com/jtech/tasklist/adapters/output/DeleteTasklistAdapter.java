package br.com.jtech.tasklist.adapters.output;

import br.com.jtech.tasklist.adapters.output.repositories.TasklistRepository;
import br.com.jtech.tasklist.application.ports.output.DeleteTasklistOutputGateway;
import br.com.jtech.tasklist.application.ports.output.GetTasklistOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DeleteTasklistAdapter  implements DeleteTasklistOutputGateway {
    private final TasklistRepository repository;

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
