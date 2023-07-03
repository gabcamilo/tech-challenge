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
    Optional<OrderDomain> updateOrderStatusCooking(String id);
    Optional<OrderDomain> updateOrderStatusReady(String id);
    Optional<OrderDomain> updateOrderStatusDelivered(String id);

    // payment status
    Optional<OrderDomain> updatePaymentStatusApproved(String id);
    Optional<OrderDomain> updatePaymentStatusRejected(String id);
}
