package br.com.gabrielacamilo.techchallenge.adapters.configs;

import br.com.gabrielacamilo.techchallenge.TechChallengeApplication;
import br.com.gabrielacamilo.techchallenge.core.ports.*;
import br.com.gabrielacamilo.techchallenge.core.services.*;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TechChallengeApplication.class)
public class BeanConfiguration {

    @Bean
    BundleServicePortImpl bundleServicePortImpl(BundlePersistencePort persistence) {
        return new BundleServicePortImpl(persistence);
    }

    @Bean
    CustomerServicePortImpl customerServicePortImpl (CustomerPersistencePort persistence) {
        return new CustomerServicePortImpl(persistence);
    }

    @Bean
    ItemServicePortImpl itemServicePortImpl (ItemPersistencePort persistence) {
        return new ItemServicePortImpl(persistence);
    }

    @Bean
    OrderServicePortImpl orderServicePortImpl (OrderPersistencePort persistence) {
        return new OrderServicePortImpl(persistence);
    }

    @Bean
    ProductServicePortImpl productServicePortImpl (ProductPersistencePort persistence) {
        return new ProductServicePortImpl(persistence);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
