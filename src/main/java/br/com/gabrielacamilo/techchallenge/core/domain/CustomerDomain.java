package br.com.gabrielacamilo.techchallenge.core.domain;

import java.time.LocalDateTime;
public class CustomerDomain {
    private String id;
    private String name;
    private String email;
    private String cpf;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CustomerDomain(String name, String email, String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public CustomerDomain() {
    }

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

    public void update(CustomerDomain customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        this.cpf = customer.getCpf();
        this.updatedAt = LocalDateTime.now();
    }
}
