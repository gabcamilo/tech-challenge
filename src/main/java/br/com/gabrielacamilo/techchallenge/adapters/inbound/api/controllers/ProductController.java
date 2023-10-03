package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product.CreateProductRequest;
import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product.ProductResponse;
import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product.UpdateProductRequest;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/api/v1/products")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    private final ProductServicePort port;

    public ProductController(ProductServicePort port) {
        this.port = port;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) throws Throwable {
        ProductDomain product = request.toDomain();
        ProductDomain saved = port.create(product);
        ProductResponse response = new ProductResponse(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String id) throws Throwable {
        ProductDomain product = port.get(id);
        return ResponseEntity.ok(new ProductResponse(product));
    }

    @GetMapping("/types")
    public ResponseEntity<List<ProductType>> listAllProductTypes() {
        return ResponseEntity.ok(
                Arrays.stream(ProductType.values()).toList()
        );
    }

    @GetMapping("/types/{type}")
    public ResponseEntity<List<ProductResponse>> listProductsByType(@PathVariable String type) {
        ProductType typeEnum = ProductType.valueOf(type.toUpperCase());

        List<ProductDomain> products = port.listProductsByType(typeEnum);
        List<ProductResponse> response = products.stream().map(ProductResponse::new).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listAllProducts() throws Throwable {
        List<ProductDomain> products = port.list();
        List<ProductResponse> response = products.stream().map(ProductResponse::new).toList();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id) throws Throwable {
        port.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody UpdateProductRequest request, @PathVariable String id) throws Throwable {
        ProductDomain domain = request.toDomain();
        ProductDomain updated = port.update(domain, id);
        return ResponseEntity.ok(new ProductResponse(updated));
    }
}
