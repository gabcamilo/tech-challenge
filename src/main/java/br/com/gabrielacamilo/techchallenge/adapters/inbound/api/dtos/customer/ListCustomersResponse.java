package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.utils.GenericMapper;

import java.io.Serializable;
import java.util.List;

public class ListCustomersResponse implements Serializable {
    private List<CustomerFromList> customers;

    public ListCustomersResponse(List<CustomerDomain> customers) {
        this.customers = GenericMapper.map(customers, CustomerFromList.class);
    }

    public List<CustomerFromList> getCustomers() {
        return customers;
    }

    static class CustomerFromList implements Serializable {
        private Long id;
        private String name;
        private String email;

        public CustomerFromList(Long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }

}
