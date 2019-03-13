package org.fasttrackit;

import org.fasttrackit.pageobjects.Header;
import org.fasttrackit.pageobjects.ProductsGrid;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class CompareListTest extends TestBase{

    @Test
    public void addToCompareFromSearchResultTest(){

        String keyword = "vase";

        Header header = PageFactory.initElements(driver, Header.class);
        header.search(keyword);

        ProductsGrid productsGrid = PageFactory.initElements(driver, ProductsGrid.class);
        for (WebElement addToCompareLink : productsGrid.getAddToCompareLinks()) {
            addToCompareLink.click();
        }

            //store all products name in a  list of strings
            List<String> productNames = new ArrayList<>();
            for (WebElement productNameContainer : productsGrid.getProductNameContainers()){
                String name = productNameContainer.getText();
                productNames.add(name);
            }

            //press the Compare button

            //switch to the new window

            driver.switchTo();

            //ne asiguram ca apare mesajul de succes
        }
    }

