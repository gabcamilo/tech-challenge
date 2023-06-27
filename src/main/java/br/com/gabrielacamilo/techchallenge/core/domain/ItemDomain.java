package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ItemType;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.math.BigDecimal;
import java.util.List;

public class ItemDomain extends ProductDomain {

    private ItemType itemType;
    private List<AddOnDomain> addOns;

    public ItemDomain(String name, String description, BigDecimal price, ItemType itemType, List<AddOnDomain> addOns) {
        super(name, ProductType.ITEM, description, price);
        this.addOns = addOns;
        this.itemType = itemType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public List<AddOnDomain> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<AddOnDomain> addOns) {
        this.addOns = addOns;
    }
}
