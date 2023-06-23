package br.com.gabrielacamilo.techchallenge.core.services;
import br.com.gabrielacamilo.techchallenge.core.domain.ItemDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ItemType;
import br.com.gabrielacamilo.techchallenge.core.ports.ItemPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.ItemServicePort;

import java.util.List;

public class ItemServicePortImpl implements ItemServicePort {
    private final ItemPersistencePort itemPersistencePort;

    public ItemServicePortImpl(ItemPersistencePort itemPersistencePort) {
        this.itemPersistencePort = itemPersistencePort;
    }

    @Override
    public ItemDomain saveItem(ItemDomain item) {
        return itemPersistencePort.saveItem(item);
    }

    @Override
    public List<ItemType> getAllItemTypes() {
        return itemPersistencePort.getAllItemTypes();
    }

    @Override
    public List<ItemDomain> getItemsByType(ItemType type) {
        return itemPersistencePort.getItemsByType(type);
    }

    @Override
    public List<ProductDomain> getAllItems() {
        return itemPersistencePort.getAllItems();
    }

    @Override
    public void deleteItem(ItemDomain item) {
        itemPersistencePort.deleteItem(item);
    }
}
