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

    /** Add Item to and Remove it From Cart Test
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

    /**
     * Adding item to the shopping cart and ordering it from the dropdown menu Test
     * 1. Navigate to https://www.booka.rs/?s=triler&post_type=product
     * 2. Hover over 'Zivvotinja' book
     * 3. Click on the 'bag icon' that appears over the book
     * 6. Hover over the shopping cart icon
     * 8. Click on 'poruci' button on the dropdown menu.
     * 11. Enter Valid First Name in the FirstName field
     * 12. Enter Valid Last Name in the LastName field
     * 13. Enter valid address and number in the AddressAndNumber field
     * 14. Enter valid name of the city in the City field
     * 14. Enter valid postal code number in the PostalCode field
     * 15. Enter valid phone number in the Phone Number field
     * 16. Enter valid email address in the Email field.
     * 17. Check the 'placanje unapred' on the form right to the 'billing form'
     * 19. Check the 'terms and conditions' checkbox under 'placanje unapred'
     *
     * expected result:
     * 4. Verify that the 'Pogledaj korpu' button has appeared
     * 5. Verify that the number on the shopping cart icon in the top right has changed to 1.
     * 7. Verify that the dropdown menu has appeared.
     * 9. Verify that the user is on the checkout page 'https://www.booka.rs/provera/'
     */

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

    /**Adding item to WishList, changing the quantity to 2 and adding those 2 items to shopping cart
     * 1. Navigate to https://www.booka.rs/knjige/savremena-knjizevnost/zivotinja/
     * 2. Click on the heart shaped button right to the book
     * 4. Click on the Quantity field above the heart shaped button
     * 5. Clear the number from the Quantity field
     * 6. Enter number 2 in the Quantity field.
     * 7. Click on the 'Dodaj u korpu' button right to the Quantity field
     *
     *
     * expected result:
     * 3. Verify that 'Proizvod je dodat! Pogledaj listu želja' message is displayed
     * 8. Verify that the number on the shopping cart icon is 2.
     */
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
