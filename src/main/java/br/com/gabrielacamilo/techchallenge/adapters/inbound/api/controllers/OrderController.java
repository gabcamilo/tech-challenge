package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.order.CreateOrderRequest;
import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.order.OrderResponse;
import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerServicePort;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderServicePort;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductServicePort;
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
    public ResponseEntity<OrderResponse> createOrder(@RequestBody CreateOrderRequest request) throws Throwable {

        CustomerDomain customer = customerPort.get(request.getCustomer());
//        if (customer.isEmpty()) {
//            return ResponseEntity.badRequest().build();
//        }

        List<String> productsIds = request.getProductsList();
        List<ProductDomain> products = productPort.listProductsByIds(productsIds);

        OrderDomain domain = request.toDomain(products, customer);
        OrderDomain createdOrder = port.saveOrder(domain);

        OrderResponse response = new OrderResponse(createdOrder);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable String id) {
        Optional<OrderDomain> order = port.getOrder(id);
        return order.map(value -> ResponseEntity.ok(new OrderResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
    public ResponseEntity<List<OrderResponse>> listAllOrders() {
        List<OrderDomain> orders = port.listAllOrders();
        List<OrderResponse> response = orders.stream().map(OrderResponse::new).toList();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/customer/{cpf}")
    public ResponseEntity<List<OrderResponse>> listOrdersByCustomer(@PathVariable String cpf) throws Throwable {
        CustomerDomain customer = customerPort.getCustomerByCpf(cpf);
//        if (customer.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }

        List<OrderDomain> orders = port.getOrdersByCustomer(customer);
        List<OrderResponse> response = orders.stream().map(OrderResponse::new).toList();
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status/cooking")
    public ResponseEntity<OrderResponse> updateOrderStatusCooking(@PathVariable String id) {
        Optional<OrderDomain> order = port.updateOrderStatusCooking(id);
        return order.map(value -> ResponseEntity.ok(new OrderResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status/ready")
    public ResponseEntity<OrderResponse> updateOrderStatusReady(@PathVariable String id) {
        Optional<OrderDomain> order = port.updateOrderStatusReady(id);
        return order.map(value -> ResponseEntity.ok(new OrderResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/status/delivered")
    public ResponseEntity<OrderResponse> updateOrderStatusDelivered(@PathVariable String id) {
        Optional<OrderDomain> order = port.updateOrderStatusDelivered(id);
        return order.map(value -> ResponseEntity.ok(new OrderResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/payment/approved")
    public ResponseEntity<OrderResponse> updatePaymentStatusApproved(@PathVariable String id) {
        Optional<OrderDomain> order = port.updatePaymentStatusApproved(id);
        return order.map(value -> ResponseEntity.ok(new OrderResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}/payment/rejected")
    public ResponseEntity<OrderResponse> updatePaymentStatusRejected(@PathVariable String id) {
        Optional<OrderDomain> order = port.updatePaymentStatusRejected(id);
        return order.map(value -> ResponseEntity.ok(new OrderResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/payment")
    public ResponseEntity<OrderResponse> payOrder(@PathVariable String id) {
        Optional<OrderDomain> orderOptional = port.getOrder(id);
        return orderOptional.map(order -> {
                    OrderDomain payedOrder = port.pay(order);
                    payedOrder.processPayment();
                    port.saveOrder(payedOrder);
                    return ResponseEntity.ok(new OrderResponse(payedOrder));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
