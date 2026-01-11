package Utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ScreenshotUtils {

    private final AndroidDriver driver;

    @Autowired
    public ScreenshotUtils(@Lazy AndroidDriver driver) {
        this.driver = driver;
    }

    public String captureScreenshot(String name) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        // Create Screenshots folder if not exists
        Path screenshotsDir = Path.of("target", "Screenshots");
        if (!Files.exists(screenshotsDir)) {
            try {
                Files.createDirectories(screenshotsDir);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create Screenshots directory", e);
            }
        }

        // Add timestamp to filename
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Path destFile = screenshotsDir.resolve(name + "_" + timestamp + ".png");

        try {
            Files.copy(srcFile.toPath(), destFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot", e);
        }

        return destFile.toString(); // return path for logging/debugging
    }


    // Attach screenshot directly to Allure report

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] captureScreenshotForAllure() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
