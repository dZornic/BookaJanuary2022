package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.BasePage;
import pages.LoginPage;
import pages.SearchPage;
import pages.Strings;

public class BaseTest {

    WebDriver driver = null;

    public WebDriver openChromeDriver() {
        print("Opening Chrome driver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[]{"--start-maximized"});
        options.addArguments(new String[]{"--ignore-certified-errors"});
        options.addArguments(new String[]{"--disable-popup-blocking"});
        options.addArguments(new String[]{"--incognito"});
        ChromeDriver driver = new ChromeDriver(options);
        driver.get(Strings.HOMEPAGE_URL);
        return driver;

    }
    // print method

    public void print(String text) {
        System.out.println(text);
    }

    public boolean isCurrentURLEqualTo(String expectedUrl) {
        print("isCurrentURLEqualTo ( " + expectedUrl + " )");
        String currentUrl = driver.getCurrentUrl();
        print("User is on " + currentUrl);
        boolean b = currentUrl.equals(expectedUrl);
        return b;
    }

    // High-level methods
    public SearchPage searchItemUsingSearchBar() {
            BasePage basePage = new BasePage(driver);
            basePage.sleep();
            basePage.enterTextInSearchBar(Strings.ITEM_SEARCH);
            SearchPage searchPage = basePage.clickSearchButton();
            print("Searching the item: " + Strings.ITEM_SEARCH + " in the search bar");
            assert isCurrentURLEqualTo(Strings.SEARCH_RESULT_PAGE) : "Error. Wrong URL.";
            searchPage.searchResultTextIsPresent();
            searchPage.getSearchResultText();
            return searchPage;
        }


    }
