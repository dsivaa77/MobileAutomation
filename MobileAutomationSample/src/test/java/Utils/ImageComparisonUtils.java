package Utils;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;

@Component
public class ImageComparisonUtils {

    private final AndroidDriver driver;

    public ImageComparisonUtils(@Lazy AndroidDriver driver) {
        this.driver = driver;
    }

    // Capture actual screenshot
    public BufferedImage captureActualScreenshot() throws Exception {
        File screenshotFile = driver.getScreenshotAs(OutputType.FILE);
        return ImageIO.read(screenshotFile);
    }

    // Load baseline screenshot
    public BufferedImage loadBaselineScreenshot(String baselinePath) throws Exception {
        return ImageIO.read(new File(baselinePath));
    }

    // Compare pixel by pixel
    public boolean compareImages(BufferedImage baseline, BufferedImage actual) {
        if (baseline.getWidth() != actual.getWidth() || baseline.getHeight() != actual.getHeight()) {
            return false;
        }
        for (int x = 0; x < baseline.getWidth(); x++) {
            for (int y = 0; y < baseline.getHeight(); y++) {
                if (baseline.getRGB(x, y) != actual.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }

    // Save diff image and attach to Allure
    public void saveAndAttachDiffImage(BufferedImage baseline, BufferedImage actual, String diffPath) throws Exception {
        BufferedImage diff = new BufferedImage(baseline.getWidth(), baseline.getHeight(), baseline.getType());
        for (int x = 0; x < baseline.getWidth(); x++) {
            for (int y = 0; y < baseline.getHeight(); y++) {
                int baseRGB = baseline.getRGB(x, y);
                int actualRGB = actual.getRGB(x, y);
                diff.setRGB(x, y, (baseRGB == actualRGB) ? baseRGB : 0xFFFF0000); // red for differences
            }
        }
        File diffFile = new File(diffPath);
        ImageIO.write(diff, "png", diffFile);

        attachBaseline(new File("src/test/resources/baseline/homepage.png"));
        attachActual(driver.getScreenshotAs(OutputType.FILE));
        attachDiff(diffFile);
    }

    // Allure attachments
    @Attachment(value = "Baseline Screenshot", type = "image/png")
    public byte[] attachBaseline(File baseline) throws Exception {
        return Files.readAllBytes(baseline.toPath());
    }

    @Attachment(value = "Actual Screenshot", type = "image/png")
    public byte[] attachActual(File actual) throws Exception {
        return Files.readAllBytes(actual.toPath());
    }

    @Attachment(value = "Diff Screenshot", type = "image/png")
    public byte[] attachDiff(File diff) throws Exception {
        return Files.readAllBytes(diff.toPath());
    }
}
