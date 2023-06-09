package br.com.gabrielacamilo.techchallenge.core.domain.order;

import br.com.gabrielacamilo.techchallenge.core.domain.BaseDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public class OrderProductDomain {

    @NotNull
    private ProductDomain product;

    @Min(1)
    private Integer quantity;

    private List<ProductDomain> addOns;


    private BigDecimal total;

    public OrderProductDomain(ProductDomain product, int quantity, List<ProductDomain> addOns) {
        this.product = product;
        this.quantity = quantity;

        if(addOns == null){
            this.addOns = List.of();
        } else {
            this.addOns = addOns;
        }
        this.total = calculatePrice();

        BaseDomain.validate(this);
    }

    public OrderProductDomain(ProductDomain product, int quantity, List<ProductDomain> addOns, BigDecimal total) {
        this.product = product;
        this.quantity = quantity;
        this.addOns = addOns;
        this.total = total;

        BaseDomain.validate(this);
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
