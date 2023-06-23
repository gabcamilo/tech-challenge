package br.com.gabrielacamilo.techchallenge.adapters.configs;

import br.com.gabrielacamilo.techchallenge.core.ports.*;
import br.com.gabrielacamilo.techchallenge.core.services.*;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

public class BeanConfiuration {
    @Bean
    public AddOnServicePortImpl addOnServicePortImpl(AddOnPersistencePort persistence) {
        return new AddOnServicePortImpl(persistence);
    }

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
