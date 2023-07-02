package br.com.gabrielacamilo.techchallenge.core.domain.enums;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {
        COOKING,
        READY,
        DELIVERED,
        PENDING,
        CANCELED;

        public static List<OrderStatus> activeStatusTypes() {
                return Arrays.asList(COOKING, READY, PENDING);
        }
}
