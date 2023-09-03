package br.com.gabrielacamilo.techchallenge.core.ports.bundle;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.PersistencePort;

import java.util.List;
import java.util.Optional;

public interface BundlePersistencePort extends PersistencePort<BundleDomain> {
    List<BundleDomain> getBundlesByItems(List<ProductDomain> items);
}
