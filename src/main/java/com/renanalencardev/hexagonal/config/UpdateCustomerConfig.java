package com.renanalencardev.hexagonal.config;

import com.renanalencardev.hexagonal.adapters.out.FindAddressByZipcodeAdapter;
import com.renanalencardev.hexagonal.adapters.out.UpdateCustomerAdapter;
import com.renanalencardev.hexagonal.application.core.usecase.FindCustomerByIdUseCase;
import com.renanalencardev.hexagonal.application.core.usecase.UpdateCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UpdateCustomerConfig {
    @Bean
    public UpdateCustomerUseCase updateCustomerUseCase(FindCustomerByIdUseCase findCustomerByIdAdapter,
                                                       FindAddressByZipcodeAdapter findAddressByZipcodeAdapter,
                                                       UpdateCustomerAdapter updateCustomerAdapter){
        return new UpdateCustomerUseCase(findCustomerByIdAdapter, findAddressByZipcodeAdapter, updateCustomerAdapter);
    }
}
