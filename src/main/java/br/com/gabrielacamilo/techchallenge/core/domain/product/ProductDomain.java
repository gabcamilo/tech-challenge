package br.com.gabrielacamilo.techchallenge.core.domain.product;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import jakarta.validation.constraints.DecimalMin;
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

    public ProductDomain(String name, ProductType type, String description, BigDecimal price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public ProductDomain(String id, String name, ProductType type, String description, BigDecimal price, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, createdAt, updatedAt);
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = price;
    }

    public void updatePrice (BigDecimal price) {
        this.price = price;
        update();
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

}
