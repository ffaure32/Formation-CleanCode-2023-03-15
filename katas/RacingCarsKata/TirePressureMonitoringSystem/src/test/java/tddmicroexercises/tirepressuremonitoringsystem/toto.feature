Feature: Returns go to stock

  In order to keep track of stock
  As a store owner
  I want to add items back to stock when they're returned

  Scenario: Refunded items should be returned to stock
    Given a customer previously bought a black sweater from me
    And I currently have 3 black sweaters left in stock
    When he returns 1 sweater for a refund
    Then I should have 4 black sweaters in stock