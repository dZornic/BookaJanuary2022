package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {

    //Webelements


    @FindBy(id = "billing_first_name")
    WebElement firstNameTextField;

    @FindBy(id = "billing_last_name")
    WebElement lastNameTextField;

    @FindBy(id = "billing_address_1")
    WebElement addressTextField;

    @FindBy(id = "billing_city")
    WebElement cityTextField;

    @FindBy(id = "billing_postcode")
    WebElement postcodeTextField;

    @FindBy(id = "billing_phone")
    WebElement phoneNumberField;

    @FindBy(id = "billing_email")
    WebElement emailTextField;

    @FindBy(id = "createaccount")
    WebElement createAccountCheckbox;

    @FindBy(id = "account_password")
    WebElement passwordTextField;

    @FindBy(xpath = "//label[@for='payment_method_bacs']")
    WebElement placanjeUnapredRadioButton;

    @FindBy(id = "terms")
    WebElement termsCheckbox;




    //Constructor

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.get(Strings.CHECKOUT_PAGE_URL);
    }


    //Wrapper Methods
    //todo popakuj sve ovo u jednu metodu

    public CheckoutPage enterTextInFirstNameField(String text) {
        assert isElementPresent(firstNameTextField) : "Error. FirstName Field is not present";
        print("Entering First Name: " + text + " in FirstName Field");
        firstNameTextField.click();
        firstNameTextField.sendKeys(text);
        sleep();
        return this;
    }

    public CheckoutPage enterTextInLastNameField(String text) {
        assert isElementPresent(lastNameTextField) : "Error. LastName Field is not present";
        print("Entering Last Name: " + text + " in LastName Field");
        lastNameTextField.click();
        lastNameTextField.sendKeys(text);
        sleep();
        return this;
    }

    public CheckoutPage enterTextInAddressField(String text) {
        assert isElementPresent(addressTextField) : "Error. Address Field is not present";
        print("Entering Address: " + text + " in Address Field");
        addressTextField.click();
        addressTextField.sendKeys(text);
        sleep();
        return this;
    }
    public CheckoutPage enterTextInCityField(String text) {
        assert isElementPresent(cityTextField) : "Error. City Field is not present";
        print("Entering City: " + text + " in City Field");
        cityTextField.click();
        cityTextField.sendKeys(text);
        sleep();
        return this;
    }
    public CheckoutPage enterNumberInPostCodeField(String text) {
        assert isElementPresent(postcodeTextField) : "Error. PostCode Field is not present";
        print("Entering Postcode: " + text + " in Postcode Field");
        postcodeTextField.click();
        postcodeTextField.sendKeys(text);
        sleep();
        return this;
    }
    public CheckoutPage enterNumberInPhoneNumberField(String text) {
        assert isElementPresent(phoneNumberField) : "Error. PhoneNumber Field is not present";
        print("Entering Phone number: " + text + " in PhoneNumber Field");
        phoneNumberField.click();
        phoneNumberField.sendKeys(text);
        sleep();
        return this;
    }

    public CheckoutPage enterTextInEmailField(String text) {
        assert isElementPresent(emailTextField) : "Error. Email Field is not present";
        print("Entering email: " + text + " in Email Field");
        emailTextField.click();
        emailTextField.sendKeys(text);
        sleep();
        return this;
    }
    public CheckoutPage enterTextInPasswordField(String text) {
        assert isElementPresent(passwordTextField) : "Error. Password Field is not present";
        print("Entering Password: " + text + " in Password Field");
        passwordTextField.click();
        passwordTextField.sendKeys(text);
        sleep();
        return this;
    }

    public CheckoutPage clickCreateAccountCheckBox(){
        assert isElementPresent(createAccountCheckbox) : "Error. Create Account checkbox is not present";
        print("Clicking Create Account checkbox");
        createAccountCheckbox.click();
        sleep();
        return this;
    }

    public CheckoutPage clickPlacanjeUnapredRadioButton(){
        assert isElementPresent(placanjeUnapredRadioButton) : "Error. Placanje unapred Button is not present";
        print("Clicking placanje unapred");
        placanjeUnapredRadioButton.click();
        sleep();
        return this;
    }
    public CheckoutPage clickTermsCheckBox(){
        assert isElementPresent(termsCheckbox) : "Error. Terms checkbox is not present";
        print("Clicking Terms checkbox");
        termsCheckbox.click();
        sleep();
        return this;
    }

}
