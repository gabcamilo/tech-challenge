package br.com.gabrielacamilo.techchallenge.core.domain.product;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.util.List;

public class BundleDomain extends ProductDomain{

    @Min(2)
    private List<ProductDomain> items;

    @DecimalMin("0")
    @DecimalMax("1")
    private BigDecimal discountPercentage;

    @Deprecated
    public BundleDomain() {
    }

    public BundleDomain(String name, String description, BigDecimal discountPercentage, List<ProductDomain> items) {
        super(name, ProductType.BUNDLE, description);
        this.items = items;
        this.discountPercentage = discountPercentage;
        BigDecimal sum = items.stream()
                .map(ProductDomain::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal finalPrice = calculatePrice(sum);
        this.setPrice(finalPrice);

        BaseDomain.validate(this);
    }

    @Override
    protected BigDecimal calculatePrice(BigDecimal price) {
        if(discountPercentage != null){
            return price.subtract(price.multiply(discountPercentage));
        }
        return price;
    }

    public List<ProductDomain> getItems() {
        return items;
    }

    public void setItems(List<ProductDomain> items) {
        this.items = items;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
