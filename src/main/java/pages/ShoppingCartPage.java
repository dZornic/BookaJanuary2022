package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCartPage extends BasePage{

    //WebElements

    //Constructor
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(Strings.SHOPPING_CART_PAGE_URL);
    }

    //Methods
}
