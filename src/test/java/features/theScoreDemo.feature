Feature: theScore Demo - Verifications of elements and steps

  Scenario Outline: Verification of Steps
    Given I launch theScore application
    And I click on the start button
    When I swipe on the screen to select the league "<league>"
    And I click on action button
    Then I handle the Tailored Content prompt if it shows up and click on "<tailoredContentSelection>"
    When I input the team name "<team>" then select the team
    Then I verify my selections are correct before going to the next page then continue if correct "<league>" and "<team>"
    And I click on action button
    And I click on action button
    Then I am on Favorites page verifying expected page open correctly "<league>" and "<team>"
    And I click on sub-menu navigating to "<subMenu>"
    When I swipe on the screen to select the league "<league>"
    Then I verify if I am on the correct tab "<league>" and details and its page should correspond to selection previously made
    When I go back to the previous page
    Then I verify if the back navigation returns me to the previous page correctly
    And I close theScore application
    Examples:
    |league       |tailoredContentSelection|team   |subMenu|
    |NHL Hockey   |Maybe Later             |Chelsea|Leagues|
#Note: change is required in NameForShort.java if new team or league is the new parameters