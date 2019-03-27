Feature:  Mini-cart

  Scenario: View produsct in minicart
    Given  I open the homepage
    And I search products by "vase"
    And I store the name of the 1st product with Add to Cart button
#    And i click on the 1st Add to Cart button
#    When I expand the minicart
    Then the previously stored product name is displayed in minicart