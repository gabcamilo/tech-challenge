package br.com.gabrielacamilo.techchallenge.core.services;
import br.com.gabrielacamilo.techchallenge.core.domain.AddOnDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ItemType;
import br.com.gabrielacamilo.techchallenge.core.ports.AddOnPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.AddOnServicePort;

import java.util.List;

public class AddOnServicePortImpl implements AddOnServicePort {
    private final AddOnPersistencePort addOnPersistencePort;

    public AddOnServicePortImpl(AddOnPersistencePort addOnPersistencePort) {
        this.addOnPersistencePort = addOnPersistencePort;
    }

    @Override
    public AddOnDomain saveAddOn(AddOnDomain addOn) {
        return addOnPersistencePort.saveAddOn(addOn);
    }

    @Override
    public void deleteAddOn(AddOnDomain addOn) {
        addOnPersistencePort.deleteAddOn(addOn);
    }

    @Override
    public List<AddOnDomain> getAllAddOns() {
        return addOnPersistencePort.getAllAddOns();
    }

    @Override
    public List<AddOnDomain> getAllAddOnsByType(ItemType type) {
        return addOnPersistencePort.getAllAddOnsByType(type);
    }
}
