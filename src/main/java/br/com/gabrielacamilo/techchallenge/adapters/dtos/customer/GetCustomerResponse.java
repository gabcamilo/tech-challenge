package br.com.gabrielacamilo.techchallenge.adapters.dtos.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;

import java.time.LocalDateTime;

public class GetCustomerResponse {
    public GetCustomerResponse(CustomerDomain domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.email = domain.getEmail();
        this.cpf = domain.getCpf();
        this.createdAt = domain.getCreatedAt();
        this.updatedAt = domain.getUpdatedAt();
    }

    public GetCustomerResponse() {
    }

    private String id;

    private String name;

    private String email;

    //TODO: mask cpf
    private String cpf;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
