package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public abstract class ProductDomain {
    // id name type price description
    private UUID id;
    private String name;
    private ProductType type;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ProductDomain(UUID id, String name, ProductType type, String description, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
        setPrice(price);
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    public BigDecimal calculatePrice(BigDecimal price) {
        return price;
    }

    public UUID getId() {
        return id;
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
        this.price = calculatePrice(price);
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
