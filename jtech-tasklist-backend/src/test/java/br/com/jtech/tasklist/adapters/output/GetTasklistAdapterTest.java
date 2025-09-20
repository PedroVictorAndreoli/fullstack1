package br.com.jtech.tasklist.adapters.output;

import br.com.jtech.tasklist.adapters.output.repositories.TasklistRepository;
import br.com.jtech.tasklist.adapters.output.repositories.entities.TasklistEntity;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.adapters.output.repositories.enums.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GetTasklistAdapterTest {

    @Mock
    private TasklistRepository repository;

    @InjectMocks
    private GetTasklistAdapter adapter;

    private TasklistEntity taskEntity1;
    private TasklistEntity taskEntity2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        taskEntity1 = TasklistEntity.builder()
                .id(UUID.randomUUID())
                .title("Tarefa 1")
                .description("Descrição 1")
                .status(StatusEnum.PENDENTE)
                .build();

        taskEntity2 = TasklistEntity.builder()
                .id(UUID.randomUUID())
                .title("Tarefa 2")
                .description("Descrição 2")
                .status(StatusEnum.CONCLUIDA)
                .build();
    }

    @Test
    void findAll_ReturnsAllTasks() {
        when(repository.findAll()).thenReturn(Arrays.asList(taskEntity1, taskEntity2));

        List<Tasklist> result = adapter.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Tarefa 1", result.get(0).getTitle());
        assertEquals("Tarefa 2", result.get(1).getTitle());

        verify(repository, times(1)).findAll();
    }

    @Test
    void findById_ReturnsTask() {
        UUID id = taskEntity1.getId();
        when(repository.findById(id)).thenReturn(Optional.of(taskEntity1));

        Tasklist result = adapter.findById(id.toString());

        assertNotNull(result);
        assertEquals("Tarefa 1", result.getTitle());
        assertEquals("Descrição 1", result.getDescription());

        verify(repository, times(1)).findById(id);
    }

    @Test
    void findById_TaskNotFound_ThrowsException() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> adapter.findById(id.toString()));

        assertEquals("Tarefa não encontrada", exception.getMessage());
        verify(repository, times(1)).findById(id);
    }
}
