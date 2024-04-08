package sanjeevAcademy.tests;


import TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageobjects.LandingPage;

import java.io.IOException;
import java.util.List;

public class VerifyBrokenLinkInFooter extends BaseTest {

    @Test
    public void countBrokenLink() throws IOException {
        LandingPage landingPage = launchApplication();
         List<WebElement> allLinks = landingPage.getFooterLink();
        SoftAssert a = new SoftAssert();
        for (WebElement link1 : allLinks)
        {
            int respCode1 = landingPage.getResponseCodeOfEachLink(link1);
            System.out.println(respCode1);

            a.assertTrue(respCode1 < 400, "The link with the text "+link1.getText() + " with the status code " + respCode1);
        }
        a.assertAll();
        System.out.println(allLinks.size());


    }


}
