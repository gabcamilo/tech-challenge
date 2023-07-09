package br.com.gabrielacamilo.techchallenge.adapters.outbound.services;

import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentServiceImpl implements PaymentService {
    @Override
    public PaymentStatus pay(OrderDomain order) {
        return PaymentStatus.APPROVED;
    }
}
