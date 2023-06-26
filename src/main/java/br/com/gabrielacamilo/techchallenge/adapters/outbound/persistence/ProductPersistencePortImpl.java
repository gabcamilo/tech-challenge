package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductPersistencePort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class ProductPersistencePortImpl implements ProductPersistencePort {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductPersistencePortImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<ProductType> getAllProductTypes() {
        return Arrays.stream(ProductType.values()).toList();
    }

    @Override
    public List<ProductDomain> getProductsByType(ProductType type) {
        return modelMapper.map(productRepository.findByType(type), List.class);
    }

    @Override
    public List<ProductDomain> getAllProducts() {
        return modelMapper.map(productRepository.findAll(), List.class);
    }
}
