package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.util.List;

public interface ProductPersistencePort {
    List<ProductType> getAllProductTypes();
    List<ProductDomain> getProductsByType(ProductType type);
    List<ProductDomain> getAllProducts();
}
