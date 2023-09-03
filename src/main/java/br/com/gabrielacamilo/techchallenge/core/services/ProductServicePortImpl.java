package br.com.gabrielacamilo.techchallenge.core.services;

import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductServicePort;

import java.util.List;
import java.util.Optional;

public class ProductServicePortImpl implements ProductServicePort {

    private final ProductPersistencePort productPersistencePort;

    public ProductServicePortImpl(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public ProductDomain saveProduct(ProductDomain product) {
        return productPersistencePort.save(product);
    }

    @Override
    public Optional<ProductDomain> getProduct(String id) {
        return productPersistencePort.get(id);
    }

    @Override
    public List<ProductDomain> listProductsByType(ProductType type) {
        return productPersistencePort.listProductsByType(type);
    }

    @Override
    public List<ProductDomain> listAllProducts() {
        return productPersistencePort.list();
    }

    @Override
    public void deleteProduct(ProductDomain product) {
        productPersistencePort.delete(product);
    }

    @Override
    public List<ProductDomain> listProductsByIds(List<String> items) {
        return productPersistencePort.listProductsByIds(items);
    }
}
