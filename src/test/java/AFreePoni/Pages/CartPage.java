package AFreePoni.Pages;

import AFreePoni.Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseTest {
    public CartPage() {
        PageFactory.initElements(driver, this);
    }
   public static @FindBy(id="remove-sauce-labs-backpack")
   WebElement CartRemoveButton;
    public static @FindBy(className = "cart_quantity")
    WebElement CartQuantity;
    public static @FindBy(id="item_4_title_link")
    WebElement Product1Title;

    public static @FindBy(id = "checkout")
    WebElement CheckoutButton;
    public static @FindBy(id = "continue-shopping")
    WebElement ContinueShopping;

    public static @FindBy(id="item_0_title_link")
    WebElement Product2Title;
    public static @FindBy(id="item_1_title_link")
    WebElement Product3Title;
    public static @FindBy(id="item_5_title_link")
    WebElement Product4Title;
    public static @FindBy(id="item_2_title_link")
    WebElement Product5Title;

    public static @FindBy(id="item_3_title_link")
    WebElement Product6Title;
//---------------------------------------------------
    public static String checkQuantityInCart() {
      String x =  CartQuantity.getText();
        return x;
    }
    public static void clickOnCartRemoveButton(){
        CartRemoveButton.click();
    }

    public  static void clickOnCheckoutButton(){
        CheckoutButton.click();
    }
    public static void clickOnContinueShopping(){
        ContinueShopping.click();
    }
    public static String checkProduct1Title() {
        String x =  Product1Title.getText();
        return x;
    }
    public static String checkProduct2Title() {
        String x =  Product2Title.getText();
        return x;
    }
    public static String checkProduct3Title() {
        String x =  Product3Title.getText();
        return x;
    }
    public static String checkProduct4Title() {
        String x =  Product4Title.getText();
        return x;
    }
    public static String checkProduct5Title() {
        String x =  Product5Title.getText();
        return x;
    }
    public static String checkProduct6Title() {
        String x =  Product6Title.getText();
        return x;
    }

}
