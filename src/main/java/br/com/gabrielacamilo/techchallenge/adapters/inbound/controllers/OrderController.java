package br.com.gabrielacamilo.techchallenge.adapters.inbound.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.dtos.order.CreateOrderRequest;
import br.com.gabrielacamilo.techchallenge.adapters.dtos.order.OrderResponse;
import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.CustomerServicePort;
import br.com.gabrielacamilo.techchallenge.core.ports.OrderServicePort;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/orders")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class OrderController {
    private final OrderServicePort port;
    private final CustomerServicePort customerPort;
    private final ProductServicePort productPort;

    public OrderController(OrderServicePort port, CustomerServicePort customerPort, ProductServicePort productPort) {
        this.port = port;
        this.customerPort = customerPort;
        this.productPort = productPort;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) {

        Optional<CustomerDomain> customer = customerPort.getCustomer(request.getCustomer());
        if (customer.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<String> productsIds = request.getProductsList();
        List<ProductDomain> products = productPort.listProductsByIds(productsIds);

        OrderDomain domain = request.toDomain(products, customer.get());
        OrderDomain createdOrder = port.createOrder(domain);

        OrderResponse response = new OrderResponse(createdOrder);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String id) {
        Optional<OrderDomain> order = port.getOrder(id);
        return order.map(value -> ResponseEntity.ok(new OrderResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
