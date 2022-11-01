package src;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppiumLaunchTest {
    public static void main(String[] args) throws InterruptedException {
        AppiumDriver<MobileElement> driver = null;

        //Desired caps
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName","android");
        desiredCapabilities.setCapability("automationName","uiautomator2");
        desiredCapabilities.setCapability("udid","emulator-5554");
        desiredCapabilities.setCapability("appPackage","com.wdiodemoapp");
        desiredCapabilities.setCapability("appActivity","com.wdiodemoapp.MainActivity");

        //Specify Appium Server URL
        URL appiumServer = null;

        try {
            appiumServer = new URL("http://localhost:4723/wd/hub");
        }catch (Exception e){
            e.printStackTrace();
        }

        if(appiumServer==null){
            throw new RuntimeException("[ERR] somehow, we couldnt construct Appium Server URL");
        }

        driver = new AndroidDriver<>(appiumServer, desiredCapabilities);

        //DEBUG PURPOSE ONLY

        Thread.sleep(3000);
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

    }
}
