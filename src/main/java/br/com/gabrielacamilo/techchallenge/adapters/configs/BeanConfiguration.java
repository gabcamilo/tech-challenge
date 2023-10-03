package br.com.gabrielacamilo.techchallenge.adapters.configs;

import br.com.gabrielacamilo.techchallenge.TechChallengeApplication;
import br.com.gabrielacamilo.techchallenge.core.ports.PaymentPort;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerValidationPort;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderValidationPort;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductValidationPort;
import br.com.gabrielacamilo.techchallenge.core.services.customer.CustomerServicePortImpl;
import br.com.gabrielacamilo.techchallenge.core.services.customer.CustomerValidationPortImpl;
import br.com.gabrielacamilo.techchallenge.core.services.order.OrderServicePortImpl;
import br.com.gabrielacamilo.techchallenge.core.services.order.OrderValidationPortImpl;
import br.com.gabrielacamilo.techchallenge.core.services.product.ProductServicePortImpl;
import br.com.gabrielacamilo.techchallenge.core.services.product.ProductValidationPortImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TechChallengeApplication.class)
public class BeanConfiguration {

    @Bean
    CustomerServicePortImpl customerServicePortImpl(CustomerPersistencePort persistence, CustomerValidationPort validation) {
        return new CustomerServicePortImpl(persistence, validation);
    }

    @Bean
    CustomerValidationPortImpl customerValidationPortImpl() {
        return new CustomerValidationPortImpl();
    }

    @Bean
    OrderServicePortImpl orderServicePortImpl(OrderPersistencePort persistence, OrderValidationPort validation, PaymentPort payment) {
        return new OrderServicePortImpl(persistence, validation, payment);
    }

    @Bean
    OrderValidationPortImpl orderValidationPortImpl() {
        return new OrderValidationPortImpl();
    }

    @Bean
    ProductServicePortImpl productServicePortImpl(ProductPersistencePort persistence, ProductValidationPort validation) {
        return new ProductServicePortImpl(persistence, validation);
    }

    @Bean
    ProductValidationPortImpl productValidationPortImpl() {
        return new ProductValidationPortImpl();
    }

}
