import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.util.List;

/**
 * <pre>
 * This class is used to transfer the requirements from user interface to all entity classes
 * and send the neccessary data from the database to the user interface.
 * </pre>
 */
public class ShopController {

    /*
     * STATIC FIELDS
     */
    
    /**
     * <pre>
     * The default product icon.
     * </pre>
     */
    public static ImageIcon NO_IMAGE_ICON = generateIcon("https://placeholdit.imgix.net/~text?txtsize=23&bg=ffffff&txtclr=000000&txt=No+Image&w=200&h=200", 150, 150);
    /**
     * <pre>
     * The store logo.
     * </pre>
     */
    public static ImageIcon LOGO_ICON = new ImageIcon(ShopController.class.getResource("logo.png"));
    /**
     * <pre>
     * The image cache that is used to save time and speed up loading.
     * </pre>
     */
    public static HashMap<String, ImageIcon> IMAGE_CACHE;
    
    /**
     * <pre>
     * Generates an icon that can be used elsewere in the application.
     * </pre>
     * @param imgLoc The URL of the image
     * @param width The desired icon width
     * @param height The desired icon height
     * @return The generated icon
     */
    public static ImageIcon generateIcon(String imgLoc, int width, int height){
        if(IMAGE_CACHE == null)  IMAGE_CACHE = new HashMap<String, ImageIcon>();
        if(IMAGE_CACHE.containsKey(imgLoc)) return IMAGE_CACHE.get(imgLoc);
        try {
            URL url = new URL(imgLoc);
            ImageIcon icon = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
            IMAGE_CACHE.put(imgLoc, icon);
            return icon;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
    
    /*
     * END STATIC
     */
    
    private JFrame window = new JFrame();
    private Model backend;
    
    private Cart cart = new Cart();
    private String currentUserID;
    
    /**
     * <pre>
     * Creates a new instance of ShopController.
     * Make sure to call the "init" method after this!
     * </pre>
     * @param b The Model with all of the back-end links that the store is to use
     */
    public ShopController(Model b){
        this.backend = b;
    }
    
    
    /**
     * <pre>
     * Use this method to show a view
     * </pre>
     * @param view A view the system need to show
     */
    public void setView(View view){
        view.setController(this);
        view.initialize();
        window.setContentPane(view);
        window.revalidate();
    }
    
    /**
     * @return The JFrame that holds the store.
     */
    public JFrame getWindow(){
        return window;
    }
    
    /**
     * @return The Model instance controlling the store.
     */
    public Model getBackend(){
        return this.backend;
    }
    
    /**
     * <pre>
     * Initialize and show the store window.
     * Also displays the LoginView.
     * </pre>
     */
    public void init(){
        window.setResizable(false);
        window.setTitle("Shop");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        this.setView(new LoginView());
    }
    
    /**
     * <pre>
     * Shows a popup message.
     * </pre>
     * @param message The text in the popup window.
     */
    public void showPopup(String message){
        JOptionPane.showMessageDialog(window, message);
    }
    
    
    /*
     * LOGIN AND USERS
     * ------------------------------------------------
     */

    /**
     * @return The customer details received from the Model instance.
     */
    public Customer getCurrentCustomerDetails(){
        return getBackend().getUserInfo(currentUserID);
    }
    
    /**
     * <pre>
     * Attempts to sign up a user.
     * 
     * This will display an error to the user if any of the following are true:
     * - The user ID is less than 3 chars long
     * - The password is less than 5 chars long
     * - The two passwords do not match.
     * 
     * If all of the above tests succeed then the back-end will be asked to create a user.
     * If the back-end succeeds in creating an account, the user will be logged in, if not, they will be shown an error.
     * </pre>
     * @param username The entered user ID.
     * @param pass The entered password.
     * @param confPass The entered confirmed password.
     */
    public void signup(String username, String pass, String confPass){
        
        // Ensuring length
        if(username.length() < 3) {
            showPopup("Your user ID must be at least 3 chars long!");
            return;
        }
        else if(pass.length() < 5){
            showPopup("Your password must be at least 5 chars long!");
            return;
        }
        else if(!pass.equals(confPass)){
            showPopup("The passwords do not match");
            return;
        }
        
        boolean success = getBackend().signup(username, pass);
        
        if(!success){
            showPopup("Signup failed, that userID may already be in use!");
        } else {
            showPopup("Your account has been created, please edit your details by clicking 'My account' in the top right.");
            attemptLogin(username, pass);
        }
    }
    
    /**
     * <pre>
     * Attempts to log a user into the store.
     * 
     * If the login succeeds, they will be presented with the product list, if not, they will be shown an error.
     * </pre>
     * @param username The supplied user ID
     * @param password The supplied password
     */
    public void attemptLogin(String username, String password){
        if(backend.login(username, password)){
            currentUserID = username;
            if (getCurrentUserType().equals("normal"))
                showProductList();
            if (getCurrentUserType().equals("admin"))
                showAdminView();
        } else {
            showPopup("Login failed! Please ensure that your user ID and password are correct.");
        }
    }
    
    /**
     * <pre>
     * Calls the appropriate methods on the Model instance to update the information about the current user.
     * 
     * If no user is logged in, an error message will be displayed in the console.
     * </pre>
     * @param c The new user details.
     */
    public void updateUserDetails(Customer c){
        if(this.currentUserID != null){
            boolean success = getBackend().setUserInfo(this.currentUserID, c);
            if(!success){
                showPopup("There was an error saving your information! Please try again later.");
            }
        } else {
            System.err.println("Can't update user info, no one is signed in!");
        }
    }

    
    /*
     * PRODUCTS
     * ------------------------------------------------
     */

    /**
     * <pre>
     * Shows the checkout dialog.
     * </pre>
     */
    public void showCheckout(){
        ConfirmDialog.display(this);
    }
    
    /**
     * @return The current user's cart.
     */
    public Cart getCart(){
         return cart;
    }
    
    /**
     * <pre>
     * Calls the appropriate methods on the Model instance to update the products database.
     * 
     * If the database is updated, show successful message to administrators.
     * </pre>
     * @param p the product of which the quantity is changed by an administrator
     * @param quantity how much is the product changed
     * @return This method returns true if the database is successfully updated, or false if it was not
     */
    public boolean addToProducts(Product p, int quantity)
    {
        boolean flag = false;
        if(getBackend().addToProducts(p, quantity))
        {
            showPopup("Successful");
            flag = true;
        }
        return flag;
    }
    
    /**
     * <pre>
     * Adds a product to the cart. See "Cart.addToCart" for more information.
     * </pre>
     * @param p The product
     * @param quantity The quantity to add
     */
    public boolean addToCart(Product p, float quantity)
    {
        boolean flag = true;
        if (p == null)
        {
            showPopup("No selected items");
            flag = false;
        }
        else
        {
            if (quantity <= 0 || (int)quantity > p.getAmount())
            {
                showPopup("The quantity you have entered is invalid!!!!!!!!!!!!!!!!!!!!");
                flag = false;
            }
            else
            {
                if ((int)(cart.getQuantity(p)) < p.getAmount())
                {
                    cart.add(p, quantity);
                    showPopup("Successful");
                }
                else
                {
                    showPopup("Sorry, you cannot add");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * <pre>
     * Shows the cart view.
     * </pre>
     */
    public void showCartView(){
        setView(new CartView());
    }
    
    /**
     * <pre>
     * See "Model.getPrice(Cart)" for more information.
     * </pre>
     * @return The total price of all item in the cart
     */
    public float getTotalCartPrice(){
        return getBackend().getPrice(cart);
    }
    
    /**
     * <pre>
     * Shows the product list view.
     * </pre>
     */
    public void showProductList() {
        setView(new ProductListView());
    }

    /**
     * <pre>
     * Attempts a transaction using the current user's details and the current cart.
     * </pre>
       */
    public boolean attemptTransaction() {
        Customer c = getBackend().getUserInfo(currentUserID);
        String prefix = "Order failed! ";
        if(c.name.trim().equals("")){
            showPopup(prefix + "You have not entered your full name!");
            return false;
        }
        else if(c.address.trim().equals("")){
            showPopup(prefix + "You have not entered your home address!");
            return false;
        }
        else if(c.phoneNumber.trim().equals("")){
            showPopup(prefix + "You have not entered your phone number!");
            return false;
        }
        else if(c.cardNumber.trim().equals("")){
            showPopup(prefix + "You have not entered your card number!");
            return false;
        }
        
        boolean success = getBackend().processOrder(c, cart, getTotalCartPrice());
        
        if(!success){
            showPopup("Sorry, your order could not be placed! Please ensure that all of your information is correct.");
        }
        else {
            showPopup("Your order has been placed successfully! Have a nice day!");
            this.cart.clear();
            this.showCartView();
        }
        return success;
    }
    
    /**
     * <pre>
     * Use the method to validate the cart.
     * 
     * If there is no item in the cart, show "no item" messsage.
     * If there are some items which are more than the available, show "order fail" messsage.
     * If there are items and they are less than the available, show the checkout dialog.
     * </pre>
     */
    public boolean checkWithAvaiable()
    {
       // Cart newCart = new Cart();
        boolean flag = true;
        if(getCart().getList().size() > 0)
        {
            for (int i=0; i<getCart().getList().size(); i++)
            {
                if(getCart().getList().get(i).getQuantity() > 
                        getCart().getList().get(i).getProduct().getAmount())
                {
                    showPopup("Order failed! You have chose items more than the available");
                    flag = false;
                    break;
                }
            }
            if (flag)
            {
                showCheckout();
            }
        }
        else
        {
            showPopup("Sorry. No items in the cart!!!!!!!!");
            flag = false;
        }
        return flag;
    }
    
     /**
     * <pre>
     * Shows the product list view for administrators.
     * </pre>
     */
    public void showAdminView()
    {
        setView(new AdminView());
    }
    
     /**
     * <pre>
     * Shows the view where administrators can create a new item.
     * </pre>
     */
    public void showCreateNewItemView()
    {
        setView(new NewItemView());
    }
    
    /**
     * <pre>
     * Calls the appropriate methods on the Model instance to add new items into the products database.
     * 
     * If the new product is not added into the database, show "is not created" messsage.
     * If the new product is added into the database, show the product list for administrators.
     * </pre>
     * @param name The new product's name
     * @param price The new product's price
     * @param url The url of the new product's image
     */
    public void addProduct(String name, float price, String url)
    {
        Product p = new Product(name, url, "price", "Unit price ($)", price, 0);
        if(getBackend().addProduct(p) && addToProducts(p,0))
            showAdminView();
        else
            showPopup("The item is not created");
    }

    /**
     * <pre>
     * Shows the report view.
     * </pre>
     */
    public void showReport()
    {
        setView(new ReportView());
    }
    
    /**
     * <pre>
     * Use this method to obtain the type of the current user
     * </pre>
     * @param type The type of the current user: "normal" or "admin"
     */
    public String getCurrentUserType()
    {
        return getBackend().getUserInfo(currentUserID).getType();
    }
    
    /**
     * <pre>
     * Use this method to look for purchased history
     * </pre>
     * @param searchStart What time the user want to search from
     * @param searchFinish What time the user want to search to
     */
    public void searchPurchasedItems(String searchStart, String searchFinish)
    {
        int startDate = Integer.parseInt(searchStart);
        int endDate = Integer.parseInt(searchFinish);
        if(getBackend().searchPurchasedItems(startDate, endDate, currentUserID))
            showReport();
        else
            showPopup("No purchased items");
    }
}
