package br.com.gabrielacamilo.techchallenge.adapters.dtos.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;

public class CreateCustomerResponse {

    public CreateCustomerResponse(CustomerDomain domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.email = domain.getEmail();
        this.cpf = domain.getCpf();
    }

    private final String id;

    private final String name;

    private final String email;

    private final String cpf;

    public String getId() {
        return id;
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
}
