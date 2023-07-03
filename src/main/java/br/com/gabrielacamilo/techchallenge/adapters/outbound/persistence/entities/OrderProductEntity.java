package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.util.List;

public class OrderProductEntity {
    @DBRef
    private ProductEntity product;

    private Integer quantity;

    @DBRef
    private List<ProductEntity> addOns;

    private BigDecimal total;

    public OrderProductEntity() {
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
}
