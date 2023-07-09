package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;

import java.util.List;
import java.util.Optional;

public interface OrderServicePort {
    OrderDomain saveOrder(OrderDomain order);

    List<OrderDomain> listAllOrders();

    List<OrderDomain> getOrdersByCustomer(CustomerDomain customer);

    Optional<OrderDomain> getOrder(String id);

    // cooking status
    Optional<OrderDomain> updateOrderStatusCooking(String order);

    Optional<OrderDomain> updateOrderStatusReady(String id);

    Optional<OrderDomain> updateOrderStatusDelivered(String id);

    // payment status
    Optional<OrderDomain> updatePaymentStatusApproved(String id);

    Optional<OrderDomain> updatePaymentStatusRejected(String id);

    OrderDomain pay(OrderDomain order);
}
