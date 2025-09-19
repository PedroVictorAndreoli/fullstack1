package br.com.jtech.tasklist.config.usecases;

import br.com.jtech.tasklist.adapters.output.DeleteTasklistAdapter;
import br.com.jtech.tasklist.adapters.output.GetTasklistAdapter;
import br.com.jtech.tasklist.application.core.usecases.DeleteTasklistUseCase;
import br.com.jtech.tasklist.application.core.usecases.GetTasklistUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteTasklistUseCaseConfig {
    @Bean("deleteTasklistUseCase")
    public DeleteTasklistUseCase deleteTasklistUseCase(DeleteTasklistAdapter adapter) {
        return new DeleteTasklistUseCase(adapter);
    }
}
