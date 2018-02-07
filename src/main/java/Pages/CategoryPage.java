package Pages;


import Configuration.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryPage extends Page {

    @FindBy(xpath = "//h1[@itemprop='name']")
    WebElement itemName;
    @FindBy(id = "our_price_display")
    WebElement itemPrice;
    @FindBy(xpath = "//*[@id='uniform-group_1']/span")
    WebElement itemSize;

    public CategoryPage(WebDriver driver){
        PageFactory.initElements(driver,this);
    }


    public void clickItem(int itemnumber) throws Throwable {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//ul[@id='homefeatured']/li["+ itemnumber +"]"))));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", item);
        getElement(item,5).click();
        Thread.sleep(3000);
        prodDetails.add(new ProductPage.productDetails(itemName.getText(),itemPrice.getText(),itemSize.getText()));

      System.out.println("Selected product details are : ");
        for(int i=0;i<prodDetails.size();i++){
            System.out.println("Product Name : "+prodDetails.get(i).getItemname());
            System.out.println("Product Price : "+prodDetails.get(i).getPrice());
            System.out.println("Product Size : "+prodDetails.get(i).getSize());
        }
    }

}

