package com.renanalencardev.hexagonal.application.core.usecase;

import com.renanalencardev.hexagonal.application.ports.in.DeleteCustomerInputPort;
import com.renanalencardev.hexagonal.application.ports.in.FindCustomerByIdInputPort;
import com.renanalencardev.hexagonal.application.ports.out.DeleteCustomerByIdOutputPort;

public class DeleteCustomerByIdUseCase implements DeleteCustomerInputPort {
    private final FindCustomerByIdInputPort findCustomerByIdInputPort;
    private final DeleteCustomerByIdOutputPort deleteCustomerByIdOutputPort;
    public DeleteCustomerByIdUseCase(FindCustomerByIdInputPort findCustomerByIdInputPort,
                                     DeleteCustomerByIdOutputPort deleteCustomerByIdOutputPort) {
        this.findCustomerByIdInputPort = findCustomerByIdInputPort;
        this.deleteCustomerByIdOutputPort = deleteCustomerByIdOutputPort;
    }
    @Override
    public void delete(String id) {
        findCustomerByIdInputPort.find(id);
        deleteCustomerByIdOutputPort.delete(id);
    }
}
