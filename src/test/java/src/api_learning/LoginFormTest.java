package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.sql.SQLOutput;

public class LoginFormTest {

    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);

        try {
            //Navigate to login Form
            MobileElement navLoginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginBtnElem.click();

            // Find login form elements
            MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            //Interact with login form | fill username and password
            emailInputElem.sendKeys("hoang@gmail.com");
            passwordInputElem.sendKeys("12345678");
            loginBtnElem.click();

            //Verification | Login Dialog
            WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
            wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy.id("android:id/alertTitle")));

            MobileElement loginDialogTitleElem = appiumDriver.findElement((MobileBy.id("android:id/alertTitle")));
            System.out.println("Dialog title: " + loginDialogTitleElem.getText());
            //Print the dialog content

            //Debug purpose ONLY
            Thread.sleep(3000);

        }
        catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();

    }
}
