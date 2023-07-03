package br.com.gabrielacamilo.techchallenge.core.services;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.OrderPaymentPort;
import br.com.gabrielacamilo.techchallenge.core.ports.OrderPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.OrderServicePort;

import java.util.List;
import java.util.Optional;

public class OrderServicePortImpl implements OrderServicePort {

    private final OrderPersistencePort orderPersistencePort;
    private final OrderPaymentPort orderPaymentPort;

    public OrderServicePortImpl(OrderPersistencePort orderPersistencePort, OrderPaymentPort orderPaymentPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.orderPaymentPort = orderPaymentPort;
    }


    @Override
    public OrderDomain saveOrder(OrderDomain order) {
        return orderPersistencePort.saveOrder(order);
    }

    @Override
    public List<OrderDomain> listAllOrders() {
        return orderPersistencePort.listAllOrders();
    }

    @Override
    public List<OrderDomain> getOrdersByCustomer(CustomerDomain customer) {
        return orderPersistencePort.getOrdersByCustomer(customer);
    }

    @Override
    public Optional<OrderDomain> getOrder(String id) {
        return orderPersistencePort.getOrder(id);
    }

    @Override
    public Optional<OrderDomain> updateOrderStatusCooking(String id) {
        return orderPersistencePort.updateOrderStatusCooking(id);
    }

    @Override
    public Optional<OrderDomain> updateOrderStatusReady(String id) {
        return orderPersistencePort.updateOrderStatusReady(id);
    }

    @Override
    public Optional<OrderDomain> updateOrderStatusDelivered(String id) {
        return orderPersistencePort.updateOrderStatusDelivered(id);
    }

    @Override
    public Optional<OrderDomain> updatePaymentStatusApproved(String id) {
        return orderPersistencePort.updatePaymentStatusApproved(id);
    }

    @Override
    public Optional<OrderDomain> updatePaymentStatusRejected(String id) {
        return orderPersistencePort.updatePaymentStatusRejected(id);
    }

    @Override
    public OrderDomain pay(OrderDomain order) {
        return orderPaymentPort.pay(order);
    }
}
