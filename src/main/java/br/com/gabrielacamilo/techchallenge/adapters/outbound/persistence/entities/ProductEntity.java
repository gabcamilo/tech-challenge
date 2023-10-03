package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
public class ProductEntity extends BaseEntity {
    private String name;
    @Indexed
    private ProductType type;
    private String description;
    private BigDecimal price;

    public ProductEntity(ProductDomain domain) {
        super(domain.getId(), domain.getCreatedAt(), domain.getUpdatedAt());
        this.name = domain.getName();
        this.type = domain.getType();
        this.description = domain.getDescription();
        this.price = domain.getPrice();
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }


    @Override
    public ProductDomain toDomain() {
        return new ProductDomain(
                this.getId(),
                this.name,
                this.type,
                this.description,
                this.price,
                this.getCreatedAt(),
                this.getUpdatedAt()
        );
    }

    @Deprecated // Spring eyes only
    public ProductEntity() {
    }
}
