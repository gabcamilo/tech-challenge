package br.com.gabrielacamilo.techchallenge.core.ports.product;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.ports.PersistencePort;

import java.util.List;
import java.util.Optional;

public interface ProductPersistencePort extends PersistencePort<ProductDomain> {
    List<ProductDomain> listProductsByType(ProductType type);
    List<ProductDomain> listProductsByIds(List<String> items);
}
