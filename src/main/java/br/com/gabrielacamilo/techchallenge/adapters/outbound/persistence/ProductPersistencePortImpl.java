package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.BundleEntity;
import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.ProductEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.BundleDomain;
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
        productRepository.findAll();
        return GenericMapper.map(productRepository.findAll(), ProductDomain.class);
    }

    @Override
    public void deleteProduct(ProductDomain product) {
        productRepository.delete(GenericMapper.map(product, ProductEntity.class));
    }

    @Override
    public List<ProductDomain> listProductsByIds(List<String> items) {
        List<ProductEntity> itemsEntities = productRepository.findAllById(items);
        return GenericMapper.map(itemsEntities, ProductDomain.class);
    }

    @Override
    public BundleDomain saveBundle(BundleDomain bundle) {
        ProductEntity bundleEntity = productRepository.save(GenericMapper.map(bundle, BundleEntity.class));
        return GenericMapper.map(bundleEntity, BundleDomain.class);
    }

    @Override
    public List<BundleDomain> listAllBundles() {
        return GenericMapper.map(productRepository.findByType(ProductType.BUNDLE), BundleDomain.class);
    }

    @Override
    public Optional<BundleDomain> getBundle(String id) {
        return GenericMapper.map(productRepository.findById(id), BundleDomain.class);
    }
}
