package Pages;

import Configuration.Page;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.testng.Assert.assertTrue;

public class AccountPage extends Page {

    @FindBy(xpath = "//a[@class='login'][@href='http://automationpractice.com/index.php?controller=my-account']")
    WebElement signInLink;

    @FindBy(id="email")
    private WebElement email;

    @FindBy(id="passwd")
    private WebElement password;

    @FindBy(id="SubmitLogin")
    private WebElement signIn;

    @FindBy(xpath="//a[@class='home']")
    private WebElement homeButton;

    @FindBy(xpath="//i[@class='icon-list-ol']")
    private WebElement orderHistory;

    @FindBy(css = "a.logout")
    private WebElement signOut;

    @FindBy(xpath = "//table[@id='order-list']")
    private WebElement orderList;

    public AccountPage(WebDriver driver){
        PageFactory.initElements(driver, this);

    }
    public void clickSignIn() throws Throwable {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", signInLink);
        getElement(signInLink, 3).click();
    }

    public void logintoMyAccount(String username,String pwd) throws Throwable {
       try{ getElement(email,1).sendKeys(username);
        getElement(password,1).sendKeys(pwd);
        getElement(signIn,1).click();
    }
       catch (Throwable throwable) {
           throw new Exception("Login not successful");
       }}

    public void backToHomePage() throws Throwable {
        try{
            getElement(homeButton,1).click();
        }
        catch (Throwable throwable) {
            throw new Exception("Back to homepage not successful");
        }
    }

    public void reviewOrderHistory() throws Throwable{
        try {
            getElement(orderHistory, 3).click();
            assertTrue(orderList.isDisplayed(), "message");
        } catch (Throwable throwable) {
            throw new Exception("Navigate to order history page not successful");
        }
    }

    public void logOutFromAccount() throws Throwable {
        try {
            getElement(signOut, 3).click();
        } catch (Throwable throwable) {
            throw new Exception("Logout not successful");
        }

    }

}
