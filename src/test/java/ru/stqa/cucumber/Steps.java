package ru.stqa.cucumber;
import io.cucumber.java8.En;

import static org.junit.Assert.assertTrue;

public class Steps implements En {

    private Application app = new Application();

    private int AmountOfProductsToAdd;
    private String ProductCategory;
    private int QuantityOfSpecificProduct;
    private String SizeOfSpecificProduct;

    public Steps() {
        Given("{string} are quantity of items that we are going to add", (String amount) -> {
            AmountOfProductsToAdd = Integer.valueOf(amount);
        });
        And("category from box of main page - {string}", (String category) -> {
            ProductCategory = category;
        });
        And("{string} is quantity and {string} is size of every item that will add to the cart", (String quantity, String size) -> {
            QuantityOfSpecificProduct = Integer.valueOf(quantity);
            SizeOfSpecificProduct = size;
        });
        When("^take and click a item on main page$", () -> {
            app.chooseProductByParametersMP(ProductCategory);
        });
        Then("item page redirects$", () -> {
            assertTrue(ProductPage.productPageOpened());
        });
        When("^take quantity and size of a item and click to AddToCart button on product page$", () -> {
            app.addProductToCart(QuantityOfSpecificProduct,SizeOfSpecificProduct);
        });
        Then("^item are put to the cart$", () -> {
            assertTrue(CartCurrentAmountOfItems.confirmAddingAProducts());
            AmountOfProductsToAdd--;
        });
        When("^put all the selected items to the cart$", () -> {
            while (AmountOfProductsToAdd>0){
                app.chooseProductByParametersMP(ProductCategory);
                app.addProductToCart(QuantityOfSpecificProduct,SizeOfSpecificProduct);
                AmountOfProductsToAdd--;
            }
            CartPage.open();
        });
        Then("^cart page redirects$", () -> {
            assertTrue(CartPage.cartPageOpened());
        });
        When("^remove all items from the cart step by step$", () -> {
            app.removeAllProductsFromTheCart();
        });
        Then("^the cart has no items$", () -> {
            assertTrue(CartPage.cartIsEmpty());
            app.closeApplication();
        });


    }
}
