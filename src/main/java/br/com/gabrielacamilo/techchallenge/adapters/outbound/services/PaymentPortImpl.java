package br.com.gabrielacamilo.techchallenge.adapters.outbound.services;

import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;
import br.com.gabrielacamilo.techchallenge.core.ports.PaymentPort;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderPersistencePort;
import org.springframework.stereotype.Component;

@Component
public class PaymentPortImpl implements PaymentPort {
    private final PaymentService paymentService;
    private final OrderPersistencePort orderPersistencePort;

    public PaymentPortImpl(PaymentService paymentService, OrderPersistencePort orderPersistencePort) {
        this.paymentService = paymentService;
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public OrderDomain pay(OrderDomain order) {
        PaymentStatus paymentStatus = paymentService.pay(order);
        return orderPersistencePort.get(order.getId())
                .map(orderDomain -> {
                    orderDomain.setPaymentStatus(paymentStatus);
                    return orderPersistencePort.update(orderDomain);
                }).get();

    }
}
