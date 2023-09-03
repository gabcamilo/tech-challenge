package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;

public interface PaymentPort {
    OrderDomain pay(OrderDomain order);
}
