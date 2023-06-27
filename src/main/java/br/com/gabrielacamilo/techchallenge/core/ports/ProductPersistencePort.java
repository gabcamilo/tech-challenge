package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductPersistencePort {
    ProductDomain saveProduct(ProductDomain Product);
    Optional<ProductDomain> getProduct(String id);
    List<ProductDomain> listProductsByType(ProductType type);
    List<ProductDomain> listAllProducts();
    void deleteProduct(ProductDomain Product);
}
