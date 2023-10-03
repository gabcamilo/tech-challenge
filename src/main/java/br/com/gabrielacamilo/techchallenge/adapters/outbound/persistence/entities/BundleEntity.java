package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "products")
public class BundleEntity extends ProductEntity {
    @DBRef
    private List<ProductEntity> products;
    private BigDecimal discountPercentage;

    public BundleEntity(BundleDomain domain) {
        super(domain);
        this.products = domain.getProducts().stream().map(ProductEntity::new).toList();
        this.discountPercentage = domain.getDiscountPercentage();
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(BigDecimal discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public BundleDomain toDomain() {
        List<ProductDomain> items = this.products.stream()
                .map(ProductEntity::toDomain)
                .toList();

        return new BundleDomain(
                this.getId(),
                this.getName(),
                this.getType(),
                this.getDescription(),
                this.getPrice(),
                items,
                this.discountPercentage,
                this.getCreatedAt(),
                this.getUpdatedAt()
        );
    }

    @Deprecated // Spring eyes only
    public BundleEntity() {
    }
}
