package Utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;

public class AppiumServerManager {

    private static AppiumDriverLocalService service;

    public static void startServer() {

        if (service == null || !service.isRunning()) {

            AppiumServiceBuilder builder = new AppiumServiceBuilder()
//                    .withNodeExecutable(new File(
//                            "C:\\Program Files\\nodejs\\node.exe"
//                    ))
//
//                    // Appium main.js (MANDATORY)
                    .withAppiumJS(new File(
                            "C:\\Users\\sdodda\\AppData\\Roaming\\npm\\appium.cmd"
                    ))

                    .withIPAddress("127.0.0.1")
                    .usingPort(4723)
                    .withArgument(GeneralServerFlag.LOG_LEVEL, "info");

            service = AppiumDriverLocalService.buildService(builder);
            service.start();

            System.out.println("Appium Server Started Successfully");
        }
    }

    public static void stopServer() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("Appium Server Stopped");
        }
    }
}
