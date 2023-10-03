package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.customer;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListCustomersResponse implements Serializable {
    private List<CustomerFromList> customers;

    public ListCustomersResponse(List<CustomerDomain> customers) {
        this.customers = customers.stream().map(CustomerFromList::new).toList();
    }

    public List<CustomerFromList> getCustomers() {
        return customers;
    }

    static class CustomerFromList implements Serializable {
        private String id;
        private String name;
        private String email;

        public CustomerFromList(CustomerDomain domain) {
            this.id = domain.getId();
            this.name = domain.getName();
            this.email = domain.getEmail();
        }

        public String getId() {
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
