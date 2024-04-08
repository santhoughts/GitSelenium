package TestComponents;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pageobjects.LandingPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public LandingPage landingPage;

    public void intializeDriver() throws IOException {

        // Properties class is used to extract some value from the GlobalData properties
        // for localSystem path we use System.getProperty("user.dir")
        Properties prop= new Properties();
        FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resources\\GlobalData.properties");
        prop.load(fis);
        String browserName=prop.getProperty("browser");

        //Chorme browser
        if (browserName.equalsIgnoreCase("chrome"))
        {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();

        }

        else if (browserName.equalsIgnoreCase("firefox"))
        {
            // firefox

        }

        else if (browserName.equalsIgnoreCase("edge"))
        {
            // Edge

        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

    }
    @BeforeTest(alwaysRun = true)
    public LandingPage launchApplication() throws IOException
    {

            intializeDriver();
            landingPage = new LandingPage(driver);
            landingPage.goTo();
            return landingPage;

    }

    public File getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts=(TakesScreenshot)driver;
        File source =ts.getScreenshotAs(OutputType.FILE);
        File file =new File(System.getProperty("user.dir") + "//reports" + testCaseName + ".png");
        FileUtils.copyFile(source,file);
        return file;
    }



        public List<HashMap<String, String>> getJsonDataToMap( String filePath) throws IOException
        {
            // Convert Json data to String

            String jsonContent =  FileUtils.readFileToString
                    (new File(filePath));

            // String to HashMap
            ObjectMapper mapper = new ObjectMapper();
            List<HashMap<String , String>> data =  mapper.readValue(jsonContent,
                    new TypeReference<List<HashMap<String , String>>>() {
                    });
            return data;
        }




    @AfterTest(alwaysRun = true)
    public void tearDown()
    {
        driver.close();
    }


}
