package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public class OrderDomain {
    // code OrderDomain properties: id, customer, status, price, description, items
    private UUID id;
    private CustomerDomain customer;
    private OrderStatus status;
    private String description;
    private List<ItemDomain> items;

    public OrderDomain(UUID id, CustomerDomain customer, OrderStatus status, String description, List<ItemDomain> items) {
        this.id = id;
        this.customer = customer;
        this.status = status;
        this.description = description;
        this.items = items;
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
}
