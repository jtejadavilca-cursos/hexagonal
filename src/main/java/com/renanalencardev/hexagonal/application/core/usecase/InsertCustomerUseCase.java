package com.renanalencardev.hexagonal.application.core.usecase;

import com.renanalencardev.hexagonal.application.core.domain.Customer;
import com.renanalencardev.hexagonal.application.ports.in.InsertCustomerInputPort;
import com.renanalencardev.hexagonal.application.ports.out.FindAddressByZipCodOutputPort;
import com.renanalencardev.hexagonal.application.ports.out.InsertCustomerOutputPort;
import com.renanalencardev.hexagonal.application.ports.out.SendCpfForValidationOutputPort;

public class InsertCustomerUseCase implements InsertCustomerInputPort {
    private final FindAddressByZipCodOutputPort findAddressByZipCodOutputPort;
    private final InsertCustomerOutputPort insertCustomerOutputPort;
    private final SendCpfForValidationOutputPort sendCpfForValidationOutputPort;
    public InsertCustomerUseCase(FindAddressByZipCodOutputPort findAddressByZipCodOutputPort, InsertCustomerOutputPort insertCustomerOutputPort, SendCpfForValidationOutputPort sendCpfForValidationOutputPort) {
        this.findAddressByZipCodOutputPort = findAddressByZipCodOutputPort;
        this.insertCustomerOutputPort = insertCustomerOutputPort;
        this.sendCpfForValidationOutputPort = sendCpfForValidationOutputPort;
    }

    @Override
    public void insert(Customer customer, String zipCode){
        var address = findAddressByZipCodOutputPort.find(zipCode);
        customer.setAddress(address);
        insertCustomerOutputPort.insert(customer);
        sendCpfForValidationOutputPort.send(customer.getCpf());
    }
}
