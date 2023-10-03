package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;

import java.math.BigDecimal;

public record UpdateProductRequest(String name, String description, BigDecimal price, ProductType type) {
    public ProductDomain toDomain() {
        return new ProductDomain(name, type, description, price);
    }
}
