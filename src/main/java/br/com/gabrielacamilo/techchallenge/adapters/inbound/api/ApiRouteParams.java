package br.com.gabrielacamilo.techchallenge.adapters.inbound.api;

record ApiRouteParams() {
    static final String API_VERSION = "v1";
    static final String API_URL = "/api/" + API_VERSION;
    static final String CUSTOMERS_URL = API_URL + "/customers";
    static final String PRODUCTS_URL = API_URL + "/products";
    static final String BUNDLES_URL = API_URL + PRODUCTS_URL + "/bundles";
    static final String ORDERS_URL = API_URL + "/orders";
}
