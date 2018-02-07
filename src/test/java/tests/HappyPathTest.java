package tests;

import Pages.AccountPage;
import Pages.CategoryPage;
import Pages.ProductPage;
import Pages.ShoppingCartPage;
import library.Utility;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Configuration.Page.*;

//This test explains the functionality of logging into an account selecting an item and changing its size then selecting second
//item leaving the size as default. Check the total price calculation equals to total products price + shipping

public class HappyPathTest {
    AccountPage ap;
    CategoryPage cp;
    ProductPage pp;
    ShoppingCartPage sp;

    @BeforeClass
    public static void setup() throws Throwable {
        goToHomePage();
    }


    @Test
    public void testForPurchasingTwoItems() throws Throwable {

        ap = new AccountPage(driver);
        cp = new CategoryPage(driver);
        pp = new ProductPage(driver);
        sp = new ShoppingCartPage(driver);

        ap.clickSignIn();
        ap.logintoMyAccount("testerxyz@yopmail.com","BJSSTest");
        ap.backToHomePage();
        cp.clickItem(1);
        pp.changesize(2);
        pp.addToCart();
        pp.continueShopping();
        ap.backToHomePage();
        cp.clickItem(2);
        pp.addToCart();
        pp.proceedToCheckout();
        sp.quickviewBasket();
        sp.checkTotalPrice();
        sp.proceedTOCheckoutonSummaryPage();
        sp.proceedTOCheckoutonAddressPage();
        sp.agreeTermsonShippingPage();
        sp.proceedTOCheckoutonShippingPage();
        sp.payByBankWire();
        sp.confirmOrder();
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
