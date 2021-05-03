package StepDefinitions;

import io.appium.java_client.android.Activity;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import theScoreDemo.*;

import java.util.concurrent.TimeUnit;

//Created by: Sean Xu
//This StepDefinitions class contains implementation of test steps

public class StepDefinitions {

    SoftAssertions softAssertions = new SoftAssertions();

    @Given("I launch theScore application")
    public void i_launch_the_score_application() throws Exception {
        try {
            CommonFunctions.capabilityConfig();
            CommonFunctions.driver.startActivity(new Activity("com.fivemobile.thescore", "com.fivemobile.thescore.ui.MainActivity"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("I click on the start button")
    public void i_click_start_button() throws Exception {

        Thread.sleep(3000);
        softAssertions.assertThat(CommonFunctions.getText(PropertiesClass.getProperties("theScore.getStarted.button.id"), "id")).isEqualTo(PropertiesClass.getProperties("getStartedButton"));
        CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.getStarted.button.xpath"), "xpath");
        CommonFunctions.click(PropertiesClass.getProperties("theScore.getStarted.button.id"), "id");

    }

    @When("^I swipe on the screen to select the league \"(.*?)\"$")
    public void iSwipeOnScreenToSelectLeague(String league) throws Exception {

//        CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.selectLeague.continueButton.id"), "id");
        CommonFunctions.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.selectLeagueAndTeam.follow_icon.xpath"), "xpath");
        while (!CommonFunctions.elementIsDisplayed("new UiSelector().text(\"" + league + "\")", "androidUIAutomator")) {
            CommonFunctions.swipeByCoordinator();
        }
        if (CommonFunctions.elementIsDisplayed("new UiSelector().text(\"" + league + "\")", "androidUIAutomator")) {
            CommonFunctions.click("new UiSelector().text(\"" + league + "\")", "androidUIAutomator");
        }
    }

    @Then("^I handle the Tailored Content prompt if it shows up and click on \"(.*?)\"$")
    public void iHandleTailoredContentPrompt(String selection) throws Exception {

        //Wait all the elements appears
        CommonFunctions.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Verify if the Tailored Content prompt shows up, click on either "Allow Location" or "Maybe Later" to handle the prompt
        if (CommonFunctions.elementIsDisplayed(PropertiesClass.getProperties("theScore.prompt.tailoredContent.option.allowLocation.id"), "id")) {
            if (selection.equalsIgnoreCase("Allow Location")) {
                CommonFunctions.click(PropertiesClass.getProperties("theScore.prompt.tailoredContent.option.allowLocation.id"), "id");
            } else {
                CommonFunctions.click(PropertiesClass.getProperties("theScore.prompt.tailoredContent.option.maybeLater.id"), "id");
            }
        }
    }

    @When("^I input the team name \"(.*?)\" then select the team$")
    public void inputTeamNameThenSelect(String team) throws Exception {

        //Input the team name then select the team
        CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.selectLeague.continueButton.id"), "id");
        CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.selectLeagueAndTeam.follow_icon.xpath"), "xpath");
        CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.selectTeam.searchBar.id"), "id");
        CommonFunctions.click(PropertiesClass.getProperties("theScore.selectTeam.searchBar.id"), "id");
        CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.selectTeam.searchInput.id"), "id");
        CommonFunctions.clear(PropertiesClass.getProperties("theScore.selectTeam.searchInput.id"), "id");
        CommonFunctions.sendKeys(PropertiesClass.getProperties("theScore.selectTeam.searchInput.id"), team, "id");
        CommonFunctions.driver.hideKeyboard();
        while (!CommonFunctions.elementIsDisplayed("new UiSelector().resourceId(\"com.fivemobile.thescore:id/txt_name\").text(\"" + team + "\")",
                "androidUIAutomator")) {
            CommonFunctions.swipeByCoordinator();
        }
        if (CommonFunctions.elementIsDisplayed("new UiSelector().resourceId(\"com.fivemobile.thescore:id/txt_name\").text(\"" + team + "\")",
                "androidUIAutomator")) {
            CommonFunctions.click("new UiSelector().resourceId(\"com.fivemobile.thescore:id/txt_name\").text(\"" + team + "\")", "androidUIAutomator");
        }
    }

    @Then("^I verify my selections are correct before going to the next page then continue if correct \"(.*?)\" and \"(.*?)\"$")
    public void verifySelectionCorrectness(String league, String team) throws Exception {
        try {
            //Convert the league and team name to shorter name
            String leagueForShort;
            String teamForShort;
            if (league.trim().length() > 8) {
                leagueForShort = NameForShort.covertNameToShort(league);
            } else {
                leagueForShort = league;
            }
            if (team.trim().length() > 8) {
                teamForShort = NameForShort.covertNameToShort(team);
            } else {
                teamForShort = team;
            }

            //Verify league
            softAssertions.assertThat(CommonFunctions.getText("new UiSelector().resourceId(\"" +
                    PropertiesClass.getProperties("theScore.selectLeagueAndTeam.icon.text.resource_id") +
                    "\").text(\"" + leagueForShort.trim() + "\")", "AndroidUIAutomator")).as("League verification")
                    .isEqualTo(leagueForShort.trim());

            //Verify team
            softAssertions.assertThat(CommonFunctions.getText("new UiSelector().resourceId(\"" +
                    PropertiesClass.getProperties("theScore.selectLeagueAndTeam.icon.text.resource_id") +
                    "\").text(\"" + teamForShort.trim() + "\")", "AndroidUIAutomator")).as("Team verification")
                    .isEqualTo(teamForShort.trim());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Then("^I am on Favorites page verifying expected page open correctly \"(.*?)\" and \"(.*?)\"$")
    public void verifyExpectedPage(String league, String team) throws Exception {
        try {
            String teamForShort;
            if (team.trim().length() > 3) {
                teamForShort = NameForShort.covertNameToShort(team);
            } else
                teamForShort = team;
            CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.homePage.favorites.messageIcon.id"), "id");
            verifySelectionCorrectness(league, teamForShort);
            System.out.println("/////Everything selected as favorites are displaying correctly on Favorites home page/////");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @And("I click on action button")
    public void i_click_on_continue() throws Exception {

        CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.selectLeague.continueButton.id"), "id");
        CommonFunctions.click(PropertiesClass.getProperties("theScore.selectLeague.continueButton.id"), "id");

    }

    @And("^I click on sub-menu navigating to \"(.*?)\"$")
    public void clickOnSubMenu(String locator) throws Exception {

        CommonFunctions.waitElement(locator, "AccessibilityId");
        CommonFunctions.click(locator, "AccessibilityId");

    }

    @Then("^I verify if I am on the correct tab \"(.*?)\" and details and its page should correspond to selection previously made$")
    public void verifyTabAndDetails(String locator) throws Exception {

        try {
            //Verification of leagues header
            CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.leagues.detailsPage.header.id"), "id");
            softAssertions.assertThat(CommonFunctions.getText(PropertiesClass.getProperties("theScore.leagues.detailsPage.header.id"), "id"))
                    .as("Verification of leagues header")
                    .isEqualTo(locator.toUpperCase());

            //Verification of tabs under the header
            CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.leagues.detailsPage.tabUnderHeader.SCORES.AndroidUiAutomator"),
                    "AndroidUiAutomator");
            softAssertions.assertThat(CommonFunctions.getText(
                    PropertiesClass.getProperties("theScore.leagues.detailsPage.tabUnderHeader.SCORES.AndroidUiAutomator"),
                    "AndroidUiAutomator")).as("Verification of SCORES tab")
                    .isEqualTo(PropertiesClass.getProperties("SCORES"));

            softAssertions.assertThat(CommonFunctions.getText(
                    PropertiesClass.getProperties("theScore.leagues.detailsPage.tabUnderHeader.NEWS.AndroidUiAutomator"),
                    "AndroidUiAutomator")).as("Verification of NEWS tab")
                    .isEqualTo(PropertiesClass.getProperties("NEWS"));

            softAssertions.assertThat(CommonFunctions.getText(
                    PropertiesClass.getProperties("theScore.leagues.detailsPage.tabUnderHeader.CHAT.AndroidUiAutomator"),
                    "AndroidUiAutomator")).as("Verification of CHAT tab")
                    .isEqualTo(PropertiesClass.getProperties("CHAT"));

            softAssertions.assertThat(CommonFunctions.getText(
                    PropertiesClass.getProperties("theScore.leagues.detailsPage.tabUnderHeader.STANDINGS.AndroidUiAutomator"),
                    "AndroidUiAutomator")).as("Verification of STANDINGS tab")
                    .isEqualTo(PropertiesClass.getProperties("STANDINGS"));

            softAssertions.assertThat(CommonFunctions.getText(
                    PropertiesClass.getProperties("theScore.leagues.detailsPage.tabUnderHeader.LEADERS.AndroidUiAutomator"),
                    "AndroidUiAutomator")).as("Verification of LEADERS tab")
                    .isEqualTo(PropertiesClass.getProperties("LEADERS"));

            //Verifying dates text (today, yesterday, the day before yesterday, tomorrow, the day before tomorrow)
            CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.leagues.detailsPage.tabUnderHeader.date.today.AndroidUiAutomator"), "AndroidUiAutomator");
            softAssertions.assertThat(CommonFunctions.elementIsDisplayed(
                    PropertiesClass.getProperties("theScore.leagues.detailsPage.tabUnderHeader.date.today.AndroidUiAutomator"),
                    "AndroidUiAutomator"))
                    .as("Verifying TODAY text").isEqualTo(true);

            for (int i = 0; i < CalendarDate.getDates().length; i++) {
                softAssertions.assertThat(CommonFunctions.driver.findElementByAndroidUIAutomator("new UiSelector().text(\"" + CalendarDate.getDates()[i] + "\")").getText())
                .as("Verifying recent four days text")
                .isEqualTo(CalendarDate.getDates()[i]);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("I go back to the previous page")
    public void goBackToPreviousPage() throws Exception {
        CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.navigateBack.button.accessibilityId"), "AccessibilityId");
        CommonFunctions.click(PropertiesClass.getProperties("theScore.navigateBack.button.accessibilityId"), "AccessibilityId");
    }

    @Then("I verify if the back navigation returns me to the previous page correctly")
    public void verifyIfBackNavigationReturnsToPreviousPage() throws Exception {

        try {
            //Verify if the image icon at the top left appears
            CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.leagues.imageIcon.className"), "className");
            softAssertions.assertThat(CommonFunctions.elementIsDisplayed(PropertiesClass.getProperties("theScore.leagues.imageIcon.className"),
                    "className")).as("Verify if the image icon at the top left appears").isEqualTo(true);

            //Verify if Manage button at the top right appears
            CommonFunctions.waitElement(PropertiesClass.getProperties("theScore.leagues.manage.id"), "id");
            softAssertions.assertThat(CommonFunctions.elementIsDisplayed(PropertiesClass.getProperties("theScore.leagues.manage.id"), "id"))
                    .as("Verify if the Manage button at the top right appears").isEqualTo(true);
            softAssertions.assertAll();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Then("I close theScore application")
    public void i_close_the_score_application() {
        try {
            CommonFunctions.driver.closeApp();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
