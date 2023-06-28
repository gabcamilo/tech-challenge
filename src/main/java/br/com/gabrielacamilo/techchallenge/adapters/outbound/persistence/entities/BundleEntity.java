package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities;

import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "products")
public class BundleEntity extends ProductEntity {
    private List<ProductDomain> items;
    private BigDecimal discountPercentage;

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
