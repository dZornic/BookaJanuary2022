package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemRemovedFromShoppingCartPage extends BasePage {

    //WebElements

    @FindBy(xpath = "//div[@class='woocommerce-message']")
    WebElement itemRemovedFromCartTextMessage;


    // Constructor
    public ItemRemovedFromShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(Strings.ITEM_REMOVED_FROM_CART_URL);
    }

    //Methods

    public String getItemRemovedFromCartText() {
        String actualText = itemRemovedFromCartTextMessage.getText();
        print("Message is: " + actualText);
        return actualText;

    }
}
