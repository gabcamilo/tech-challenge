package br.com.gabrielacamilo.techchallenge.adapters.dtos.product;

import br.com.gabrielacamilo.techchallenge.core.domain.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BundleResponse {

    private final String id;

    private final String name;

    private final String description;

    private final BigDecimal price;

    private final ProductType type;

    private List<CreateBundleResponseItems> items;

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

        this.items = domain.getItems().stream().map(item -> new CreateBundleResponseItems(
                item.getId(),
                item.getName(),
                item.getType(),
                item.getPrice()
        )).toList();
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

    public List<CreateBundleResponseItems> getItems() {
        return items;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    private class CreateBundleResponseItems {
        private String id;
        private String name;
        private ProductType type;
        private BigDecimal price;

        public CreateBundleResponseItems(String id, String name, ProductType type, BigDecimal price) {
            this.id = id;
            this.name = name;
            this.type = type;
            this.price = price;
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

