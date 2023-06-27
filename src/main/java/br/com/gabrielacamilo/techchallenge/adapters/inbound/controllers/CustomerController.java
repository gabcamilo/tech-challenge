package br.com.gabrielacamilo.techchallenge.adapters.inbound.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.dtos.customer.CreateCustomerRequest;
import br.com.gabrielacamilo.techchallenge.adapters.dtos.customer.CreateCustomerResponse;
import br.com.gabrielacamilo.techchallenge.adapters.dtos.customer.GetCustomerResponse;
import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.CustomerServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/customers")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    final CustomerServicePort port;

    public CustomerController(CustomerServicePort port) {
        this.port = port;
    }

    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@RequestBody @Valid CreateCustomerRequest request) {
        CustomerDomain customer = request.toDomain();
        CustomerDomain saved = port.saveCustomer(customer);
        CreateCustomerResponse response = new CreateCustomerResponse(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<GetCustomerResponse> getCustomerByCpf(@PathVariable String cpf) {
        //TODO: less attributes when listing all customers
        Optional<CustomerDomain> customer = port.getCustomerByCpf(cpf);
        return customer.isEmpty() ?
                ResponseEntity.notFound().build() :
                ResponseEntity.ok(customer.get());
    }

    @GetMapping
    public ResponseEntity<List<CustomerDomain>> getAllCustomers() {
        return ResponseEntity.ok(port.getAllCustomers());
    }
}
