package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.AddOnDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.ItemType;

import java.util.List;

public interface AddOnPersistencePort {
    AddOnDomain saveAddOn(AddOnDomain addOn);
    void deleteAddOn(AddOnDomain addOn);
    List<AddOnDomain> getAllAddOns();
    List<AddOnDomain> getAllAddOnsByType(ItemType type);
}
