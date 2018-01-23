import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * This class represents a shopping cart, the place where the selected items are held before they are purchased.
 * </pre>
 */
public class Cart {

    private ArrayList<CartItem> items = new ArrayList<>();
    
    /**
     * <pre>
     * Adds a certain quantity of a certain product to the cart.
     * </pre>
     * @param p The product to add
     * @param quantity The quantity of the product to add
     */
    public void add(Product p, float quantity){
        for(CartItem ci : items){
            if(ci.product.equals(p)){
                ci.quantity += quantity;
                return;
            }
        }
        add(new CartItem(p, quantity));
    }
    
    /**
     * <pre>
     * Use this method to obtain the quantity of a particular item
     * </pre>
     * @return The quantity of a particular item
     */
    public float getQuantity(Product p)
    {
       float quantity = 0;
       for(CartItem ci : items){
            if(ci.product.equals(p))
                quantity = ci.quantity;
       }
       return quantity;
    }
    
    /**
     * <pre>
     * Adds a pre-formatted CartItem to the cart.
     * Preferably use add(Product, float) as that method performs checks to prevent duplicate items.
     * </pre>
     * @param item The CartItem to add
     */
    public void add(CartItem item){
        items.add(item);
    }
    
    /**
     * <pre>
     * Returns a list of all CartItems.
     * </pre>
     * @return The list of CartItems
     */
    public List<CartItem> getList(){
        return items;
    }
    
    /**
     * <pre>
     * Removes all items from this cart.
     * </pre>
     */
    public void clear(){
        items.clear();
    }

    /**
     * <pre>
     * Replaces the list of items with a different one.
     * </pre>
     * @param items The new list of items.
     */
    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }
    
    /**
     * <pre>
     * Removes a specific item from the cart.
     * </pre>
     * @param item The item to remove
     */
    public void remove(CartItem item){
        items.remove(item);
    }
    
}
