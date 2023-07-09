package br.com.gabrielacamilo.techchallenge.core.domain.product;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductDomain extends BaseDomain {

    @NotBlank
    private String name;

    @NotNull
    private ProductType type;

    private String description;

    @NotNull
    @DecimalMin("0")
    private BigDecimal price;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Deprecated
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

        this.validate(this);
    }

    public ProductDomain(String name, ProductType productType, String description) {
        this.name = name;
        this.type = productType;
        this.description = description;
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();

        this.validate(this);
    }

    protected BigDecimal calculatePrice(BigDecimal price) {
        return price;
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
