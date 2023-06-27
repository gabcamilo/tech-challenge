package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductPersistencePort;
import br.com.gabrielacamilo.techchallenge.utils.GenericMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductPersistencePortImpl implements ProductPersistencePort {
    private final ProductRepository productRepository;

    public ProductPersistencePortImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductType> getAllProductTypes() {
        return Arrays.stream(ProductType.values()).toList();
    }

    @Override
    public List<ProductDomain> getProductsByType(ProductType type) {
        return GenericMapper.map(productRepository.findByType(type), ProductDomain.class);
    }

    @Override
    public List<ProductDomain> getAllProducts() {
        return GenericMapper.map(productRepository.findAll(), ProductDomain.class);
    }
}
