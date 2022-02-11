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
import java.util.Collections;
import java.util.List;

public class SearchPage extends BasePage {


    //Webelements

    @FindBy(xpath = "//div[@class='loop-description']//p")
    public WebElement searchResultText;



    @FindBy(xpath = "//h3[@title='Životinja']")
    WebElement bookZivotinja;

    @FindBy(xpath = "//h3[@title='Crna knjiga']")
    WebElement bookCrnaKnjiga;

    @FindBy(xpath = "//h3[@title='U miso supi']")
    WebElement bookUMisoSupi;

    @FindBy(xpath = "//div[@class='yith-wcwl-add-button']")
    WebElement heartButton;

    @FindBy(xpath = "//div[@class='yith-wcwl-wishlistaddedbrowse']/span[@class='feedback']")
    public WebElement itemAddedToWishListText;

    @FindBy(xpath = "//div[@class = 'quantity']/input[@class = 'input-text qty text']")
    WebElement inputQuantityField;

    @FindBy(xpath = "//div[@class='add-to-cart']")
    WebElement addItemToCart;

    @FindBy(xpath = "//a[@data-product_id='44360']")
    WebElement addBookZivotinjaToCart;

    @FindBy(xpath = "//a[@data-product_id='162']")
    WebElement addBookCrnaKnjigaToCart;

    @FindBy(xpath = "//a[@data-product_id='5']")
    WebElement addBookUMisoSupiToCart;

    @FindBy(xpath = "//a[@class='added_to_cart wc-forward']")
    public WebElement checkTheShoppingCartButton;

    @FindBy(xpath = "//button[@class='menu-cart-trigger']")
    WebElement shoppingCartButton;

    @FindBy(xpath = "//span[@class='cart-count']")
    public WebElement numberOnTheShoppingCartIcon;

    @FindBy(xpath = "//div[@class='widget woocommerce widget_shopping_cart']")
    WebElement shoppingCartDropDownMenu;

    @FindBy(xpath = "//li[@class='mini_cart_item']/a[@class='remove']")
    WebElement removeItemFromCartButton;

    @FindBy(xpath = "//div[@class='widget woocommerce widget_shopping_cart']/div[@class='widget_shopping_cart_content']/p[@class='buttons']/a[text()='Pogledaj korpu']")
    public WebElement pogledajKorpuButton;

    @FindBy(xpath = "//div[@class='widget woocommerce widget_shopping_cart']/div[@class='widget_shopping_cart_content']/p[@class='buttons']/a[text()='Poruči']")
    WebElement poruciButton;

    @FindBy(xpath = "//div[@class='woocommerce-message']")
    public WebElement itemRemovedFromCartTextMessage;

    @FindBy(xpath = "//select[@class='orderby']")
    WebElement dropDownSortByMenu;

//    @FindBy(xpath = "//select[@class='orderby']//option[text()='A do Ž']")
//    WebElement dropDownSortAlphabeticallyButton;


    //Constructor

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //Wrapper Methods
    public void clickSortAlphabeticallyButtonFromDropDownMenu() {
        assert isElementPresent(dropDownSortByMenu) : "Error. The Drop Down Sort By Menu is not present";
        Select dropDown = new Select(driver.findElement(By.className("orderby")));
        dropDown.selectByVisibleText("A do Ž");
    }

    public void searchResultTextIsPresent() {
        assert isElementPresent(searchResultText) : "Error. The element is not present.";
        assert searchResultText.getText().equals("Pretražujete rezultate za \"triler\"") : "Error. The search result is incorrect";
    }

    public String getSearchResultText() {
        String actualSearchResultText = searchResultText.getText();
        print("Message is: " + actualSearchResultText);
        return actualSearchResultText;
    }

    public ProductPage chooseZivotinjaBook() {
        assert isElementPresent(bookZivotinja): "Error. The element is not present on the page";
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

    public void addCrnaKnjigaToCart() {
        Actions actions = new Actions(driver);
        actions.moveToElement(bookCrnaKnjiga);
        actions.moveToElement(addBookCrnaKnjigaToCart);
        actions.click().build().perform();
        print("Adding a book named 'Crna Knjiga' to the shopping cart.");
        sleep();
    }

    public void addUMisoSupiToCart() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,600)");
        sleep();
        Actions actions = new Actions(driver);
        actions.moveToElement(bookUMisoSupi);
        actions.moveToElement(addBookUMisoSupiToCart);
        actions.click().build().perform();
        print("Adding a book named 'U Miso Supi' to the shopping cart.");
        sleep();
    }

    public String getNumberOnTheShoppingCartIcon() {
        print("Getting the Number on the Shopping Cart Icon.");
        return numberOnTheShoppingCartIcon.getText();
    }

    public void checkTheShoppingCartButtonIsDisplayed() {
        assert isElementPresent(checkTheShoppingCartButton) : "Error. The element is not present on the page";
        print("Shopping cart button is present");
    }


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

    public ShoppingCartPage clickPogledajKorpuButtonFromCart() {
        Actions actions = new Actions(driver);
        actions.moveToElement(shoppingCartButton).perform();
        print("Mouse hovers over shopping cart icon");
        actions.moveToElement(shoppingCartDropDownMenu).perform();
        print("Mouse hovers over the Shopping Cart DropDown Menu");
        actions.moveToElement(pogledajKorpuButton).click();
        print("Clicking pogledaj korpu Button");
        pogledajKorpuButton.click();
        sleep();
        return new ShoppingCartPage(driver);
    }

    public CheckoutPage clickPoruciButtonFromCart() {
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

    public List<WebElement> getUnsortedList() {
        List<WebElement> unsortedList = driver.findElements(By.className("product__inner"));
        for (WebElement e : unsortedList) {
            String bookTitle = e.findElement(By.className("product__title")).getText();
            System.out.println("Book title is " + bookTitle);
        }
        return unsortedList;
    }

    public List<WebElement> getSortedList() {
        List<WebElement> sortedList = driver.findElements(By.className("product__inner"));
        for (WebElement e : sortedList) {
            String bookTitle = e.findElement(By.className("product__title")).getText();
            System.out.println("Book title is " + bookTitle);
        }
        return sortedList;
    }


}
