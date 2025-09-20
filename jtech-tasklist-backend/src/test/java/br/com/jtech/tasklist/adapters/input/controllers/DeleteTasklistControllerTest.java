package br.com.jtech.tasklist.adapters.input.controllers;

import br.com.jtech.tasklist.application.ports.input.DeleteTasklistInputGateway;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class DeleteTasklistControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DeleteTasklistInputGateway deleteTasklistInputGateway;

    @Test
    void shouldDeleteTasklistSuccessfully() throws Exception {
        UUID id = UUID.randomUUID();

        Mockito.doNothing().when(deleteTasklistInputGateway).deleteById(id);

        mockMvc.perform(delete("/api/v1/tasklists/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        Mockito.verify(deleteTasklistInputGateway, Mockito.times(1)).deleteById(id);
    }
}
