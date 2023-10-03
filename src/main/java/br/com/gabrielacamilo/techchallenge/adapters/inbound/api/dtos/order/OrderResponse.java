package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.order;

import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product.ProductResponse;
import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderProductDomain;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse implements Serializable {
    private String id;
    private OrderCustomer customer;
    private List<OrderItem> items;
    private String note;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderResponse(OrderDomain order) {
        this.id = order.getId();
        this.customer = new OrderCustomer(order.getCustomer());
        this.items = generateItemsList(order);
        this.note = order.getNote();
        this.status = order.getStatus();
        this.paymentStatus = order.getPaymentStatus();
        this.total = order.getTotal();
        this.createdAt = order.getCreatedAt();
        this.updatedAt = order.getUpdatedAt();
    }

    private List<OrderItem> generateItemsList(OrderDomain order) {
        return order.getItems().stream().map(domainItem ->
                new OrderItem(domainItem)
        ).toList();
    }

    public String getId() {
        return id;
    }

    public OrderCustomer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getNote() {
        return note;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    private static class OrderCustomer {
        public OrderCustomer(CustomerDomain customer) {
            this.id = customer.getId();
            this.name = customer.getName();
            this.cpf = customer.getCpf();
        }

        private String id;
        private String name;
        private String cpf;

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCpf() {
            return cpf;
        }
    }

    private static class OrderItem {
        private ProductResponse product;

        private Integer quantity;

        private List<ProductResponse> addOns;

        public OrderItem(OrderProductDomain domainItem) {
            this.product = new ProductResponse(domainItem.getProduct());
            this.quantity = domainItem.getQuantity();
            this.addOns = domainItem.getAddOns().stream().map(ProductResponse::new).toList();
        }

        public ProductResponse getProduct() {
            return product;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public List<ProductResponse> getAddOns() {
            return addOns;
        }
    }
}
