package org.fasttrackit.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fasttrackit.TestBase;
import org.fasttrackit.pageobjects.ProductsGrid;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

public class ProductsGridSteps extends TestBase {

    private ProductsGrid productsGrid =
            PageFactory.initElements(driver, ProductsGrid.class);

    @When("^I sort products by \"([^\"]*)\" in (.+) order$")
    public void iSortProductsByInAscendingOrder(String sortCriteria, String sortDirection) {
        productsGrid.getSortBySelectList().selectByVisibleText(sortCriteria);

        String sortDirectionButtonClass = productsGrid.getSortDirectionButton().getAttribute("class");


//        not very clear...maybe not the best implementation
        if (
                (
                        sortDirectionButtonClass.contains("--asc") &&
                                sortDirection.equals("descending")
                ) ||
                        (sortDirectionButtonClass.contains("--desc") &&
                                sortDirection.equals("ascending")
                        )
        ) {
            productsGrid.getSortDirectionButton().click();

        }
    }

    @Then("^all products are sorted by \"([^\"]*)\" in (.+) order$")
    public void allProductsAreSortedByInDescendingOrder(String sortCriteria, String sortDirection) {

        Comparator comparator;

        if (sortDirection.equalsIgnoreCase("ascending")) {
            comparator = Comparator.naturalOrder();
        } else {
            comparator = Comparator.reverseOrder();
        }

        //Sortare dupa pret
        if (sortCriteria.equalsIgnoreCase("Price")) {
            assertCorectSortByPrice(comparator);
        } else if (sortCriteria.equalsIgnoreCase("Name")) {
            assertCorrectSortByName(comparator);
        } else {
            throw new PendingException("Assertion for sort by " + sortCriteria + " not implemented");
        }
    }

    private void assertCorrectSortByName(Comparator comparator) {
        List<String> names = productsGrid.getProductNames();
        List<String> sortedNames = new ArrayList<>(names);
        sortedNames.sort(comparator);
        assertThat("Product are not sorted corectly.", names, equalTo(sortedNames));
    }

    private void assertCorectSortByPrice(Comparator comparator) {
        List<Double> prices = productsGrid.getActualProductPricesAsDoubles();
        List<Double> sortedPrices = new ArrayList<>(prices);
        sortedPrices.sort(comparator);
        assertThat("Product are not sorted corectly.", prices, equalTo(sortedPrices));
    }


    @And("^I store the name of the (\\d+)(?:.+?) product with Add to Cart button$")
    public void iStoreTheNameOfTheStProductWithAddToCartButton(int productNumber) {
        List<WebElement> nameContainers =
                productsGrid.getAddToCartProductNameContainers();

        assertThat("Not enough elements displayed.",
                nameContainers.size(), greaterThanOrEqualTo(productNumber));

        String productName = nameContainers.get(productNumber - 1).getText();

        getStepVariables().put("addToCartProductName",productName);

    }
}
