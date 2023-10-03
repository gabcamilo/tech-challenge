package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities;

import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderProductDomain;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderProductEntity {
    @DBRef
    private ProductEntity product;

    private Integer quantity;

    @DBRef
    private List<ProductEntity> addOns;

    private BigDecimal total;

    public OrderProductEntity(OrderProductDomain domain) {
        this.product = new ProductEntity(domain.getProduct());
        this.quantity = domain.getQuantity();
        this.addOns = domain.getAddOns().stream().map(ProductEntity::new).toList();
        this.total = domain.getTotal();
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<ProductEntity> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<ProductEntity> addOns) {
        this.addOns = addOns;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public OrderProductDomain toDomain() {
        return new OrderProductDomain(
                this.getProduct().toDomain(),
                this.getQuantity(),
                this.getAddOns().stream().map(ProductEntity::toDomain).toList(),
                this.getTotal()
        );
    }

    @Deprecated // Spring eyes only
    public OrderProductEntity() {
    }
}
