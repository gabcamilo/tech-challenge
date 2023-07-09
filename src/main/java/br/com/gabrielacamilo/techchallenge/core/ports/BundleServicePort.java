package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;

import java.util.List;

public interface BundleServicePort {
    BundleDomain saveBundle(BundleDomain bundle);
    List<BundleDomain> getBundlesByItems(List<ProductDomain> Products);

    List<BundleDomain> getAllBundles();
    void deleteBundle(BundleDomain bundle);
}
