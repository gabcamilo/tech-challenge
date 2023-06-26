package br.com.gabrielacamilo.techchallenge.adapters.inbound.controllers;

import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductServicePort;

import java.util.List;

@RequestMapping("/api/v1/products")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ProductController {
    final ProductServicePort port;

    public ProductController(ProductServicePort port) {
        this.port = port;
    }

    @GetMapping("/types")
    public ResponseEntity<List<ProductType>> getAllProductTypes() {
        return ResponseEntity.ok(port.getAllProductTypes());
    }

    @GetMapping("/types/{type}")
    public ResponseEntity<List<ProductDomain>> getProductsByType(@PathVariable String type) {
        ProductType typeEnum = ProductType.valueOf(type.toUpperCase());
        return ResponseEntity.ok(port.getProductsByType(typeEnum));
    }

    @GetMapping
    public ResponseEntity<List<ProductDomain>> getAllProducts() {
        return ResponseEntity.ok(port.getAllProducts());
    }

//    List<ProductDomain> getAllProducts();
//
}
