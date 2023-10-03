package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "customers")
public class CustomerEntity extends BaseEntity {
    private String name;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String cpf;

    public CustomerEntity(CustomerDomain domain) {
        super(domain.getId(), domain.getCreatedAt(), domain.getUpdatedAt());
        this.name = domain.getName();
        this.email = domain.getEmail();
        this.cpf = domain.getCpf();
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

    @Override
    public CustomerDomain toDomain() {
        return new CustomerDomain(
                this.getId(),
                this.name,
                this.email,
                this.cpf,
                this.getCreatedAt(),
                this.getUpdatedAt());
    }

    @Deprecated // Spring eyes only
    public CustomerEntity() {

    }
}
