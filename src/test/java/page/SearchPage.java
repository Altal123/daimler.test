package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SearchPage extends BasePage{

    @FindBy (id = "sb_form_q")
    public WebElement searchField;

    @FindBy(css = "svg[viewBox=\"0 0 25 25\"]")
    public WebElement searchButton;

    @FindBy(linkText = "Miracle | Definition of Miracle by Merriam-Webster")
    public WebElement searchResult;


    public SearchPage(WebDriver driver) {
        super(driver);
    }


}
