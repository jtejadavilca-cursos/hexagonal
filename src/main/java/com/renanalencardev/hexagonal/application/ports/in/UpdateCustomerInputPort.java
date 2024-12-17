package com.renanalencardev.hexagonal.application.ports.in;

import com.renanalencardev.hexagonal.application.core.domain.Customer;

public interface UpdateCustomerInputPort {
    void update(Customer customer, String zipCode);
}
