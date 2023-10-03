package br.com.gabrielacamilo.techchallenge.adapters.inbound.api.dtos.order;

import br.com.gabrielacamilo.techchallenge.core.domain.customer.CustomerDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.order.OrderProductDomain;
import br.com.gabrielacamilo.techchallenge.core.domain.product.ProductDomain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class CreateOrderRequest {
    private String customerId;
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
    }

    public List<String> getProductsList() {
        List<String> products = new ArrayList<>();
        products.addAll(getItemsIds());
        products.addAll(getAddOnsList());

        return new ArrayList<>(new HashSet<>(products));
    }


    public String getCustomerId() {
        return customerId;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getNote() {
        return note;
    }

    public OrderDomain toDomain(String customerId) {
//        HashMap<String, ProductDomain> productsDic = new HashMap<>();
//
//        List<OrderProductDomain> productsDomain = items.stream().map(item -> {
//            ProductDomain product = productsDic.get(item.getId());
//            List<ProductDomain> addOns = new ArrayList<>();
//            item.getAddOnds().forEach(addOnId ->
//                    addOns.add(productsDic.get(addOnId))
//            );
//            return new OrderProductDomain(product, item.getQuantity(), addOns);
//        }).toList();
//
//        CustomerDomain customerDomain = new CustomerDomain(customerId);
//
//        return new OrderDomain(customerDomain, note, productsDomain);
        return null;

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
