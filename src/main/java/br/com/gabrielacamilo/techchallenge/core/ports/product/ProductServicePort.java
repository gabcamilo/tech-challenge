package br.com.gabrielacamilo.techchallenge.core.ports.product;

import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.util.List;

public interface ProductServicePort {
    ProductDomain saveProduct(ProductDomain item);

    List<ProductDomain> listProductsByType(ProductType type);

<<<<<<< Updated upstream
    List<ProductDomain> listAllProducts();

    Optional<ProductDomain> getProduct(String id);

    void deleteProduct(ProductDomain item);

    List<ProductDomain>  listProductsByIds(List<String> items);
=======
    List<ProductDomain> listProductsByIds(List<String> items);

>>>>>>> Stashed changes
}
