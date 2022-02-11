package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.SearchPage;
import pages.Strings;

public class SearchTest extends BaseTest {


    /**
     * Search TEST  with 'triler' typed in the search bar
     * <p>
     * 1. Navigate to ""https://www.booka.rs/"
     * 2. Click on the search bar on the top right of the web page
     * 3. Type 'triler' in the search bar
     * 4. Click on the search button to the right of the search bar
     * <p>
     * Expected result:
     * 5. Verify that the user is on the "https://www.booka.rs/?s=triler&post_type=product"
     * 6. Verify that the 'Pretražujete rezultate za "triler"' message is displayed.
     **/


    @Test
    public void SearchItemUsingSearchBar() {
        driver = openChromeDriver();
        print("Opening Chrome Browser");
        try {
            BasePage basePage = new BasePage(driver);
            basePage.sleep();
            basePage.enterTextInSearchBar(Strings.VALID_KEYWORD_SEARCH);
            SearchPage searchPage = basePage.clickSearchButton();

            assert isCurrentURLEqualTo(Strings.SEARCH_RESULT_PAGE) : "Error. Wrong URL.";
            searchPage.searchResultTextIsPresent();
            assert searchPage.searchResultText.getText().contains(Strings.SEARCH_RESULT_MESSAGE) : "Error. The message is not displayed";
        } finally {
            driver.quit();
        }
    }
    /**
     * Search TEST  with 'jabuka' typed in the search bar
     * <p>
     * 1. Navigate to ""https://www.booka.rs/"
     * 2. Click on the search bar on the top right of the web page
     * 3. Type 'jabuka' in the search bar
     * 4. Click on the search button to the right of the search bar
     * <p>
     * Expected result:
     * 5. Verify that the user is on the "https://www.booka.rs/?s=jabuka&post_type=product"
     * 6. Verify that the 'Nijedan proizvod ne odgovara vašem upitu.' message is displayed.
     **/
    @Test
    public void SearchNonExistentKeyWordInSearchBar() {
        driver = openChromeDriver();
        print("Opening Chrome Browser");
        try {
            BasePage basepage = new BasePage(driver);
            basepage.sleep();
            basepage.enterTextInSearchBar(Strings.NONEXISTANT_KEYWORD_SEARCH);
            basepage.clickSearchButtonInTheSearchBar();

            assert isCurrentURLEqualTo(Strings.ITEM_NOT_FOUND_PAGE) : "Error. USer is on the wrong page";
            basepage.getNonExistantKeyWordSearchResultText();
            basepage.sleep();
            assert basepage.nonExistantKeyWordText.getText().equals(Strings.NO_ITEM_MATCHES_YOUR_QUERY) : "Error. Wrong Message";
        } finally {
            driver.quit();
        }


    }
}
