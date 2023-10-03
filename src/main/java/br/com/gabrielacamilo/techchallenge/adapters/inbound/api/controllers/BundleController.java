package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product.BundleResponse;
import br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.product.CreateBundleRequest;
import br.com.gabrielacamilo.techchallenge.core.domain.product.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.bundle.BundleServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/products/bundles")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BundleController {
    private final BundleServicePort port;

    public BundleController(BundleServicePort port) {
        this.port = port;
    }

    @PostMapping
    public ResponseEntity<BundleResponse> createBundle(@RequestBody CreateBundleRequest request) throws Throwable {
        BundleDomain bundleDomain = request.toDomain();
        List<String> products = request.getItems();
        BundleDomain saved = port.create(bundleDomain, products);
        return ResponseEntity.ok(new BundleResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<BundleResponse>> listAllBundles() throws Throwable {
        List<BundleDomain> bundles = port.list();
        var response = bundles.stream().map(BundleResponse::new).toList();
        return ResponseEntity.ok(response);
    }
}
