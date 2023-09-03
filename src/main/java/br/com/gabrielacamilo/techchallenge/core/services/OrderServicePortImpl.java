package br.com.gabrielacamilo.techchallenge.core.services;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.PaymentPort;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderServicePort;

import java.util.List;
import java.util.Optional;

public class OrderServicePortImpl implements OrderServicePort {

    private final OrderPersistencePort orderPersistencePort;
    private final PaymentPort paymentPort;

    public OrderServicePortImpl(OrderPersistencePort orderPersistencePort, PaymentPort paymentPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.paymentPort = paymentPort;
    }


    @Override
    public OrderDomain saveOrder(OrderDomain order) {
        return orderPersistencePort.save(order);
    }

    @Override
    public List<OrderDomain> listAllOrders() {
        return orderPersistencePort.list();
    }

    @Override
    public List<OrderDomain> getOrdersByCustomer(CustomerDomain customer) {
        return orderPersistencePort.getOrdersByCustomer(customer);
    }

    @Override
    public Optional<OrderDomain> getOrder(String id) {
        return orderPersistencePort.get(id);
    }

    @Override
    public Optional<OrderDomain> updateOrderStatusCooking(String id) {
        return updateOrderStatus(id, OrderStatus.COOKING);
    }

    @Override
    public Optional<OrderDomain> updateOrderStatusReady(String id) {
       return updateOrderStatus(id, OrderStatus.READY);
    }

    @Override
    public Optional<OrderDomain> updateOrderStatusDelivered(String id) {
        return updateOrderStatus(id, OrderStatus.DELIVERED);
    }

    @Override
    public Optional<OrderDomain> updatePaymentStatusApproved(String id) {
        return updateOrderPaymentStatus(id, PaymentStatus.APPROVED);
    }

    @Override
    public Optional<OrderDomain> updatePaymentStatusRejected(String id) {
        return updateOrderPaymentStatus(id, PaymentStatus.REJECTED);
    }

    @Override
    public OrderDomain pay(OrderDomain order) {
        return paymentPort.pay(order);
    }

    private Optional<OrderDomain> updateOrderStatus(String id, OrderStatus status) {
        return orderPersistencePort.get(id).map(order -> {
            order.setStatus(status);
            return orderPersistencePort.update(order);
        });
    }

    private Optional<OrderDomain> updateOrderPaymentStatus(String id, PaymentStatus status) {
        return orderPersistencePort.get(id).map(order -> {
            order.setPaymentStatus(status);
            return orderPersistencePort.update(order);
        });
    }
}
