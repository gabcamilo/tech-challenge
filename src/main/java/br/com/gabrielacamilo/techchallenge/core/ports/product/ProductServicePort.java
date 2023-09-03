package br.com.gabrielacamilo.techchallenge.core.ports.product;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.util.List;
import java.util.Optional;

public interface ProductServicePort {
    ProductDomain saveProduct(ProductDomain item);

    List<ProductDomain> listProductsByType(ProductType type);

    List<ProductDomain> listAllProducts();

    Optional<ProductDomain> getProduct(String id);

    void deleteProduct(ProductDomain item);

    List<ProductDomain>  listProductsByIds(List<String> items);
}
