package Configuration;

import Pages.ProductPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;

public class Page {

    public static WebDriver driver;

    public static ArrayList<ProductPage.productDetails> prodDetails = new ArrayList<ProductPage.productDetails>();

    public static void goToHomePage() throws Throwable {
//        Below settings can be used if we have drivers in the project build itself
//        System.setProperty("webdriver.chrome.driver", System.getProperty(("user.dir")+"/src/main/resources/chromedriver"));
//        driver=new ChromeDriver();
        DesiredCapabilities ca = DesiredCapabilities.firefox();
        ca.setCapability("marionette", false);
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\geckodriver.exe");
//        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.navigate().to("http://automationpractice.com/index.php");
    }

    public static void close(){
        driver.quit();
    }

    protected ElementImpl getElement(WebElement element, int seconds) throws Throwable {
        try {
            WebDriverWait wait = new WebDriverWait(driver, seconds);
            return new ElementImpl(wait.until(ExpectedConditions.visibilityOf(element)));
        } catch (Exception e) {
            throw e;
        }
    }

}
