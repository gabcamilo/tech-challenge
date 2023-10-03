package br.com.gabrielacamilo.techchallenge.core.domain.product;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BundleDomain extends ProductDomain {

    @Min(2)
    private List<ProductDomain> products;

    @DecimalMin("0")
    @DecimalMax("1")
    private BigDecimal discountPercentage;

    public BundleDomain(String id, String name, ProductType type, String description, BigDecimal price, List<ProductDomain> products, BigDecimal discountPercentage, LocalDateTime createdAt, LocalDateTime updatedAt) {
        super(id, name, type, description, price, createdAt, updatedAt);
        this.products = products;
        this.discountPercentage = discountPercentage;
    }

    public BundleDomain(String name, String description, List<ProductDomain> products, BigDecimal discountPercentage) {
        super(name, ProductType.BUNDLE, description, BigDecimal.ZERO);
        this.products = products;
        this.discountPercentage = discountPercentage;
        updatePrice(calculateTotalPrice());
    }

    public void updateDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
        updatePrice(calculateTotalPrice());
    }

    private BigDecimal calculateTotalPrice() {
        BigDecimal totalPrice = products.stream()
                .map(ProductDomain::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalPrice.subtract(totalPrice.multiply(discountPercentage));
    }

    public List<ProductDomain> getProducts() {
        return products;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }
}
