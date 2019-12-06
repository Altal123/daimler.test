package test;

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import page.Config;
import page.SearchPage;

public class SearchTest extends BaseTest {

    @Test
    public void searchResultsByClickEnter(){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.openUrl();
        searchPage.checkTitle(Config.TITLE);
        find(searchPage.searchField).sendKeys("Miracle"  + Keys.ENTER);
        assertResult(searchPage.searchResult);
        searchPage.checkTitle(Config.RESULT_TITLE);
    }

    @Test
    public void searchResultsByClickMouse(){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.openUrl();
        searchPage.checkTitle(Config.TITLE);
        find(searchPage.searchField).sendKeys("Miracle");
        searchPage.searchButton.click();
//        clickActionsBuilder(searchPage.searchButton); //is not acceptable. Why?
//        find(searchPage.searchButton).click();        //is not acceptable. Why?
//        jsClick(searchPage.searchEnter);              //is not acceptable. Why?
        assertResult(searchPage.searchResult);
    }

    @Test
    public void searchResultsByClickEnterInEmptyField(){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.openUrl();
        searchPage.checkTitle(Config.TITLE);
        find(searchPage.searchField).sendKeys("" + Keys.ENTER);
        searchPage.checkTitle(Config.TITLE);
    }

    @Test
    public void searchResultsByClickMouseWithEmptyField(){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.openUrl();
        searchPage.checkTitle(Config.TITLE);
        find(searchPage.searchButton).click();
        searchPage.checkTitle(Config.TITLE);
    }

}
