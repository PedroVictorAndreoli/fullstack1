package br.com.jtech.tasklist.adapters.input.controllers;

import br.com.jtech.tasklist.adapters.input.protocols.TasklistRequest;
import br.com.jtech.tasklist.adapters.input.protocols.TasklistResponse;
import br.com.jtech.tasklist.application.core.domains.Tasklist;
import br.com.jtech.tasklist.application.ports.input.CreateTasklistInputGateway;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CreateTasklistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private CreateTasklistInputGateway createTasklistInputGateway;

    @Test
    void shouldCreateTasklistSuccessfully() throws Exception {
        // Simula request
        TasklistRequest request = new TasklistRequest();
        request.setTitle("Minha Tasklist");
        request.setDescription("Descrição da tasklist");

        // Simula domain criado pelo caso de uso
        Tasklist domainCreated = Tasklist.of(request);
        domainCreated.setId(UUID.randomUUID());

        // Mocka o gateway
        Mockito.when(createTasklistInputGateway.create(Mockito.any(Tasklist.class)))
                .thenReturn(domainCreated);

        // Executa a chamada POST
        mockMvc.perform(post("/api/v1/tasklists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/tasklists/" + domainCreated.getId()))
                .andExpect(jsonPath("$.id").value(domainCreated.getId().toString()))
                .andExpect(jsonPath("$.title").value("Minha Tasklist"))
                .andExpect(jsonPath("$.description").value("Descrição da tasklist"));

        // Verifica se o gateway foi chamado
        Mockito.verify(createTasklistInputGateway, Mockito.times(1))
                .create(Mockito.any(Tasklist.class));
    }
}
