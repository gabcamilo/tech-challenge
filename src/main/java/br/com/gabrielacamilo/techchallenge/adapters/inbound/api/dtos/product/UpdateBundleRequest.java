package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class UpdateBundleRequest {

    @NotBlank
    private final String name;

    private final String description;

    @DecimalMin("0")
    @DecimalMax("1")
    private final BigDecimal discountPercentage;

    @NotEmpty
    List<String> items;

    public UpdateBundleRequest(String name, String description, BigDecimal discountPercentage, List<String> items) {
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

    public BundleDomain toDomain(List<ProductDomain> products, String id) {
        HashMap<String, ProductDomain> productsDic = new HashMap<>();
        products.forEach(product ->
                productsDic.put(product.getId(), product)
        );

        List<ProductDomain> itemsDomain = items.stream().map(item -> productsDic.put(item, productsDic.get(item))).toList();
        BundleDomain bundle = new BundleDomain(name, description, discountPercentage, itemsDomain);
        bundle.setId(id);
        return bundle;
    }
}
