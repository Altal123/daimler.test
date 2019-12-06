package test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;

    @BeforeTest
    public void before(){
        System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterTest
    public void after(){
        driver.close();
    }

    public WebElement find(WebElement proxyElement){
        WebDriverWait element = new WebDriverWait(driver,4);
        element.until(ExpectedConditions.visibilityOf(proxyElement));
        return element.until(ExpectedConditions.elementToBeClickable(proxyElement));
    }

    public void assertResult(WebElement element){
        Assert.assertTrue(element.isDisplayed());
    }

    public void jsClick(WebElement element){
        (new WebDriverWait(driver, 4)).until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    public void clickActionsBuilder(WebElement element){
        Actions builder = new Actions(driver);
        builder.click(element).build().perform();
    }


    protected void dumbWait (int seconds){
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
