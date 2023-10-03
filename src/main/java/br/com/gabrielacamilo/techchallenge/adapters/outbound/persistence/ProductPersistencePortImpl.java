package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.ProductEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductPersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductPersistencePortImpl implements ProductPersistencePort {
    private final ProductRepository productRepository;

    public ProductPersistencePortImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDomain save(ProductDomain product) {
        ProductEntity productEntity = new ProductEntity(product);
        return productRepository.save(productEntity).toDomain();
    }

    @Override
    public Optional<ProductDomain> get(String id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        return productEntity.map(ProductEntity::toDomain);
    }

    @Override
    public ProductDomain update(ProductDomain product) {
        ProductEntity productEntity = new ProductEntity(product);
        return productRepository.save(productEntity).toDomain();
    }

    @Override
    public List<ProductDomain> listProductsByType(ProductType type) {
        return productRepository.findByType(type).stream().map(ProductEntity::toDomain).toList();
    }

    @Override
    public List<ProductDomain> list() {
        productRepository.findAll();
        return productRepository.findAll().stream().map(ProductEntity::toDomain).toList();
    }

    @Override
    public void delete(ProductDomain product) {
        ProductEntity productEntity = new ProductEntity(product);
        productRepository.delete(productEntity);
    }

    @Override
    public List<ProductDomain> listProductsByIds(List<String> products) {
        List<ProductEntity> productsEntities = productRepository.findAllById(products);
        return productsEntities.stream().map(ProductEntity::toDomain).toList();
    }
}
