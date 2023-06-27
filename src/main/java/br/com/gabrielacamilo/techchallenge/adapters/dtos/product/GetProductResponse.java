package br.com.gabrielacamilo.techchallenge.adapters.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class GetProductResponse {
    public GetProductResponse(ProductDomain domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.type = domain.getType();
        this.description = domain.getDescription();
        this.price = domain.getPrice();
        this.createdAt = domain.getCreatedAt();
        this.updatedAt = domain.getUpdatedAt();
    }

    public GetProductResponse() {
    }

    private String id;
    private String name;
    private ProductType type;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
}
