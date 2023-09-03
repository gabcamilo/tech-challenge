package br.com.gabrielacamilo.techchallenge.core.services.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerValidationPort;

public class CustomerValidationPortImpl implements CustomerValidationPort {

    @Override
    public void validateCreationalBusinessRules(CustomerDomain customerDomain, CustomerPersistencePort customerPersistencePort) throws IllegalArgumentException {
        validateUniqueCpf(customerDomain.getCpf(), customerPersistencePort);
        validateUniqueEmail(customerDomain.getEmail(), customerPersistencePort);
    }

    @Override
    public void validateUpdateBusinessRules(CustomerDomain updatedData, CustomerDomain customerDomain, CustomerPersistencePort persistencePort) throws IllegalArgumentException {

        if (!customerDomain.getCpf().equals(updatedData.getCpf())) {
            throw new IllegalArgumentException("CPF cannot be changed");
        }

        if (!customerDomain.getEmail().equals(updatedData.getEmail())) {
            validateUniqueEmail(updatedData.getEmail(), persistencePort);
        }

        //TODO: remove ifs
    }

    @Override
    public void validateDeleteBusinessRules(CustomerDomain domainItem, CustomerPersistencePort persistencePort) throws IllegalArgumentException {
        System.out.println("Validating delete business rules");
    }

    @Override
    public void validateUniqueCpf(String cpf, CustomerPersistencePort customerPersistencePort) throws IllegalArgumentException {
        mustNotExist(customerPersistencePort.getCustomerByCpf(cpf));
    }

    @Override
    public void validateUniqueEmail(String email, CustomerPersistencePort customerPersistencePort) {
        mustNotExist(customerPersistencePort.getCustomerByEmail(email));
    }
}

