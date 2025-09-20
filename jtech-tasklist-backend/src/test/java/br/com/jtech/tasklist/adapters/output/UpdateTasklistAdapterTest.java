package br.com.jtech.tasklist.adapters.output;

import br.com.jtech.tasklist.adapters.output.repositories.TasklistRepository;
import br.com.jtech.tasklist.adapters.output.repositories.entities.TasklistEntity;
import br.com.jtech.tasklist.adapters.output.repositories.enums.StatusEnum;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateTasklistAdapterTest {

    @Mock
    private TasklistRepository repository;

    @InjectMocks
    private UpdateTasklistAdapter adapter;

    private Tasklist taskDomain;
    private TasklistEntity taskEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        UUID id = UUID.randomUUID();

        taskDomain = Tasklist.builder()
                .id(id)
                .title("Tarefa Original")
                .description("Descrição Original")
                .status(StatusEnum.valueOf("PENDENTE"))
                .build();

        taskEntity = TasklistEntity.builder()
                .id(id)
                .title("Tarefa Original")
                .description("Descrição Original")
                .status(br.com.jtech.tasklist.adapters.output.repositories.enums.StatusEnum.PENDENTE)
                .build();
    }

    @Test
    void updateTasklist_Success() {
        when(repository.findById(taskDomain.getId())).thenReturn(Optional.of(taskEntity));
        when(repository.save(any(TasklistEntity.class))).thenAnswer(i -> i.getArguments()[0]);

        Tasklist updated = adapter.update(Tasklist.builder()
                .id(taskDomain.getId())
                .title("Tarefa Atualizada")
                .description("Descrição Atualizada")
                .status(StatusEnum.fromDescription("CONCLUÍDA"))
                .build());

        assertNotNull(updated);
        assertEquals("Tarefa Atualizada", updated.getTitle());
        assertEquals("Descrição Atualizada", updated.getDescription());
        assertEquals("CONCLUIDA", updated.getStatus().toString());

        verify(repository, times(1)).findById(taskDomain.getId());
        verify(repository, times(1)).save(any(TasklistEntity.class));
    }

    @Test
    void updateTasklist_NotFound() {
        when(repository.findById(taskDomain.getId())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                adapter.update(taskDomain)
        );

        assertTrue(exception.getMessage().contains("Tarefa não encontrada"));
        verify(repository, times(1)).findById(taskDomain.getId());
        verify(repository, never()).save(any());
    }
}
