package sanjeevAcademy.tests;

import TestComponents.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.IOException;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups = {"ErrorHandling"})
    public void LoginErrorValidation() throws IOException {

        LandingPage landingPage = launchApplication();
        ProductCatalouge productCatalouge = landingPage.loginApplication
                ("sanjeev@gmail.com", "Sanjeev@");
        String msg = landingPage.getErrorMessage();
        Assert.assertEquals("Incorrect email or password.", msg);


    }

    @Test
    public void ProductErrorValidation() throws IOException {

        String productName = "ZARA COAT 3";
        LandingPage landingPage = launchApplication();
        ProductCatalouge productCatalouge = landingPage.loginApplication
                ("sanjeev052@gmail.com", "Sanjeev@123");
        productCatalouge.addProductToCart(productName);
        CartPage cartPage = productCatalouge.goTOCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);

    }
}
