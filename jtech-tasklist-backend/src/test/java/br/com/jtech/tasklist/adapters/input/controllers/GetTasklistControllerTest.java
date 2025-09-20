package br.com.jtech.tasklist.adapters.input.controllers;

import br.com.jtech.tasklist.adapters.input.protocols.TasklistRequest;
import br.com.jtech.tasklist.adapters.input.protocols.TasklistResponse;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.input.GetTasklistInputGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class GetTasklistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private GetTasklistInputGateway getTasklistInputGateway;

    @Test
    void shouldReturnAllTasklists() throws Exception {
        Tasklist t1 = new Tasklist();
        t1.setTitle("Task 1");
        t1.setDescription("Descrição 1");
        t1.setId(UUID.randomUUID());

        Tasklist t2 = new Tasklist();
        t2.setTitle("Task 2");
        t2.setDescription("Descrição 2");
        t2.setId(UUID.randomUUID());

        List<Tasklist> tasklists = List.of(t1, t2);

        Mockito.when(getTasklistInputGateway.findAll()).thenReturn(tasklists);

        mockMvc.perform(get("/api/v1/tasklists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(t1.getId().toString()))
                .andExpect(jsonPath("$[0].title").value("Task 1"))
                .andExpect(jsonPath("$[1].id").value(t2.getId().toString()))
                .andExpect(jsonPath("$[1].title").value("Task 2"));

        Mockito.verify(getTasklistInputGateway, Mockito.times(1)).findAll();
    }

    @Test
    void shouldReturnTasklistById() throws Exception {
        Tasklist t1 = new Tasklist();
        t1.setTitle("Task 1");
        t1.setDescription("Descrição 1");
        t1.setId(UUID.randomUUID());

        Mockito.when(getTasklistInputGateway.findById(t1.getId().toString()))
                .thenReturn(t1);

        mockMvc.perform(get("/api/v1/tasklists/{id}", t1.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(t1.getId().toString()))
                .andExpect(jsonPath("$.title").value("Task 1"))
                .andExpect(jsonPath("$.description").value("Descrição 1"));

        Mockito.verify(getTasklistInputGateway, Mockito.times(1))
                .findById(t1.getId().toString());
    }
}
