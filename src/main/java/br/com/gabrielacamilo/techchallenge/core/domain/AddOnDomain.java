package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ItemType;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.math.BigDecimal;
import java.util.UUID;

public class AddOnDomain extends ProductDomain {
    private ItemType addOnType;

    public AddOnDomain(UUID id, String name, String description, BigDecimal price, ItemType addOnType) {
        super(id, name, ProductType.ADDON, description, price);
        this.addOnType = addOnType;
    }

    public ItemType getAddOnType() {
        return addOnType;
    }

    public void setAddOnType(ItemType addOnType) {
        this.addOnType = addOnType;
    }
}
