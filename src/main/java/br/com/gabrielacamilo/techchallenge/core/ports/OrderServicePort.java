package br.com.gabrielacamilo.techchallenge.core.ports;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;

import java.util.List;

public interface OrderServicePort {
    OrderDomain createOrder(OrderDomain order);
    List<OrderDomain> getAllActiveOrders();
    List<OrderDomain> getOrderByCustomer(CustomerDomain customer);

    // cooking status
    OrderDomain updateOrderStatusCooking(OrderDomain order);
    OrderDomain updateOrderStatusReady(OrderDomain order);
    OrderDomain updateOrderStatusDelivered(OrderDomain order);

    // payment status
    OrderDomain updatePaymentStatusApproved(OrderDomain order);
    OrderDomain updatePaymentStatusRejected(OrderDomain order);
}
