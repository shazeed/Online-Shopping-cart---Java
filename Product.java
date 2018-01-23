import java.util.HashMap;

import javax.swing.ImageIcon;

/**
 * <pre>
 * This class represents a product in the store.
 * Each product must have a name and may contain an image.
 * 
 * Products may also contain properties that are set through the "setProperty(String, String, Object)" method.
 * Properties can be used to represent various bits of data about the product, some properties could be:
 * - Unit price
 * - Sale price
 * - Warranty information
 * - Sale "% off"
 *</pre>
 */
public class Product {

	private HashMap<String, Object> props = new HashMap<String, Object>();
	private HashMap<String, String> dispNames = new HashMap<String, String>();
	
	private String name;
	private ImageIcon image = null;
	private int amount = 0;
	private String url = null;
	
	/**
	 * <pre>
	 * Product constructor.
	 * </pre>
	 * @param name The name of the product
	 */
	public Product(String name){
		setName(name);
	}
	
	/**
	 * <pre>
	 * Product constructor.
	 * </pre>
	 * @param name The name of the product
	 * @param url The url of the product's image
	 * @param id The key of two HashMap: props & dispNames
	 * @param displayName The description of the property
	 * @param value The value of the property
	 * @param amount The quantity of the product
	 */
	public Product(String name, String url, String id, String displayName, Object value, int amount)
	{
	    setName(name);
	    setImage(url);
	    setProperty(id, displayName, value);
	    setAmount(amount);
	}
	
	public Product(String name, Object value)
	{
	    setName(name);
	    setProperty("unitprice", "unitprice", value);
	    //setAmount(amount);
	}
	
	/**
	 * <pre>
	 * Sets the image to be used for the product.
	 * If this method is never called, a default "no image" icon is used.
	 * </pre>
	 * @param url The URL of the image, it will be loaded from the internet and resized.
	 */
	public void setImage(String url){
		this.image = ShopController.generateIcon(url, 150, 150);
		this.url = url;
	}
	
	/**
	 * <pre>
	 * Sets the name of this product.
	 * </pre>
	 * @param name The new product name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * <pre>
	 * Returns the name of this product.
	 * </pre>
	 * @return The name of this product
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * <pre>
	 * Sets a property of this product.
	 * 
	 * A property consists of an ID, a display name  and a value.
	 * The ID is the name used to refer to the property via the "getPropertyValue(id)" method.
	 * 
	 * The display name and value is shown to the user in the form of "{display name}: {value}" on the product details page.
	 * Each property is displayed to the user on it's own line and formatting is taken care of.
	 * 
	 * EG:
	 *      This:          setProperty("unitPrice", "Unit price ($) ", 100f)
	 *      Results in:   "Unit price ($): 100.0"
	 * 
	 * IMPORTANT: IF PROPERTIES ARE BEING USED TO CALCULATE PRICE, THEY MUST BE FLOATS AS THE "calculatePrice" METHOD IN "Model" EXPECTS FLOATS!
	 * </pre>
	 * @param id
	 * @param displayName
	 * @param value
	 */
	public void setProperty(String id, String displayName, Object value){
		this.props.put(id, value);
		this.dispNames.put(id, displayName);
	}
	
	/**
	 * <pre>
	 * Returns the value of the specified property.
	 * IMPORTANT: MAKE SURE THAT YOU CAST THE VALUE TO THE TYPE YOU NEED!
	 * </pre>
	 * @param id The property to get the value of
	 * @return The value of the property.
	 */
	public Object getPropertyValue(String id){
		return props.get(id);
	}
	
	/**
	 * <pre>
	 * Returns the display name of the specified property.
	 * </pre>
	 * @param id The property
	 * @return The display name of the property
	 */
	public String getPropertyDisplayName(String id){
		return dispNames.get(id);
	}
	
	/**
	 * <pre>
	 * This method is used internally to generate the text seen in the "ProductDetails" dialog.
	 * </pre>
	 * @return A formatted string representation of all properties this product has.
	 */
	public String getPropertiesAsText(){
		String out = "<html>";
		for(String key : this.props.keySet()) out += (getPropertyDisplayName(key) + ": " + getPropertyValue(key).toString()) + "<br/>";
		out += "</html>";
		return out;
	}
	
	/**
	 * <pre>
	 * Checks if this products contains a specific property.
	 * </pre>
	 * @param name The property ID
	 * @return Returns true if this product contains the property, false otherwise.
	 */
	public boolean hasProperty(String id){
		return props.containsKey(id);
	}
	
	/**
	 * <pre>
	 * Returns the image associated with this product.
	 * </pre>
	 * @return The image associated with this product
	 */
	public ImageIcon getImage(){
		if(this.image == null) return ShopController.NO_IMAGE_ICON;
		else return this.image;
	}
	
	/**
	 * This method is used to change the amount of a product
	 */
	public void setAmount(int changeQuantity)
	{
	    amount += changeQuantity;
	}
	
	/**
	* Returns the amount of the product
	*@return integer
	*/
	public int getAmount()
	{
	    return amount;
	}
	
	/**
	 * <pre>
	 * Returns a hashmap
	 * @return HashMap
	 */
	public HashMap<String, Object> getProps()
	{
	    return props;
	}
	
	/**
	*returns the url of the image
	@return String
	*/
	public String getUrl()
	{
	    return url;
	}
}
