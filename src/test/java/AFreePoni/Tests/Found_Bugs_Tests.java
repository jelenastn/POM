package AFreePoni.Tests;

import AFreePoni.Base.BaseTest;
import AFreePoni.Pages.CartPage;
import AFreePoni.Pages.LoginPage;
import AFreePoni.Pages.ShopPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static AFreePoni.Pages.CartPage.checkQuantityInCart;
import static AFreePoni.Pages.ShopPage.CartBadge;

public class Found_Bugs_Tests extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(homeURL);
        loginPage = new LoginPage();
        shopPage = new ShopPage();
        cartPage = new CartPage();
    }
    public void Login(){
        String validUsername = excelReader.getStringData("Login", 1, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();
    }
    public void LoginUser2(){
        String validUsername = excelReader.getStringData("Login", 2, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();
    }

//FIRST USER - standard_user
//Nemoguće je naručiti vise od jednog artikla jer se pri kliku dugme iz Add trajno pretvori u Remove
    @Test
    public void CanNotOrderFirstProductTwoSameItems() {
        Login();
        ShopPage.clickOnAddToCart();
        ShopPage.clickOnCart();
        Assert.assertEquals(checkQuantityInCart(), "1");
        CartPage.clickOnContinueShopping();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        Assert.assertTrue(isDisplayed(ShopPage.Cart1Add));

    }
//Kada se resetuje porudžbina dugmetom Reset App, nestane broj naručenih artikala, ali i dalje ostaje aktivno dugme Remove umesto Add to Cart
    @Test
    public void ResetAndRemoveButtons(){
        LoginUser2();
        ShopPage.clickOnAddToCart();
        Assert.assertEquals(ShopPage.checkStatusCartBadge(), "1");
        ShopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.ResetAppButton));
        shopPage.clickOnResetAppButton();
        Assert.assertFalse((isDisplayed(CartBadge)));
        Assert.assertFalse(isDisplayed(ShopPage.RemoveButton1));

    }
    //--------------------------------------------------------------------------------------------------------
    //SECOND USER -problem_user
    @Test
    public void LeftDropdownMeniAboutPage(){
        LoginUser2();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
        shopPage.clickOnAboutButton();
        String aboutUrl = excelReader.getStringData("URL", 1, 2);
        Assert.assertEquals(driver.getCurrentUrl(), aboutUrl );
    }


//1.Product

    //Invalid Image Link and URL prvog proizvoda
    @Test
    public void verifyFirstProductImageLink() {

        LoginUser2();
        ShopPage.clickOnProduct1Image();
        String product1Url = excelReader.getStringData("URL", 1, 3);
        Assert.assertEquals(driver.getCurrentUrl(), product1Url);
    }
    //Invalid Title Link - First Product
    @Test
    public void verifyFirstProductTitleLink() {
        LoginUser2();
        String product1Url = excelReader.getStringData("URL", 1, 3);
        ShopPage.clickOnProduct1Link();
        Assert.assertEquals(driver.getCurrentUrl(), product1Url);
    }
    //doesn't work Button Remove on Shop Page
    @Test
    public void verifyRemoveButtonOnShopPage() {
        LoginUser2();
        ShopPage.clickOnAddToCart();
        ShopPage.clickOnRemoveButton1();
        ShopPage.clickOnCart();
        Assert.assertFalse(isDisplayed(CartPage.CartQuantity));

    }
    //2.Product
    //Invalid Image Link and URL - Second Product
    @Test
    public void verifySecondProductImageLink() {

        LoginUser2();
        ShopPage.clickOnProduct2Image();
        String product2Url = excelReader.getStringData("URL", 1, 5);
        Assert.assertEquals(driver.getCurrentUrl(), product2Url);
    }
    //Invalid Title Link - First Product
    @Test
    public void verifySecondProductTitleLink() {
        LoginUser2();
        String product2Url = excelReader.getStringData("URL", 1, 5);
        ShopPage.clickOnProduct2Link();
        Assert.assertEquals(driver.getCurrentUrl(), product2Url);
    }
    //doesn't work Button Remove on Shop Page
    @Test
    public void verifyRemoveButtonOnShopPageSecondProduct() {
        LoginUser2();
        ShopPage.clickOnAddToCart2();
        ShopPage.clickOnRemoveButton2();
        ShopPage.clickOnCart();
        Assert.assertFalse(isDisplayed(CartPage.CartQuantity));

    }
   // 3.Product
