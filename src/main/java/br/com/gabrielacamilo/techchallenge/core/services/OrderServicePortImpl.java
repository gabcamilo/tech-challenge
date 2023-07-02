package br.com.gabrielacamilo.techchallenge.core.services;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.OrderPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.OrderServicePort;

import java.util.List;
import java.util.Optional;

public class OrderServicePortImpl implements OrderServicePort {

    private final OrderPersistencePort orderPersistencePort;

    public OrderServicePortImpl(OrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }


    @Override
    public OrderDomain createOrder(OrderDomain order) {
        return orderPersistencePort.createOrder(order);
    }

    @Override
    public List<OrderDomain> getAllActiveOrders() {
        return orderPersistencePort.getAllActiveOrders();
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
    public OrderDomain updateOrderStatusCooking(OrderDomain order) {
        return orderPersistencePort.updateOrderStatusCooking(order);
    }

    @Override
    public OrderDomain updateOrderStatusReady(OrderDomain order) {
        return orderPersistencePort.updateOrderStatusReady(order);
    }

    @Override
    public OrderDomain updateOrderStatusDelivered(OrderDomain order) {
        return orderPersistencePort.updateOrderStatusDelivered(order);
    }

    @Override
    public OrderDomain updatePaymentStatusApproved(OrderDomain order) {
        return orderPersistencePort.updatePaymentStatusApproved(order);
    }

    @Override
    public OrderDomain updatePaymentStatusRejected(OrderDomain order) {
        return orderPersistencePort.updatePaymentStatusRejected(order);
    }
}
