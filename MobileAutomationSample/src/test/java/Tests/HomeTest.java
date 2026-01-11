package Tests;

import Config.SpringTestConfig;
import Pages.HomePage;
import Utils.ImageComparisonUtils;
import Utils.ScreenshotUtils;
import Utils.SwipeUtils;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.*;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStreamReader;


@SpringBootTest
@ContextConfiguration(classes = SpringTestConfig.class)
public class HomeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private HomePage homePage;

    @Autowired
    private ScreenshotUtils screenshotUtils;

    @Autowired
    private SwipeUtils swipeUtils;

    @Autowired
    protected AndroidDriver driver;

    @Autowired
    protected ImageComparisonUtils imageComparisonUtils;

    @Epic("YouTube Tests")
    @Feature("Search Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that YouTube search works with Telugu songs")
    @Test(priority = 1, enabled = true)
    public void searchfunctionality() {
        homePage.clickSearch();
        screenshotUtils.captureScreenshot("searchiconclicked");
        System.out.println("Search icon clicked successfully.");
        String cardetails = "grand vitara top model 2025 details";
        homePage.enterSearchText(cardetails);
        screenshotUtils.captureScreenshot("searchfunctionality");
        System.out.println("Search text entered successfully.");
        homePage.scrollDown();
        System.out.println("Scrolled down successfully.");
        homePage.scrollMultipleTimes(5);
        screenshotUtils.captureScreenshot("scrolldownmultipletimes");
        System.out.println("Scrolled down multiple times successfully.");
    }

    @Epic("YouTube Tests")
    @Feature("HomePage Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that YouTube HomePage functions correctly")
    @Test(priority = 2, enabled = true)
    public void homepagefunctionality() throws Exception {
        homePage.goToHome();
        screenshotUtils.captureScreenshot("homepage");
        BufferedImage baseline = imageComparisonUtils.loadBaselineScreenshot("src/test/resources/baseline/homepage.png");
        File file = new File("src/test/resources/baseline/homepage.png");
        System.out.println("Exists: " + file.exists());
        System.out.println("Absolute path: " + file.getAbsolutePath());
        // Step 3: Capture actual screenshot
        BufferedImage actual = imageComparisonUtils.captureActualScreenshot();
        // Step 4: Compare baseline vs actual
        boolean match = imageComparisonUtils.compareImages(baseline, actual);
        if (!match) {
            imageComparisonUtils.saveAndAttachDiffImage(baseline, actual, "src/test/resources/diff/homepage_diff.png");
            Assert.fail("Visual mismatch detected! See diff/homepage_diff.png");
        } else {
            System.out.println("Homepage matches baseline screenshot.");
        }
    }

    @Epic("YouTube Tests")
    @Feature("Shorts Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that YouTube Shorts functions correctly")
    @Test(priority = 3, enabled = true)
    public void shortsfunctionality() {
        homePage.goToShorts();
        screenshotUtils.captureScreenshot("shorts");
    }

    @Epic("YouTube Tests")
    @Feature("Subscriptions Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that YouTube subscriptions functions correctly")
    @Test(priority = 4, enabled = true)
    public void subscriptionsfunctionality() throws InterruptedException {
        homePage.goToSubscriptions();
        Thread.sleep(5000);
        screenshotUtils.captureScreenshot("subscriptions");
        swipeUtils.scrollChannelRowForward();
        screenshotUtils.captureScreenshot("channelRowScrollForward");
        swipeUtils.scrollChannelRowBackward();
        screenshotUtils.captureScreenshot("channelRowScrollBackward");
        Thread.sleep(4000);
        // Swipe until "Thulasi Chandu" channel is visible
        WebElement channel = swipeUtils.swipeUntilChannelVisible("Thulasi Chandu");
        screenshotUtils.captureScreenshot("channelVisible");
        channel.click();
        screenshotUtils.captureScreenshot("channelClicked");
    }

    @Epic("YouTube Tests")
    @Feature("Profile Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that YouTube profile functions correctly")
    @Test(priority = 5, enabled = true)
    public void profilefunctionality() {
        homePage.goToProfile();
        screenshotUtils.captureScreenshot("profile");
    }

    @Epic("YouTube Tests")
    @Feature("Toast Message Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that YouTube toast messages function correctly")
    @Test(priority = 6, enabled = true)
    public void toastMessage() {
        homePage.turnonIncognito();
        screenshotUtils.captureScreenshot("incognitomodeon");
        homePage.goToProfile();
        screenshotUtils.captureScreenshot("profilewithincognitomode");
        homePage.turnoffIncognito();
        screenshotUtils.captureScreenshot("incognitomodeoff");
        String tmessage = homePage.toastMessage();
        screenshotUtils.captureScreenshot("toastmessage");
        System.out.println("Toast message captured: " + tmessage);
    }

    @Epic("YouTube Tests")
    @Feature("App close and launch Functionality")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that YouTube app closes and launches correctly")
    @Test(priority = 7, enabled = true)
    public void appActions() throws InterruptedException {
        homePage.closeApp();
        homePage.relaunchApp();
        screenshotUtils.captureScreenshot("applaunched");
    }

    @Test(priority = 8, description = "Visual comparison of YouTube HomePage with baseline")
    public void homePageVisualComparison() throws Exception {
        BufferedImage baseline = imageComparisonUtils.loadBaselineScreenshot("baseline/homepage.png");
        BufferedImage actual = imageComparisonUtils.captureActualScreenshot();

        boolean match = imageComparisonUtils.compareImages(baseline, actual);

        if (!match) {
            imageComparisonUtils.saveAndAttachDiffImage(baseline, actual, "diff/homepage_diff.png");
            Assert.fail("Visual mismatch detected! See attached baseline, actual, and diff screenshots in Allure.");
        } else {
            System.out.println("Homepage matches baseline screenshot.");
        }
    }

    @BeforeClass(alwaysRun = true)
    public void setUp(ITestContext context) {
        if (driver == null) {
            throw new RuntimeException("AndroidDriver is NULL – Spring injection failed");
        }
        context.setAttribute("driver", driver);
        System.out.println("Driver stored in TestNG context successfully");
    }

    @BeforeSuite
    public void cleanArtifacts() throws IOException {
        // Clean or create baseline and diff directories
        File baselineDir = new File("src/test/resources/baseline");
        File diffDir = new File("src/test/resources/diff");

        if (diffDir.exists()) {
            FileUtils.cleanDirectory(diffDir);
            System.out.println("Diff directory cleaned.");
        } else {
            diffDir.mkdirs();
            System.out.println("Diff directory created.");
        }

        if (!baselineDir.exists()) {
            baselineDir.mkdirs();
            System.out.println("Baseline directory created.");
        }
        // Clean Allure results directory
        File resultsDir = new File("target/allure-results");
        if (resultsDir.exists()) {
            FileUtils.deleteDirectory(resultsDir);
            System.out.println("Deleted old allure-results folder.");
        }
        // Clean Allure report (if generated previously)
        File reportDir = new File("target/allure-report");
        if (reportDir.exists()) {
            FileUtils.deleteDirectory(reportDir);
            System.out.println("Deleted old allure-report folder.");
        }
        // Clean screenshots folder
        File screenshotsDir = new File("target/screenshots");
        if (screenshotsDir.exists()) {
            FileUtils.cleanDirectory(screenshotsDir);
            System.out.println("Cleaned screenshots folder.");
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        if (driver != null && driver.getSessionId() != null) {
            driver.quit();
            System.out.println("Driver quit at suite end.");
        }
    }

}