package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.OrderDomain;

import java.util.Optional;

public interface OrderPaymentPort {
    OrderDomain pay(OrderDomain order);
}
