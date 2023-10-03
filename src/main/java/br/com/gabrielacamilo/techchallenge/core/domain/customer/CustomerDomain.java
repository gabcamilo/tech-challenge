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
    private String email;

    @NotBlank
    @CPF
    private final String cpf;

    public CustomerDomain(
            String name,
            String email,
            String cpf) {
        super();
        this.name = name;
        this.email = email;
        this.cpf = cpf;
    }

    public CustomerDomain(
            String id,
            String name,
            String email,
            String cpf,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
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

    public void update(CustomerDomain customer) {
        this.name = customer.getName();
        this.email = customer.getEmail();
        update();
    }
}
