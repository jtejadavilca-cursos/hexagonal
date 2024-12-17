package com.renanalencardev.hexagonal.config;

import com.renanalencardev.hexagonal.adapters.out.FindAddressByZipcodeAdapter;
import com.renanalencardev.hexagonal.adapters.out.InsertCustomerAdapter;
import com.renanalencardev.hexagonal.adapters.out.SendCpfValidationAdapter;
import com.renanalencardev.hexagonal.application.core.usecase.InsertCustomerUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InsertCustomerConfig {
    @Bean
    public InsertCustomerUseCase insertCustomerUseCase(
            FindAddressByZipcodeAdapter findAddressByZipcodeAdapter,
            InsertCustomerAdapter insertCustomerAdapter,
            SendCpfValidationAdapter sendCpfValidationAdapter){
        return new InsertCustomerUseCase(findAddressByZipcodeAdapter, insertCustomerAdapter, sendCpfValidationAdapter);
    }
}
