package test;

import org.testng.annotations.Test;
import page.Config;
import page.UkrNetPage;

public class UkrNetTest extends BaseTest{

    @Test
    public void checkAvailableTitles(){
        UkrNetPage page = new UkrNetPage(driver);
        page.openUrl(Config.URL_UKR_NET);
        page.checkTitle(Config.TITLE_UKR_NET);
        page.verifyTitles();
    }

    @Test
    public void switchToRussianLangPossibility(){
        UkrNetPage page = new UkrNetPage(driver);
        page.openUrl(Config.URL_UKR_NET + "ru");
        page.checkTitle(Config.TITLE_UKR_NET_IN_RUSSIAN);
    }

    @Test
    public void verifyCredential(){
        UkrNetPage page = new UkrNetPage(driver);
        page.openUrl(Config.URL_UKR_NET);
//        page.checkTitle(Config.TITLE_UKR_NET);
        page.switchToMailFrame();
        find(page.login).sendKeys(Config.UKR_NET_LOGIN);
        find(page.password).sendKeys(Config.UKR_NET_PASSWORD);
        find(page.buttonEnter).click();
        page.switchToFrameWithErrorMessageAndVerifyMessage();
    }
}
