package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.CustomerEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerPersistencePort;
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
    public CustomerDomain save(CustomerDomain item) {
        CustomerEntity customerEntity = customerRepository.save(GenericMapper.map(item, CustomerEntity.class));
        return GenericMapper.map(customerEntity, CustomerDomain.class);
    }

    @Override
    public List<CustomerDomain> list() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return GenericMapper.map(customerEntities, CustomerDomain.class);
    }

    @Override
    public Optional<CustomerDomain> get(String id) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(id);
        return GenericMapper.map(customerEntity, CustomerDomain.class);
    }

    @Override
    public void delete(CustomerDomain item) {
        CustomerEntity customerEntity = GenericMapper.map(item, CustomerEntity.class);
        customerRepository.delete(customerEntity);
    }

    @Override
    public CustomerDomain update(CustomerDomain item) {
        CustomerEntity customerEntity = GenericMapper.map(item, CustomerEntity.class);
        CustomerEntity saved = customerRepository.save(customerEntity);
        return GenericMapper.map(saved, CustomerDomain.class);
    }

    @Override
    public Optional<CustomerDomain> getCustomerByCpf(String cpf) {
        Optional<CustomerEntity> customerEntity = customerRepository.findByCpf(cpf);
        return GenericMapper.map(customerEntity, CustomerDomain.class);
    }

    @Override
    public Optional<CustomerDomain> getCustomerByEmail(String email) {
        Optional<CustomerEntity> customerEntity = customerRepository.findByEmail(email);
        return GenericMapper.map(customerEntity, CustomerDomain.class);
    }
}
