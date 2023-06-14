package br.com.gabrielacamilo.techchallenge.core.domain;

import br.com.gabrielacamilo.techchallenge.core.domain.enums.ProductType;

import java.math.BigDecimal;
import java.util.UUID;

public class AddOnDomain extends ProductDomain {

    public AddOnDomain(UUID id, String name, BigDecimal price, ProductType type, String description) {
        super(id, name, type, description, price);
    }
}
