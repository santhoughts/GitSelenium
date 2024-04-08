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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;

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
        // Reusable code is write in the abstractComponent Class
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
    }

    public void WaitForWebElementToAppear(WebElement FindBy)
    {
        // Reusable code is write in the abstractComponent Class
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
        js.executeScript("window.scrollBy(0,1000)");
    }

    // Footer link count

    @FindBy(css = "li[class='gf-li'] a")
    List<WebElement> numberOfLink;

    // get number of link
    public List<WebElement> getFooterLink()
    {
      return  numberOfLink;
    }

    public int getResponseCodeOfEachLink(WebElement link1) throws IOException {
       Iterator var5 = numberOfLink.iterator();
       while (var5.hasNext())
       {
           windowScroll();
           WebElement link = (WebElement)var5.next();
           String url =link.getAttribute("href");
           HttpURLConnection conn = (HttpURLConnection)(new URL(url)).openConnection();
           conn.setRequestMethod("HEAD");
           conn.connect();
           int respCode = conn.getResponseCode();


           return respCode;


       }

        return 0;
    }



}
