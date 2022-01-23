package tests;

import org.testng.annotations.Test;
import pages.SearchPage;
import pages.Strings;

public class SortingTests extends BaseTest{


    @Test
    public void SortingItemsAlphabetically() {

        driver = openChromeDriver();
        try{
            //todo
            SearchPage searchPage = searchItemUsingSearchBar();
            searchPage.clickDropDownMenu();
            searchPage.clickSortAlphabeticallyButton();
            assert isCurrentURLEqualTo(Strings.ALPHABETICALLYSORTED_PRODUCT_PAGE_URL) : "Error. Wrong URL. Extected: "
                    + Strings.ALPHABETICALLYSORTED_PRODUCT_PAGE_URL + " Actual: " + Strings.SEARCH_RESULT_PAGE;



            //todo
            //asertuj URL - done
            //asertuj da su knjige poredjane po abecednom redu

            //todo
            //napravi kratak search test ali odradi vise sortiranja i asertuj 2-3 testa

        }finally {
            driver.quit();
        }
    }

}
