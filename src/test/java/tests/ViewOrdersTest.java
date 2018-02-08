package tests;

import Pages.*;
import library.Utility;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Configuration.Page.*;

//This test explains the functionality of logging into an account, going to order history, select an order by date, add assertion
//for the comment added through tests

public class ViewOrdersTest {
    AccountPage ap;
    CategoryPage cp;
    ProductPage pp;
    ShoppingCartPage sp;
    OrderHistoryPage op;


    @BeforeClass
    public static void setup() throws Throwable {
        goToHomePage();
    }


    @Test
    public void testForPreviousOrders() throws Throwable {
        ap = new AccountPage(driver);
        cp = new CategoryPage(driver);
        pp = new ProductPage(driver);
        sp = new ShoppingCartPage(driver);
        op = new OrderHistoryPage(driver);
        ap.clickSignIn();
        ap.logintoMyAccount("testerxyz@yopmail.com","BJSSTest");
        ap.reviewOrderHistory();
        op.selectOrderFromHistory("02/07/2018");
        op.selectProductFromOrders(2);
        op.addCommentsToProducts("Comments added successfully");
        ap.logOutFromAccount();
    }

    @AfterMethod
    public void tearDown(ITestResult result)
    {
        if(ITestResult.FAILURE==result.getStatus()){
            Utility.captureScreenshot(driver,result.getName());
        }
        driver.quit();
    }

    @AfterClass
    public static void closeSetup()
    {
        close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}


