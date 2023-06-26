package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "products")
public class ProductEntity {
    @Id
    private String id;
    private String name;
    @Indexed
    private ProductType type;
    private String description;
    private BigDecimal price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
