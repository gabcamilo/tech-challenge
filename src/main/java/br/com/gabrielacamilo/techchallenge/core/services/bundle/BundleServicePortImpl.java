package br.com.gabrielacamilo.techchallenge.core.services.bundle;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.bundle.BundlePersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.bundle.BundleServicePort;
import br.com.gabrielacamilo.techchallenge.core.ports.bundle.BundleValidationPort;

import java.util.List;

public class BundleServicePortImpl implements BundleServicePort {
    private final BundlePersistencePort persistencePort;

    private final BundleValidationPort validation;

    private static final String BUNDLE = "Bundle";

    public BundleServicePortImpl(BundlePersistencePort persistencePort, BundleValidationPort validation) {
        this.persistencePort = persistencePort;
        this.validation = validation;
    }


    @Override
    public BundleDomain create(BundleDomain domain) throws Throwable {
        validation.validateCreationalBusinessRules(domain, persistencePort);
        return validateDomainDataAndSave(domain, validation, persistencePort);
    }

    @Override
    public BundleDomain update(BundleDomain bundle, String id) throws Throwable {
        BundleDomain bundleDomain = validation.mustExist(persistencePort.get(id), BUNDLE);
        validation.validateUpdateBusinessRules(bundle, bundleDomain, persistencePort);
        return validateDomainDataAndSave(bundle, validation, persistencePort);
    }

    @Override
    public BundleDomain get(String id) throws Throwable {
        return validation.mustExist(persistencePort.get(id), BUNDLE);
    }

    @Override
    public void delete(String id) throws Throwable {
        BundleDomain bundleDomain = validation.mustExist(persistencePort.get(id), BUNDLE);
        validation.validateDeleteBusinessRules(bundleDomain, persistencePort);
        persistencePort.delete(bundleDomain);
    }

    @Override
    public List<BundleDomain> list() throws Throwable {
        return persistencePort.list();
    }

    @Override
    public BundleDomain validateDomainDataAndSave(BundleDomain domain, BundleValidationPort validationPort, BundlePersistencePort persistencePort) throws Throwable {
        validationPort.validateCreationalBusinessRules(domain, persistencePort);
        return persistencePort.save(domain);
    }

    @Override
    public BundleDomain create(BundleDomain domain, List<String> products) throws Throwable {
        validation.validateCreationalBusinessRules(domain, persistencePort);
        return validateDomainDataAndSave(domain, validation, persistencePort);
    }
}
