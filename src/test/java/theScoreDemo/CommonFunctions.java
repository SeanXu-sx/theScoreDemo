package theScoreDemo;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

//Created by: Sean Xu
//This CommonFunctions class contains initiation of the AndroidDriver, commonly used methods as such click, sendKeys, swipeOnScreen, etc.,
//those Selenium and Appium methods have been wrapped in corresponding methods for better re-usability.

public class CommonFunctions {

    public static AndroidDriver<AndroidElement> driver;


    public static void capabilityConfig() throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability("appPackage", "com.fivemobile.thescore");
        caps.setCapability("appActivity", "com.fivemobile.thescore.ui.MainActivity");
        caps.setCapability("noReset", "false");

        driver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);

    }

    public static void click(String locator, String using) throws Exception {
        try {
            PrintToConsole.toConsole(locator, using);
            if (using.equalsIgnoreCase("id")) {
                driver.findElementById(locator).click();
            }
            else if (using.equalsIgnoreCase("xpath")) {
                driver.findElementByXPath(locator).click();
            }
            else if (using.equalsIgnoreCase("AndroidUIAutomator")) {
                driver.findElementByAndroidUIAutomator(locator).click();
            }
            else if (using.equalsIgnoreCase("AccessibilityId")) {
                driver.findElementByAccessibilityId(locator).click();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void waitElement(String locator, String using) throws Exception {
        try {

            WebDriverWait wait = new WebDriverWait(driver, 60, 1);
            if (using.equalsIgnoreCase("id")) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));
            }
            else if (using.equalsIgnoreCase("xpath")) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
            }
            else if (using.equalsIgnoreCase("AccessibilityId")) {
                wait.until(ExpectedConditions.presenceOfElementLocated(new MobileBy.ByAccessibilityId(locator)));
            }
            else if (using.equalsIgnoreCase("AndroidUiAutomator")) {
                wait.until(ExpectedConditions.presenceOfElementLocated(new MobileBy.ByAndroidUIAutomator(locator)));
            }
            else if (using.equalsIgnoreCase("className")) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locator)));
            }
            PrintToConsole.toConsole(locator, using);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void clear(String locator, String using) throws Exception {
        try {
            PrintToConsole.toConsole(locator, using);
            if (using.equalsIgnoreCase("id")) {
                driver.findElementById(locator).clear();
            }
            else if (using.equalsIgnoreCase("xpath")) {
                driver.findElementByXPath(locator).clear();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendKeys(String locator, String key, String using) throws Exception {
        try {
            PrintToConsole.toConsole(locator, using);
            if (using.equalsIgnoreCase("id")) {
                driver.findElementById(locator).sendKeys(key);
            }
            else if (using.equalsIgnoreCase("xpath")) {
                driver.findElementByXPath(locator).sendKeys(key);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getText(String locator, String using) throws Exception {
        String text = "";
        try {
            PrintToConsole.toConsole(locator, using);
            if (using.equalsIgnoreCase("id")) {
                text = driver.findElementById(locator).getText();
            }
            else if (using.equalsIgnoreCase("xpath")) {
                text = driver.findElementByXPath(locator).getText();
            }
            else if (using.equalsIgnoreCase("AndroidUIAutomator")) {
                text = driver.findElementByAndroidUIAutomator(locator).getText();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return text;
    }

    public static boolean elementIsDisplayed(String locator, String using) throws Exception {

        try {

            if (using.equalsIgnoreCase("id")) {
                if ((driver.findElementsById(locator)).size() != 0) {
                    return true;
                }
            } else if (using.equalsIgnoreCase("xpath")) {
                if ((driver.findElementsByXPath(locator)).size() != 0) {
                    return true;
                }
            } else if (using.equalsIgnoreCase("AndroidUIAutomator")) {
                if ((driver.findElementsByAndroidUIAutomator(locator)).size() != 0) {
                    return true;
                }
            }
            else if (using.equalsIgnoreCase("className")) {
                if ((driver.findElementsByClassName(locator)).size() != 0) {
                    return true;
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static int[] getScreenSize() {
        int[] screen = new int[2];
        screen[0] = driver.manage().window().getSize().width;
        screen[1] = driver.manage().window().getSize().height;
        return screen;
    }

    public static void swipeByCoordinator(){
        try {
            System.out.println("//////////Swipe on the screen by coordinators//////////");
            int screenWidth = getScreenSize()[0];
            int screenHeight = getScreenSize()[1];
            Double scrollStartDouble = screenHeight * 0.8;
            Double scrollEndDouble = screenHeight * 0.65;
            int scrollStart = scrollStartDouble.intValue();
            int scrollEnd = scrollEndDouble.intValue();
            new TouchAction(driver).press(PointOption.point(120, scrollStart)).
                    waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1))).
                    moveTo(PointOption.point(120, scrollEnd)).
                    release().perform();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
