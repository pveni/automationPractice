package Pages;

import Configuration.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShoppingCartPage extends Page {

    @FindBy(id = "total_price")
    private WebElement totalPrice;
    @FindBy(css = "a.button.btn.btn-default.standard-checkout.button-medium span")
    private WebElement proceedCheckOutSummary;
    @FindBy(css = "button.button.btn.btn-default.button-medium span")
    private WebElement proceedCheckOutAddress;
    @FindBy(css = "button.button.btn.btn-default.standard-checkout.button-medium span")
    private WebElement proceedCheckOutShipping;
    @FindBy(xpath = "//*[@id='uniform-cgv']")
    private WebElement agreeTC;
    @FindBy(css = "a.bankwire")
    private WebElement payByWire;
    @FindBy(css = "button.button.btn.btn-default.button-medium span")
    private WebElement confirmOrderOption;
    @FindBy(id="center_column")
    private WebElement deliveryAddress;
    @FindBy(id="form")
    private WebElement shippingOption;
    @FindBy(id="order-detail-content")
    private WebElement orderDetailsPage;
    @FindBy(css=".box.cheque-box")
    private WebElement orderSummary;
    @FindBy(id="center_column")
    private WebElement orderConfirmation;

    public ShoppingCartPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }

    public void quickviewBasket() {

        for (int i = 1; i <= prodDetails.size(); i++) {
            WebElement prodDesc = driver.findElement(By.xpath("//tr[" + i + "]/td[@class='cart_description']/p[@class='product-name']/a"));
            WebElement prodsize = driver.findElement(By.xpath("//tr[" + i + "]/td[@class='cart_description']/small/a"));
            WebElement prodprice = driver.findElement(By.xpath("//tr[" + i + "]/td[@class='cart_unit']/span/span"));

            assertTrue(prodDesc.getText().equalsIgnoreCase(prodDetails.get(i - 1).getItemname()), "Verfies that the product details name is same as in the basket");
            assertTrue(prodsize.getText().substring((prodsize.getText().length()) - 1).equalsIgnoreCase(prodDetails.get(i - 1).getSize()), "Verfies that the product details size is same as in the basket");
            assertTrue(prodprice.getText().equalsIgnoreCase(prodDetails.get(i - 1).getPrice()), "Verfies that the product details price is same as in the basket");

        }
    }

    public void checkTotalPrice() throws Throwable {

        float totprice = 2;
        for (int i = 0; i < prodDetails.size(); i++)
            totprice += Float.parseFloat(prodDetails.get(i).getPrice().substring(1, (prodDetails.get(i).getPrice().length())));

        BigDecimal totalprice = new BigDecimal(totprice);
        totalprice = totalprice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal calculatedTotalPrice = new BigDecimal(getElement(totalPrice, 2).getText().substring(1, ((getElement(totalPrice, 2)).getText().length())));
        calculatedTotalPrice = calculatedTotalPrice.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        System.out.println("Total Price is :" + totalprice);
        System.out.println("Calculated Price is :" + Float.parseFloat(getElement(totalPrice, 2).getText().substring(1, ((getElement(totalPrice, 2)).getText().length()))));

        assertTrue(totalprice.equals(calculatedTotalPrice), "Total products + shipping price equals Total Amount");

    }

    public void proceedTOCheckoutonSummaryPage() throws Throwable {

        try {
            getElement(proceedCheckOutSummary, 3).click();
            assertTrue(deliveryAddress.isDisplayed(),"Delivery Address is displayed");
        } catch (Throwable throwable) {
            throw new Exception("proceed to checkout not successful on summary page");
        }
    }

    public void proceedTOCheckoutonAddressPage() throws Throwable {
         try {
            getElement(proceedCheckOutAddress, 3).click();
            assertTrue(shippingOption.isDisplayed(),"Shipping option is displayed");
        } catch (Throwable throwable) {
            throw new Exception("proceed not successful from address page");
        }
    }

    public void agreeTermsonShippingPage() throws Throwable {

        try {
            getElement(agreeTC, 3).click();
        } catch (Throwable throwable) {
            throw new Exception("Click button not successful on shipping page");
        }
    }

    public void proceedTOCheckoutonShippingPage() throws Throwable {

        try {
            getElement(proceedCheckOutShipping, 3).click();
            assertTrue(orderDetailsPage.isDisplayed(),"Order details displayed");
        } catch (Throwable throwable) {
            throw new Exception("proceed to shipping not successful");
        }
    }

    public void payByBankWire() throws Throwable {

        try {
            getElement(payByWire, 3).click();
            assertTrue(orderSummary.isDisplayed(),"Order summary is displayed");
        } catch (Throwable throwable) {
            throw new Exception("pay by wire button click not successful");
        }
    }

    public void confirmOrder() throws Throwable {

        try {
            getElement(confirmOrderOption, 3).click();
            assertTrue(orderConfirmation.isDisplayed(),"Order confirmation is displayed");
        } catch (Throwable throwable) {
            throw new Exception("confirm order not successful");
        }
    }
}
