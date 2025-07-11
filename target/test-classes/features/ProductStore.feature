Feature: Work on product store website

  @QA1
  Scenario Outline: Product store
    Given User opens the website
    When User clicks on "Monitors"
    And User is able to see "Apple monitor 24"
    When User clicks on "Apple monitor 24"
    And User is able to see "LED Cinema Display features"
    When User clicks on "Add to cart"
    Then User accepts the alert button
