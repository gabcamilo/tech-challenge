package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;

import java.math.BigDecimal;
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

    public BundleDomain toDomain(List<ProductDomain> products) {
        HashMap<String, ProductDomain> productsDic = new HashMap<>();
        products.forEach(product ->
                productsDic.put(product.getId(), product)
        );

        List<ProductDomain> itemsDomain = items.stream().map(item -> productsDic.put(item, productsDic.get(item))).toList();
        return new BundleDomain(name, description, discountPercentage, itemsDomain);
    }
}
