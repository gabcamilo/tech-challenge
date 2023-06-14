package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OrderDomain {
    // code OrderDomain properties: id, customer, status, price, description, items
    private UUID id;
    private CustomerDomain customer;
    private String description;
    private List<ItemDomain> items;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderDomain(UUID id, CustomerDomain customer, String description, List<ItemDomain> items) {
        this.id = id;
        this.customer = customer;
        this.description = description;
        this.items = items;
        this.status = OrderStatus.PENDING;
        this.paymentStatus = PaymentStatus.PENDING;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public CustomerDomain getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDomain customer) {
        this.customer = customer;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ItemDomain> getItems() {
        return items;
    }

    public void setItems(List<ItemDomain> items) {
        this.items = items;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
