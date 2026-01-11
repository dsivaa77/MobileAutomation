package Pages;

import Utils.WaitUtils;
import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Component
public class HomePage {

    private final AndroidDriver driver;
    private WaitUtils waitUtils;

    @AndroidFindBy(accessibility = "Search")
    private WebElement searchIcon;

    @AndroidFindBy(id = "com.google.android.youtube:id/search_edit_text")
    private WebElement searchTextBox;

    @AndroidFindBy(accessibility = "Home")
    private WebElement homeButton;

    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id=\"com.google.android.youtube:id/image\"])[2]")
    private WebElement shortsButton;

    @AndroidFindBy(xpath = "//*[contains(@content-desc,'Subscriptions')]")
    private WebElement subscriptionsButton;

    @AndroidFindBy(accessibility = "You")
    private WebElement profileButton;

    @AndroidFindBy(accessibility = "Turn on Incognito")
    private WebElement turnonincognitoButton;

    @AndroidFindBy(id = "com.google.android.youtube:id/button")
    private WebElement turnoffincognitoButton;

    @AndroidFindBy(id = "com.google.android.youtube:id/message")
    private WebElement toastmessage;

    public HomePage(@Lazy AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(10)), this);
        this.waitUtils = new WaitUtils(driver, 50);
    }

    @Step("Click on Search Icon")
    public void clickSearch() {
        if (searchIcon != null) {
            waitUtils.waitForClickable(searchIcon);
            searchIcon.click();
        }
        System.out.println("Search icon clicked.");
    }

    @Step("Enter Search Text: {text}")
    public void enterSearchText(String text) {
        if (searchTextBox != null) {
            waitUtils.waitForVisibility(searchTextBox);
            searchTextBox.sendKeys(text);
            searchTextBox.clear();
            searchTextBox.sendKeys(text);
            System.out.println("Search text entered: " + text);
            driver.executeScript("mobile: performEditorAction", ImmutableMap.of("action", "search"));
            System.out.println("Search executed");
        }
    }

    @Step("Scroll Down")
    public void scrollDown() {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("left", 0);
            params.put("top", 400);
            params.put("width", 1080);
            params.put("height", 1200);
            params.put("direction", "down");
            params.put("percent", 0.75);
            driver.executeScript("mobile: scrollGesture", params);
        } catch (Exception e) {
            System.out.println("scrollDown fallback: " + e.getMessage());
            try {
                Map<String, Object> swipe = new HashMap<>();
                swipe.put("startX", 540);
                swipe.put("startY", 1200);
                swipe.put("endX", 540);
                swipe.put("endY", 400);
                swipe.put("duration", 800);
                driver.executeScript("mobile: swipeGesture", swipe);
            } catch (Exception ex) {
                System.out.println("swipe fallback failed: " + ex.getMessage());
            }
        }
    }

    @Step("Scroll Down Multiple Times: {count}")
    public void scrollMultipleTimes(int count) {
        for (int i = 0; i < count; i++) {
            scrollDown();
            try {
                Thread.sleep(700);
            } catch (InterruptedException ignored) {
            }
        }
    }

    @Step("Navigate to Home")
    public void goToHome() {
        if (homeButton != null) {
            waitUtils.waitForClickable(homeButton);
            homeButton.click();
            System.out.println("Navigated to Home.");
        }
    }

    @Step("Navigate to Shorts")
    public void goToShorts() {
        if (shortsButton != null) {
            waitUtils.waitForClickable(shortsButton);
            shortsButton.click();
            System.out.println("Navigated to Shorts.");
        }
    }

    @Step("Navigate to Subscriptions")
    public void goToSubscriptions() {
        if (subscriptionsButton != null) {
            waitUtils.waitForClickable(subscriptionsButton);
            subscriptionsButton.click();
            System.out.println("Navigated to Subscriptions.");
        }
    }

    @Step("Navigate to Profile")
    public void goToProfile() {
        if (profileButton != null) {
            waitUtils.waitForClickable(profileButton);
            profileButton.click();
            System.out.println("Navigated to Profile.");
        }
    }

    @Step("Turn on Incognito Mode")
    public void turnonIncognito() {
        if (turnonincognitoButton != null) {
            waitUtils.waitForClickable(turnonincognitoButton);
            turnonincognitoButton.click();
        }
    }

    @Step("Turn off Incognito Mode")
    public void turnoffIncognito() {
        if (turnoffincognitoButton != null) {
            waitUtils.waitForClickable(turnoffincognitoButton);
            turnoffincognitoButton.click();
        }
    }

    @Step("Close YouTube App")
    public void closeApp() throws InterruptedException {
        if (driver != null) {
            driver.terminateApp("com.google.android.youtube");
            System.out.println("App closed successfully.");
            Thread.sleep(4000); // wait for 4 seconds
        }
    }

    @Step("Relaunch YouTube App")
    public void relaunchApp() throws InterruptedException {
        if (driver != null) {
            driver.startActivity(new Activity("com.google.android.youtube", "com.google.android.youtube.HomeActivity"));
            System.out.println("App launched successfully.");
        }
    }

    @Step("Handle Toast Message")
    public String toastMessage(){
        if (toastmessage != null) {
            waitUtils.waitForVisibility(toastmessage);
        }
        String message = toastmessage.getText();
        return message;
    }

}

