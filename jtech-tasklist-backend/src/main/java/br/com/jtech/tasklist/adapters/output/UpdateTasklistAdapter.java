package br.com.jtech.tasklist.adapters.output;

import br.com.jtech.tasklist.adapters.output.repositories.TasklistRepository;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.output.CreateTasklistOutputGateway;
import br.com.jtech.tasklist.application.ports.output.UpdateTasklistOutputGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
@RequiredArgsConstructor
public class UpdateTasklistAdapter implements UpdateTasklistOutputGateway {
    private final TasklistRepository repository;

    @Override
    public Tasklist update(Tasklist tasklist) {
        // Verifica se a tarefa existe
        var entityOptional = repository.findById(UUID.fromString(tasklist.getId()));
        if (entityOptional.isEmpty()) {
            throw new RuntimeException("Tarefa não encontrada com ID: " + tasklist.getId());
        }

        var entity = entityOptional.get();
        // Atualiza os campos
        entity.setTitle(tasklist.getTitle());
        entity.setDescription(tasklist.getDescription());
        entity.setStatus(tasklist.getStatus());

        // Salva no banco
        var updatedEntity = repository.save(entity);

        // Converte de volta para domain
        return Tasklist.of(updatedEntity);
    }
}
