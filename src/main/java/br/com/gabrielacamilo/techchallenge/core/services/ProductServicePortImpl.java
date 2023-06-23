package br.com.gabrielacamilo.techchallenge.core.services;

import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductServicePort;

import java.util.List;

public class ProductServicePortImpl implements ProductServicePort {

    private final ProductPersistencePort productPersistencePort;

    public ProductServicePortImpl(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public List<ProductType> getAllProductTypes() {
        return productPersistencePort.getAllProductTypes();
    }

    @Override
    public List<ProductDomain> getProductsByType(ProductType type) {
        return productPersistencePort.getProductsByType(type);
    }

    @Override
    public List<ProductDomain> getAllProducts() {
        return productPersistencePort.getAllProducts();
    }
}
