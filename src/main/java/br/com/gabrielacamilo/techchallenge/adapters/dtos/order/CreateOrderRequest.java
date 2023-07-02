package br.com.gabrielacamilo.techchallenge.adapters.dtos.order;

import br.com.gabrielacamilo.techchallenge.core.domain.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.ProductOrderDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CreateOrderRequest {
    private String customer;
    private List<OrderItem> items;
    private String note;

    private List<String> getItemsIds() {
        return items.stream().map(OrderItem::getId).toList();
    }

    private List<String> getAddOnsList() {
        List<String> addOns = new ArrayList<>();
        items.forEach(item ->
            addOns.addAll(item.getAddOnds())
        );
        return addOns;
//        return new ArrayList<>(new HashSet<>(addOns));
    }

    public List<String> getProductsList() {
        List<String> products = new ArrayList<>();
        products.addAll(getItemsIds());
        products.addAll(getAddOnsList());

        return new ArrayList<>(new HashSet<>(products));
    }


    public String getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getNote() {
        return note;
    }

    public OrderDomain toDomain(List<ProductDomain> products, CustomerDomain customer) {
        HashMap<String, ProductDomain> productsDic = new HashMap<>();

        products.forEach(product ->
            productsDic.put(product.getId(), product)
        );

        List<ProductOrderDomain> productsDomain = items.stream().map(item -> {
            ProductDomain product = productsDic.get(item.getId());
            List<ProductDomain> addOns = new ArrayList<>();
            item.getAddOnds().forEach(addOnId ->
                addOns.add(productsDic.get(addOnId))
            );
            return new ProductOrderDomain(product, item.getQuantity(), addOns);
        }).toList();

        return new OrderDomain(customer, note, productsDomain);

    }

    private static class OrderItem {
        private String id;

        private Integer quantity;

        private List<String> addOns;

        public String getId() {
            return id;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public List<String> getAddOnds() {
            return addOns;
        }

        public void setAddOns(List<String> addOns) {
            this.addOns = addOns;
        }
    }
}
