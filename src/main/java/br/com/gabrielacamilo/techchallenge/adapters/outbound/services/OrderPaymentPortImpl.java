package br.com.gabrielacamilo.techchallenge.adapters.outbound.services;

import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;
import br.com.gabrielacamilo.techchallenge.core.ports.OrderPaymentPort;
import br.com.gabrielacamilo.techchallenge.core.ports.OrderPersistencePort;
import org.springframework.stereotype.Component;

@Component
public class OrderPaymentPortImpl implements OrderPaymentPort {
    private final PaymentService paymentService;
    private final OrderPersistencePort orderPersistencePort;

    public OrderPaymentPortImpl(PaymentService paymentService, OrderPersistencePort orderPersistencePort) {
        this.paymentService = paymentService;
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public OrderDomain pay(OrderDomain order) {
        PaymentStatus paymentStatus = paymentService.pay(order);
        return orderPersistencePort.updatePaymentStatus(order.getId(), paymentStatus);
    }
}
