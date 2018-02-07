package Pages;

import Configuration.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.testng.Assert.assertTrue;

public class ProductPage extends Page {


    public static class productDetails {

        public String itemname;
        public String itemsize;
        public String itemprice;
        public productDetails p;


        public productDetails(String itemname, String itemprice, String itemsize) {

            this.itemname = itemname;
            this.itemprice = itemprice;
            this.itemsize = itemsize;

        }

        public productDetails(String itemsize) {
            this.itemsize = itemsize;
        }

        public productDetails(productDetails p) {
            this.p = p;
        }

        public String getItemname() {
            return itemname;
        }

        public String getSize() {
            return itemsize;
        }

        public String getPrice() {
            return itemprice;
        }

        public void setItemName(String itemname) {
            this.itemname = itemname;
        }

        public void setPrice(String itemprice) {
            this.itemprice = itemprice;
        }

        public void setSize(String size) {
            this.itemsize = size;
        }
    }


        @FindBy(id = "group_1")
        WebElement sizeDropDownList;
        @FindBy(css = "button.exclusive span")
        WebElement addToCartButton;
        @FindBy(css = "span.continue.btn.btn-default.button.exclusive-medium")
        WebElement continueShoppingButton;
        @FindBy(css = "a.btn.btn-default.button.button-medium")
        WebElement proceedToCheckOutButton;
        @FindBy(id = "center_column")
        private WebElement shoppingCartSummary;

        public ProductPage(WebDriver driver) {

            PageFactory.initElements(driver, this);
        }


        public void changesize(int value) {

            String size = null;
            Select dropdown = new Select(sizeDropDownList);
            dropdown.selectByIndex(1);
            if (value == 1) size = "S";
            else if (value == 2) size = "M";
            else if (value == 3) size = "L";
            productDetails pd = prodDetails.get(0);
            pd.setSize(size);
            System.out.println("Changed size is :" + prodDetails.get(prodDetails.size() - 1).getSize());
        }


        public void addToCart() throws Throwable {
            try {
                getElement(addToCartButton, 1).click();
            } catch (Throwable throwable) {
                throw new Exception("Add item to cart not successful");
            }
        }

        public void continueShopping() throws Throwable {
             try {
                getElement(continueShoppingButton, 3).click();
            } catch (Throwable throwable) {
                throw new Exception("Unable to click the button to continue shopping");
            }
        }

        public void proceedToCheckout() throws Throwable {

            getElement(proceedToCheckOutButton, 3).click();
            try {
                assertTrue(getElement(shoppingCartSummary, 1).isDisplayed(), "Shopping cart summary is displayed");
            } catch (Throwable throwable) {
                throw new Exception("Proceed to checkout not successful");
            }
        }
    }

