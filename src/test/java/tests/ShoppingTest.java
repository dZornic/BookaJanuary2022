package tests;

import org.testng.annotations.Test;
import pages.*;

public class ShoppingTest extends BaseTest{


    /** Add item to Cart Test
     * 1. Navigate to https://www.booka.rs/?s=triler&post_type=product
     * 2. Click add to cart button on the bottom of the 'Zivotinja' book in the item list
     *
     * Expected result:
     * 3. verify that the number in the cart icon is '1'
     * 4. verify that the 'pogledaj korpu' button on the bottom of the Zivotinja book is present
      */

    @Test
    public void AddingItemToCart() {
        driver = openChromeDriver();
        try{
            SearchPage searchPage = searchItemUsingSearchBar();
            searchPage.addZivotinjaToCart();
            searchPage.sleep();
            String numberOfItemsInShoppingCart = searchPage.getNumberOnTheShoppingCartIcon ();
            assert numberOfItemsInShoppingCart.equals("1") : "Error. The number of items in the shopping cart mismatches " +
                    "the number on the shopping cart icon.";
            searchPage.checkTheShoppingCartButtonIsDisplayed();

        }finally {
            driver.quit();
        }
    }

    /** Add Item to and Remove it From Cart
     * 1. Navigate to https://www.booka.rs/?s=triler&post_type=product
     * 2. Click the 'add to cart' button on the item 'Zivotinja' in the item list
     * 3. Hover over the shopping cart icon and click on the 'trash bin' icon
     *
     * Expected result:
     * 4. Verify that the user is on the https://www.booka.rs/?s=triler&post_type=product&removed_item=1
     * 5. Verify that the message 'Proizvod “Životinja” je uklonjen. Poništi?' is displayed
     */
    @Test
    public void AddingItemToAndRemovingItFromTheCartFromTheDropDownMenu() {
        driver = openChromeDriver();
        try{
            SearchPage searchPage = searchItemUsingSearchBar();
            searchPage.addZivotinjaToCart();
            searchPage.removeItemFromCart();
            searchPage.sleep();
            assert isCurrentURLEqualTo(Strings.ITEM_REMOVED_FROM_CART_URL) : "Error.Wrong URL";
            searchPage.getItemRemovedFromCartText();
            String numberOfItemsInShoppingCart = searchPage.getNumberOnTheShoppingCartIcon ();
            assert numberOfItemsInShoppingCart.equals("0") : "Error. The number of items in the shopping cart mismatches " +
                    "the number on the shopping cart icon.";

        }finally {
            driver.quit();
        }
    }

    @Test
    public void AddingItemToTheShoppingCartAndOrderingItFromTheDropDownMenu() {
        driver = openChromeDriver();
        try{
            SearchPage searchPage = searchItemUsingSearchBar();
            searchPage.addZivotinjaToCart();
            CheckoutPage checkoutPage = searchPage.clickPoruciButtonFromCart();
            assert isCurrentURLEqualTo(Strings.CHECKOUT_PAGE_URL);
            checkoutPage.enterTextInFirstNameField(Strings.FIRST_NAME)
                        .enterTextInLastNameField(Strings.LAST_NAME)
                        .enterTextInAddressField(Strings.ADDRESS_ONE)
                        .enterTextInCityField(Strings.CITY)
                        .enterNumberInPostCodeField(Strings.POSTCODE)
                        .enterNumberInPhoneNumberField(Strings.PHONE_NUMBER)
                        .enterTextInEmailField(Strings.EMAIL_ADDRESS)
                        .clickCreateAccountCheckBox()
                        .enterTextInPasswordField(Strings.NEW_PASSWORD)
                        .clickPlacanjeUnapredRadioButton()
                        .clickTermsCheckBox();

        }finally {
            driver.quit();
        }
    }

    @Test
    public void AddingItemToWishListChangingItsQuantityAndAddingItToShoppingCart() {
        driver = openChromeDriver();
        print("Opening Chrome Browser");
        try{
            SearchPage searchPage = searchItemUsingSearchBar();
            ProductPage productPage = searchPage.chooseZivotinjaBook();
            assert isCurrentURLEqualTo(Strings.PRODUCT_PAGE_URL) : "Error. Wrong URL.";
            productPage.addItemToWishList()
                       .itemAddedToWishListTextIsDisplayed()
                       .clearThenEnterQuantityInQuantityField(Strings.QUANTITY)
                       .addItemToShoppingCart()
                       .getItemAddedToShoppingCartText();
            String numberOfItemsInAShoppingCart = productPage.getNewNumberOnTheShoppingCartIcon ();
            assert numberOfItemsInAShoppingCart.equals("2") : "Error. The number of items in the shopping cart mismatches " +
                    "the number on the shopping cart icon.";

        }finally {
            driver.quit();
        }
    }
}
