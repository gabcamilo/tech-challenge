package br.com.gabrielacamilo.techchallenge.core.services;

import br.com.gabrielacamilo.techchallenge.core.domain.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.BundlePersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.BundleServicePort;

import java.util.List;

public class BundleServicePortImpl implements BundleServicePort {
    private final BundlePersistencePort bundlePersistencePort;

    public BundleServicePortImpl(BundlePersistencePort bundlePersistencePort) {
        this.bundlePersistencePort = bundlePersistencePort;
    }

    @Override
    public BundleDomain saveBundle(BundleDomain bundle) {
        return bundlePersistencePort.saveBundle(bundle);
    }

    @Override
    public List<BundleDomain> getBundlesByItems(List<ProductDomain> products) {
        return bundlePersistencePort.getBundlesByItems(products);
    }

    @Override
    public List<BundleDomain> getAllBundles() {
        return bundlePersistencePort.getAllBundles();
    }

    @Override
    public void deleteBundle(BundleDomain bundle) {
        bundlePersistencePort.deleteBundle(bundle);
    }
}
