package br.com.gabrielacamilo.techchallenge.adapters.inbound.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.dtos.product.CreateProductRequest;
import br.com.gabrielacamilo.techchallenge.adapters.dtos.product.ProductResponse;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductServicePort;
import br.com.gabrielacamilo.techchallenge.utils.GenericMapper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/products")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    private final ProductServicePort port;

    public ProductController(ProductServicePort port) {
        this.port = port;
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid CreateProductRequest request) {
        ProductDomain product = request.toDomain();
        ProductDomain saved = port.saveProduct(product);
        ProductResponse response = new ProductResponse(saved);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable String id) {
        Optional<ProductDomain> product = port.getProduct(id);
        return product.map(value -> ResponseEntity.ok(new ProductResponse(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
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
        List<ProductResponse> productsResponse = GenericMapper.map(products, ProductResponse.class);

        return ResponseEntity.ok(productsResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> listAllProducts() {
        List<ProductDomain> products = port.listAllProducts();
        List<ProductResponse> productsResponse = GenericMapper.map(products, ProductResponse.class);

        return ResponseEntity.ok(productsResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id){
        Optional<ProductDomain> product = port.getProduct(id);
        if(product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        product.ifPresent(value -> port.deleteProduct(value));
        return ResponseEntity.noContent().build();
    }
}
