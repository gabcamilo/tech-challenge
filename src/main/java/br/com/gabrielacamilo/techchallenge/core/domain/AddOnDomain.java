package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ItemType;

import java.math.BigDecimal;
import java.util.UUID;

public class AddOnDomain {
    private UUID id;
    private String name;
    private BigDecimal price;
    private ItemType type;

    public AddOnDomain(UUID id, String name, BigDecimal price, ItemType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
