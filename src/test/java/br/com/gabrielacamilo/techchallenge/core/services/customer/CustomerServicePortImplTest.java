package br.com.gabrielacamilo.techchallenge.core.services.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.ServicePort;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerServicePort;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerValidationPort;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CustomerServicePortImpl.class})
@ExtendWith(SpringExtension.class)
class CustomerServicePortImplTest {
    @MockBean
    private CustomerPersistencePort customerPersistencePort;

    @MockBean
    private CustomerValidationPort customerValidationPort;

    @MockBean
    private CustomerDomain customerDomain;

    @Autowired
    private CustomerServicePortImpl customerServicePortImpl;

    @BeforeEach
    void setUp() {
        setupPersistenceAllOk();
        setupValidationAllValid();
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should create a valid CustomerValidationPort instance")
    void testConstructor() {
        CustomerServicePortImpl customerServicePort = new CustomerServicePortImpl(customerPersistencePort, customerValidationPort);
        assertTrue(customerServicePort instanceof ServicePort);
        assertTrue(customerServicePort instanceof CustomerServicePort);
        assertTrue(customerServicePort instanceof CustomerServicePortImpl);
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should validate business rules, domain data and persist data")
    void testCreate() throws Throwable {
        assertSame(customerDomain,
                customerServicePortImpl.create(customerDomain));
        // should validate business rules
        verify(customerValidationPort).validateCreationalBusinessRules(customerDomain, customerPersistencePort);
        // should validate domain data
        verify(customerValidationPort).validateDomainData(customerDomain);
        // should persist data
        verify(customerPersistencePort).save(customerDomain);
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should throw an error when trying to create with invalid business data")
    void testCreateWithInvalidBusinessData() throws ConstraintViolationException, IllegalArgumentException {
        doThrow(new IllegalArgumentException()).when(customerValidationPort).validateCreationalBusinessRules(customerDomain, customerPersistencePort);

        assertThrows(IllegalArgumentException.class,
                () -> customerServicePortImpl.create(customerDomain));
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should throw an error when trying to create with invalid domain data")
    void testCreateWithInvalidDomainData() throws ConstraintViolationException, IllegalArgumentException {
        doThrow(new ConstraintViolationException(null)).when(customerValidationPort).validateDomainData(customerDomain);

        assertThrows(ConstraintViolationException.class,
                () -> customerServicePortImpl.create(customerDomain));
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should validate cpf existence through persistence port and return a valid CustomerDomain instance")
    void testGetCustomerByCpf() throws Throwable {
        assertSame(customerDomain, customerServicePortImpl.getCustomerByCpf(Mockito.<String>any()));

        verify(customerPersistencePort).getCustomerByCpf(Mockito.<String>any());
        verify(customerValidationPort).mustExist(Mockito.<Optional<CustomerDomain>>any(), Mockito.<String>any());
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should throw an error when trying to get a customer by cpf that does not exist")
    void testGetCustomerByCpfInvalidCpf() throws Throwable {
        doThrow(new IllegalArgumentException()).when(customerValidationPort).mustExist(Mockito.<Optional<CustomerDomain>>any(), Mockito.<String>any());
        assertThrows(IllegalArgumentException.class,
                () -> customerServicePortImpl.getCustomerByCpf(Mockito.<String>any()));
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should obtain a list of CustomerDomain instances through persistence port and return it")
    void testList() {
        assertInstanceOf(ArrayList.class, customerServicePortImpl.list());
        verify(customerPersistencePort).list();
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should validate a customer exists, get it through persistence port and return it")
    void testGet() throws Throwable {
        assertSame(customerDomain, customerServicePortImpl.get(Mockito.<String>any()));
        verify(customerPersistencePort).get(Mockito.<String>any());
        verify(customerValidationPort).mustExist(Mockito.<Optional<CustomerDomain>>any(), Mockito.<String>any());
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should throw an error when trying to get a customer that does not exist")
    void testGetNonExistent() throws Throwable {
        doThrow(new IllegalArgumentException()).when(customerValidationPort).mustExist(Mockito.<Optional<CustomerDomain>>any(), Mockito.<String>any());
        assertThrows(IllegalArgumentException.class,
                () -> customerServicePortImpl.get(Mockito.<String>any()));
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should validate delete business rules, validate the customer exists and delete it through persistence port")
    void testDelete() throws Throwable {
        customerServicePortImpl.delete(Mockito.<String>any());
        verify(customerValidationPort).mustExist(Mockito.<Optional<CustomerDomain>>any(), Mockito.<String>any());
        verify(customerPersistencePort).get(Mockito.<String>any());
        verify(customerPersistencePort).delete(Mockito.<CustomerDomain>any());
        verify(customerValidationPort).validateDeleteBusinessRules(Mockito.<CustomerDomain>any(),
                Mockito.<CustomerPersistencePort>any());

    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should throw an error when trying to delete a customer that does not exist")
    void testDeleteNonExistent() throws Throwable {
        doThrow(new IllegalArgumentException()).when(customerValidationPort).mustExist(Mockito.<Optional<CustomerDomain>>any(), Mockito.<String>any());
        assertThrows(IllegalArgumentException.class,
                () -> customerServicePortImpl.delete(Mockito.<String>any()));
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should validate the customer exists, validate update business rules, validate domain data and save it through persistence port")
    void testUpdate() throws Throwable {
        assertSame(customerDomain, customerServicePortImpl.update(customerDomain, Mockito.<String>any()));

        verify(customerPersistencePort).get(Mockito.<String>any());
        verify(customerValidationPort).mustExist(Mockito.<Optional<CustomerDomain>>any(), Mockito.<String>any());

        verify(customerValidationPort).validateUpdateBusinessRules(Mockito.<CustomerDomain>any(),
                Mockito.<CustomerDomain>any(), Mockito.<CustomerPersistencePort>any());

        verify(customerDomain).update(Mockito.<CustomerDomain>any());

        verify(customerValidationPort).validateDomainData(Mockito.<CustomerDomain>any());
        verify(customerPersistencePort).save(Mockito.<CustomerDomain>any());
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should throw an error when trying to update a customer that does not exist")
    void testUpdateNonExistent() throws Throwable {
        doThrow(new IllegalArgumentException()).when(customerValidationPort).mustExist(Mockito.<Optional<CustomerDomain>>any(), Mockito.<String>any());

        assertThrows(IllegalArgumentException.class,
                () -> customerServicePortImpl.update(customerDomain, Mockito.<String>any()));

    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should throw an error when trying to update a customer that does not attend update business rules")
    void testUpdateInvalidBusinessRules() throws Throwable {
        doThrow(new IllegalArgumentException()).when(customerValidationPort).validateUpdateBusinessRules(Mockito.<CustomerDomain>any(), Mockito.<CustomerDomain>any(), Mockito.<CustomerPersistencePort>any());

        assertThrows(IllegalArgumentException.class,
                () -> customerServicePortImpl.update(customerDomain, Mockito.<String>any()));
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should throw an error when trying to update a customer that does not attend update business rules")
    void testUpdateWithInvalidDomainData() throws Throwable {
        doThrow(new IllegalArgumentException()).when(customerValidationPort).validateDomainData(Mockito.<CustomerDomain>any());

        assertThrows(IllegalArgumentException.class,
                () -> customerServicePortImpl.update(customerDomain, Mockito.<String>any()));
    }

    @Test
    void testValidateDomainDataAndSave() throws Throwable {
        assertSame(customerDomain,
                customerServicePortImpl.validateDomainDataAndSave(customerDomain, customerValidationPort, customerPersistencePort));

        verify(customerValidationPort).validateDomainData(Mockito.<CustomerDomain>any());
        verify(customerPersistencePort).save(Mockito.<CustomerDomain>any());
    }

    @Test
    @DisplayName("[DOMAIN][CUSTOMER SERVICE] - should throw an error when trying to save a customer with invalid domain data")
    void testValidateDomainDataAndSaveWithInvalidDomainData() throws Throwable {
        doThrow(new IllegalArgumentException()).when(customerValidationPort).validateDomainData(Mockito.<CustomerDomain>any());

        assertThrows(IllegalArgumentException.class,
                () -> customerServicePortImpl.validateDomainDataAndSave(customerDomain, customerValidationPort, customerPersistencePort));
    }

    private void setupValidationAllValid() {
        doNothing().when(customerValidationPort).validateDomainData(Mockito.<CustomerDomain>any());
        when(customerValidationPort.mustExist(Mockito.<Optional<CustomerDomain>>any(), Mockito.<String>any())).thenReturn(customerDomain);
        doNothing().when(customerValidationPort).mustNotExist(Mockito.<Optional<CustomerDomain>>any(), Mockito.<String>any());

        doNothing().when(customerValidationPort)
                .validateCreationalBusinessRules(Mockito.<CustomerDomain>any(), Mockito.<CustomerPersistencePort>any());
        doNothing().when(customerValidationPort)
                .validateUpdateBusinessRules(Mockito.<CustomerDomain>any(), Mockito.<CustomerDomain>any(),
                        Mockito.<CustomerPersistencePort>any());
        doNothing().when(customerValidationPort)
                .validateDeleteBusinessRules(Mockito.<CustomerDomain>any(), Mockito.<CustomerPersistencePort>any());
    }

    private void setupPersistenceAllOk() {
        when(customerPersistencePort.save(Mockito.<CustomerDomain>any())).thenReturn(customerDomain);
        when(customerPersistencePort.list()).thenReturn(new ArrayList<CustomerDomain>());
        when(customerPersistencePort.get(Mockito.<String>any())).thenReturn(Optional.of(customerDomain));
        when(customerPersistencePort.update(Mockito.<CustomerDomain>any())).thenReturn(customerDomain);
        doNothing().when(customerPersistencePort).delete(Mockito.<CustomerDomain>any());

        when(customerPersistencePort.getCustomerByEmail(Mockito.<String>any())).thenReturn(Optional.of(customerDomain));
        when(customerPersistencePort.getCustomerByCpf(Mockito.<String>any())).thenReturn(Optional.of(customerDomain));
    }
}

