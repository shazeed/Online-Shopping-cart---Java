import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * This class creates the order for a particular customer with detailed information
 */
public class Order
{
    private int orderId;
    private Cart cart;
    private float total_price;
    private Customer customer;
    private String purchasedTime;
    
    /**
     * Constructor for objects of class Order
     */
    public Order(int currentMaxId, Customer currentCustomer, Cart currentCart, float total_price)
    {
        // initialise instance variables
        orderId = ++currentMaxId;
        customer = currentCustomer;
        cart = currentCart;
        this.total_price = total_price;
        purchasedTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
    }

    /**
     * returns the orderID
	 * @return integer
     */
    public int getOrderId()
    {
        return orderId;
    }
    
    /**
	*returns the Cart
	*@return Cart
	*/
    public Cart getCart()
    {
        return cart;
    }
    
    /**
	*returns total price of the order
	*@return float
	*/
    public float getTotalPrice()
    {
        return total_price;
    }
    
    /**
	*returns Customer of that order
	*@return Customer
	*/
    public Customer getCustomer()
    {
        return customer;
    }
    
    /**
	*returns purchased time of the order
	*@return String
	*/
    public String getPurchasedTime()
    {
        return purchasedTime;
    }
}
