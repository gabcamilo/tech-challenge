package br.com.gabrielacamilo.techchallenge.core.services;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.bundle.BundlePersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.bundle.BundleServicePort;

import java.util.List;

public class BundleServicePortImpl implements BundleServicePort {
    private final BundlePersistencePort bundlePersistencePort;

    public BundleServicePortImpl(BundlePersistencePort bundlePersistencePort) {
        this.bundlePersistencePort = bundlePersistencePort;
    }

    @Override
    public BundleDomain saveBundle(BundleDomain bundle) {
        return bundlePersistencePort.save(bundle);
    }

    @Override
    public List<BundleDomain> getBundlesByItems(List<ProductDomain> products) {
        return bundlePersistencePort.getBundlesByItems(products);
    }

    @Override
    public List<BundleDomain> getAllBundles() {
        return bundlePersistencePort.list();
    }

    @Override
    public void deleteBundle(BundleDomain bundle) {
        bundlePersistencePort.delete(bundle);
    }
}
