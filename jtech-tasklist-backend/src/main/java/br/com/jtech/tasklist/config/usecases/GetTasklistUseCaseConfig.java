package br.com.jtech.tasklist.config.usecases;

import br.com.jtech.tasklist.adapters.output.GetTasklistAdapter;
import br.com.jtech.tasklist.adapters.output.UpdateTasklistAdapter;
import br.com.jtech.tasklist.application.core.usecases.GetTasklistUseCase;
import br.com.jtech.tasklist.application.core.usecases.UpdateTasklistUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetTasklistUseCaseConfig {
    @Bean("getTasklistUseCase")
    public GetTasklistUseCase useCase(GetTasklistAdapter getTasklistAdapter) {
        return new GetTasklistUseCase(getTasklistAdapter);
    }
}
