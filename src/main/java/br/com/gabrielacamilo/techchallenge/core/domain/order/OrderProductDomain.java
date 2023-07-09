package br.com.gabrielacamilo.techchallenge.core.domain.order;

import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;

import java.math.BigDecimal;
import java.util.List;

public class OrderProductDomain {
    private ProductDomain product;

    private Integer quantity;

    private List<ProductDomain> addOns;

    private BigDecimal total;

    public OrderProductDomain(ProductDomain product, int quantity, List<ProductDomain> addOns) {
        this.product = product;
        this.quantity = quantity;
        this.addOns = addOns;
        this.total = calculatePrice();
    }public OrderProductDomain(ProductDomain product, int quantity, List<ProductDomain> addOns, BigDecimal total) {
        this.product = product;
        this.quantity = quantity;
        this.addOns = addOns;
        this.total = total;
    }

    private BigDecimal calculatePrice() {
        BigDecimal addOnsTotalPrice = this.addOns.stream()
                .map(addOn -> addOn.getPrice())
                .reduce(BigDecimal.ZERO, BigDecimal::add);


        return product.getPrice()
                .add(addOnsTotalPrice)
                .multiply(BigDecimal.valueOf(quantity));
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
