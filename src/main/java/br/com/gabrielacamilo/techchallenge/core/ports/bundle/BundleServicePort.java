package br.com.gabrielacamilo.techchallenge.core.ports.bundle;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.ServicePort;

import java.util.List;

public interface BundleServicePort extends ServicePort<BundleDomain, BundleValidationPort, BundlePersistencePort> {
    BundleDomain create(BundleDomain domain, List<String> products) throws Throwable;

}
