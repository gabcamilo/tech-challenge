package br.com.gabrielacamilo.techchallenge.adapters.inbound.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.dtos.customer.CreateCustomerRequest;
import br.com.gabrielacamilo.techchallenge.adapters.dtos.customer.CustomerResponse;
import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.CustomerServicePort;
import br.com.gabrielacamilo.techchallenge.utils.GenericMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/customers")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    private final CustomerServicePort port;

    public CustomerController(CustomerServicePort port) {
        this.port = port;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        CustomerDomain customer = request.toDomain();
        CustomerDomain saved = port.saveCustomer(customer);
        CustomerResponse response = new CustomerResponse(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CustomerResponse> getCustomerByCpf(@PathVariable String cpf) {
        //TODO: less attributes when listing all customers
        Optional<CustomerDomain> customer = port.getCustomerByCpf(cpf);
        return customer.map(value -> ResponseEntity.ok(new CustomerResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponse>> listAllCustomers() {
        List<CustomerDomain> customers = port.listAllCustomers();
        List<CustomerResponse> customersResponse = GenericMapper.map(customers, CustomerResponse.class);
        return ResponseEntity.ok(customersResponse);
    }
}
