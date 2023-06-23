package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.OrderDomain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderServicePort {
    OrderDomain createOrder(OrderDomain order);
    List<OrderDomain> getAllActiveOrders();
    List<OrderDomain> getOrdersByCustomer(CustomerDomain customer);
    Optional<OrderDomain> getOrder (UUID id);

    // cooking status
    OrderDomain updateOrderStatusCooking(OrderDomain order);
    OrderDomain updateOrderStatusReady(OrderDomain order);
    OrderDomain updateOrderStatusDelivered(OrderDomain order);

    // payment status
    OrderDomain updatePaymentStatusApproved(OrderDomain order);
    OrderDomain updatePaymentStatusRejected(OrderDomain order);
}
