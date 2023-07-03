package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.CustomerEntity;
import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.OrderEntity;
import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.OrderProductEntity;
import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.ProductEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.OrderProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
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
        OrderEntity orderEntity = mapOrderDomainToEntity(order);
        OrderEntity saved = orderRepository.save(orderEntity);
        return mapOrderEntityToDomain(saved);
    }

    @Override
    public Optional<OrderDomain> getOrder(String id) {
        var orderEntity = orderRepository.findById(id);
        return orderEntity.map(this::mapOrderEntityToDomain);
    }

    @Override
    public List<OrderDomain> getAllActiveOrders() {
        List<OrderEntity> orderEntities = orderRepository.findByStatus(OrderStatus.activeStatusTypes());
        return GenericMapper.map(orderEntities, OrderDomain.class);
    }

    @Override
    public List<OrderDomain> getOrdersByCustomer(CustomerDomain customer) {
        CustomerEntity customerEntity = GenericMapper.map(customer, CustomerEntity.class);
        List<OrderEntity> orderEntities = orderRepository.findByCustomer(customerEntity);
        return GenericMapper.map(orderEntities, OrderDomain.class);
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

        List<OrderProductDomain> itemsDomain = orderEntity.getItems().stream().map(item -> {
            OrderProductDomain domain = new OrderProductDomain(
                    GenericMapper.map(item.getProduct(), ProductDomain.class),
                    item.getQuantity(),
                    GenericMapper.map(item.getAddOns(), ProductDomain.class),
                    item.getTotal()
            );
            return domain;
        }).toList();

        OrderDomain orderDomain = new OrderDomain(
                orderEntity.getId(),
                GenericMapper.map(orderEntity.getCustomer(), CustomerDomain.class),
                itemsDomain,
                orderEntity.getStatus(),
                orderEntity.getPaymentStatus(),
                orderEntity.getNote(),
                orderEntity.getTotal(),
                orderEntity.getCreatedAt(),
                orderEntity.getUpdatedAt());
        return orderDomain;
    }
}
