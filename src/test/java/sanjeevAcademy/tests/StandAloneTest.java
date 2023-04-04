package sanjeevAcademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pageobjects.LandingPage;

import javax.swing.*;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {

        String productName = "ZARA COAT 3";
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        //Maximize the screen.
        driver.manage().window().maximize();

        // Implicit timeout
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");


        driver.findElement(By.id("userEmail")).sendKeys("sanjeev052@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Sanjeev@123");
        driver.findElement(By.id("login")).click();
//        Thread.sleep(10000);


        // Give some wait for the page to appear all the elements on the page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        // Use findElements driver to find more than one element and store into the List
        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//
//        // use streams to itreate through the lsit of products
        WebElement prod = products.stream().filter(product ->
                        product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst().orElse(null);
//
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        // Use WebDriverwait class for waiting to appear some elements on the website
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

        //Locatar element for the loader screen "ng-animating"
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        // Click on cart icon
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

        // Itreatte through thr list of product which are available on the My cart section and match the product
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct ->
                cartProduct.getText().equalsIgnoreCase(productName));

        // Assert the validation here to check the conditions
        Assert.assertTrue(match);

        // Click on checkout button for further proceed
        driver.findElement(By.cssSelector(".totalRow button")).click();

        // use actions class to choose the value from the dropdown suggestion
        Actions a = new Actions(driver);
        a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),
                "india").build().perform();

        // wait is use here of visibility of the suggestion  screen
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

        //Click on suggestion list
        driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

        //click on the place order button
        driver.findElement(By.cssSelector(".action__submit")).click();

        // grab the text from the thank you screen and confirm the message
        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();


    }

}
