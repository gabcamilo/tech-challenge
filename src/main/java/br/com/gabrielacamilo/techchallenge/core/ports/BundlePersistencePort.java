package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;

import java.util.List;

public interface BundlePersistencePort {
    BundleDomain saveBundle(BundleDomain bundle);
    List<BundleDomain> getBundlesByItems(List<ProductDomain> items);
    List<BundleDomain> getAllBundles();
    void deleteBundle(BundleDomain bundle);
}
