package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDomain {
    private String id;
    private String name;
    private ProductType type;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductDomain() {
    }

    public ProductDomain(String name, ProductType type, String description, BigDecimal price) {
        this.name = name;
        this.type = type;
        this.description = description;
        BigDecimal finalPrice = calculatePrice(price);
        setPrice(finalPrice);
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public ProductDomain(String name, ProductType productType, String description) {
        this.name = name;
        this.type = productType;
        this.description = description;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }


    public BigDecimal calculatePrice(BigDecimal price) {
        return price;
    }

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
