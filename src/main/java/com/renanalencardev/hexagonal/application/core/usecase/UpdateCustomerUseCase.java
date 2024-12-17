package com.renanalencardev.hexagonal.application.core.usecase;

import com.renanalencardev.hexagonal.application.core.domain.Customer;
import com.renanalencardev.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import com.renanalencardev.hexagonal.application.ports.in.UpdateCustomerInputPort;
import com.renanalencardev.hexagonal.application.ports.out.FindAddressByZipCodOutputPort;
import com.renanalencardev.hexagonal.application.ports.out.UpdateCustomerOutputPort;

public class UpdateCustomerUseCase implements UpdateCustomerInputPort {
    private final FindCustomerByIdInputPort findCustomerByIdInputPort;
    private final FindAddressByZipCodOutputPort findAddressByZipCodOutputPort;
    private final UpdateCustomerOutputPort updateCustomerOutputPort;
    public UpdateCustomerUseCase(FindCustomerByIdInputPort findCustomerByIdInputPort,
                                 FindAddressByZipCodOutputPort findAddressByZipCodOutputPort,
                                 UpdateCustomerOutputPort updateCustomerOutputPort) {
        this.findCustomerByIdInputPort = findCustomerByIdInputPort;
        this.findAddressByZipCodOutputPort = findAddressByZipCodOutputPort;
        this.updateCustomerOutputPort = updateCustomerOutputPort;
    }
    @Override
    public void update(Customer customer, String zipCode){
        findCustomerByIdInputPort.find(customer.getId());
        var address = findAddressByZipCodOutputPort.find(zipCode);
        customer.setAddress(address);
        updateCustomerOutputPort.update(customer);
    }
}
