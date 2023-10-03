package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.BundleEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.bundle.BundlePersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BundlePersistencePortImpl implements BundlePersistencePort {

    private final BundleRepository bundleRepository;

    public BundlePersistencePortImpl(BundleRepository bundleRepository) {
        this.bundleRepository = bundleRepository;
    }

    @Override
    public BundleDomain save(BundleDomain item) {
        BundleEntity bundleEntity = new BundleEntity(item);
        return bundleRepository.save(bundleEntity).toDomain();
    }

    @Override
    public List<BundleDomain> list() {
        List<BundleEntity> bundleEntities = bundleRepository.findAll();
        return bundleEntities.stream().map(BundleEntity::toDomain).toList();
    }

    @Override
    public Optional<BundleDomain> get(String id) {
        Optional<BundleEntity> bundleEntity = bundleRepository.findById(id);
        return bundleEntity.map(BundleEntity::toDomain);
    }

    @Override
    public BundleDomain update(BundleDomain item) {
        BundleEntity bundleEntity = new BundleEntity(item);
        return bundleRepository.save(bundleEntity).toDomain();
    }

    @Override
    public void delete(BundleDomain item) {
        BundleEntity bundleEntity = new BundleEntity(item);
        bundleRepository.delete(bundleEntity);
    }
}
