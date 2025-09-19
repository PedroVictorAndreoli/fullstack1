package br.com.jtech.tasklist.config.usecases;

import br.com.jtech.tasklist.adapters.output.UpdateTasklistAdapter;
import br.com.jtech.tasklist.application.core.usecases.UpdateTasklistUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateTasklistUseCaseConfig {
    @Bean("updateTasklistUseCase")
    public UpdateTasklistUseCase useCase(UpdateTasklistAdapter updateTasklistAdapter) {
        return new UpdateTasklistUseCase(updateTasklistAdapter);
    }
}
