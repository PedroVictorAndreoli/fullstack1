package br.com.jtech.tasklist.adapters.output;

import br.com.jtech.tasklist.adapters.output.repositories.TasklistRepository;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.output.GetTasklistOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetTasklistAdapter implements GetTasklistOutputGateway {

    private final TasklistRepository repository;

    @Override
    public List<Tasklist> findAll() {
        // Converte entities para domain
        return Tasklist.of(repository.findAll());
    }

    @Override
    public Tasklist findById(String id) {
        var entity = repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
        return Tasklist.of(entity);
    }
}
