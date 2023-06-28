package br.com.gabrielacamilo.techchallenge.adapters.inbound.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.dtos.product.CreateBundleRequest;
import br.com.gabrielacamilo.techchallenge.adapters.dtos.product.CreateBundleResponse;
import br.com.gabrielacamilo.techchallenge.core.domain.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/products/bundles")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BundleController {
    private final ProductServicePort port;

    public BundleController(ProductServicePort port) {
        this.port = port;
    }

    @PostMapping
    public ResponseEntity<CreateBundleResponse> createBundle(@RequestBody @Valid CreateBundleRequest request) {
        List<ProductDomain> items = port.listProductsByIds(request.getItems());
        BundleDomain bundle = port.createBundle(request.toDomain(items));
        return ResponseEntity.ok(new CreateBundleResponse(bundle));
    }

}
