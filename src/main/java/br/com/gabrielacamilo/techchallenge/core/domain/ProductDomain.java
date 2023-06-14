package br.com.gabrielacamilo.techchallenge.core.domain;

import java.math.BigDecimal;

public abstract class ProductDomain {
    // id name type price description
    private String id;
    private String name;
    private String type;
    private BigDecimal price;
    private String description;

    public ProductDomain(String id, String name, String type, String description) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public abstract BigDecimal calculatePrice(BigDecimal price);

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
}
