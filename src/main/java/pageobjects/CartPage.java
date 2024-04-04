package pageobjects;

import SanjeevAcademy.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
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
    List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    WebElement checkoutEle;

    By checkOut = By.cssSelector(".totalRow button");

    public Boolean VerifyProductDisplay(String productName)
    {

        Boolean match = cartProducts.stream().anyMatch(product ->
                product.getText().equalsIgnoreCase(productName));
        return match;
    }

    public CheckoutPage goTOCheckout()
    {
        //waitForElementToAppear(checkOut);
        windowScroll();
        checkoutEle.click();
        return new CheckoutPage(driver);
    }
}

