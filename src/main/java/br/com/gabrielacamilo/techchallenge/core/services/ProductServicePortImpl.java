package br.com.gabrielacamilo.techchallenge.core.services;

import br.com.gabrielacamilo.techchallenge.core.domain.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductServicePort;

import java.util.List;
import java.util.Optional;

public class ProductServicePortImpl implements ProductServicePort {

    private final ProductPersistencePort productPersistencePort;

    public ProductServicePortImpl(ProductPersistencePort productPersistencePort) {
        this.productPersistencePort = productPersistencePort;
    }

    @Override
    public ProductDomain saveProduct(ProductDomain product) {
        return productPersistencePort.saveProduct(product);
    }

    @Override
    public Optional<ProductDomain> getProduct(String id) {
        return productPersistencePort.getProduct(id);
    }

    @Override
    public List<ProductDomain> listProductsByType(ProductType type) {
        return productPersistencePort.listProductsByType(type);
    }

    @Override
    public List<ProductDomain> listAllProducts() {
        return productPersistencePort.listAllProducts();
    }

    @Override
    public void deleteProduct(ProductDomain product) {
        productPersistencePort.deleteProduct(product);
    }

    @Override
    public List<ProductDomain> listProductsByIds(List<String> items) {
        return productPersistencePort.listProductsByIds(items);
    }

    @Override
    public BundleDomain saveBundle(BundleDomain bundleDomain) {
        return productPersistencePort.saveBundle(bundleDomain);
    }

    @Override
    public Optional<BundleDomain> getBundle(String id) {
        return productPersistencePort.getBundle(id);
    }

    @Override
    public List<BundleDomain> listAllBundles() {
        return productPersistencePort.listAllBundles();
    }
}
