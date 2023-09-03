package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;

public record UpdateCustomerRequest(String name, String email, String cpf) {

    public CustomerDomain toDomain() {
        return new CustomerDomain(name, email, cpf);
    }
}
