package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.SearchPage;
import pages.Strings;

public class SearchTest extends BaseTest{


   /* Search test  with 'triler' typed in the search bar
   * 1. Navigate to ""https://www.booka.rs/"
   * 2. Click on the search bar on the top right of the web page
   * 3. Type 'triler' in the search bar
   * 4. Click on the search button to the right of the search bar
   *
   * Expected result:
   * 5. Verify that the user is on the "https://www.booka.rs/?s=triler&post_type=product"
   * 6. Verify that the 'REZULTATI PRETRAGE' message is displayed.
   * */


    @Test
    public void SearchItemUsingSearchBar()  {
        driver = openChromeDriver();
        print("Opening Chrome Browser");
        try {

            BasePage basePage = new BasePage(driver);
            basePage.sleep();
            basePage.enterTextInSearchBar(Strings.ITEM_SEARCH);
            SearchPage searchPage = basePage.clickSearchButton();
            assert isCurrentURLEqualTo(Strings.SEARCH_RESULT_PAGE) : "Error. Wrong URL.";
            searchPage.searchResultTextIsPresent();
            searchPage.getSearchResultText();
        }finally {
            driver.quit();
        }
    }


}
