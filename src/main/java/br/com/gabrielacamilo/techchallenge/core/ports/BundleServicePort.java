package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.BundleDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ItemDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ItemType;

import java.util.List;

public interface BundleServicePort {
    BundleDomain saveBundle(BundleDomain bundle);
    List<BundleDomain> getBundlesByItems(List<ItemDomain> Items);
    List<BundleDomain> getAllBundles();
    void deleteBundle(BundleDomain bundle);
}
