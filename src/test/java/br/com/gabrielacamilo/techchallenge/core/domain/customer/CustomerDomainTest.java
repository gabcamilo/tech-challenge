package br.com.gabrielacamilo.techchallenge.core.domain.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDomainTest {

    private CustomerDomain customerDomain = Mockito.mock(CustomerDomain.class);

    @Test
    @DisplayName("[DOMAIN][CUSTOMER DOMAIN] - should create a valid CustomerDomain instance")
    void testConstructor() {
        String name = "Name";
        String email = "jane.doe@example.org";
        String cpf = "Cpf";

        CustomerDomain customerDomain = new CustomerDomain(name, email, cpf);

        assertEquals(cpf, customerDomain.getCpf());
        assertEquals(name, customerDomain.getName());
        assertEquals(email, customerDomain.getEmail());

        assertTrue(customerDomain instanceof BaseDomain);
        assertTrue(customerDomain instanceof CustomerDomain);
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER DOMAIN] - should update the CustomerDomain instances name, email and updatedAt values")
    void testUpdate() {
        String name = "Name";
        String email = "jane.doe@example.org";
        String cpf = "Cpf";

        LocalDateTime createdAt;
        LocalDateTime updatedAt;

        String another_name = "Another Name";
        String another_email = "another.jane.doe@example.org";
        String another = "Another Cpf";

        CustomerDomain customerDomain = new CustomerDomain(name, email, cpf);
        createdAt = customerDomain.getCreatedAt();
        updatedAt = customerDomain.getUpdatedAt();

        customerDomain.update(new CustomerDomain(another_name, another_email, another));

        assertEquals(createdAt, customerDomain.getCreatedAt());
        assertEquals(another_name, customerDomain.getName());
        assertEquals(another_email, customerDomain.getEmail());

        assertNotEquals(updatedAt, customerDomain.getUpdatedAt());
        assertNotEquals(another, customerDomain.getCpf());
    }
}

