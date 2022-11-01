package src;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppiumLaunchTest {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> driver = null;

        //Desired caps
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName","android");
        desiredCapabilities.setCapability("platformName","uiautomator2");
        desiredCapabilities.setCapability("udid","emulator-5554");
        desiredCapabilities.setCapability("appPackage","com.wdiodemoapp");
        desiredCapabilities.setCapability("appActivity","com.wdiodemoapp.MainActivity");

        //Specify Appium Server URL
        URL appiumServer = null;

        try {
            appiumServer = new URL("http://localhost:4725/wd/hub/session");
        }catch (Exception e){
            e.printStackTrace();
        }

        if(appiumServer==null){
            throw new RuntimeException("[ERR] somwhow, we couldnt constrcut Appium Server URL");
        }

        driver = new AndroidDriver<>(appiumServer, desiredCapabilities);

        //DEBUG PURPOSE ONLY
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
