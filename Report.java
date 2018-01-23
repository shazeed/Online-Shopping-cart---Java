/**
 * <pre>
 * This class represents a report for customers or administrators, 
 * and the report includes the id of each order, total price, purchased time, customer and items with related quantity.
 * </pre>
 */
public class Report
{
    private int orderId;
    private float totalPrice;
    private String purchasedTime;
    private Customer customer;
    private Cart items;
    
    /**
     * <pre>
     * Constructor for objects of class Report
     * </pre>
     * @param orderId The order ID
     * @param customer The customer who create the order
     * @param items The products the customer has bought
     * @param totalPrice The total price of the order
     * @param purchasedTime The time the order was created
     */
    public Report(int orderId, Customer customer, Cart items, float totalPrice,
               String purchasedTime)
    {
        // initialise instance variables
        this.orderId = orderId;
        this.customer = customer;
        this.items = items;
        this.purchasedTime = purchasedTime;
        this.totalPrice = totalPrice;
    }

    /**
     * <pre>
     * Use this method to obtain customer's name
     * </pre>
     * @return Customer's name
     */
    /*
    public String getCustomerName()
    {
        return customer.name;
    }*/
    
    /**
     * <pre>
     * Use this method to obtain the purchased time
     * </pre>
     * @return The purchased time
     */
    public String getPurchasedDate()
    {
        return purchasedTime;
    }
   
    /**
     * <pre>
     * Use this method to obtain the total price of the order
     * </pre>
     * @return The total price of the order
     */
    public float getTotalPrice()
    {
        return totalPrice;
    }
    
    /**
     * <pre>
     * Use this method to obtain the order ID
     * </pre>
     * @return The order ID
     */
    public int getOrderId()
    {
        return orderId;
    }
    
    /**
     * <pre>
     * Use this method to obtain all items in the order
     * </pre>
     * @return All items in the order
     */
    public Cart getItems()
    {
        return items;
    }
}
