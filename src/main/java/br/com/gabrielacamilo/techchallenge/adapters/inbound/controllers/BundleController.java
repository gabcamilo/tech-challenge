package br.com.gabrielacamilo.techchallenge.adapters.inbound.controllers;

import br.com.gabrielacamilo.techchallenge.adapters.dtos.product.BundleResponse;
import br.com.gabrielacamilo.techchallenge.adapters.dtos.product.CreateBundleRequest;
import br.com.gabrielacamilo.techchallenge.adapters.dtos.product.UpdateBundleRequest;
import br.com.gabrielacamilo.techchallenge.core.domain.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.ProductServicePort;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/products/bundles")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BundleController {
    private final ProductServicePort port;

    public BundleController(ProductServicePort port) {
        this.port = port;
    }

    @PostMapping
    public ResponseEntity<BundleResponse> createBundle(@RequestBody @Valid CreateBundleRequest request) {
        List<ProductDomain> items = port.listProductsByIds(request.getItems());
        BundleDomain bundle = port.saveBundle(request.toDomain(items));
        return ResponseEntity.ok(new BundleResponse(bundle));
    }

    @GetMapping
    public ResponseEntity<List<BundleResponse>> listAllBundles() {
        List<BundleDomain> bundles = port.listAllBundles();
        var response = bundles.stream().map(BundleResponse::new).toList();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BundleResponse> updateBundle(@RequestBody @Valid UpdateBundleRequest request, @PathVariable String id) {
        Optional<BundleDomain> bundleDomainOptional = port.getBundle(id);
        List<ProductDomain> items = port.listProductsByIds(request.getItems());


        return bundleDomainOptional.map(bundle -> {
                    BundleDomain domain = request.toDomain(items, id);
                    bundle.setItems(domain.getItems());
                    bundle.setName(domain.getName());
                    bundle.setDescription(domain.getDescription());
                    bundle.setDiscountPercentage(domain.getDiscountPercentage());
                    bundle.setPrice(domain.getPrice());

                    BundleDomain saved = port.saveBundle(domain);
                    return ResponseEntity.ok(new BundleResponse(saved));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
