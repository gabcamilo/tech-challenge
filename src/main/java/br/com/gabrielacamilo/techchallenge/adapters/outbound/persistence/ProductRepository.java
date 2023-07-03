package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.ProductEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<ProductEntity, String> {
    List<ProductEntity> findByType(ProductType type);
}
