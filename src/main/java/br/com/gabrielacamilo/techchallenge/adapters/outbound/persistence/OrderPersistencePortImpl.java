package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.OrderEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;
import br.com.gabrielacamilo.techchallenge.core.ports.OrderPersistencePort;
import br.com.gabrielacamilo.techchallenge.utils.GenericMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderPersistencePortImpl implements OrderPersistencePort {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderPersistencePortImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public OrderDomain createOrder(OrderDomain order) {
        OrderEntity saved = orderRepository.save(GenericMapper.map(order, OrderEntity.class));
        GenericMapper.map(saved, OrderDomain.class);
        return GenericMapper.map(saved, OrderDomain.class);
    }

    @Override
    public Optional<OrderDomain> getOrder(String id) {
        Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        return GenericMapper.map(orderEntity, OrderDomain.class);
    }

    @Override
    public List<OrderDomain> getAllActiveOrders() {
        List<OrderEntity> orderEntities = orderRepository.findByStatus(OrderStatus.activeStatusTypes());
        return GenericMapper.map(orderEntities, OrderDomain.class);
    }

    @Override
    public List<OrderDomain> getOrdersByCustomer(CustomerDomain customer) {
        return null;
    }

    @Override
    public OrderDomain updateOrderStatusCooking(OrderDomain order) {
        return null;
    }

    @Override
    public OrderDomain updateOrderStatusReady(OrderDomain order) {
        return null;
    }

    @Override
    public OrderDomain updateOrderStatusDelivered(OrderDomain order) {
        return null;
    }

    @Override
    public OrderDomain updatePaymentStatusApproved(OrderDomain order) {
        return null;
    }

    @Override
    public OrderDomain updatePaymentStatusRejected(OrderDomain order) {
        return null;
    }
}
