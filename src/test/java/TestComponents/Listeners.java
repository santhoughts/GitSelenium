package TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.ExtentReporterNG;

import java.io.IOException;

public class Listeners extends BaseTest implements ITestListener {
    ExtentTest test;

    ExtentReports extent= ExtentReporterNG.getReportObject(); // create a extent object to used the extent objec in this class
    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // use ThreadLocal to stop the concurrency
    @Override
    public void onTestStart(ITestResult result)
    {
         test= extent.createTest(result.getMethod().getMethodName());
         // result.getMethod().getMethodName() give the test name on the basis of test name
        extentTest.set(test); // Thread safe create unique thread id

    }
    @Override
    public void onTestSuccess(ITestResult result)
    {
        extentTest.get().log(Status.PASS, "Test Passed"); // Log is used to print the status in extent report
    }
    @Override
    public void onTestFailure(ITestResult result)
    {
    extentTest.get().fail(result.getThrowable()); // get throwable is used to print the error in the extent report
        // if the test is failed then follow two steps to take screenshot and add into the report
        // Gave the life to the driver
        try {
            driver = (WebDriver) result.getTestClass()
                    .getRealClass().getField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
        String filePath;
        try {
            filePath = String.valueOf(getScreenshot(result.getMethod().getMethodName()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        extentTest.get().addScreenCaptureFromPath(filePath , result.getMethod().getMethodName());


    }
    @Override
    public void onTestSkipped(ITestResult result)
    {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result)
    {

    }

    @Override
    public void onStart(ITestContext context)
    {

    }

    @Override
    public void onFinish(ITestContext context)
    {
        extent.flush(); // extent.flush is used to generate the report after finishing the test
    }

}
