package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    // This method is used to run the failed test multiple times
    int count = 0;
    int maxTry = 1;
    @Override
    public boolean retry(ITestResult iTestResult)
    {
        if (count<maxTry)
        {
            count++;
            return true;
        }
        return false;
    }
}
