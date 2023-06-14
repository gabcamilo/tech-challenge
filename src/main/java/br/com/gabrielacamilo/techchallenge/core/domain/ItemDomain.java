package br.com.gabrielacamilo.techchallenge.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class ItemDomain extends ProductDomain {

    private List<AddOnDomain> addOns;

    // constructor
    public ItemDomain(String id, String name, String type, String description, List<AddOnDomain> addOns, BigDecimal price) {
        super(id, name, type, description);
        setPrice(price);
        this.addOns = addOns;
    }

    @Override
    public BigDecimal calculatePrice(BigDecimal price) {
        return price;
    }

    public List<AddOnDomain> getAddOns() {
        return addOns;
    }

    public void setAddOns(List<AddOnDomain> addOns) {
        this.addOns = addOns;
    }
}
