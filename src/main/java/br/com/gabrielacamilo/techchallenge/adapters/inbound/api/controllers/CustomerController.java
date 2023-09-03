package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.ApiResponse;
import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.customer.CreateCustomerRequest;
import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.customer.CustomerResponse;
import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.customer.ListCustomersResponse;
import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.customer.UpdateCustomerRequest;
import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/v1/customers")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class CustomerController {
    private final CustomerServicePort port;

    public CustomerController(CustomerServicePort port) {
        this.port = port;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createCustomer(@RequestBody @Valid CreateCustomerRequest request) throws Throwable {
        CustomerDomain customer = request.toDomain();
        CustomerDomain saved = port.create(customer);
        CustomerResponse data = new CustomerResponse(saved);
        ApiResponse response = new ApiResponse("Customer created successfully", 201, data);

        return ResponseEntity.created(new URI("/api/v1/customers/" + saved.getId())).body(response);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<ApiResponse> getCustomerByCpf(@PathVariable String cpf) throws Throwable {
        var customer = port.getCustomerByCpf(cpf);
        CustomerResponse data = new CustomerResponse(customer);
        ApiResponse response = new ApiResponse("Customer found successfully", 200, data);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> listAllCustomers() throws Throwable {
        List<CustomerDomain> customers = port.list();
        ListCustomersResponse responseData = new ListCustomersResponse(customers);

        ApiResponse response = new ApiResponse("List of customers obtained successfully", 200, responseData);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCustomer(@RequestBody @Valid UpdateCustomerRequest request, @PathVariable String id) throws Throwable {
        var updatedData = port.update(request.toDomain(), id);
        var responseData = new CustomerResponse(updatedData);

        var response = new ApiResponse(
                "Customer updated successfully",
                200,
                responseData);

        return ResponseEntity.ok(response);
    }
}
