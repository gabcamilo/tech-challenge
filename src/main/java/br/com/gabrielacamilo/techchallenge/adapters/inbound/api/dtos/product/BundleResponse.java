package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BundleResponse implements Serializable {

    private final String id;

    private final String name;

    private final String description;

    private final BigDecimal price;

    private final ProductType type;

    private List<CreateBundleResponseItem> items;

    private BigDecimal discountPercentage;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public BundleResponse(BundleDomain domain) {
        this.id = domain.getId();
        this.name = domain.getName();
        this.description = domain.getDescription();
        this.price = domain.getPrice();
        this.type = domain.getType();
        this.discountPercentage = domain.getDiscountPercentage();
        this.createdAt = domain.getCreatedAt();
        this.updatedAt = domain.getUpdatedAt();

        this.items = domain.getProducts().stream().map(CreateBundleResponseItem::new).toList();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductType getType() {
        return type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<CreateBundleResponseItem> getItems() {
        return items;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    private class CreateBundleResponseItem {
        private String id;
        private String name;
        private ProductType type;
        private BigDecimal price;

        public CreateBundleResponseItem(ProductDomain domain) {
            this.id = domain.getId();
            this.name = domain.getName();
            this.type = domain.getType();
            this.price = domain.getPrice();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public ProductType getType() {
            return type;
        }

        public BigDecimal getPrice() {
            return price;
        }
    }
}

