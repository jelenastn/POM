package AFreePoni.Pages;

import AFreePoni.Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class ShopPage extends BaseTest {
    public ShopPage(){
        PageFactory.initElements(driver, this);
    }

//Meni
   public static @FindBy(id = "react-burger-menu-btn")
    WebElement MeniButton;

    public static @FindBy(id = "about_sidebar_link")
    WebElement AboutButton;
    public static @FindBy(id = "logout_sidebar_link")
    WebElement LogOutButton;
    public static @FindBy(id = "reset_sidebar_link")
    WebElement ResetAppButton;

    public static @FindBy(id = "react-burger-cross-btn")
    WebElement XButton;
    public static @FindBy(className = "social_twitter")
    WebElement TwitterButton;
    public static @FindBy(className = "social_facebook")
    WebElement FacebookButton;
    public static @FindBy(className = "social_linkedin")
    WebElement LinkedinButton;

public static  @FindBy(xpath="./html/body/div[4]/div/div/section/button/icon/svg")
WebElement PopupX;


    //Product 1 left
    public static @FindBy(id = "item_4_img_link")
    WebElement Product1Image;
    public static @FindBy(id = "item_4_title_link")
    WebElement Product1Link;

   // public static @FindBy(css=".inventory_details_name.large_size")
  //  WebElement Product1;

    public static @FindBy(id = "add-to-cart-sauce-labs-backpack")
    WebElement Cart1Add;

    public static @FindBy(id="shopping_cart_container")
    WebElement Cart;
    public static @FindBy(className = "shopping_cart_badge")
    WebElement CartBadge;

    public static @FindBy(id="remove-sauce-labs-backpack")
    WebElement RemoveButton1;
    public static @FindBy(id = "item_0_img_link")
    WebElement Product2Image;
    public static @FindBy(id = "item_0_title_link")
    WebElement Product2Link;

    public static @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    WebElement Cart2Add;
    public static @FindBy(id="remove-sauce-labs-bike-light")
    WebElement RemoveButton2;
    public static @FindBy(id = "item_1_img_link")
    WebElement Product3Image;
    public static @FindBy(id = "item_1_title_link")
    WebElement Product3Link;

    public static @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    WebElement Cart3Add;
    public static @FindBy(id="remove-sauce-labs-bolt-t-shirt")
    WebElement RemoveButton3;
    public static @FindBy(id = "item_5_img_link")
    WebElement Product4Image;
    public static @FindBy(id = "item_5_title_link")
    WebElement Product4Link;

    public static @FindBy(id = "add-to-cart-sauce-labs-fleece-jacket")
    WebElement Cart4Add;
    public static @FindBy(id="remove-sauce-labs-fleece-jacket")
    WebElement RemoveButton4;
    public static @FindBy(id = "item_2_img_link")
    WebElement Product5Image;
    public static @FindBy(id = "item_2_title_link")
    WebElement Product5Link;

    public static @FindBy(id = "add-to-cart-sauce-labs-onesie")
    WebElement Cart5Add;
    public static @FindBy(id="remove-sauce-labs-onesie")
    WebElement RemoveButton5;
    public static @FindBy(id = "item_3_img_link")
    WebElement Product6Image;
    public static @FindBy(id = "item_3_title_link")
    WebElement Product6Link;

    public static @FindBy(id = "add-to-cart-test.allthethings()-t-shirt-(red)")
    WebElement Cart6Add;
    public static @FindBy(id="remove-test.allthethings()-t-shirt-(red)")
    WebElement RemoveButton6;

//------------------------------------------------------------------
    public static void clickOnMeniButton() {
        MeniButton.click();
    }

    public void clickOnAboutButton() {

        AboutButton.click();
    }
    public void clickOnLogOutButton() {

        LogOutButton.click();
    }
    public void clickOnResetAppButton() {

        ResetAppButton.click();
    }
    public void clickOnCloseButton() {

        XButton.click();
    }
    public static void clickOnTwitterButton(){
        TwitterButton.click();
    }
    public static void clickOnFacebookButton(){
        FacebookButton.click();
    }
    public static void clickOnLinkedinButton(){
        LinkedinButton.click();
    }
public static  void clickOnPopup(){
        PopupX.click();
}




    //product 1 left side
    public static void clickOnProduct1Image(){
        Product1Image.click();
    }
    public static void clickOnProduct1Link(){
        Product1Link.click();
    }
    public static void clickOnAddToCart(){
        Cart1Add.click();
    }

    public static void clickOnCart(){
        Cart.click();
    }

    public static void clickOnRemoveButton1(){
        RemoveButton1.click();
    }
    public static String checkStatusCartBadge() {
        String x =  CartBadge.getText();
        return x;
    }
//product 1 right side

    public static void clickOnProduct2Image(){
        Product2Image.click();
    }
    public static void clickOnProduct2Link(){
        Product2Link.click();
    }

    public static void clickOnAddToCart2(){
        Cart2Add.click();
    }
    public static void clickOnRemoveButton2(){
        RemoveButton2.click();
    }
    public static void clickOnProduct3Image(){
        Product3Image.click();
    }
    public static void clickOnProduct3Link(){
        Product3Link.click();
    }

    public static void clickOnAddToCart3(){
        Cart3Add.click();
    }
    public static void clickOnRemoveButton3(){
        RemoveButton3.click();
    }
    public static void clickOnProduct4Image(){
        Product4Image.click();
    }
    public static void clickOnProduct4Link(){
        Product4Link.click();
    }

    public static void clickOnAddToCart4(){
        Cart4Add.click();
    }
    public static void clickOnRemoveButton4(){
        RemoveButton4.click();
    }
    public static void clickOnProduct5Image(){
        Product5Image.click();
    }
    public static void clickOnProduct5Link(){
        Product5Link.click();
    }

    public static void clickOnAddToCart5(){
        Cart5Add.click();
    }
    public static void clickOnRemoveButton5(){
        RemoveButton5.click();
    }
    public static void clickOnProduct6Image(){
        Product6Image.click();
    }
    public static void clickOnProduct6Link(){
        Product6Link.click();
    }

    public static void clickOnAddToCart6(){
        Cart6Add.click();
    }
    public static void clickOnRemoveButton6(){
        RemoveButton6.click();
    }
}