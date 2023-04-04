package sanjeevAcademy.tests;

import TestComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class SubmitOrderTest extends BaseTest {

    String productName = "ZARA COAT 3";

    @Test
    public  void submitOrder() throws IOException {

        LandingPage landingPage=launchApplication();
        ProductCatalouge productCatalouge=landingPage.loginApplication
                ("sanjeev052@gmail.com","Sanjeev@123");

        // Retrieve list of product elements
        // List<WebElement> products =productCatalouge.getProductList();
        productCatalouge.addProductToCart(productName);
        CartPage cartPage=productCatalouge.goTOCartPage();
        Boolean match=cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage= cartPage.goTOCheckout();
        checkoutPage.selectCountry("india");
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

}
