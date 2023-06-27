package br.com.gabrielacamilo.techchallenge.adapters.dtos.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;

import java.time.LocalDateTime;

public class CreateCustomerResponse {

    public CreateCustomerResponse(CustomerDomain domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.email = domain.getEmail();
        this.cpf = domain.getCpf();
        this.createdAt = domain.getCreatedAt();
        this.updatedAt = domain.getUpdatedAt();
    }

    private final String id;

    private final String name;

    private final String email;

    //TODO: mask cpf
    private final String cpf;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
