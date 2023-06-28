package br.com.gabrielacamilo.techchallenge.adapters.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class CreateProductRequest {
    @NotBlank
    private final String name;

    private final String description;

    @NotNull
    @DecimalMin("0")
    private final BigDecimal price;

    @NotNull
    private final ProductType type;

    public CreateProductRequest(String name, String description, BigDecimal price, ProductType type) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.type = type;
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

    public ProductType getItemType() {
        return type;
    }


    public ProductDomain toDomain() {
        return new ProductDomain(name, type, description, price);
    }
}
