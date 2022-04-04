package iLAB.pageObject;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import iLAB.report.report;
import iLAB.webUtilities.webActions;
import iLAB.webpageObject.apply;
import iLAB.webpageObject.country;
import iLAB.webpageObject.internOpenings;
import iLAB.webpageObject.landingPage;
import org.openqa.selenium.WebDriver;

public class webFunctions extends webActions {
    report reporting= new report();
    String fileName;

    public void landingPage(WebDriver driver, ExtentTest test) {
        landingPage landingpage2 = new landingPage(driver);
        country country2 = new country(driver);
        try {

            clickObject(landingpage2.CareerLink, driver);

        } catch (Exception e) {
        }


    }

    public void country(WebDriver driver,ExtentTest test) {
        country country2 = new country(driver);
        internOpenings opening2 = new internOpenings(driver);
        try {
            fileName = reporting.CaptureScreenShot(driver);
            clickObject(country2.CountryLink, driver);

        } catch (Exception e) {
        }


    }

    public void internOpening(WebDriver driver,ExtentTest test) {
        internOpenings opening2 = new internOpenings(driver);
        apply apply2 = new apply(driver);
        try {

            clickObject(opening2.internOpeningLink, driver);

        } catch (Exception e) {
        }
    }

    public void apply(WebDriver driver, ExtentTest test, String Firstname,String Email,String phone) {
        apply apply2 = new apply(driver);
        try {

            clickObject(apply2.apply, driver);
            passData(apply2.firstname, driver,Firstname);
            passData(apply2.email, driver,Email);
            passData(apply2.phoneNumber, driver,phone);
            clickObject(apply2.sendApplication, driver);

            fileName = reporting.CaptureScreenShot(driver);

            if (apply2.errorMessage.isDisplayed()) {
                test.pass("Successfull. Error message is displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(fileName).build());
            } else {
                test.fail("Error. Error message not displayed");
            }
        } catch (Exception e) {
        }
    }
}
