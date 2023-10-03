package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreateBundleRequest {

    private final String name;

    private final String description;

    private final BigDecimal discountPercentage;

    List<String> items;

    public CreateBundleRequest(String name, String description, BigDecimal discountPercentage, List<String> items) {
        this.name = name;
        this.description = description;
        this.discountPercentage = discountPercentage;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public List<String> getItems() {
        return items;
    }

    public BundleDomain toDomain() {
       return new BundleDomain(name, description, new ArrayList<ProductDomain>(), discountPercentage);
    }
}
