/**
 * <pre>
 * This is the main class of the OSS. 
 * </pre>
 */
public class Main {

    /**
     * <pre>
     * This method is the start of this program
     * </pre>
     * 
     * @param args not special meaning, just the String[]
     */
	public static void main(String[] args){
		
		Model shopModel = new DummyModel();
		
		ShopController c = new ShopController(shopModel);
		c.init();
		
	}
	
}
