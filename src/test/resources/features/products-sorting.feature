Feature: Products Sorting
  As a customer
  I want to sort products by diferent criteria
  In order to easily find what i need


#  Scenario: Sort products by price
#    Given I open the homepage
#    And I search products by "vase"
##    And I search products by "camera"
##    coment-ul se face tot cu ctrl+/, dar se pune #
#    When I sort products by "Price" in descending order
#    Then all products are sorted by "Price" in descending order


  Scenario Outline: Sort products by different criteria
    Given I open the homepage
    And I search products by "vase"
#    And I search products by "camera"
#    coment-ul se face tot cu ctrl+/, dar se pune #
    When I sort products by "<sort criteria>" in <sort direction> order
    Then all products are sorted by "<sort criteria>" in <sort direction> order

    Examples:
      |sort criteria |sort direction |
      |Price         |descending     |
      |Price         |ascending      |
      |Name          |descending     |
      |Name          |ascending      |