package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.Strings;

public class RedirectionTests extends BaseTest{

    /** Redirecting to Booka Facebook and Instagram Page Test
     *
     * 1. navigate to https://www.booka.rs/
     * 2. scroll down to the bottom of the webpage and click on Facebook icon on the bottom left
     * 4. click on the Instagram icon next to the Facebook one
     *
     * Expected result:
     * 3. Verify that the user is on https://www.facebook.com/bookaizdavastvo/
     * 5. Verify that the user is on https://www.instagram.com/accounts/login/?next=/bookastore/
    * */

    @Test
    public void RedirectToFacebookAndInstagramTest() {
        driver = openChromeDriver();
        try{
            BasePage basePage = new BasePage(driver);
            //assert isCurrentURLEqualTo is written in each of these two methods
            basePage.redirectToFacebook();
            print("User is redirected to Booka Facebook Page");
            basePage.redirectToInstagram();
            print("User is redirected to Booka Instagram Page");

        }finally {
            driver.quit();
        }
    }

}
