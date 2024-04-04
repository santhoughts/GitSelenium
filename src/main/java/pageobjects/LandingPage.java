package pageobjects;

import SanjeevAcademy.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends AbstractComponents {

    WebDriver driver;

    // We create the constructor class for initialization(If we execute this class first one constructor is executed)
    public LandingPage(WebDriver driver) {

        // super key use to send the variables from the child class to parent class by using the superkey
        super(driver);
        this.driver = driver;
        // Use page factory init methods for using the driver key word in page factory methods
        PageFactory.initElements(driver, this);
    }

    //WebElement userEmails =driver.findElement(By.id("userEmail"));

    //Page Factory Method(Use page factory method for the different locators)

    @FindBy(id = "userEmail")
    WebElement userEmails;

    @FindBy(id = "userPassword")
    WebElement password;

    @FindBy(id = "login")
    WebElement submit;

    @FindBy(css = "[class*='flyInOut']")
    WebElement errorMessage;


    // Below write the action methods
    public ProductCatalouge loginApplication(String email, String Password)
    {
        userEmails.sendKeys(email);
        password.sendKeys(Password);
        submit.click();
        // We are make sure after login we land on the productCatalogue page then here make the object of that class
        // and return those object
        ProductCatalouge productCatalouge=new ProductCatalouge(driver);
        return productCatalouge;
    }

    public String getErrorMessage()
    {
        WaitForWebElementToAppear(errorMessage);
        return errorMessage.getText();

    }

    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");

    }


}