//Invalid Image link - Third Product
    @Test
    public void verifyThirdProductImageLink() {

        LoginUser2();
        ShopPage.clickOnProduct3Image();
        String product3Url = excelReader.getStringData("URL", 1, 6);
        Assert.assertEquals(driver.getCurrentUrl(), product3Url);
    }
    //Invalid Title Link - Third Product
    @Test
    public void verifyThirdProductTitleLink() {
        LoginUser2();
        String product3Url = excelReader.getStringData("URL", 1, 6);
        ShopPage.clickOnProduct3Link();
        Assert.assertEquals(driver.getCurrentUrl(), product3Url);
    }
    // doesn't work Button Add To Cart and Remove on Shop Page
    @Test
    public void verifyRemoveButtonOnShopPageThirdProduct() {
        LoginUser2();
        ShopPage.clickOnAddToCart3();
        ShopPage.clickOnCart();
        Assert.assertEquals(checkQuantityInCart(), "1");

    }
    //4.Product
//Invalid Image Link and URL
    @Test
    public void verifyFourthProductImageLink() {

        LoginUser2();
        ShopPage.clickOnProduct4Image();
        String product4Url = excelReader.getStringData("URL", 1, 7);
        Assert.assertEquals(driver.getCurrentUrl(), product4Url);
    }
    //Invalid Title Link - Third Product
    @Test
    public void verifyFourthProductTitleLink() {
        LoginUser2();
        String product4Url = excelReader.getStringData("URL", 1, 7);
        ShopPage.clickOnProduct4Link();
        Assert.assertEquals(driver.getCurrentUrl(), product4Url);
    }
    //Doesn't work Button Add To Cart and Remove on Shop Page
    @Test
    public void verifyRemoveButtonOnShopPageFourthProduct() {
        LoginUser2();
        ShopPage.clickOnAddToCart4();
        ShopPage.clickOnCart();
        Assert.assertEquals(checkQuantityInCart(), "1");

    }
//5.Product
    //Invalid Image Link, URL
    @Test
    public void verifyFifthProductImageLink() {

        LoginUser2();
        ShopPage.clickOnProduct5Image();
        String product5Url = excelReader.getStringData("URL", 1, 8);
        Assert.assertEquals(driver.getCurrentUrl(), product5Url);
    }
    //Invalid Product Title Link
    @Test
    public void verifyFifthProductTitleLink() {
        LoginUser2();
        String product5Url = excelReader.getStringData("URL", 1, 8);
        ShopPage.clickOnProduct5Link();
        Assert.assertEquals(driver.getCurrentUrl(), product5Url);
    }
    //Doesn't work Button Remove on Shop Page
    @Test
    public void verifyRemoveButtonOnShopPageFifthProduct() {
        LoginUser2();
        ShopPage.clickOnAddToCart5();
        ShopPage.clickOnRemoveButton5();
        ShopPage.clickOnCart();
        Assert.assertFalse(isDisplayed(CartPage.CartQuantity));

    }
    //6.Product
    //Invalid Image Link, URL
    @Test
    public void verifySixthProductImageLink() {

        LoginUser2();
        ShopPage.clickOnProduct6Image();
        String product6Url = excelReader.getStringData("URL", 1, 9);
        Assert.assertEquals(driver.getCurrentUrl(), product6Url);
    }
    //Invalid Title Link - Sixth Product
    @Test
    public void verifySixthProductTitleLink() {
        LoginUser2();
        String product6Url = excelReader.getStringData("URL", 1, 9);
        ShopPage.clickOnProduct6Link();
        Assert.assertEquals(driver.getCurrentUrl(), product6Url);
    }
    //doesn't work Button Add To Cart and Remove on Shop Page
    @Test
    public void verifyRemoveButtonOnShopPageSixthProduct() {
        LoginUser2();
        ShopPage.clickOnAddToCart6();
        ShopPage.clickOnCart();
        Assert.assertEquals(checkQuantityInCart(), "1");

    }

    @AfterMethod
    public void driverClose()   {

        driver.quit();
    }


}
