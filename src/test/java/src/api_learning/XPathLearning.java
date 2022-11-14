package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.util.List;

public class XPathLearning {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
        try{

            // Navigate to login screen
            MobileElement navLoginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginBtnElem.click();

            // find all matching elems | sharing baout hte trick with find Elems

            List<MobileElement> credFieldsElem = appiumDriver.findElements(MobileBy.xpath("//android.widget.EditText"));

            final int USER_INDEX = 0;
            final int PASSWORD_INDEX = 1;
            credFieldsElem.get(USER_INDEX).sendKeys("hoang@gmail.com");
            credFieldsElem.get(PASSWORD_INDEX).sendKeys("12345678");

            // find log in infor intext by IuSelector
            MobileElement loginInsructionElem = appiumDriver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"When the device\")"));
            System.out.println(loginInsructionElem.getText());
            //Debug purpose only

            Thread.sleep(3000);
    } catch (Exception e){
        e.printStackTrace();
        }
    }
}
