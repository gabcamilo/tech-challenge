package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDomain {
    private String id;
    private CustomerDomain customer;
    private List<OrderProductDomain> items;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private String note;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderDomain() {
    }

    public OrderDomain(CustomerDomain customer, String note, List<OrderProductDomain> items) {
        this.customer = customer;
        this.note = note;
        this.items = items;
        this.status = OrderStatus.PENDING;
        this.paymentStatus = PaymentStatus.PENDING;
        this.total = calculateTotal();

        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public OrderDomain(String id, CustomerDomain customer, List<OrderProductDomain> items, OrderStatus status, PaymentStatus paymentStatus, String note, BigDecimal total, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.customer = customer;
        this.items = items;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.note = note;
        this.total = total;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderProductDomain::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CustomerDomain getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDomain customer) {
        this.customer = customer;
    }

    public List<OrderProductDomain> getItems() {
        return items;
    }

    public void setItems(List<OrderProductDomain> items) {
        this.items = items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void processPayment() {
        switch (paymentStatus) {
            case APPROVED:
                status = OrderStatus.COOKING;
                break;
            case REJECTED:
                status = OrderStatus.CANCELED;
                break;
        }
    }
}
