package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.*;

import base.BaseTest;

public class TestListener implements ITestListener {

    ExtentReports extent = ExtentManager.getReport();
    ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test.set(extent.createTest(result.getMethod().getMethodName()));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        Object obj = result.getInstance();
        BaseTest base = (BaseTest) obj;

        byte[] screenshot =
                ((TakesScreenshot) base.driver)
                        .getScreenshotAs(OutputType.BYTES);

        test.get().addScreenCaptureFromBase64String(
                java.util.Base64.getEncoder().encodeToString(screenshot)
        );
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
