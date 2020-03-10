package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.*;

public class UkrNetPage extends BasePage{

    @FindBy(css = "li.sinoptik")
    protected WebElement titleWeather;

    @FindBy(css = "li.currency")
    protected WebElement titleCurrency;

    @FindBy(css = "li.fuel")
    protected WebElement titleFuel;

    @FindBy(css = "li.orakul")
    protected WebElement titleOrakul;

    @FindBy(css = "div.dropdown")
    protected WebElement linkRusLanguage;

    @FindBy(id = "id-input-login")
    public WebElement login;

    @FindBy(id = "id-input-password")
    public WebElement password;

    @FindBy(css = "button.form__submit")
    public WebElement buttonEnter;

    @FindBy(css = "p.form__error_wrong")
    public WebElement wrongCredential;

    public UkrNetPage(WebDriver driver) {
        super(driver);
    }

    public void verifyTitles(){
        assertEquals(titleWeather.getText(), "Погода");
        assertEquals(titleCurrency.getText(), "Валюта");
        assertEquals(titleFuel.getText(), "Пальне");
        assertEquals(titleOrakul.getText(), "Гороскопи");
        System.out.println("UkrNet titles successfully matched expected ones");
    }

    public void jsChangeAttributeElement(){
//        (new WebDriverWait(driver, 4)).until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute('style', 'display: block;');", linkRusLanguage);
    }

    public void switchToFrameWithErrorMessageAndVerifyMessage(){
        try{
            Assert.assertTrue(wrongCredential.isDisplayed());
//            if (!wrongCredential.isDisplayed())
//                throw new Exception("Error message hasn`t been appeared!");
        }catch (Exception e){
            for(String winHandle: driver.getWindowHandles()){
                driver.switchTo().defaultContent();
                driver.switchTo().window(winHandle);
                try{
                    if (driver.findElement(By.id("recaptcha-anchor")).isDisplayed())
                        driver.findElement(By.id("recaptcha-anchor")).click();
                    dumbWait(1);
                    Assert.assertTrue(wrongCredential.isDisplayed());
                }catch (Exception en){
                    System.out.println("winHandle: " + winHandle + " is not correct");
                }
            }
        }

    }

    public void switchToMailFrame(){
        driver.switchTo().frame("mail widget");
    }


}
