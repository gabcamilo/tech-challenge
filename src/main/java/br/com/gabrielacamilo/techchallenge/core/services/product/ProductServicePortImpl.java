package br.com.gabrielacamilo.techchallenge.core.services.product;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductServicePort;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductValidationPort;

import java.util.List;

public class ProductServicePortImpl implements ProductServicePort {

    private final ProductPersistencePort persistencePort;
    private final ProductValidationPort validationPort;

    private static final String PRODUCT = "Product";

    public ProductServicePortImpl(ProductPersistencePort persistencePort, ProductValidationPort validationPort) {
        this.persistencePort = persistencePort;
        this.validationPort = validationPort;
    }

    @Override
    public ProductDomain create(ProductDomain product) throws Throwable {
        validationPort.validateCreationalBusinessRules(product, persistencePort);
        return validateDomainDataAndSave(product, validationPort, persistencePort);
    }

    @Override
    public ProductDomain update(ProductDomain product, String id) throws Throwable {
        ProductDomain productDomain = validationPort.mustExist(persistencePort.get(id), PRODUCT);
        validationPort.validateUpdateBusinessRules(product, productDomain, persistencePort);
        return validateDomainDataAndSave(product, validationPort, persistencePort);
    }

    @Override
    public ProductDomain get(String id) {
        return validationPort.mustExist(persistencePort.get(id), PRODUCT);
    }

    @Override
    public List<ProductDomain> listProductsByType(ProductType type) {
        return persistencePort.listProductsByType(type);
    }

    @Override
    public List<ProductDomain> list() {
        return persistencePort.list();
    }

    @Override
    public List<ProductDomain> listProductsByIds(List<String> items) {
        return persistencePort.listProductsByIds(items);
    }

    @Override
    public void delete(String id) throws Throwable {
        ProductDomain productDomain = validationPort.mustExist(persistencePort.get(id), PRODUCT);
        validationPort.validateDeleteBusinessRules(productDomain, persistencePort);
        persistencePort.delete(productDomain);
    }

    @Override
    public ProductDomain validateDomainDataAndSave(ProductDomain domain, ProductValidationPort validationPort, ProductPersistencePort persistencePort) throws Throwable {
        validationPort.validateDomainData(domain);
        return persistencePort.save(domain);
    }
}
