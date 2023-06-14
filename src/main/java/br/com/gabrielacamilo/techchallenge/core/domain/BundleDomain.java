package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class BundleDomain extends ProductDomain{

    private List<ItemDomain> items;
    private BigDecimal discountPercentage;

    public BundleDomain(UUID id, String name, String description, List<ItemDomain> items, BigDecimal discountPercentage) {
        super(id, name, ProductType.BUNDLE, description, BigDecimal.ZERO);
        BigDecimal sum = items.stream().map(ItemDomain::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        this.items = items;
        this.discountPercentage = discountPercentage;
        setPrice(sum);
    }

    @Override
    public BigDecimal calculatePrice(BigDecimal price) {
        return price.multiply(discountPercentage);
    }
}
