package utils;
import entity.order.Order;
import java.util.Random;
public class NormalShippingFeesCalculator implements ShippingFeesCalculator {
    // 
    @Override
    public int calculateShippingFees (Order order) {
        Random rand = new Random();
        if(order.getAmount() >= 1000) {
            return 0;
        }
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        return fees;
    }
}
