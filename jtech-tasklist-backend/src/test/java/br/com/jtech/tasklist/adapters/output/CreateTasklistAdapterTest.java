package br.com.jtech.tasklist.adapters.output;

import br.com.jtech.tasklist.adapters.output.repositories.TasklistRepository;
import br.com.jtech.tasklist.adapters.output.repositories.entities.TasklistEntity;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.adapters.output.repositories.enums.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateTasklistAdapterTest {

    @Mock
    private TasklistRepository repository;

    @InjectMocks
    private CreateTasklistAdapter adapter;

    private Tasklist taskDomain;
    private TasklistEntity taskEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        taskDomain = Tasklist.builder()
                .title("Nova Tarefa")
                .description("Descrição da tarefa")
                .status(StatusEnum.PENDENTE)
                .build();

        taskEntity = TasklistEntity.builder()
                .id(java.util.UUID.randomUUID())
                .title("Nova Tarefa")
                .description("Descrição da tarefa")
                .status(StatusEnum.PENDENTE)
                .build();
    }

    @Test
    void createTasklist_Success() {
        when(repository.save(any(TasklistEntity.class))).thenReturn(taskEntity);

        Tasklist result = adapter.create(taskDomain);

        assertNotNull(result);
        assertEquals(taskEntity.getTitle(), result.getTitle());
        assertEquals(taskEntity.getDescription(), result.getDescription());
        assertEquals(taskEntity.getStatus().getDescription(), result.getStatus().getDescription());

        verify(repository, times(1)).save(any(TasklistEntity.class));
    }
}
