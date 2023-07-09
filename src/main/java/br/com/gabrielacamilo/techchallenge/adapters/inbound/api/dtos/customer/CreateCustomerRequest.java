package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;

public class CreateCustomerRequest {

    private final String name;
    private final String email;
    private final String cpf;

    public CreateCustomerRequest(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

    public CustomerDomain toDomain() {
        return new CustomerDomain(name, email, cpf);
    }
}
