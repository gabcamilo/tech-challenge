package br.com.gabrielacamilo.techchallenge.adapters.configs;

import br.com.gabrielacamilo.techchallenge.TechChallengeApplication;
import br.com.gabrielacamilo.techchallenge.core.ports.*;
import br.com.gabrielacamilo.techchallenge.core.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TechChallengeApplication.class)
public class BeanConfiguration {

    @Bean
    CustomerServicePortImpl customerServicePortImpl (CustomerPersistencePort persistence) {
        return new CustomerServicePortImpl(persistence);
    }

//    @Bean
//    OrderServicePortImpl orderServicePortImpl (OrderPersistencePort persistence) {
//        return new OrderServicePortImpl(persistence);
//    }

    @Bean
    ProductServicePortImpl productServicePortImpl (ProductPersistencePort persistence) {
        return new ProductServicePortImpl(persistence);
    }
}
