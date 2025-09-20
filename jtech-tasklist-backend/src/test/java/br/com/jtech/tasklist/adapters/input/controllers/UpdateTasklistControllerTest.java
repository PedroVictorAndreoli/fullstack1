package br.com.jtech.tasklist.adapters.input.controllers;

import br.com.jtech.tasklist.adapters.input.protocols.TasklistRequest;
import br.com.jtech.tasklist.adapters.input.protocols.TasklistResponse;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.input.UpdateTasklistInputGateway;
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

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UpdateTasklistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private UpdateTasklistInputGateway updateTasklistInputGateway;

    @Test
    void shouldUpdateTasklistSuccessfully() throws Exception {
        UUID tasklistId = UUID.randomUUID();

        TasklistRequest request = new TasklistRequest();
        request.setTitle("Updated Task");
        request.setDescription("Updated Description");
        request.setStatus("PENDENTE");


        Tasklist tasklistToReturn = Tasklist.of(request);
        tasklistToReturn.setId(tasklistId);

        Mockito.when(updateTasklistInputGateway.update(Mockito.any(Tasklist.class)))
                .thenReturn(tasklistToReturn);

        mockMvc.perform(put("/api/v1/tasklists/{id}", tasklistId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(tasklistId.toString()))
                .andExpect(jsonPath("$.title").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("Updated Description"));

        Mockito.verify(updateTasklistInputGateway, Mockito.times(1))
                .update(Mockito.any(Tasklist.class));
    }
}
