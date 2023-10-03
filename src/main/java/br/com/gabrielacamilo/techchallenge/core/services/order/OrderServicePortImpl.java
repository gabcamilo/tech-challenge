package br.com.gabrielacamilo.techchallenge.core.services.order;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.PaymentPort;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderServicePort;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderValidationPort;

import java.util.List;
import java.util.Optional;

public class OrderServicePortImpl implements OrderServicePort {

    private final OrderPersistencePort persistencePort;
    private final OrderValidationPort validationPort;
    private final PaymentPort paymentPort;

    private static final String ORDER = "Order";

    public OrderServicePortImpl(OrderPersistencePort persistencePort, OrderValidationPort validationPort, PaymentPort paymentPort) {
        this.persistencePort = persistencePort;
        this.validationPort = validationPort;
        this.paymentPort = paymentPort;
    }


    @Override
    public OrderDomain create(OrderDomain order) throws Throwable {
        validationPort.validateCreationalBusinessRules(order, persistencePort);
        return validateDomainDataAndSave(order, validationPort, persistencePort);
    }

    @Override
    public List<OrderDomain> list() {
        return persistencePort.list();
    }

    @Override
    public OrderDomain validateDomainDataAndSave(OrderDomain domain, OrderValidationPort validationPort, OrderPersistencePort persistencePort) throws Throwable {
        return null;
    }

    @Override
    public List<OrderDomain> getOrdersByCustomer(CustomerDomain customer) {
        return persistencePort.getOrdersByCustomer(customer);
    }

    @Override
    public OrderDomain get(String id) {
        return validationPort.mustExist(persistencePort.get(id), ORDER);
    }

    @Override
    public void delete(String id) throws Throwable {

    }

    @Override
    public OrderDomain updateOrderStatusCooking(String id) throws Throwable {
        return updateOrderStatus(id, OrderStatus.COOKING);
    }

    @Override
    public OrderDomain updateOrderStatusReady(String id) throws Throwable {
        return updateOrderStatus(id, OrderStatus.READY);
    }

    @Override
    public OrderDomain updateOrderStatusDelivered(String id) throws Throwable {
        return updateOrderStatus(id, OrderStatus.DELIVERED);
    }

    @Override
    public OrderDomain updatePaymentStatusApproved(String id) throws Throwable {
        return updateOrderPaymentStatus(id, PaymentStatus.APPROVED);
    }

    @Override
    public OrderDomain updatePaymentStatusRejected(String id) throws Throwable {
        return updateOrderPaymentStatus(id, PaymentStatus.REJECTED);
    }

    @Override
    public OrderDomain pay(OrderDomain order) {
        return paymentPort.pay(order);
    }

    private OrderDomain updateOrderStatus(String id, OrderStatus status) throws Throwable {
        var orderDomain = new OrderDomain();
        orderDomain.updateOrderStatus(status);
        return update(orderDomain, id);
    }

    private OrderDomain updateOrderPaymentStatus(String id, PaymentStatus status) throws Throwable {
        var orderDomain = new OrderDomain();
        orderDomain.updatePaymentStatus(status);
        return update(orderDomain, id);
    }

    @Override
    public OrderDomain update(OrderDomain domain, String id) throws Throwable {
        var orderDomain = validationPort.mustExist(persistencePort.get(id), ORDER);
        validationPort.validateUpdateBusinessRules(domain, orderDomain, persistencePort);
        orderDomain.update(domain);
        return validateDomainDataAndSave(domain, validationPort, persistencePort);
    }
}
