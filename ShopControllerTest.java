

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ShopControllerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ShopControllerTest
{
    private DummyModel dummyMod1;
    private ShopController shopCont1;
    private Product dell;

    /**
     * Default constructor for test class ShopControllerTest
     */
    public ShopControllerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        dummyMod1 = new DummyModel();
        shopCont1 = new ShopController(dummyMod1);
        dell = new Product("dell", "http://www.windowspasswordsreset.com/windows-password-knowledge/images/dell-laptop.jpg", "price", "unitprice", 123f, 222);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testAddToCartT1_S1()
    {
        assertEquals(true, shopCont1.addToCart(dell, 2f));
    }

    @Test
    public void testAddToCartT1_S2()
    {
        assertEquals(false, shopCont1.addToCart(dell, 223f));
    }

    @Test
    public void testAddToCartT1_S3()
    {
        assertEquals(false, shopCont1.addToCart(null, 2f));
    }

    @Test
    public void testAddToCartT1_S4()
    {
        assertEquals(false, shopCont1.addToCart(null, -1f));
    }


    @Test
    public void testAddToCartT1_S5()
    {
        assertEquals(false, shopCont1.addToCart(dell, -1f));
    }

    @Test
    public void testGetTotalCartPriceT2_S1()
    {
        assertEquals(true, shopCont1.addToCart(dell, 2f));
        assertEquals(246.0, shopCont1.getTotalCartPrice(), 0.1);
    }

    @Test
    public void testGetTotalCartPriceT2_S2()
    {
        assertEquals(false, shopCont1.addToCart(dell, -1f));
        assertEquals(0.0, shopCont1.getTotalCartPrice(), 0.1);
    }

    @Test
    public void testGetTotalCartPriceT2_S3()
    {
        assertEquals(false, shopCont1.addToCart(null, -1f));
        assertEquals(0.0, shopCont1.getTotalCartPrice(), 0.1);
    }

    @Test
    public void testGetTotalCartPriceT2_S4()
    {
        assertEquals(false, shopCont1.addToCart(null, 1f));
        assertEquals(0.0, shopCont1.getTotalCartPrice(), 0.1);
    }

    @Test
    public void testAttemptTransactionT3_S1()
    {
        shopCont1.attemptLogin("fancy", "fancy");
        assertEquals(true, shopCont1.attemptTransaction());
    }

    @Test
    public void testAttemptTransactionT3_S3()
    {
        shopCont1.attemptLogin("shazeed", "shazeed");
        assertEquals(false, shopCont1.attemptTransaction());
    }

    @Test
    public void testAttemptTransactionT3_S2()
    {
        shopCont1.attemptLogin("sean", "sean");
        assertEquals(false, shopCont1.attemptTransaction());
    }

    @Test
    public void testAttemptTransactionT3_S4()
    {
        shopCont1.attemptLogin("cindy", "cindy");
        assertEquals(false, shopCont1.attemptTransaction());
    }

    @Test
    public void testAttemptTransactionT3_S5()
    {
        shopCont1.attemptLogin("dora", "dora");
        assertEquals(false, shopCont1.attemptTransaction());
    }


    @Test
    public void testCheckWithAvaiableT4_S1()
    {
        shopCont1.attemptLogin("fancy", "fancy");
        assertEquals(true, shopCont1.addToCart(dell, 2f));
        assertEquals(true, shopCont1.checkWithAvaiable());
    }

    @Test
    public void testCheckWithAvaiableT4_S2()
    {
        shopCont1.attemptLogin("fancy", "fancy");
        assertEquals(false, shopCont1.addToCart(dell, 223f));
        assertEquals(false, shopCont1.checkWithAvaiable());
    }

    @Test
    public void testCheckWithAvaiableT4_S3()
    {
        shopCont1.attemptLogin("fancy", "fancy");
        assertEquals(false, shopCont1.checkWithAvaiable());
    }
}




















