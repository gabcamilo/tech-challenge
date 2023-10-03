package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.CustomerEntity;
import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.OrderEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderPersistencePort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderPersistencePortImpl implements OrderPersistencePort {

    private final OrderRepository orderRepository;

    public OrderPersistencePortImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDomain save(OrderDomain order) {
        OrderEntity orderEntity = new OrderEntity(order);
        return orderRepository.save(orderEntity).toDomain();
    }

    @Override
    public Optional<OrderDomain> get(String id) {
        return orderRepository.findById(id).map(OrderEntity::toDomain);
    }

    @Override
    public OrderDomain update(OrderDomain item) {
        OrderEntity orderEntity = new OrderEntity(item);
        return orderRepository.save(orderEntity).toDomain();
    }

    @Override
    public void delete(OrderDomain item) {
        OrderEntity orderEntity = new OrderEntity(item);
        orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDomain> list() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return orderEntities.stream().map(OrderEntity::toDomain).toList();
    }

    @Override
    public List<OrderDomain> getOrdersByCustomer(CustomerDomain customer) {
        CustomerEntity customerEntity = new CustomerEntity(customer);
        List<OrderEntity> orderEntities = orderRepository.findByCustomer(customerEntity);
        return orderEntities.stream().map(OrderEntity::toDomain).toList();
    }
}
