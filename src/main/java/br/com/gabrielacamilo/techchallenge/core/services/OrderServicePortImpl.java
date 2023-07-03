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
}
