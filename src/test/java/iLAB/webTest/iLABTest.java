package iLAB.webTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import iLAB.data.DbConnection;
import iLAB.generateNumber.phoneNumberGenerator;
import iLAB.pageObject.webFunctions;
import iLAB.report.report;
import iLAB.webUtilities.webUtilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.sql.ResultSet;

public class iLABTest {
    webUtilities webUtilities2 = new webUtilities();
    phoneNumberGenerator phoneNumberGenerator2 = new phoneNumberGenerator();
    webFunctions webFunctions2 = new webFunctions();
    String sURL,sBrowser;
    report reporting = new report();
    ExtentReports ExtentReport2;
    DbConnection Conn = new DbConnection();
    ResultSet DataFromDB;

    @Parameters({"iLABURL","Browser"})
    @BeforeTest
    public void initialiseBrowser(String suRL,String browser){
        sURL= suRL;
        sBrowser= browser;
        webUtilities2.setWebDriver(webUtilities2.initializeWebDriver(sBrowser));
        DataFromDB = Conn.ConnectDb();
        ExtentReport2= reporting.initializeExtentReports("reports/iLABReport.html");
    }
    @Test
    public void iLAB() {
        ExtentTest test= ExtentReport2.createTest("iLABReport").assignAuthor("Sinazo");
        // ExtentTest Node = test.createNode("Error Message Validation");

        try {
            while (DataFromDB.next()) {
                webUtilities2.navigate(sURL);
                webFunctions2.landingPage(webUtilities.getWebDriver(), test);
                webFunctions2.country(webUtilities.getWebDriver(), test);
                webFunctions2.internOpening(webUtilities.getWebDriver(), test);
                webFunctions2.apply(webUtilities.getWebDriver(),test, DataFromDB.getString("Name")
                        ,DataFromDB.getString("Email"),phoneNumberGenerator2.phoneNumberGenerator());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        webUtilities.getWebDriver().quit();
        ExtentReport2.flush();
    }
}
