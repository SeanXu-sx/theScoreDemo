package theScoreDemo;

//Created by: Sean Xu
//This PrintToConsole contains toConsole() method that prints whatever the element the program is looking for currently
//and prints the method the program is using to find the element such as by id or by xpath. Finally it prints the result
//as true or false no matter the program is able to find the element or not.

public class PrintToConsole {

    public static void toConsole(String locator, String using) throws Exception {
        try {
            if (using.equalsIgnoreCase("id")) {
                while ((CommonFunctions.driver.findElementsById(locator)).size() == 0) {
                    System.out.println("Executing findElements parameters: " + locator + " using: " + using + " Result: " + false);
                }
                if ((CommonFunctions.driver.findElementsById(locator)).size() != 0) {
                    System.out.println("Executing findElements parameters: " + locator + " using: " + using + "  Result: " + true);
                }
            }
            else if (using.equalsIgnoreCase("xpath")) {
                while ((CommonFunctions.driver.findElementsByXPath(locator)).size() == 0) {
                    System.out.println("Executing findElements parameters: " + locator + " using: " + using + "  Result: " + false);
                }
                if ((CommonFunctions.driver.findElementsByXPath(locator)).size() != 0) {
                    System.out.println("Executing findElements parameters: " + locator + " using: " + using + "  Result: " + true);
                }
            }
            else if (using.equalsIgnoreCase("AndroidUIAutomator")) {
                while ((CommonFunctions.driver.findElementsByAndroidUIAutomator(locator)).size() == 0) {
                    System.out.println("Executing findElements parameters: " + locator + " using: " + using + "  Result: " + false);
                }
                if ((CommonFunctions.driver.findElementsByAndroidUIAutomator(locator)).size() != 0) {
                    System.out.println("Executing findElements parameters: " + locator + " using: " + using + "  Result: " + true);
                }
            }
            else if (using.equalsIgnoreCase("AccessibilityId")) {
                while ((CommonFunctions.driver.findElementsByAccessibilityId(locator)).size() == 0) {
                    System.out.println("Executing findElements parameters: " + locator + " using: " + using + "  Result: " + false);
                }
                if ((CommonFunctions.driver.findElementsByAccessibilityId(locator)).size() != 0) {
                    System.out.println("Executing findElements parameters: " + locator + " using: " + using + "  Result: " + true);
                }
            }
            else if (using.equalsIgnoreCase("className")) {
                while ((CommonFunctions.driver.findElementsByClassName(locator)).size() == 0) {
                    System.out.println("Executing findElements parameters: " + locator + " using: " + using + "  Result: " + false);
                }
                if ((CommonFunctions.driver.findElementsByClassName(locator)).size() != 0) {
                    System.out.println("Executing findElements parameters: " + locator + " using: " + using + "  Result: " + true);
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
