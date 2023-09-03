package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.ProductEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductPersistencePort;
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
    public ProductDomain save(ProductDomain product) {
        ProductEntity productEntity = productRepository.save(GenericMapper.map(product, ProductEntity.class));
        return GenericMapper.map(productEntity, ProductDomain.class);
    }

    @Override
    public Optional<ProductDomain> get(String id) {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        return GenericMapper.map(productEntity, ProductDomain.class);
    }

    @Override
    public ProductDomain update(ProductDomain product) {
        ProductEntity productEntity = productRepository.save(GenericMapper.map(product, ProductEntity.class));
        return GenericMapper.map(productEntity, ProductDomain.class);
    }

    @Override
    public List<ProductDomain> listProductsByType(ProductType type) {
        return GenericMapper.map(productRepository.findByType(type), ProductDomain.class);
    }

    @Override
    public List<ProductDomain> list() {
        productRepository.findAll();
        return GenericMapper.map(productRepository.findAll(), ProductDomain.class);
    }

    @Override
    public void delete(ProductDomain product) {
        productRepository.delete(GenericMapper.map(product, ProductEntity.class));
    }

    @Override
    public List<ProductDomain> listProductsByIds(List<String> products) {
        List<ProductEntity> productsEntities = productRepository.findAllById(products);
        return GenericMapper.map(productsEntities, ProductDomain.class);
    }
}
