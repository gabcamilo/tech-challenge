package br.com.gabrielacamilo.techchallenge.core.ports.order;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.enums.PaymentStatus;
import br.com.gabrielacamilo.techchallenge.core.ports.PersistencePort;

import java.util.List;
import java.util.Optional;

public interface OrderPersistencePort extends PersistencePort<OrderDomain> {
    List<OrderDomain> getOrdersByCustomer(CustomerDomain customer);
}
