package Utils;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import io.appium.java_client.AppiumBy;

@Component
public class SwipeUtils {

    private final AndroidDriver driver;

    @Autowired
    public SwipeUtils(@Lazy AndroidDriver driver) {
        this.driver = driver;
    }

    public void scrollChannelRowForward() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" +
                        ".className(\"android.support.v7.widget.RecyclerView\").instance(0))" +
                        ".setAsHorizontalList()" +
                        ".scrollForward()"
        ));
    }

    public void scrollChannelRowBackward() {
        driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector()" +
                        ".className(\"android.support.v7.widget.RecyclerView\").instance(0))" +
                        ".setAsHorizontalList()" +
                        ".scrollBackward()"
        ));
    }

    public WebElement swipeUntilChannelVisible(String channelDescription) {
         return driver.findElement(AppiumBy.androidUIAutomator( "new UiScrollable(new UiSelector()" + ".className(\"android.support.v7.widget.RecyclerView\").instance(0))" + ".setAsHorizontalList()" + ".scrollIntoView(new UiSelector().description(\"" + channelDescription + "\"))" ));
    }

    }
