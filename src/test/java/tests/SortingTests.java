package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.Strings;

public class SortingTests extends BaseTest {

    /**
     * Sort items alphabetically Test
     * 1. Navigate to www.booka.rs
     * 2. Enter 'triler' in the search bar
     * 4. In the 'Sort By' DropDown Menu click on 'A do Ž'
     * <p>
     * expected result:
     * 3. Verify that the user is on the https://www.booka.rs/?orderby=alphabetical&paged=1&s=triler&post_type=product
     * 5. Verify that items are sorted out alphabetically //todo
     */
    @Test
    public void SortingItemsAlphabetically() {
        driver = openChromeDriver();
        try {
            SearchPage searchPage = searchItemUsingSearchBar();
            searchPage.getUnsortedList();
            searchPage.clickSortAlphabeticallyButtonFromDropDownMenu();
            searchPage.getSortedList();
            assert isCurrentURLEqualTo(Strings.ALPHABETICALLYSORTED_PRODUCT_PAGE_URL) : "Error. Wrong URL."
                    + Strings.ALPHABETICALLYSORTED_PRODUCT_PAGE_URL + " Actual: " + Strings.SEARCH_RESULT_PAGE;

            //todo assert using Collections
        } finally {
            driver.quit();
        }
    }
}
    //todo add sorting alphabetically reverse