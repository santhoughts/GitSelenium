package sanjeevAcademy.tests;

import TestComponents.BaseTest;
import TestComponents.Retry;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ErrorValidationsTest extends BaseTest {

    String productName = "ZARA COAT 33";

    @Test(dataProvider = "getData",retryAnalyzer = Retry.class)
    public void LoginErrorValidation(HashMap<String, String> input) throws IOException, InterruptedException {

        LandingPage landingPage = launchApplication();
        ProductCatalouge productCatalouge = landingPage.loginApplication
                (input.get("Email"), input.get("Password"));
        String msg = landingPage.getErrorMessage();
        Assert.assertEquals("Incorrect email  password.", msg);
        landingPage.clearUserEmailsAndPasswordField();



    }

    @Test(dataProvider = "getData1")
    public void ProductErrorValidation(HashMap<String, String> input) throws IOException {


        LandingPage landingPage=launchApplication();
        ProductCatalouge productCatalouge=landingPage.loginApplication
                (input.get("Email"),input.get("Password"));

        // Retrieve list of product elements
        // List<WebElement> products =productCatalouge.getProductList();
        productCatalouge.addProductToCart(input.get("productName"));
        CartPage cartPage=productCatalouge.goTOCartPage();
        Boolean match=cartPage.VerifyProductDisplay(input.get("productName"));

    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
                + "\\src\\test\\Data\\LoginData.json");
        return new Object[][] {{data.get(1)}, {data.get(2)}};
    }

    @DataProvider
    public Object[][] getData1() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
                + "\\src\\test\\Data\\LoginData.json");
        return new Object[][] {{data.get(0)}};
    }
}
