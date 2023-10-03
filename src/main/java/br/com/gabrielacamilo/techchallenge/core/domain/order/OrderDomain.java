package br.com.gabrielacamilo.techchallenge.core.domain.order;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDomain extends BaseDomain {

    @JsonInclude(Include.NON_NULL)
    private CustomerDomain customer;
    @Min(1)
    private List<OrderProductDomain> items;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private String note;
    private BigDecimal total;

    public OrderDomain(CustomerDomain customer, String note, List<OrderProductDomain> items) {
        this.customer = customer;
        this.note = note;
        this.items = items;
        this.status = OrderStatus.PENDING;
        this.paymentStatus = PaymentStatus.PENDING;
        this.total = calculateTotal();
    }

    public OrderDomain(String id, CustomerDomain customer, List<OrderProductDomain> items, OrderStatus status, PaymentStatus paymentStatus, String note, BigDecimal total, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.customer = customer;
        this.items = items;
        this.status = status;
        this.paymentStatus = paymentStatus;
        this.note = note;
        this.total = total;
    }

    @Deprecated
    public OrderDomain() {

    }

    public void updateOrderStatus(OrderStatus status) {
        this.status = status;
    }

    private BigDecimal calculateTotal() {
        return items.stream()
                .map(OrderProductDomain::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public CustomerDomain getCustomer() {
        return customer;
    }

    public List<OrderProductDomain> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public String getNote() {
        return note;
    }

    public BigDecimal getTotal() {
        return total;
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

    public void update(OrderDomain domain) {
    }

    public void updatePaymentStatus(PaymentStatus status) {
        this.paymentStatus = status;
    }
}
