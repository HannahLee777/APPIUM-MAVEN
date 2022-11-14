package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.internal.CapabilityHelpers;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.io.File;

public class TakingScreenshot {
    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver((Platform.ANDROID));

        try{

            //navigate to login screen
            MobileElement navLoginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginBtnElem.click();

            //whole screen
            File base64ScreenshotData = appiumDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginScreen.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

            //An Area
            MobileElement loginFormElem = appiumDriver.findElement((MobileBy.AccessibilityId("Login-screen")));
            File base64LoginFormData = loginFormElem.getScreenshotAs(OutputType.FILE);
            String fileLoginFormLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginForm.png");
            FileUtils.copyFile(base64LoginFormData, new File(fileLoginFormLocation));


            //An element
            MobileElement loginBtnElem = appiumDriver.findElement((MobileBy.AccessibilityId("button-LOGIN")));
            File base64LoginBtnData = loginBtnElem.getScreenshotAs(OutputType.FILE);
            String fileLoginBtnLocation = System.getProperty("user.dir").concat("/screenshots/").concat("LoginBtn.png");
            FileUtils.copyFile(base64LoginBtnData, new File(fileLoginBtnLocation));

            //Detect platform
            Capabilities caps = appiumDriver.getCapabilities();
            String platform = CapabilityHelpers.getCapability(caps, "platformName", String.class);
            System.out.println("Current platform "+platform);

        }catch (Exception e){
            e.printStackTrace();

        }
        appiumDriver.quit();
    }
}