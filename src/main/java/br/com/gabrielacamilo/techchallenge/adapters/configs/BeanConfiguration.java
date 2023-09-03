package br.com.gabrielacamilo.techchallenge.adapters.configs;

import br.com.gabrielacamilo.techchallenge.TechChallengeApplication;
import br.com.gabrielacamilo.techchallenge.core.ports.*;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.customer.CustomerValidationPort;
import br.com.gabrielacamilo.techchallenge.core.ports.order.OrderPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.ports.product.ProductPersistencePort;
import br.com.gabrielacamilo.techchallenge.core.services.customer.CustomerServicePortImpl;
import br.com.gabrielacamilo.techchallenge.core.services.OrderServicePortImpl;
import br.com.gabrielacamilo.techchallenge.core.services.ProductServicePortImpl;
import br.com.gabrielacamilo.techchallenge.core.services.customer.CustomerValidationPortImpl;
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
    OrderServicePortImpl orderServicePortImpl(OrderPersistencePort persistence, PaymentPort payment) {
        return new OrderServicePortImpl(persistence, payment);
    }

    @Bean
    ProductServicePortImpl productServicePortImpl(ProductPersistencePort persistence) {
        return new ProductServicePortImpl(persistence);
    }
}
