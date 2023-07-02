package br.com.gabrielacamilo.techchallenge.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class ProductOrderDomain {
    private ProductDomain product;

    private Integer quantity;

    private List<ProductDomain> addOns;

    private BigDecimal total;

    public ProductOrderDomain (ProductDomain product, int quantity, List<ProductDomain> addOns) {
        this.product = product;
        this.quantity = quantity;
        this.addOns = addOns;
        this.total = calculatePrice();
    }

    private BigDecimal calculatePrice() {
        BigDecimal addOnsTotalPrice = this.addOns.stream()
                .map(addOn -> addOn.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        BigDecimal finalPrice = product.getPrice()
                .add(addOnsTotalPrice)
                .multiply(BigDecimal.valueOf(quantity));

        return finalPrice;
    }

    public ProductDomain getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public List<ProductDomain> getAddOns() {
        return addOns;
    }

    public BigDecimal getTotal() {
        return total;
    }
}
