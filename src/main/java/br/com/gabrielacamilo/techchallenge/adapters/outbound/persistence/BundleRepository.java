package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.BundleEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BundleRepository extends MongoRepository<BundleEntity, String> {
//    List<BundleEntity> findByItems(List<ProductDomain> items);
}
