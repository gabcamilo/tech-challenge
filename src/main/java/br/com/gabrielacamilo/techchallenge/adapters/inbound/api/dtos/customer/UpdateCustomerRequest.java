package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.customer;

public class UpdateCustomerRequest {
    private final String name;
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
