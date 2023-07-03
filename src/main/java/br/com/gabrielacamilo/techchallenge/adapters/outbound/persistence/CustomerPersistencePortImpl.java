package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.CustomerEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.CustomerPersistencePort;
import br.com.gabrielacamilo.techchallenge.utils.GenericMapper;
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
    public CustomerDomain saveCustomer(CustomerDomain customerDomain) {
        CustomerEntity customerEntity = customerRepository.save(GenericMapper.map(customerDomain, CustomerEntity.class));
        return GenericMapper.map(customerEntity, CustomerDomain.class);
    }

    @Override
    public Optional<CustomerDomain> getCustomerByCpf(String cpf) {
        Optional<CustomerEntity> customerEntity = customerRepository.findByCpf(cpf);
        return GenericMapper.map(customerEntity, CustomerDomain.class);
    }

    @Override
    public List<CustomerDomain> listAllCustomers() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return GenericMapper.map(customerEntities, CustomerDomain.class);
    }

    @Override
    public Optional<CustomerDomain> getCustomer(String id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        return GenericMapper.map(customerEntity, CustomerDomain.class);
    }
}
