/**
 * <pre>
 * This class represents a customer, it holds all of the data that the store needs to know about someone.
 * Whilst this information is not sufficient to place an order in real life, it is detailed enough to serve as a good learning example.
 * </pre>
 */
public class Customer {

	/**
	 * <pre>
	 * The customer's full name.
	 * </pre>
	 */
	public String name;
	
	/**
	 * <pre>
	 * The customer's home address.
	 * </pre>
	 */
	public String address;
	
	/**
	 * <pre>
	 * The customer's card number.
	 * </pre>
	 */
	public String cardNumber;
	
	/**
	 * <pre>
	 * The customer's phone number.
	 * </pre>
	 */
	public String phoneNumber;
	
	public String type;
	
	/**
	 * <pre>
	 * Customer constructor
	 * </pre>
	 * @param name Customer's name
	 * @param address Customer's address
	 * @param cardNumber Customer's card number
	 * @param phoneNumber Customer's phone number
	 * @param type Customer's type ("normal" or "admin")
	 */
	public Customer(String name, String address, String cardNumber, String phoneNumber, String type) {
		this.name = name;
		this.address = address;
		this.cardNumber = cardNumber;
		this.phoneNumber = phoneNumber;
		this.type = type;
	}
	
	/**
     * returns the type of a customer
     * @return String
     */
	public String getType()
	{
	    return type;
	}
}
