package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
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

    BundleDomain saveBundle(BundleDomain bundleDomain);

    Optional<BundleDomain> getBundle(String id);

    List<BundleDomain> listAllBundles();
}
