package com.renanalencardev.hexagonal.config;

import com.renanalencardev.hexagonal.adapters.out.FindCustomerByIdAdapter;
import com.renanalencardev.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindCustomerByIdConfig {
    @Bean
    public FindCustomerByIdUseCase findCustomerByIdUseCase(FindCustomerByIdAdapter findCustomerByIdAdapter){
       return new FindCustomerByIdUseCase(findCustomerByIdAdapter);
    }
}
