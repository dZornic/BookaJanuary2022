package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BasePage {

    WebDriver driver = null;

    @FindBy(xpath = "//a[@class ='open-login-popup']")
    WebElement prijaviSeLink;

    @FindBy(id = "product-search-keyword")
    WebElement searchBar;

    @FindBy(xpath =" //button[@class='search-icon']")
    WebElement searchButton;

    @FindBy(xpath = "*//i[@class='fab-facebook-f']")
    public WebElement connectToFacebookButton;

    @FindBy(xpath = "*//i[@class='fa-instagram']")
    public WebElement connectToInstagramButton;

    @FindBy(xpath = "//p[@class='woocommerce-info']")
    public WebElement nonExistantKeyWordText;



    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

     public BasePage() {

    }

    public void clickPrijaviSeLink() {
        assert isElementPresent(prijaviSeLink) : "Error. PrijaviSe Link is not present";
        print("Clicking PrijaviSe Link");
        prijaviSeLink.click();
    }

    public void enterTextInSearchBar(String text){
        assert isElementPresent(searchBar) : "Error. Search Bar is not present";
        searchBar.click();
        print("Entering: " + text + " in search bar");
        searchBar.sendKeys(text);
    }

    public SearchPage clickSearchButton(){
        assert isElementPresent(searchButton) : "Error. Search Button is not present";
        print("Clicking search button");
        searchButton.click();
        return new SearchPage(driver);
    }

    public void clickSearchButtonInTheSearchBar(){
        assert isElementPresent(searchButton) : "Error. Search Button is not present";
        print("Clicking search button");
        searchButton.click();

    }

    public void print(String text) {
        System.out.println(text);
    }

    public void sleep() {
        try {
            Thread.sleep(3000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public String getNonExistantKeyWordSearchResultText(){
        String actualText = nonExistantKeyWordText.getText();
        print("Message is: " + actualText);
        return actualText;
    }



    public void redirectToFacebook() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", connectToFacebookButton);
        sleep();
        assert isElementPresent(connectToFacebookButton) : "Error. The Facebook Redirect Button is not present on the page";
        print("The facebook redirect button is present");
        connectToFacebookButton.click();
        List<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        sleep ();
        assert isCurrentURLEqualTo(Strings.FACEBOOK_BOOKA_PAGE) : "Error. The user is not redirected to Booka Facebook Page";
        driver.close();
        driver.switchTo().window(tabs.get(0));

    }

    public void redirectToInstagram() {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();", connectToInstagramButton);
        sleep();
        assert isElementPresent(connectToInstagramButton) : "Error. The Instagram Redirect Button is not present on the page";
        print("The Instagram redirect button is present");
        connectToInstagramButton.click();
        List<String> tabs = new ArrayList(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        sleep ();
        assert isCurrentURLEqualTo(Strings.INSTAGRAM_BOOKA_PAGE) : "Error. The user is not redirected to Booka Instagram Page";
        driver.close();
        driver.switchTo().window(tabs.get(0));

    }

    //is element present
    public boolean isElementPresent(WebElement element) {
        print("Element is Present");
        try {
            boolean isPresent = element.isDisplayed();
            return true;
        } catch (Exception e) {
            print(e.getMessage());
            print("Element is not present on page");
            return false;
        }
    }
    public boolean isCurrentURLEqualTo(String expectedUrl) {
        print("isCurrentURLEqualTo ( " + expectedUrl + " )");
        String currentUrl = driver.getCurrentUrl();
        print("User is on " + currentUrl);
        boolean b = currentUrl.equals(expectedUrl);
        return b;
    }

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
