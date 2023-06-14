package br.com.gabrielacamilo.techchallenge.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class CustomerDomain {
    private UUID id;
    private String name;
    private String email;
    private String cpf;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CustomerDomain(UUID id, String name, String email, String cpf) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
