package br.com.gabrielacamilo.techchallenge.adapters.dtos.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UpdateCustomerRequest {
    @NotBlank
    private final String name;
    @Email
    @NotBlank
    private final String email;

    public UpdateCustomerRequest(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

}
