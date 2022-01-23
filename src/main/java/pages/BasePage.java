package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver = null;

    @FindBy(xpath = "//a[@class ='open-login-popup']")
    WebElement prijaviSeLink;

    @FindBy(id = "product-search-keyword")
    WebElement searchBar;

    @FindBy(xpath =" //button[@class='search-icon']")
    WebElement searchButton;

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

    // novi tab
//    List<String> tabs = new ArrayList(driver.getWindowHandles());
//        driver.switchTo().window(tabs.get(1));

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

    public void waitForElement(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
