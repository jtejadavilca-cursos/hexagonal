package com.renanalencardev.hexagonal.config;

import com.renanalencardev.hexagonal.adapters.out.DeleteCustomerByIdAdapter;
import com.renanalencardev.hexagonal.application.core.usecase.DeleteCustomerByIdUseCase;
import com.renanalencardev.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeleteCustomerByIdConfig {
    @Bean
    public DeleteCustomerByIdUseCase deleteCustomerByIdUseCase(
            FindCustomerByIdUseCase findCustomerByIdUseCase,
            DeleteCustomerByIdAdapter deleteCustomerByIdAdapter){
        return new DeleteCustomerByIdUseCase(findCustomerByIdUseCase, deleteCustomerByIdAdapter);
    }
}
