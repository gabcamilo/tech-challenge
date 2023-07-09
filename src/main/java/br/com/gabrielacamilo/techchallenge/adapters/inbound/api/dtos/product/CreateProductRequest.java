package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;

import java.math.BigDecimal;

public class CreateProductRequest {
    private final String name;

    private final String description;

    private final BigDecimal price;

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
