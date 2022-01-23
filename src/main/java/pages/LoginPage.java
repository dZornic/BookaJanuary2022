package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    //Web elements

    @FindBy(xpath = "//a[@class ='open-login-popup']")
    WebElement prijaviSeLink;

    @FindBy(xpath = "//input[@id = 'username']")
    WebElement userNameTextField;

    @FindBy(xpath = "//input[@id = 'password']")
    WebElement passwordTextField;

    @FindBy(xpath = "//input[@id ='rememberme']")
    WebElement zapamtiMeCheckBox;

    @FindBy(xpath = "//input[@name= 'login']")
    WebElement prijaviSeButton;

    @FindBy(xpath ="//ul[@class='woocommerce-error']")
    WebElement errorMessageText;

    @FindBy(xpath ="//button[@class='tokoo-popup__close']")
    WebElement closeErrorMessageButton;

    @FindBy(xpath ="//button[@class='menu-user-avatar']")
    WebElement avatarButton;

    @FindBy(xpath = "//div[@class='menu-user-wrap']")
    WebElement mainMenu;

    @FindBy(xpath = "//ul[@class='menu']/li[@class='menu-item']/a[text()='Odjavi se']")
    WebElement odjaviSeButton;


    // Constructor

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(Strings.HOMEPAGE_URL);
    }

    // Methods

    public void clickPrijaviSeLink() {
        assert isElementPresent(prijaviSeLink) : "Error. Prijavi se Link is not present";
        print("Clicking PrijaviSe Link");
        prijaviSeLink.click();
    }

    public void enterTextInUsernameField(String text) {
        assert isElementPresent(userNameTextField) : "Error. UserName Text Field is not present";
        print("Entering username: " + text + " in Username Field");
        userNameTextField.click();
        userNameTextField.sendKeys(text);
    }

    public void removeTextFromUsernameField() {
        assert isElementPresent(userNameTextField) : "Error. UserName Text Field is not present";
        print("Deleting invalid username from username field");
        userNameTextField.clear();
        sleep();
    }

    public void enterTextInPasswordField(String text) {
        assert isElementPresent(passwordTextField) : "Error. Password Text Field is not present";
        print("Entering password: " + text + " in Password Field");
        passwordTextField.click();
        passwordTextField.sendKeys(text);
    }

    public void clickZapamtiMeCheckBox() {
        assert isElementPresent(zapamtiMeCheckBox) : "Error. ZapamtiMe CheckBox is not present";
        print("Clicking ZapamtiMe Checkbox");
        zapamtiMeCheckBox.click();
    }

    public void clickPrijaviSeButton() {
        assert isElementPresent(prijaviSeButton) : "Error. PrijaviSe Button is present";
        print("Clicking PrijaviSe Button");
        prijaviSeButton.click();
        sleep();
    }

    public void avatarButtonIsPresent() {
        assert isElementPresent(avatarButton) : "Error. User is not logged in";
        print("User is logged in");
    }

    public void errorMessageIsDisplayed(){
        assert isElementPresent(errorMessageText) : "Error. The Error Message is not present";
    }

    public String getErrorMessageText() {
        String actualErrorMessage = errorMessageText.getText();
        print("Error message is: " + actualErrorMessage);
        return actualErrorMessage;

    }
    public void clickCloseErrorMessageButton() {
        assert isElementPresent(closeErrorMessageButton) : "Error. The Close Error Message Button not present";
        print("Clicking Close Error Message Button");
        closeErrorMessageButton.click();
    }

    public void logOutOfTheWebPage() {
        Actions actions = new Actions(driver);
        actions.moveToElement(mainMenu);
        print("Clicking OdjaviSe Button");
        actions.moveToElement(odjaviSeButton).click();
        odjaviSeButton.click();
        sleep();

    }


}
