package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.ItemDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ItemType;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.util.List;

public interface ItemServicePort {
    ItemDomain saveItem(ItemDomain item);
    List<ItemType> getAllItemTypes();
    List<ItemDomain> getItemsByType(ItemType type);
    List<ProductDomain> getAllItems();
    void deleteItem(ItemDomain item);
}
