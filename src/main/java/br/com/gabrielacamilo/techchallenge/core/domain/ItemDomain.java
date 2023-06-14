package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ItemDomain extends ProductDomain {

    private List<AddOnDomain> addOns;

    // constructor
    public ItemDomain(UUID id, String name, ProductType type, String description, List<AddOnDomain> addOns, BigDecimal price) {
        super(id, name, type, description, price);
        this.addOns = addOns;
    }

    public List<AddOnDomain> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<AddOnDomain> addOns) {
        this.addOns = addOns;
    }
}
