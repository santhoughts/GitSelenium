package pageobjects;

import SanjeevAcademy.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalouge extends AbstractComponents {

    WebDriver driver;

    // We create the constructor class for intialization(If we execute this class first one constructor is executed)
    public ProductCatalouge(WebDriver driver) {

        super(driver);
        this.driver = driver;
        // Use pagefactory init methods for using the driver key word in pagefactory methods
        PageFactory.initElements(driver, this);
    }

//    List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//    PgaeFactory Method(Use pagefactory method for the different WebElement)

    @FindBy(css = ".mb-3")
   List <WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;


    // Find BY use if there is no driver.find element is persent
    By productsBy = By.cssSelector(".mb-3");
    By addToCart =By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    By productNameElement = By.cssSelector("b");

    // Action Methods

    public List<WebElement> getProductList()
    {
        waitForElementToAppear(productsBy);
        return products;
    }

    public WebElement getProductByName(String productName)
    {
        WebElement prod = getProductList().stream().filter(product ->
                        product.findElement(productNameElement).getText().equals(productName))
                .findFirst().orElse(null);
        return prod;
    }

    public  void addProductToCart(String productName)
    {
        WebElement prod= getProductByName(productName);
        prod.findElement(addToCart).click();
        waitForElementToAppear(toastMessage);
        waitForElementToDisappear(spinner);
    }

   }



