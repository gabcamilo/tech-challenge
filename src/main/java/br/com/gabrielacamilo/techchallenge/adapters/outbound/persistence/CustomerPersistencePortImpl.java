package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.CustomerEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerPersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerPersistencePortImpl implements CustomerPersistencePort {
    private final CustomerRepository customerRepository;

    public CustomerPersistencePortImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDomain save(CustomerDomain item) {
        CustomerEntity customerEntity = new CustomerEntity(item);
        return customerRepository.save(customerEntity).toDomain();
    }

    @Override
    public List<CustomerDomain> list() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerEntities.stream().map(CustomerEntity::toDomain).toList();
    }

    @Override
    public Optional<CustomerDomain> get(String id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        return customerEntity.map(CustomerEntity::toDomain);
    }

    @Override
    public void delete(CustomerDomain item) {
        CustomerEntity customerEntity = new CustomerEntity(item);
        customerRepository.delete(customerEntity);
    }

    @Override
    public CustomerDomain update(CustomerDomain item) {
        CustomerEntity customerEntity = new CustomerEntity(item);
        return customerRepository.save(customerEntity).toDomain();
    }

    @Override
    public Optional<CustomerDomain> getCustomerByCpf(String cpf) {
        Optional<CustomerEntity> customerEntity = customerRepository.findByCpf(cpf);
        return customerEntity.map(CustomerEntity::toDomain);
    }

    @Override
    public Optional<CustomerDomain> getCustomerByEmail(String email) {
        Optional<CustomerEntity> customerEntity = customerRepository.findByEmail(email);
        return customerEntity.map(CustomerEntity::toDomain);
    }
}
