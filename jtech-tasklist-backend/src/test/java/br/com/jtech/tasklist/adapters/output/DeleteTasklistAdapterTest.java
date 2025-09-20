package br.com.jtech.tasklist.adapters.output;

import br.com.jtech.tasklist.adapters.output.repositories.TasklistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.UUID;

import static org.mockito.Mockito.*;

class DeleteTasklistAdapterTest {

    @Mock
    private TasklistRepository repository;

    @InjectMocks
    private DeleteTasklistAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteById_CallsRepository() {
        UUID id = UUID.randomUUID();

        adapter.deleteById(id);

        verify(repository, times(1)).deleteById(id);
    }
}
