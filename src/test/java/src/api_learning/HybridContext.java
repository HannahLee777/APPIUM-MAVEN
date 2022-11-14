package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.contexts.Contexts;
import src.contexts.WaitMoreThanOneContext;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.util.ArrayList;
import java.util.List;

public class HybridContext {
    public static void main(String[] args) {

        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ANDROID);
         try{

             //navigate to webview
             appiumDriver.findElement(MobileBy.AccessibilityId("Webview")).click();

             //Wait for more than one contex
             WebDriverWait wait = new WebDriverWait(appiumDriver, 5L);
             wait.until(new WaitMoreThanOneContext(appiumDriver));

             // print all available contexts
             for (String contextHandle : appiumDriver.getContextHandles()) {
                 System.out.println("Context" +contextHandle);

             //switch Webview context
             appiumDriver.context(Contexts.WEB_VIEW);
             WebElement navToggleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
             navToggleBtnElem.click();
             List<MobileElement> menuItemsElem = appiumDriver.findElementsByCssSelector(".menu__list li a");
             if(menuItemsElem.isEmpty())
                 throw new RuntimeException("[ERR] There is no list items to test");

             List<MenuItemData> menuItemDataList = new ArrayList<>();

             for(MobileElement menuItemElem : menuItemsElem){
                 String itemText = menuItemElem.getText();
                 String itemHref = menuItemElem.getAttribute("href");
                 if(itemText.isEmpty()){
                     menuItemDataList.add(new MenuItemData("Github", itemHref));
                     //old: System.out.println("Github: " +itemHref);
                 } else {
                     menuItemDataList.add(new MenuItemData(itemText, itemHref));
                     //old: System.out.println(itemText.concat(": ") +itemHref);
                 }
             }

             List<MenuItemData> expectedMenuItemList = new ArrayList<>();
             System.out.println(menuItemDataList);



             // switch back to native context

            appiumDriver.context(Contexts.NATIVE);
            appiumDriver.findElement(MobileBy.AccessibilityId("Home")).click();

            // debug purpose only
                 Thread.sleep(3000);
             }
         } catch (Exception e) {
             e.printStackTrace();
         }
         appiumDriver.quit();
    }

    public static class MenuItemData {
        private final String name;
        private final String href;

        public MenuItemData(String name, String href) {
            this.name = name;
            this.href = href;
        }

        public String getName() {
            return name;
        }

        public String getHref() {
            return href;
        }

        @Override
        public String toString() {
            return "MenuItemData{" +
                    "name='" + name + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }
}
