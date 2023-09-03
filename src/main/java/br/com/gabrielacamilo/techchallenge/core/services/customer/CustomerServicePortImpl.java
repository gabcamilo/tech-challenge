package br.com.gabrielacamilo.techchallenge.core.services.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerServicePort;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerValidationPort;

import java.util.List;

public class CustomerServicePortImpl implements CustomerServicePort {

    private final CustomerPersistencePort persistencePort;
    private final CustomerValidationPort validationPort;

    public CustomerServicePortImpl(CustomerPersistencePort persistencePort, CustomerValidationPort customerValidationPort) {
        this.persistencePort = persistencePort;
        this.validationPort = customerValidationPort;
    }

    @Override
    public CustomerDomain create(CustomerDomain customer) throws Throwable {
        validationPort.validateCreationalBusinessRules(customer, persistencePort);
        return validateDomainDataAndSave(customer, validationPort, persistencePort);
    }

    @Override
    public CustomerDomain getCustomerByCpf(String cpf) throws Throwable {
        return validationPort.mustExist(persistencePort.getCustomerByCpf(cpf));
    }

    @Override
    public List<CustomerDomain> list() {
        return persistencePort.list();
    }

    @Override
    public CustomerDomain get(String id) throws Throwable {
        return validationPort.mustExist(persistencePort.get(id));
    }

    @Override
    public void delete(String id) throws Throwable {
        CustomerDomain customerDomain = validationPort.mustExist(persistencePort.get(id));
        validationPort.validateDeleteBusinessRules(customerDomain, persistencePort);
        persistencePort.delete(customerDomain);
    }

    @Override
    public CustomerDomain update(CustomerDomain customerNewData, String id) throws Throwable {
        var customerDomain = validationPort.mustExist(persistencePort.get(id));
        validationPort.validateUpdateBusinessRules(customerNewData, customerDomain, persistencePort);
        customerDomain.update(customerNewData);
        return validateDomainDataAndSave(customerNewData, validationPort, persistencePort);
    }

    @Override
    public CustomerDomain validateDomainDataAndSave(CustomerDomain customer, CustomerValidationPort validationPort, CustomerPersistencePort persistencePort) throws Throwable {
        validationPort.validateDomainData(customer);
        return persistencePort.save(customer);
    }
}
