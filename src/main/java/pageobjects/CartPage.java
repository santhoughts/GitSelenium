package pageobjects;

import SanjeevAcademy.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends AbstractComponents {

    WebDriver driver;
    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);


    }

    @FindBy(css = ".cartSection h3")
    private  List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    public Boolean VerifyProductDisplay(String productName)
    {

        Boolean match = cartProducts.stream().anyMatch(product ->
                product.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage goTOCheckout()
    {
        checkoutEle.click();
        return new CheckoutPage(driver);

    }
}

