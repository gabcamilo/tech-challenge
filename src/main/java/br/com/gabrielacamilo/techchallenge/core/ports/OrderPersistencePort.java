package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.OrderDomain;

import java.util.List;
import java.util.Optional;

public interface OrderPersistencePort {
    OrderDomain createOrder(OrderDomain order);

    Optional<OrderDomain> getOrder(String id);
    List<OrderDomain> listAllOrders();
    List<OrderDomain> getOrdersByCustomer(CustomerDomain customer);

    // cooking status
    OrderDomain updateOrderStatusCooking(OrderDomain order);
    OrderDomain updateOrderStatusReady(OrderDomain order);
    OrderDomain updateOrderStatusDelivered(OrderDomain order);

    // payment status
    OrderDomain updatePaymentStatusApproved(OrderDomain order);
    OrderDomain updatePaymentStatusRejected(OrderDomain order);
}
