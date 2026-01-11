package Utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class AllureScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        AndroidDriver driver = null;

        try {
            driver = (AndroidDriver) result.getTestContext()
                    .getAttribute("driver");
        } catch (Exception ignored) {
        }

        saveScreenshotPNG(driver);
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG(AndroidDriver driver) {
        try {
            if (driver != null && driver.getSessionId() != null) {
                return driver.getScreenshotAs(OutputType.BYTES);
            }
        } catch (Exception e) {
            System.out.println("Screenshot skipped: " + e.getMessage());
        }
        return new byte[0];
    }
}


