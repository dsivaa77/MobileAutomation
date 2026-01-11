package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyLogin() {
        log.info("Starting Login Test");
        LoginPage login = new LoginPage(driver);
        login.login();
        log.info("Login Test completed");
    }
}
