package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;

public class ShoppingCartPage extends BasePage{


    //WebElements


    @FindBy(xpath = "//button[@class='button']")
    public WebElement azurirajKorpuButton;

    @FindBy(xpath = "//div[@class='wc-proceed-to-checkout']")
    public WebElement nastaviPorudzbinuButton;

    @FindBy(xpath = "//a[@class='button wc-backward']")
    public WebElement povratakUProdavnicuButton;

    @FindBy(xpath = "//a[@data-product_id='44360']")
    WebElement removeZivotinjaFromCartButton;

    @FindBy(xpath = "//a[@data-product_id='162']")
    WebElement removeCrnaKnjigaFromCartButton;

    @FindBy(xpath = "//a[@data-product_id='5']")
    WebElement removeUMisoSupiFromCartButton;

    //Constructor
    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(Strings.SHOPPING_CART_PAGE_URL);
    }

    //Methods

    public void clickAzurirajKorpuButton() {
        assert isElementPresent(azurirajKorpuButton) : "Error. The eAzuriraj Korpu Button is not present on the page";
        print("Clicking Azuriraj korpu Button");
        azurirajKorpuButton.click();
    }

    public CheckoutPage clickNastaviNarudzbinuButton() {
        assert isElementPresent(nastaviPorudzbinuButton) : "Error. The Nastavi Porudzbinu Button is not present on the page";
        print("Clicking Nastavi Porudzbinu Button");
        nastaviPorudzbinuButton.click();
        return new CheckoutPage(driver);
    }

    public void clickPovratakUProdavnicuButton() {
        assert isElementPresent(povratakUProdavnicuButton) : "Error. The Povratak u Korpu button is not present on the page";
        print("Clicking the Povratak u Prodavnicu Button");
        povratakUProdavnicuButton.click();
    }

    public void removeZivotinjaFromCart(){
        assert isElementPresent(removeZivotinjaFromCartButton) : "Error. The element is not present on the page";
        print("Removing book Zivotinja from Shopping Cart");
        removeZivotinjaFromCartButton.click();
        sleep();

    }


}
