package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.ProductEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductPersistencePort;
import br.com.gabrielacamilo.techchallenge.utils.GenericMapper;
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
    public ProductDomain saveProduct(ProductDomain Product) {
        ProductEntity productEntity = productRepository.save(GenericMapper.map(Product, ProductEntity.class));
        return GenericMapper.map(productEntity, ProductDomain.class);
    }

    @Override
    public Optional<ProductDomain> getProduct(String id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        return GenericMapper.map(productEntity, ProductDomain.class);
    }

    @Override
    public List<ProductDomain> listProductsByType(ProductType type) {
        return GenericMapper.map(productRepository.findByType(type), ProductDomain.class);
    }

    @Override
    public List<ProductDomain> listAllProducts() {
        return GenericMapper.map(productRepository.findAll(), ProductDomain.class);
    }

    @Override
    public void deleteProduct(ProductDomain product) {
        productRepository.delete(GenericMapper.map(product, ProductEntity.class));
    }
}
