package br.com.gabrielacamilo.techchallenge.adapters.outbound.services;

import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;

public interface PaymentService {
    public PaymentStatus pay(OrderDomain order);
}
