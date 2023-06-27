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

    private final String id;
    private final String name;
    private final ProductType type;
    private final String description;
    private final BigDecimal price;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;


}
