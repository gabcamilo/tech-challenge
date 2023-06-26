package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.CustomerEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.CustomerPersistencePort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerPersistencePortImpl implements CustomerPersistencePort {
    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public CustomerPersistencePortImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CustomerDomain saveCustomer(CustomerDomain customerDomain) {
        CustomerEntity customerEntity = customerRepository.save(modelMapper.map(customerDomain, CustomerEntity.class));
        return modelMapper.map(customerEntity, CustomerDomain.class);
    }

    @Override
    public Optional<CustomerDomain> getCustomerByCpf(String cpf) {
        Optional<CustomerEntity> customerEntity = customerRepository.findByCpf(cpf);
        return customerEntity.map(entity -> modelMapper.map(entity, CustomerDomain.class));
    }

    @Override
    public List<CustomerDomain> getAllCustomers() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return modelMapper.map(customerEntities, List.class);
    }
}
