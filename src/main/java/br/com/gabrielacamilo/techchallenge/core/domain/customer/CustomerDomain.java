package br.com.gabrielacamilo.techchallenge.core.domain.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDateTime;

public class CustomerDomain extends BaseDomain {

    @NotBlank
    private String name;

    @NotBlank
    @Email
    private String email; //unico

    @NotBlank
    @CPF
    private String cpf; //unico
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CustomerDomain(
            String name,
            String email,
            String cpf) {
        this.name = name;
        this.email = email;
        this.cpf = cpf;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        BaseDomain.validate(this);
    }

    public CustomerDomain() {
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
