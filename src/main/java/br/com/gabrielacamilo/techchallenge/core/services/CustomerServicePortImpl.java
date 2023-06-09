package br.com.gabrielacamilo.techchallenge.core.services;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.CustomerPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.CustomerServicePort;

import java.util.List;
import java.util.Optional;

public class CustomerServicePortImpl implements CustomerServicePort {

    private final CustomerPersistencePort customerPersistencePort;

    public CustomerServicePortImpl(CustomerPersistencePort customerPersistencePort) {
        this.customerPersistencePort = customerPersistencePort;
    }

    @Override
    public CustomerDomain saveCustomer(CustomerDomain customer) {
        customerPersistencePort.validateCustomer(customer.getCpf(), customer.getEmail());
        return customerPersistencePort.saveCustomer(customer);
    }

    @Override
    public Optional<CustomerDomain> getCustomerByCpf(String cpf) {
        return customerPersistencePort.getCustomerByCpf(cpf);
    }

    @Override
    public List<CustomerDomain> listAllCustomers() {
        return customerPersistencePort.listAllCustomers();
    }

    @Override
    public Optional<CustomerDomain> getCustomer(String id) {
        return customerPersistencePort.getCustomer(id);
    }
}
