package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.Strings;

import javax.print.DocFlavor;

public class LoginTest extends BaseTest {

    /**
     * Log in Test with Valid Username and Valid Password
     * 1. Navigate to www.booka.rs
     * 2. Click on Prijavi Se link
     * 3. Enter valid username in username text field
     * 4. Enter valid password in password text field
     * 5. Clcik on Zapamti checkbox
     * 6. Click on Prijavi Se button
     * 7. Click on 'odjavi se' button in the dropdown menu
     * <p>
     * expected result:
     * User is logged in
     * Verify that the Avatar button is present
     * Verify that the 'prijavi se' button is present
     */

    @Test
    public void LoggingInWithValidUsernameAndValidPassword() {

        driver = openChromeDriver();
        print("Opening Chrome browser");
        try {
            LoginPage loginPage = new LoginPage(driver); //instanciranje objekata klase
            loginPage.clickPrijaviSeLink();
            loginPage.enterTextInUsernameField(Strings.VALID_USERNAME);
            loginPage.enterTextInPasswordField(Strings.VALID_PASSWORD);
            loginPage.clickZapamtiMeCheckBox();
            loginPage.clickPrijaviSeButton();
            loginPage.sleep();
            assert isCurrentURLEqualTo(Strings.HOMEPAGE_URL) : "Error. Wrong URL.";

            //log out
            loginPage.avatarButtonIsPresent();
            loginPage.logOutOfTheWebPage();
        } finally {
            driver.quit();
        }

    }

    /**
     * Log in Test with Valid Username and Invalid Password
     * 1. Navigate to www.booka.rs
     * 2. Click on Prijavi Se link
     * 3. Enter valid username in username text field
     * 4. enter invalid password in password text field
     * 5. click on Prijavi Se button
     * <p>
     * expected result:
     * User stays on the Home page and the error message is displayed.
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
            assert isCurrentURLEqualTo(Strings.HOMEPAGE_URL) : "Error. Wrong URL.";
            loginPage.getErrorMessageText();
            loginPage.clickCloseErrorMessageButton();
        } finally {
            driver.quit();
        }

    }

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
            loginPage.sleep();
            assert isCurrentURLEqualTo(Strings.HOMEPAGE_URL) : "Error. Wrong URL.";

            //logout
            loginPage.avatarButtonIsPresent();
            loginPage.logOutOfTheWebPage();

        }finally{
            driver.quit();
        }

    }
}




