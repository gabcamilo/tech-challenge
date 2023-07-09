package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public class CreateCustomerRequest {

    @NotBlank
    private final String name;
    @Email
    @NotBlank
    private final String email;
    @CPF
    @NotBlank
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
