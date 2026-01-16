package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(priority = 4)
    public void verifyLogin() {
        log.info("Starting Login Test");
        LoginPage login = new LoginPage(driver);
        login.login();
        log.info("Login Test completed");
    }

    @Test(priority = 1)
    public void test1(){
        log.info("Test Method 1");
    }

    @Test(priority = 3)
    public void test2(){
        log.info("Test Method 2");
    }

    @Test(priority = 0)
    public void test3(){
        log.info("Test Method 3");
    }

    @Test(priority = 2)
    public void test4(){
        log.info("Test Method 4");
    }
}
