package tests;

import Pages.*;
import library.Utility;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Configuration.Page.*;

//This test explains about going into the user account selecting an order from order history page and assert to make the
//test fail by sending different dress color

public class CaptureImageTest {
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
    public void testToCaptureFailedScreenshot() throws Throwable {

        ap = new AccountPage(driver);
        cp = new CategoryPage(driver);
        pp = new ProductPage(driver);
        sp = new ShoppingCartPage(driver);
        op=new OrderHistoryPage(driver);
        ap.clickSignIn();
        ap.logintoMyAccount("testerxyz@yopmail.com","BJSSTest");
        ap.reviewOrderHistory();
        op.selectOrderFromHistory("02/05/2018");
        op.getDressColorfromProductOrders(3);
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
}
