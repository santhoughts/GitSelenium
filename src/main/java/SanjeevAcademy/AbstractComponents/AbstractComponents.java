package SanjeevAcademy.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.CartPage;
import pageobjects.OrderPage;

import java.time.Duration;

public class AbstractComponents {

    //create local object for use the driver class throughout the class
    WebDriver driver;
    public AbstractComponents(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;


    public void waitForElementToAppear(By FindBy)
    {
        // Reusable code is write in the abstacatComponent Class
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
    }

    public void WaitForWebElementToAppear(WebElement FindBy)
    {
        // Reusable code is write in the abstacatComponent Class
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(FindBy));
    }

    public CartPage goTOCartPage()
    {
        cartHeader.click();
        CartPage cartPage= new CartPage(driver);
        return cartPage;

    }

    public OrderPage goToOrderPage()
    {
        orderHeader.click();
        OrderPage orderPage= new OrderPage(driver);
        return orderPage;

    }



    public void waitForElementToDisappear(WebElement ele)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void windowScroll()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,50000)");
    }
}
