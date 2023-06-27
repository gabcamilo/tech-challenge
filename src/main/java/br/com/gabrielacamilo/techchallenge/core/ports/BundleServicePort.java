package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;

import java.util.List;

public interface BundleServicePort {
    BundleDomain saveBundle(BundleDomain bundle);
    List<BundleDomain> getBundlesByItems(List<ProductDomain> Products);

    List<BundleDomain> getAllBundles();
    void deleteBundle(BundleDomain bundle);
}
