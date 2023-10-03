package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderProductDomain;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
public class OrderEntity extends BaseEntity {
    @DBRef
    private CustomerEntity customer;
    private List<OrderProductEntity> items;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private String note;
    private BigDecimal total;

    public OrderEntity(OrderDomain domain) {
        super(domain.getId(), domain.getCreatedAt(), domain.getUpdatedAt());
        this.customer = new CustomerEntity(domain.getCustomer());
        this.status = domain.getStatus();
        this.paymentStatus = domain.getPaymentStatus();
        this.note = domain.getNote();
        this.total = domain.getTotal();

        this.items = domain.getItems().stream().map(OrderProductEntity::new).toList();
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public List<OrderProductEntity> getItems() {
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


    @Override
    public OrderDomain toDomain() {
        List<OrderProductDomain> items = this.items.stream().map(OrderProductEntity::toDomain).toList();
        return new OrderDomain(
                this.getId(),
                this.customer.toDomain(),
                items,
                this.status,
                this.paymentStatus,
                this.note,
                this.total,
                this.getCreatedAt(),
                this.getUpdatedAt()
        );
    }

    @Deprecated // Spring eyes only
    public OrderEntity() {
    }
}
