package sanjeevAcademy.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class BrokenLink {

    public static void main(String[] args) throws MalformedURLException, IOException {
//        System.setProperty("webdriver.chrome.driver", "C:\\Drivers\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
        WebDriverManager.chromedriver().setup();
       WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
        SoftAssert a = new SoftAssert();
        Iterator var5 = links.iterator();

        while(var5.hasNext()) {
            WebElement link = (WebElement)var5.next();
            String url = link.getAttribute("href");
            HttpURLConnection conn = (HttpURLConnection)(new URL(url)).openConnection();
            conn.setRequestMethod("HEAD");
            conn.connect();
            int respCode = conn.getResponseCode();
            System.out.println(respCode);
            a.assertTrue(respCode < 400, "The link with the text " + link.getText() + " with the status code " + respCode);
        }

        a.assertAll();
    }
}

