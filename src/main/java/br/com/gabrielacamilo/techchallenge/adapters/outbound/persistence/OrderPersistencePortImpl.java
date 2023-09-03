package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.CustomerEntity;
import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.OrderEntity;
import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.OrderProductEntity;
import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.ProductEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderPersistencePort;
import br.com.gabrielacamilo.techchallenge.utils.GenericMapper;
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
        OrderEntity orderEntity = mapOrderDomainToEntity(order);
        OrderEntity saved = orderRepository.save(orderEntity);
        return mapOrderEntityToDomain(saved);
    }

    @Override
    public Optional<OrderDomain> get(String id) {
        var orderEntity = orderRepository.findById(id);
        return orderEntity.map(this::mapOrderEntityToDomain);
    }

    @Override
    public OrderDomain update(OrderDomain item) {
        OrderEntity orderEntity = mapOrderDomainToEntity(item);
        OrderEntity saved = orderRepository.save(orderEntity);
        return mapOrderEntityToDomain(saved);
    }

    @Override
    public void delete(OrderDomain item) {
        OrderEntity orderEntity = mapOrderDomainToEntity(item);
        orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDomain> list() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        return mapOrderEntityToDomain(orderEntities);
    }

    @Override
    public List<OrderDomain> getOrdersByCustomer(CustomerDomain customer) {
        CustomerEntity customerEntity = GenericMapper.map(customer, CustomerEntity.class);
        List<OrderEntity> orderEntities = orderRepository.findByCustomer(customerEntity);
        return mapOrderEntityToDomain(orderEntities);
    }

    // order mappers
    private OrderEntity mapOrderDomainToEntity(OrderDomain orderDomain) {
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setCustomer(GenericMapper.map(orderDomain.getCustomer(), CustomerEntity.class));
        orderEntity.setStatus(orderDomain.getStatus());
        orderEntity.setPaymentStatus(orderDomain.getPaymentStatus());
        orderEntity.setNote(orderDomain.getNote());
        orderEntity.setTotal(orderDomain.getTotal());
        orderEntity.setCreatedAt(orderDomain.getCreatedAt());
        orderEntity.setUpdatedAt(orderDomain.getUpdatedAt());

        List<OrderProductDomain> items = orderDomain.getItems();

        List<OrderProductEntity> itemsEntity = items.stream().map(item -> {
            OrderProductEntity entity = new OrderProductEntity();
            entity.setProduct(GenericMapper.map(item.getProduct(), ProductEntity.class));
            entity.setQuantity(item.getQuantity());
            entity.setAddOns(GenericMapper.map(item.getAddOns(), ProductEntity.class));
            entity.setTotal(item.getTotal());
            return entity;
        }).toList();

        orderEntity.setItems(itemsEntity);
        return orderEntity;
    }

    private OrderDomain mapOrderEntityToDomain(OrderEntity orderEntity) {

        List<OrderProductDomain> itemsDomain = orderEntity.getItems().stream().map(item ->
                new OrderProductDomain(
                        GenericMapper.map(item.getProduct(), ProductDomain.class),
                        item.getQuantity(),
                        GenericMapper.map(item.getAddOns(), ProductDomain.class),
                        item.getTotal()
                )
        ).toList();

        return new OrderDomain(
                orderEntity.getId(),
                GenericMapper.map(orderEntity.getCustomer(), CustomerDomain.class),
                itemsDomain,
                orderEntity.getStatus(),
                orderEntity.getPaymentStatus(),
                orderEntity.getNote(),
                orderEntity.getTotal(),
                orderEntity.getCreatedAt(),
                orderEntity.getUpdatedAt());
    }

    private List<OrderDomain> mapOrderEntityToDomain(List<OrderEntity> orderEntities) {
        return orderEntities.stream().map(this::mapOrderEntityToDomain).toList();
    }
}
