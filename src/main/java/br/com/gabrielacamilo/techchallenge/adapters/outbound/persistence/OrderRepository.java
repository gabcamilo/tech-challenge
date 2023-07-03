package br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence;

import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.CustomerEntity;
import br.com.gabrielacamilo.techchallenge.adapters.outbound.persistence.entities.OrderEntity;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.OrderStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    List<OrderEntity> findByStatus(List<OrderStatus> statuses);

    List<OrderEntity> findByCustomer(CustomerEntity customer);
}
