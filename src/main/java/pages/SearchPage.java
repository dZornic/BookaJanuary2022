package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends BasePage {


    //Webelements

    @FindBy(xpath = "//div[@class='loop-description']//p")
    WebElement searchResultText;

    @FindBy(xpath = "//div[@class='product-grid grid-layout columns-5']")
    WebElement allItems;

    @FindBy(xpath = "//h3[@title='Životinja']")
    WebElement bookZivotinja;

    @FindBy(xpath = "//div[@class='yith-wcwl-add-button']")
    WebElement heartButton;

    @FindBy(xpath = "//div[@class='yith-wcwl-wishlistaddedbrowse']/span[@class='feedback']")
    WebElement itemAddedToWishListText;

    @FindBy(xpath = "//div[@class = 'quantity']/input[@class = 'input-text qty text']")
    WebElement inputQuantityField;

    @FindBy(xpath = "//div[@class='add-to-cart']")
    WebElement addItemToCart;

    @FindBy(xpath = "//a[@data-product_id='44360']")
    WebElement addBookZivotinjaToCart;

    @FindBy(xpath = "//a[@class='added_to_cart wc-forward']")
    WebElement checkTheShoppingCartButton;

    @FindBy(xpath = "//button[@class='menu-cart-trigger']")
    WebElement shoppingCartButton;

    @FindBy(xpath = "//span[@class='cart-count']")
    WebElement numberOnTheShoppingCartIcon;

    @FindBy(xpath = "//div[@class='widget woocommerce widget_shopping_cart']")
    WebElement shoppingCartDropDownMenu;

    @FindBy(xpath = "//li[@class='mini_cart_item']/a[@class='remove']")
    WebElement removeItemFromCartButton;

    @FindBy(xpath = "//div[@class='widget woocommerce widget_shopping_cart']/div[@class='widget_shopping_cart_content']/p[@class='buttons']/a[text()='Poruči']")
    WebElement poruciButton;

    @FindBy(xpath = "//div[@class='woocommerce-message']")
    WebElement itemRemovedFromCartTextMessage;

    @FindBy(xpath = "//select[@class='orderby']")
    WebElement dropDownSortByMenu;

    @FindBy(xpath = "//select[@class='orderby']//option[text()='A do Ž']")
    WebElement dropDownSortAlphabeticallyButton;


    //Constructor

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Wrapper Methods

    public void clickDropDownMenu() {
        assert isElementPresent(dropDownSortByMenu) : "Error. The Drop Down Sort By Menu is not present";
        print("Clicking on 'Dropdown' menu");
        dropDownSortByMenu.click();
    }

    public void clickSortAlphabeticallyButton() {
        assert isElementPresent(dropDownSortAlphabeticallyButton) : "Error. The element is not present";
        print("Clicking on 'SortAlphabetically' Button");
        dropDownSortAlphabeticallyButton.click();
    }

    public void searchResultTextIsPresent() {
        assert isElementPresent(searchResultText) : "Error. The element is not present.";
    }

    public String getSearchResultText() {
        String actualSearchResultText = searchResultText.getText();
        print("Message is: " + actualSearchResultText);
        return actualSearchResultText;
    }

    public ProductPage chooseZivotinjaBook() {
        isElementPresent(bookZivotinja);
        print("Clicking on Zivotinja book");
        bookZivotinja.click();
        sleep();
        return new ProductPage(driver);
    }

    public void addZivotinjaToCart() {
        Actions actions = new Actions(driver);
        actions.moveToElement(bookZivotinja);
        actions.moveToElement(addBookZivotinjaToCart);
        actions.click().build().perform();
        print("Adding a book named 'Zivotinja' to the shopping cart.");
        sleep();
    }

    public String getNumberOnTheShoppingCartIcon() {
        print("Getting the Number on the Shopping Cart Icon.");
        return numberOnTheShoppingCartIcon.getText();
    }

    public void checkTheShoppingCartButtonIsDisplayed(){
        isElementPresent(checkTheShoppingCartButton);
        print("Shopping cart button is present");
    }

    //todo ova metoda treba da vraca shopping cart page
    public ShoppingCartPage clickCheckTheShoppingCartButton() {
        checkTheShoppingCartButtonIsDisplayed();
        checkTheShoppingCartButton.click();
        return new ShoppingCartPage(driver);
    }

    public void removeItemFromCart() {
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCartButton).perform();
        print("Mouse hovers over shopping cart icon");
        actions.moveToElement(shoppingCartDropDownMenu).perform();
        print("Mouse hovers over the Shopping Cart DropDown Menu");
        actions.moveToElement(removeItemFromCartButton).click();
        removeItemFromCartButton.click();
        sleep();
    }

    public String getItemRemovedFromCartText() {
        String actualText = itemRemovedFromCartTextMessage.getText();
        print("Message is: " + actualText);
        return actualText;
    }

    public CheckoutPage clickPoruciButtonFromCart(){
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCartButton).perform();
        print("Mouse hovers over shopping cart icon");
        actions.moveToElement(shoppingCartDropDownMenu).perform();
        print("Mouse hovers over the Shopping Cart DropDown Menu");
        actions.moveToElement(poruciButton).click();
        poruciButton.click();
        sleep();
        return new CheckoutPage(driver);
    }

    public List<WebElement> getAllItems() {
        List<WebElement> items = driver.findElements(By.className("product-grid grid-layout columns-5"));
        System.out.println(items.size());
        for (WebElement item : items) {
            String currentName = item.findElement(By.xpath(".//h3[@class = 'product__title']")).getText();
            System.out.println(items.indexOf(item) + "." + currentName);
        }
        return items;
    }

    public void searchAllProduct() {

        ArrayList<String> sortedArray = new ArrayList<>();
        //Stampa posle sortiranja
        List<WebElement> sortedList = driver.findElements(By.xpath("//div[@class='block products-listing product product-listing-items ']"));
        for (WebElement e : sortedList) {
            String itemName = e.findElement(By.xpath("//div[@class='product-shortname']")).getText();
            sortedArray.add(itemName);
            String price = e.findElement(By.xpath("//div[@class='prices-wrapper']")).getText();
            System.out.println("Cena za  " + itemName + " je " + price);
        }
    }
}