package br.com.gabrielacamilo.techchallenge.adapters.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateProductResponse {

    public CreateProductResponse(ProductDomain domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.description = domain.getDescription();
        this.price = domain.getPrice();
        this.type = domain.getType();
        this.createdAt = domain.getCreatedAt();
        this.updatedAt = domain.getUpdatedAt();
    }

    private final String id;

    private final String name;

    private final String description;

    private final BigDecimal price;

    private final ProductType type;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
