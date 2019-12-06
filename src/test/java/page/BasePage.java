package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

  public void openUrl(){
        driver.get(Config.URL);
  }

  public void checkTitle(String title){
      new WebDriverWait(driver, 4).until(ExpectedConditions.titleContains(title));
  }


}
