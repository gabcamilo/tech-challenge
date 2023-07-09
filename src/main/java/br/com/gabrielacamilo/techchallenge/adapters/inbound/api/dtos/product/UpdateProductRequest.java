package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdateProductRequest {

    private final String name;

    private final String description;

    private final BigDecimal price;

    private final ProductType type;

    public UpdateProductRequest(@NotBlank String name, String description, @NotNull BigDecimal price, @NotNull ProductType type) {
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

    public ProductType getType() {
        return type;
    }
}
