package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.Strings;

import javax.print.DocFlavor;

public class LoginTest extends BaseTest {

    /**
     * Login Test with Valid Username and Valid Password
     * 1. Navigate to www.booka.rs
     * 2. Click on Prijavi Se link in the top right
     * 3. Enter valid username in username text field
     * 4. Enter valid password in password text field
     * 5. Click on Zapamti checkbox
     * 6. Click on Prijavi Se button
     * <p>
     * expected result:
     * Verify that the User is on the Home page
     * Verify that the Avatar button is present
     */

    @Test
    public void LoggingInWithValidUsernameAndValidPassword() {

        driver = openChromeDriver();
        print("Opening Chrome browser");
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickPrijaviSeLink();
            loginPage.enterTextInUsernameField(Strings.VALID_USERNAME);
            loginPage.enterTextInPasswordField(Strings.VALID_PASSWORD);
            loginPage.clickZapamtiMeCheckBox();
            loginPage.clickPrijaviSeButton();
            loginPage.sleep();
            loginPage.avatarButtonIsPresent();

            assert isCurrentURLEqualTo(Strings.HOMEPAGE_URL) : "Error. Wrong URL.";
            assert loginPage.avatarButton.isDisplayed() : "Error. The user is not logged in";


        } finally {
            driver.quit();
        }

    }

    /**
     * Login Test with Valid Username and Invalid Password
     * 1. Navigate to www.booka.rs
     * 2. Click on Prijavi Se link
     * 3. Enter valid username in username text field
     * 4. enter invalid password in password text field
     * 5. click on Prijavi Se button
     *
     * expected result:
     * Verify that the 'ERROR: The username or password you entered is incorrect. Lost your password?' message is displayed.
     *
     */

    @Test
    public void LoggingInWithValidUsernameAndInvalidPassword() {

        driver = openChromeDriver();
        print("Opening Chrome browser");
        try {

            //u login pageu ti metoda ne vraca loginpage pa moras svaki put pre metode da napises objekat login page
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickPrijaviSeLink();
            loginPage.enterTextInUsernameField(Strings.VALID_USERNAME);
            loginPage.enterTextInPasswordField(Strings.INVALID_PASSWORD);
            loginPage.clickZapamtiMeCheckBox();
            loginPage.clickPrijaviSeButton();
            loginPage.errorMessageIsDisplayed();
            loginPage.getErrorMessageText();

            assert isCurrentURLEqualTo(Strings.HOMEPAGE_URL) : "Error. Wrong URL.";
            assert loginPage.errorMessageText.getText().contains(Strings.LOGIN_ERROR_MESSAGE) : "Error. The Message is not displayed";
            loginPage.clickCloseErrorMessageButton();

        } finally {
            driver.quit();
        }

    }


    /**Login Test with Invalid username and Valid password then retyped valid username
     *
     * 1. Navigate to www.booka.rs
     * 2. Click on Prijavi Se link
     * 3. Enter invalid username in username text field
     * 4. Enter invalid password in password text field
     * 5. Click on 'Prijavi' Se button
     * 6. Verify that the 'error message' is present
     * 7. Clear the invalid username from username text field
     * 8. Enter valid username in username text field
     * 9. Click on 'Prijavi se' button
     *
     * Expected result:
     * 10. Verify that the User is on the home page
     * 11. Verify that the Avatar button is present
     *
     */

    @Test
    public void LoggingInWithInvalidUsernameAndValidPasswordAndReloggingInWithValidCRedentials() {
        driver = openChromeDriver();
        print("Opening Chrome Browser");
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.clickPrijaviSeLink();
            loginPage.enterTextInUsernameField(Strings.INVALID_USERNAME);
            loginPage.enterTextInPasswordField(Strings.VALID_PASSWORD);
            loginPage.clickZapamtiMeCheckBox();
            loginPage.clickPrijaviSeButton();
            assert isCurrentURLEqualTo(Strings.HOMEPAGE_URL) : "Error. Wrong URL.";
            loginPage.errorMessageIsDisplayed();
            loginPage.getErrorMessageText();
            loginPage.removeTextFromUsernameField();
            loginPage.enterTextInUsernameField(Strings.VALID_USERNAME);
            loginPage.enterTextInPasswordField(Strings.VALID_PASSWORD);
            loginPage.clickZapamtiMeCheckBox();
            loginPage.clickPrijaviSeButton();
            loginPage.avatarButtonIsPresent();
            loginPage.sleep();

            assert isCurrentURLEqualTo(Strings.HOMEPAGE_URL) : "Error. Wrong URL.";
            assert loginPage.avatarButton.isDisplayed() : "Error. The Avatar button is not displayed. The user is not logged in.";


        }finally{
            driver.quit();
        }

    }
}




