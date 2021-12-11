package controller;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class PlaceRushOrderController {

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(),
                    cartMedia.getQuantity(),
                    cartMedia.getPrice());
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }

    /**
     * The method validates the info
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
        if(!validateName(info.get("name"))){
            throw new InterruptedException("Name is invalid");
        }
        if(!validateAddress(info.get("address"))){
            throw new InterruptedException("Address is invalid");
        }
        if(!validatePhoneNumber(info.get("phone"))){
            throw new InterruptedException("Phone number is invalid");
        }
        //Neu khach hang lua chon Place Rush Order thi tien hanh kiem tra xem du dieu kien khong
        if(info.get("rushOrder").equals("true")) {
            if(!validateProvince(info.get("province"))) {
                throw new InterruptedException("Province does not support rush order");
            }
        }
    }

    /**
     * This method validate phone number
     * @param phoneNumber
     * @return isvalid
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        if(phoneNumber==null) return false;
        return Pattern.matches("[0][0-9]{9}",phoneNumber);
    }
    /**
     * This method validate name
     * @param name
     * @return isvalid
     */
    public boolean validateName(String name) {
        if (name==null) return false;
        return Pattern.matches("[a-zA-Z ]+",name);
    }
    /**
     * This method validate address
     * @param address
     * @return isvalid
     */
    public boolean validateAddress(String address) {
        if (address==null) return false;
        return Pattern.matches("[a-zA-Z0-9 ,]+",address);
    }
    /**
     * Kiem tra xem dia diem co ho tro giao hang nhanh hay khong, cu the o day chi ho tro Ha Noi hoac TP.Ho Chi Minh hoac Da Nang
     * @param province : dia diem khach hang yeu cau van chuyen den
     * @return
     */
    public boolean validateProvince(String province){
        // Dia diem dao hang khong duoc de trong
        if(province == null){
            return false;
        }

        //Chi giao hang nhanh o Ha Noi, Da Nang va Ho Chi Minh
        if(province.equals("Hà Nội") || province.equals("Đà Nẵng") || province.equals("Hồ Chí Minh")) {
            return true;
        }
        return false;
    }

    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){
        Random rand = new Random();
        int fees = (int)( ( (rand.nextFloat()*10)/100 ) * order.getAmount() );
        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
