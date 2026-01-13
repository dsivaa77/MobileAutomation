package Utils;

import io.appium.java_client.AppiumDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DriverUtils {

    private final AppiumDriver driver;

    protected Logger logger = LogManager.getLogger(this.getClass());
    public DriverUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    public void closeApp() {
        if (driver != null) {
            driver.close();
            logger.info("App closed.");
        }
    }

    public void quitSession() {
        if (driver != null) {
            driver.quit();
            logger.info("Driver session quit.");
        }
    }
}
