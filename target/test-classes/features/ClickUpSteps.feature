Feature: ClickUp API test feature

  Scenario: Add new list to the test board after changing title
    Given I create 'My Folder' folder and verify name
    When I create new list 'My List' in Folder
    Then I verify list name 'My List' is correct
    Then I create unique task in list
    And I check that the task name is correct
    When I remove task from the list
    Then I check there is no tasks left in list