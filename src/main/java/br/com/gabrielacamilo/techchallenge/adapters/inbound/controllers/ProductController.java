package br.com.gabrielacamilo.techchallenge.adapters.inbound.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.dtos.product.GetProductResponse;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import br.com.gabrielacamilo.techchallenge.utils.GenericMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductServicePort;

import java.util.List;

@RequestMapping("/api/v1/products")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    private final ProductServicePort port;

    public ProductController(ProductServicePort port) {
        this.port = port;
    }

    @GetMapping("/types")
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        return ResponseEntity.ok(port.getAllProductTypes());
    }

    @GetMapping("/types/{type}")
    public ResponseEntity<List<GetProductResponse>> listProductsByType(@PathVariable String type) {
        ProductType typeEnum = ProductType.valueOf(type.toUpperCase());

        List<ProductDomain> products = port.getProductsByType(typeEnum);
        List<GetProductResponse> productsResponse = GenericMapper.map(products, GetProductResponse.class);

        return ResponseEntity.ok(productsResponse);
    }

    @GetMapping
    public ResponseEntity<List<GetProductResponse>> listAllProducts() {
        List<ProductDomain> products = port.getAllProducts();
        List<GetProductResponse> productsResponse = GenericMapper.map(products, GetProductResponse.class);

        return ResponseEntity.ok(productsResponse);
    }
}
