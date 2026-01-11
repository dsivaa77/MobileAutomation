package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class LoginPage {

    private WebDriver driver;
    private Logger log = LogManager.getLogger(LoginPage.class);
    SoftAssert softAssert = new SoftAssert();

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(){
        driver.getTitle();
        log.info("Verifying page title after login {}", driver.getTitle());
        softAssert.assertEquals(driver.getTitle(), "DEMOQA", "Page title does not match expected value.");
        softAssert.assertAll();
    }
}
