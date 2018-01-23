import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.io.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class DummyModel implements Model {

    ArrayList<Product> productList = new ArrayList<Product>();
    HashMap<String, String> passwords = new HashMap<>();
    HashMap<String, Customer> details = new HashMap<>();
    ArrayList<Report> reportList = new ArrayList<Report>();
    
    public DummyModel(){
        
        passwords.put("admin", "admin");
        details.put("admin", new Customer("john", "900 dandenong rd", "123", "456","admin"));
        
        
        passwords.put("fancy", "fancy");
        details.put("fancy", new Customer("fancy", "18 Weight Grove", "786", "456","normal"));
        
        passwords.put("sean", "sean");
        details.put("sean", new Customer("", "prin", "786", "456","normal"));
        
        passwords.put("shazeed", "shazeed");
        details.put("shazeed", new Customer("shazeed", "", "786", "456","normal"));
        
        passwords.put("cindy", "cindy");
        details.put("cindy", new Customer("cindy", "clayton", "786", "","normal"));
        
        passwords.put("dora", "dora");
        details.put("dora", new Customer("shazeed", "caulfied", "", "456","normal"));
        
        readTheFile();
        /*
        for(int i = 0; i < 5; i++){
            Product birb = new Product("Item #"+i);
            birb.setProperty("price", "Price ($)", 100f);
            productList.add(birb);
        }
        productList.get(1).setImage("http://images.anandtech.com/doci/3753/ASUS%20ROG%20G53%20gaming%20notebooka.jpg");
        productList.get(2).setImage("http://www.www8-hp.com/au/en/images/us_en_module2_psg_Elite_Family_20160615_tcm184_2274096_tcm184_2110149_tcm184-2274096.jpg");
        productList.get(3).setImage("http://www.lenovo.com/images/gallery/560x345/lenovo-laptop-ideapad-z710-main.png");
        productList.get(4).setImage("http://www.lenovo.com/images/OneWebImages/SubSeries/gallery/laptops/ThinkPad-X1-Carbon-Laptop-PC-Front-View-3-gallery-940x529.jpg");
        productList.get(0).setImage("http://www.www8-hp.com/us/en/images/laptops_hybrids_tcm_245_1765828.jpg");
        */
    }
    
    public List<Product> getProducts() {
        return productList;
    }

    public boolean login(String username, String password) {
        if(passwords.containsKey(username)){
            return passwords.get(username).equals(password);
        }
        return false;
    }

    public boolean signup(String username, String password) {
        if(passwords.containsKey(username)) return false;
        passwords.put(username, password);
        details.put(username, new Customer(username, "", "", "","normal"));
        return true;
    }

    public Customer getUserInfo(String username) {
        return details.get(username);
    }

    public boolean setUserInfo(String username, Customer info) {
        details.put(username, info);
        return true;
    }

    public float getPrice(Cart cart) {
        float total = 0;
        for(CartItem item : cart.getList()) if(item.product.hasProperty("price")) total += ((float) item.product.getPropertyValue("price")) * item.quantity;
        return total;
    }

    public boolean processOrder(Customer customer, Cart cart, float total_price) {
        boolean success = true;
        if(!saveToFile(new Order(getMaxId(),customer, cart, total_price)) 
            || !changeAvailableAmount(cart))
            success = false;
        return success;
    }

    public void readTheFile()
    {
        try
        {
            FileReader readProd = new FileReader("products.txt");
            Scanner parser = new Scanner(readProd);
            while(parser.hasNextLine())
            {
                String prodLine = parser.nextLine();
                String[] splittedValues = prodLine.split(",");
                //System.out.println(splittedValues.length);
                String name = splittedValues[0];
                String url = splittedValues[1];
                String type = splittedValues[2];
                String displayName = splittedValues[3];
                float price = Float.parseFloat(splittedValues[4]);
                String sQuantity = splittedValues[5];
                int quantity = Integer.parseInt(sQuantity);
                addProduct(new Product(name, url, type, displayName, price, quantity)); 
            }
            readProd.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("Have not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
    }
    
    public boolean saveToFile(Order order)
    {
        boolean flag = true;
        try
        {
            PrintWriter outputFile = new PrintWriter(new FileWriter("orders.txt",true));
            outputFile.print(order.getOrderId()+",");
            List<CartItem> cartList = order.getCart().getList();
            for (int index = 0; index < cartList.size(); index++)
            {
                outputFile.print(cartList.get(index).getProduct().getName()+",");
                outputFile.print(cartList.get(index).getProduct().getPropertyValue("price")+",");
                outputFile.print(cartList.get(index).getQuantity()+",");
            }
            outputFile.print(order.getTotalPrice()+",");
            outputFile.print(order.getCustomer().name + "," +
                                order.getCustomer().address + ","
                                + order.getCustomer().cardNumber+ ","
                                + order.getCustomer().phoneNumber + ",");
            outputFile.println(order.getPurchasedTime());
            outputFile.close();
        }
        catch (IOException e)
        {
            System.out.println("Unexpected I/O error occured");
            flag = false;
        }
        return flag;
    }
    
    public int getMaxId()
    {        
        ArrayList<String> orderList = new ArrayList<String>(); 
        File booksFile = new File("books.txt");
        try
        {
            FileReader inputFile = new FileReader("orders.txt");
            Scanner parser = new Scanner(inputFile);
            while (parser.hasNextLine())
            {
                orderList.add(parser.nextLine());
            }
            inputFile.close();
        }
        catch (FileNotFoundException exception)
        {
            System.out.println("Have not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        return orderList.size();
    }
    
    public boolean saveToProductFile()
    {
        boolean flag = true;
        try
        {
            PrintWriter outputFile = new PrintWriter(new FileWriter("products.txt",false));  
            for (int index = 0; index < productList.size(); index++)
            {
                outputFile.print(productList.get(index).getName());
                outputFile.print("," + productList.get(index).getUrl());
                for(String key : productList.get(index).getProps().keySet())
                    outputFile.print("," + key + 
                        "," + productList.get(index).getPropertyDisplayName(key) + 
                        "," + productList.get(index).getPropertyValue(key));
                outputFile.print("," + productList.get(index).getAmount());
                outputFile.println();
            }
            outputFile.close();
        }
        catch (IOException e)
        {
            System.out.println("Unexpected I/O error occured");
            flag = false;
        }
        return flag;
    }
    
    
    public boolean addProduct(Product p)
    {
        productList.add(p);
        return true;
    }
   
    
    public boolean addToProducts(Product p, int quantity)
    {
        p.setAmount(quantity);
        if(saveToProductFile())
            return true;
        else
            return false;
    }
    
    public boolean changeAvailableAmount(Cart cart)
    {
        boolean flag = false;
        for (int i=0; i < cart.getList().size(); i++)
        {
            CartItem item = cart.getList().get(i);
            for (int j=0; j < productList.size(); j++)
                if(productList.get(j).getName().equals(item.getProduct().getName()))
                    productList.get(j).setAmount(0-(int)item.getQuantity());
        }
        if(saveToProductFile())
            flag = true;
        return flag;
    }
    
    public boolean searchPurchasedItems(int startDate, int endDate, String currentUserID)
    {
       //ArrayList<Report> reports = new ArrayList<Report>();
       boolean hasPurchasedItems = false;
        try
        {
            FileReader readProd = new FileReader("orders.txt");
            Scanner parser = new Scanner(readProd);
            while(parser.hasNextLine())
            {
                String prodLine = parser.nextLine();
                String[] splittedValues = prodLine.split(",");
                //System.out.println(splittedValues.length);
                int orderId = Integer.parseInt(splittedValues[0]);
                String purchasedTime = splittedValues[splittedValues.length-1];
                String phoneNum = splittedValues[splittedValues.length-2];
                String cardNum = splittedValues[splittedValues.length-3];
                String address = splittedValues[splittedValues.length-4];
                String customerName = splittedValues[splittedValues.length-5];
                float totalPrice = Float.parseFloat(splittedValues[splittedValues.length-6]);
                int purDate = splitDateAndTime(purchasedTime);
                
                if ((getUserInfo(currentUserID).getType().equals("admin") && startDate == 0 && endDate == 0)||
                    (getUserInfo(currentUserID).getType().equals("admin") && compareIntDate(purDate, startDate, endDate))||
                    (startDate == 0 && endDate == 0 && customerName.equals(currentUserID)) ||
                    (compareIntDate(purDate, startDate, endDate) && customerName.equals(currentUserID)))
                {
                    Customer c = new Customer(customerName, address, cardNum, phoneNum, "normal");
                    Cart items = new Cart();
                    for (int i=1; i<splittedValues.length-6; i+=3)
                    {
                        items.add(new Product(splittedValues[i],splittedValues[i+1]),Float.parseFloat(splittedValues[i+2]));
                    }
                
                    Report p = new Report(orderId, c, items, totalPrice, purchasedTime);
                    reportList.add(p);
                    //p.display();
                    hasPurchasedItems = true;
                }      
            }
            readProd.close();
       }
        catch (FileNotFoundException exception)
        {
            System.out.println("Have not found");
        }
        catch (IOException exception)
        {
            System.out.println("Unexpected I/O error occured");
        }
        return hasPurchasedItems;
    }
    
    public ArrayList<Report> getReportList()
    {
        return reportList;
    }
     
    public int splitDateAndTime(String sth)
    {
        String[] splittedValues = sth.split("\\s+");
        String dateField = splittedValues[0];
        String timeField = splittedValues[1];
        return dateModify(dateField);
    }
    
    public int dateModify(String date)
    {
        String[] splittedDate = date.split("-");
        String year = splittedDate[0];
        String month = splittedDate[1];
        String day = splittedDate[2];
        String newDate = year + month + day;
        int comDate = Integer.parseInt(newDate);
        return comDate;
        //System.out.println(comDate);
    }
    
    public boolean compareIntDate(int comDate, int startDate, int endDate)
    {
        if (comDate >= startDate && comDate <= endDate)
        {
            return true;
        }
        return false;
    }
}
