package Pages;

import Configuration.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderHistoryPage extends Page {


    @FindBy(xpath = "//select[@name='id_product']")
    private WebElement prodDropdown;
    @FindBy(xpath = "//p[@class='form-group']/textarea")
    private WebElement addComment;
    @FindBy(css = "button.button.btn.btn-default.button-medium span")
    private WebElement submit;
    @FindBy(xpath="//div[@class='table_block'][2]/table[@class='detail_step_by_step table table-bordered']/tbody/tr[@class='first_item item']/td[2]")
    private WebElement message;

    static String match;

    public OrderHistoryPage(WebDriver driver) {
       PageFactory.initElements(driver, this);
    }

    public void selectOrderFromHistory(int index) throws Throwable {
       try{
            WebElement order = driver.findElement(By.xpath("//tr["+index+"]//a[@class='color-myaccount']"));
            order.click();
        }
        catch (Throwable throwable) {
            throw new Exception("Order cannot be selected from index");
        }
    }


    public void selectOrderFromHistory(String Date) throws Throwable {
       try{

            WebElement order = driver.findElement(By.xpath("//tr//td[contains(text(),'"+ Date +"')]/preceding-sibling::td"));
           ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", order);
//           driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            order.click();
        }
        catch (Throwable throwable) {
            throw new Exception("Order cannot be selected by date");
        }
    }
    public void selectProductFromOrders(int index) throws Throwable {
        try {
            getElement(prodDropdown,5).click();
            Select dropdown = new Select(prodDropdown);
            dropdown.selectByIndex(index);
        }
        catch (Throwable throwable) {
            throw new Exception("Product cannot be selected from the index");
        }
    }
    public void getDressColorfromProductOrders(int index) throws Throwable {
          try{
            driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
            WebElement element = driver.findElement(By.xpath("//div[@class='table_block'][2]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
            WebElement dressColour = driver.findElement(By.cssSelector("select[name='id_product'] option:nth-child("+index+")"));
            String s = getElement(dressColour,3).getText();
            System.out.println("Selected item is : " + s);
            Pattern pat = Pattern.compile("Color.+?[,]");
            Matcher mat = pat.matcher(s);
            while(mat.find())
                match = s.substring(mat.start(), (mat.end()));
                match = match.substring(8,(match.length())-1);
               assertTrue(match.equalsIgnoreCase("Lavender"), "Verifying dress color is Lavender");
        }
        catch (Throwable throwable) {
            throw new Exception("Dress color not found from the selected product order list");
        }
    }

    public void addCommentsToProducts(String text) throws Throwable{
        try{
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addComment);
            getElement(addComment,3).sendKeys(text);
            getElement(submit,3).click();
        }
        catch (Throwable throwable) {
            throw new Exception("Message cannot be added");
        }
    }
}
