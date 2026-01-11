package Utils;

import io.appium.java_client.AppiumDriver;

public class DriverUtils {

    private final AppiumDriver driver;

    public DriverUtils(AppiumDriver driver) {
        this.driver = driver;
    }

    public void closeApp() {
        if (driver != null) {
            driver.close();
            System.out.println("App closed.");
        }
    }

    public void quitSession() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver session quit.");
        }
    }
}
