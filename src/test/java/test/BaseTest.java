package test;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver;
    private int i = 0;

    @BeforeTest
    public void before() throws IOException {

        //local running
        System.setProperty("webdriver.gecko.driver","src\\test\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();

        //remote running
//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), firefoxOptions);

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //очистка директории со скриншотами
        if (new File("D:\\test-output").exists())
            FileUtils.cleanDirectory(new File("D:\\test-output"));
    }

    @AfterMethod
    public void afterEachTestMethod() throws IOException {
        i++;
        TakesScreenshot scr = ((TakesScreenshot) driver);
        File file1 = scr.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(file1, new File("D:\\test-output\\test" + i + ".PNG"));
        System.out.println("Screenshot of the test is taken");
    }

    @AfterTest
    public void after(){
        driver.quit();
    }

    public WebElement find(WebElement proxyElement){
        WebDriverWait element = new WebDriverWait(driver,4);
//        element.until(ExpectedConditions.visibilityOf(proxyElement));
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


}
