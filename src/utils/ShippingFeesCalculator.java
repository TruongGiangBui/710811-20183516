package utils;

import entity.order.Order;

public interface ShippingFeesCalculator {
    int calculateShippingFees(Order order);
}
