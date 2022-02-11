package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {
    //Webelements

    @FindBy(xpath = "//div[@class='yith-wcwl-add-button']")
    WebElement heartButton;

    @FindBy(xpath = "//div[@class='yith-wcwl-wishlistaddedbrowse']")
    public WebElement itemAddedToWishListText;

    @FindBy(xpath = "//div[@class = 'quantity']/input[@class = 'input-text qty text']")
    WebElement inputQuantityField;

    @FindBy(xpath = "//button[@class='single_add_to_cart_button button alt']")
    WebElement addItemToCart;

    @FindBy(xpath = "//div[@class ='woocommerce-message']")
    public WebElement itemAddedToShoppingCart;

    @FindBy(xpath = "//span[@class='cart-count']")
    WebElement getItemCountOnTheShoppingCartIcon;


    //Constructor

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(Strings.PRODUCT_PAGE_URL);
    }

    //Wrapper Methods

    public ProductPage addItemToWishList() {
        assert isElementPresent(heartButton) : "Error. The element is not present on the page";
        print("Adding Zivotinja Book to Wish List");
        heartButton.click();
        sleep();
        return this;
    }

    public ProductPage itemAddedToWishListTextIsDisplayed() {
        assert isElementPresent(itemAddedToWishListText) : "Error. The element is not present on the page";
        print("Item is added to your wish list");
        return this;
    }

    public ProductPage clearThenEnterQuantityInQuantityField(String text) {
        assert isElementPresent(inputQuantityField) : "Error. The element is not present on the page";
        print("Inputting " + text + " in quantity field");
        inputQuantityField.click();
        inputQuantityField.clear();
        inputQuantityField.sendKeys(text);
        sleep();
        return this;
    }

    public ProductPage addItemToShoppingCart() {
        assert isElementPresent(addItemToCart) : "Error. The element is not present on the page";
        print("Adding item to shopping cart");
        addItemToCart.click();
        sleep();
        return this;

    }
    public String getItemAddedToShoppingCartText() {
        String actualText = itemAddedToShoppingCart.getText();
        print("Message is: " + actualText);
        return itemAddedToShoppingCart.getText();
    }
    public String getNewNumberOnTheShoppingCartIcon() {
        print("Getting the Number on the Shopping Cart Icon.");
        return getItemCountOnTheShoppingCartIcon.getText();
    }
}
