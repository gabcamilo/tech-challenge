package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.controllers;

import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductServicePort;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/products/bundles")
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class BundleController {
    private final ProductServicePort servicePort;

    public BundleController(ProductServicePort servicePort) {
        this.servicePort = servicePort;
    }

//    @PostMapping
//    public ResponseEntity<BundleResponse> createBundle(@RequestBody @Valid CreateBundleRequest request) {
//        List<ProductDomain> items = servicePort.listProductsByIds(request.getItems());
//        BundleDomain bundle = servicePort.saveProduct(request.toDomain(items));
//        return ResponseEntity.ok(new BundleResponse(bundle));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<BundleResponse>> listAllBundles() {
//        List<BundleDomain> bundles = servicePort.list();
//        var response = bundles.stream().map(BundleResponse::new).toList();
//        return ResponseEntity.ok(response);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<BundleResponse> updateBundle(@RequestBody @Valid UpdateBundleRequest request, @PathVariable String id) {
//        Optional<BundleDomain> bundleDomainOptional = servicePort.getBundle(id);
//        List<ProductDomain> items = servicePort.listProductsByIds(request.getItems());
//
//
//        return bundleDomainOptional.map(bundle -> {
//                    BundleDomain domain = request.toDomain(items, id);
//                    bundle.setItems(domain.getItems());
//                    bundle.setName(domain.getName());
//                    bundle.setDescription(domain.getDescription());
//                    bundle.setDiscountPercentage(domain.getDiscountPercentage());
//                    bundle.setPrice(domain.getPrice());
//
//                    BundleDomain saved = servicePort.saveBundle(domain);
//                    return ResponseEntity.ok(new BundleResponse(saved));
//                })
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }

}
