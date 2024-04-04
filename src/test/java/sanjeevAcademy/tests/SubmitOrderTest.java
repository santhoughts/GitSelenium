package sanjeevAcademy.tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.IOException;
import java.util.HashMap;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test(dataProvider="getData")
    public  void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {

        LandingPage landingPage=launchApplication();
        ProductCatalouge productCatalouge=landingPage.loginApplication
                (input.get("email"),input.get("password"));

        // Retrieve list of product elements
        // List<WebElement> products =productCatalouge.getProductList();
        productCatalouge.addProductToCart(input.get("productName"));
        CartPage cartPage=productCatalouge.goTOCartPage();
        Boolean match=cartPage.VerifyProductDisplay(input.get("productName"));
        Assert.assertTrue(match);
        Thread.sleep(3000);
        CheckoutPage checkoutPage= cartPage.goTOCheckout();
        checkoutPage.selectCountry("india");
        Thread.sleep(3000);
        ConfirmationPage confirmationPage =checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    @Test(dependsOnMethods = "submitOrder")
    public void OrderHistoryTest()
    {
        ProductCatalouge productCatalouge=landingPage.loginApplication
                ("sanjeev052@gmail.com","Sanjeev@123");
        OrderPage orderPage=productCatalouge.goToOrderPage();
        orderPage.VerifyOrderDisplay(productName);
        Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
    }

    // Get the screenshot if the test is failed



     //dataProvider we used to fill the multiple data at one time
   /* @DataProvider

    public  Object[][] getData()
    {
        return new Object[][]{{"sanjeev052@gmail.com","Sanjeev@123","ZARA COAT 3"}};
    } */

    // Provide the data using HashMap
    @DataProvider
    public  Object[][] getData()
    {
        HashMap<String,String> map= new HashMap<String,String>();
        map.put("email","sanjeev052@gmail.com");
        map.put("password","Sanjeev@123");
        map.put("productName","ZARA COAT 3");

//        HashMap<String,String> map1= new HashMap<String,String>();
//        map1.put("email","sanjeev0523@gmail.com");
//        map1.put("password","Sanjeev@123");
//        map1.put("productName","ADIDAS ORIGINAL");


        return new Object[][]{{map}};
    }


}
