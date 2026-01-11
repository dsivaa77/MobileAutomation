package Config;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.net.URL;
import java.time.Duration;

@Configuration
public class MobileDriverConfig {
    @Bean
    @Lazy
    public AndroidDriver driver() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();

        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("Xiaomi 11 Lite NE");
        options.setUdid("7ec026aa");
        options.setPlatformVersion("14");
        options.setNewCommandTimeout(Duration.ofSeconds(300));
        options.setAdbExecTimeout(Duration.ofSeconds(60000));
        options.setUiautomator2ServerInstallTimeout(Duration.ofSeconds(60000));
        options.setUiautomator2ServerLaunchTimeout(Duration.ofSeconds(60000));
        options.setDisableWindowAnimation(true);
        options.setAppPackage("com.google.android.youtube");
        options.setAppActivity("com.google.android.youtube.HomeActivity");
        options.setNoReset(true);
        options.setNewCommandTimeout(java.time.Duration.ofSeconds(300));

         return new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
    }
}

