package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static Logger log = LogManager.getLogger(DriverFactory.class);

    public static void initDriver() {
        WebDriverManager.chromedriver().setup();
        log.info("Launching Chrome browser");
        driver.set(new ChromeDriver());
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        log.info("Quitting Chrome browser");
        driver.get().quit();
        driver.remove();
    }
}
