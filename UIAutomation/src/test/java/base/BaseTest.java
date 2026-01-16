package base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import utils.DriverFactory;

public class BaseTest {

    public WebDriver driver;
    protected Logger log = LogManager.getLogger(this.getClass());

    @BeforeClass
    public void setUp() {
        log.info("Initializing Chrome Driver");
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        log.info("Maximizing browser window");
        driver.manage().window().maximize();
        log.info("Navigating to application URL");
        driver.get("https://demoqa.com/");
    }

    @AfterClass
    public void tearDown() {
        log.info("Closing browser");
        DriverFactory.quitDriver();
    }
}
