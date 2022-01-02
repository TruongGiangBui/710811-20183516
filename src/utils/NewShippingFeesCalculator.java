package utils;
import entity.order.Order;
public class NewShippingFeesCalculator implements ShippingFeesCalculator {
    // - 
    @Override
    public int calculateShippingFees(Order order) {
        int fees = 0;
        int amount = order.getAmount();
        float weight = order.getTotalWeight() + order.getTotalAlternativeWeight();

        // order amount >= 100000 -> free Ship
        if (amount >= 100000) {
            return fees;
        }

        String province = order.getDeliveryInfo().get("province").toString();
        if (province.equals("Hà Nội") || province.equals("Hồ Chí Minh")) {
            fees = weight <= 3 ? 22000 : (int) (22000 + 5000 * (weight - 3));
        } else {
            fees = weight <= 0.5 ? 30000 : (int) (30000 + 5000 * (weight - 0.5));
        }

        if (order.rushOrder()) {
            fees += 10000 * order.getlstOrderMedia().size();
        }

        return fees;
    }
}
