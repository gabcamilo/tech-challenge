package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.order;

import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product.ProductResponse;
import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;
import br.com.gabrielacamilo.techchallenge.utils.GenericMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderResponse {
    private String id;
    private Customer customer;
    private List<OrderItem> items;
    private String note;
    private OrderStatus status;
    private PaymentStatus paymentStatus;
    private BigDecimal total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public OrderResponse(OrderDomain order) {
        this.id = order.getId();
        this.customer = new Customer(order.getCustomer());
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

    public Customer getCustomer() {
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

    private static class Customer {
        public Customer(CustomerDomain customer) {
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
            this.product = GenericMapper.map(domainItem.getProduct(), ProductResponse.class);
            this.quantity = domainItem.getQuantity();
            this.addOns = new ArrayList<>();

            domainItem.getAddOns().forEach(addOn -> {
                this.addOns.add(GenericMapper.map(addOn, ProductResponse.class));
            });

            System.out.printf("addOns: %s\n", this.addOns.toString());
        }

        public OrderItem() {
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
