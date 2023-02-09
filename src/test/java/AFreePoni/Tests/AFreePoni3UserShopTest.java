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
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AFreePoni3UserShopTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        driver.get(homeURL);
        loginPage = new LoginPage();
        shopPage = new ShopPage();
        cartPage = new CartPage();
    }


    public void Login(){
        String validUsername = excelReader.getStringData("Login", 3, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();
    }


    //standard_user tests
    @Test
    public void verifyLeftDropdownMeni() {

        Login();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
        shopPage.clickOnAboutButton();
        String aboutUrl = excelReader.getStringData("URL", 1, 2);
        Assert.assertEquals(driver.getCurrentUrl(), aboutUrl );
        driver.navigate().back();
        ShopPage.clickOnAddToCart();
        Assert.assertEquals(CartBadge.getText(), "1");
        shopPage.clickOnResetAppButton();
        Assert.assertFalse((isDisplayed(CartBadge)));
        shopPage.clickOnCloseButton();
        shopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
        shopPage.clickOnLogOutButton();
        Assert.assertEquals(driver.getCurrentUrl(), homeURL );
    }


    @Test
    public void verifyTwitterLinkInFooter() {
        Login();
        String originalWindow = driver.getWindowHandle();
        ShopPage.clickOnTwitterButton();
        String TwitterUrl = excelReader.getStringData("URL", 5, 0);
        wdwait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        wdwait.until(titleIs("Twitter"));
        Assert.assertEquals(driver.getCurrentUrl(), TwitterUrl);
    }


    @Test
    public void verifyFacebookLinkInFooter() {
        Login();
        String originalWindow = driver.getWindowHandle();
        ShopPage.clickOnFacebookButton();
        String FacebookUrl = excelReader.getStringData("URL", 8, 0);
        wdwait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        wdwait.until(ExpectedConditions.urlToBe("https://www.facebook.com/saucelabs"));
        Assert.assertEquals(driver.getCurrentUrl(), FacebookUrl);
    }


    @Test
    public void verifyLinkedinLinkInFooter() {
        Login();
        String originalWindow = driver.getWindowHandle();
        ShopPage.clickOnLinkedinButton();
        String LinkedinUrl = excelReader.getStringData("URL", 11, 0);
        wdwait.until(numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        wdwait.until(ExpectedConditions.urlToBe("https://www.linkedin.com/company/sauce-labs/?original_referer="));
        Assert.assertEquals(driver.getCurrentUrl(), LinkedinUrl);
    }


    @Test
    public void verifyCart() {
        Login();
        ShopPage.clickOnAddToCart();
        ShopPage.clickOnCart();
        Assert.assertEquals(checkQuantityInCart(), "1");
        Assert.assertTrue(isDisplayed(CartPage.CartQuantity));
        Assert.assertEquals(CartPage.checkProduct1Title(), "Sauce Labs Backpack" );
        CartPage.clickOnCartRemoveButton();
        Assert.assertFalse(isDisplayed(CartPage.CartQuantity));
        CartPage.clickOnCheckoutButton();
        String checkoutUrl = excelReader.getStringData("URL", 15, 0);
        Assert.assertEquals(driver.getCurrentUrl(), checkoutUrl);
        driver.navigate().back();
        CartPage.clickOnContinueShopping();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl );

    }



    // Left Side -First Row Product
    @Test
    public void verifyFirstProduct_OrderOneItem() {
        Login();
        ShopPage.clickOnProduct1Image();
        String product1Url = excelReader.getStringData("URL", 1, 3);
        Assert.assertEquals(driver.getCurrentUrl(), product1Url );
        driver.navigate().back();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl );
        ShopPage.clickOnProduct1Link();
        Assert.assertEquals(driver.getCurrentUrl(), product1Url );
        driver.navigate().back();
        ShopPage.clickOnAddToCart();
        ShopPage.clickOnCart();
        String cartUrl = excelReader.getStringData("URL", 1, 4);
        Assert.assertEquals(driver.getCurrentUrl(), cartUrl);
        Assert.assertEquals(checkQuantityInCart(), "1");
        Assert.assertEquals(CartPage.checkProduct1Title(), "Sauce Labs Backpack" );
        CartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl );

        shopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
        shopPage.clickOnLogOutButton();

    }


    //LeftSide - First Row Product and Right Side - First Row Product
    @Test
    public void verifyOrderTwoDifferentItems() {
        Login();
        //1.product
        ShopPage.clickOnAddToCart();
        //2.product
        ShopPage.clickOnAddToCart2();
        Assert.assertEquals(CartBadge.getText(), "2");

    }


    //Right Side - First Product
    @Test
    public void verifySecondProduct_OrderOneItem() {
        Login();
        ShopPage.clickOnProduct2Image();
        String product2Url = excelReader.getStringData("URL", 1, 5);
        Assert.assertEquals(driver.getCurrentUrl(), product2Url );
        driver.navigate().back();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl );
        ShopPage.clickOnProduct2Link();
        Assert.assertEquals(driver.getCurrentUrl(), product2Url );
        driver.navigate().back();
        ShopPage.clickOnAddToCart2();
        ShopPage.clickOnCart();
        String cartUrl = excelReader.getStringData("URL", 1, 4);
        Assert.assertEquals(driver.getCurrentUrl(), cartUrl);
        Assert.assertEquals(checkQuantityInCart(), "1");
        Assert.assertEquals(CartPage.checkProduct2Title(), "Sauce Labs Bike Light" );
        CartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl );
        ShopPage.clickOnRemoveButton2();
        ShopPage.clickOnCart();
        Assert.assertFalse(isDisplayed(CartPage.CartQuantity));
        shopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
        shopPage.clickOnLogOutButton();

    }


    //Left Side - Second Row Product
    @Test
    public void verifyThirdProduct_OrderOneItem() {
        Login();
        ShopPage.clickOnProduct3Image();
        String product3Url = excelReader.getStringData("URL", 1, 6);
        Assert.assertEquals(driver.getCurrentUrl(), product3Url);
        driver.navigate().back();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnProduct3Link();
        Assert.assertEquals(driver.getCurrentUrl(), product3Url);
        driver.navigate().back();
        ShopPage.clickOnAddToCart3();
        ShopPage.clickOnCart();
        String cartUrl = excelReader.getStringData("URL", 1, 4);
        Assert.assertEquals(driver.getCurrentUrl(), cartUrl);
        Assert.assertEquals(checkQuantityInCart(), "1");
        Assert.assertEquals(CartPage.checkProduct3Title(), "Sauce Labs Bolt T-Shirt");
        CartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnRemoveButton3();
        ShopPage.clickOnCart();
        Assert.assertFalse(isDisplayed(CartPage.CartQuantity));
        shopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
        shopPage.clickOnLogOutButton();

    }


    //Right Side - Second Row Product
    @Test
    public void verifyFourthProduct_OrderOneItem() {
        Login();
        ShopPage.clickOnProduct4Image();
        String product4Url = excelReader.getStringData("URL", 1, 7);
        Assert.assertEquals(driver.getCurrentUrl(), product4Url);
        driver.navigate().back();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnProduct4Link();
        Assert.assertEquals(driver.getCurrentUrl(), product4Url);
        driver.navigate().back();
        ShopPage.clickOnAddToCart4();
        ShopPage.clickOnCart();
        String cartUrl = excelReader.getStringData("URL", 1, 4);
        Assert.assertEquals(driver.getCurrentUrl(), cartUrl);
        Assert.assertEquals(checkQuantityInCart(), "1");
        Assert.assertEquals(CartPage.checkProduct4Title(), "Sauce Labs Fleece Jacket");
        CartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnRemoveButton4();
        ShopPage.clickOnCart();
        Assert.assertFalse(isDisplayed(CartPage.CartQuantity));
        shopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
        shopPage.clickOnLogOutButton();

    }



    //Left Side - Third Row Product
    @Test
    public void verifyFifthProduct_OrderOneItem() {
        Login();
        ShopPage.clickOnProduct5Image();
        String product5Url = excelReader.getStringData("URL", 1, 8);
        Assert.assertEquals(driver.getCurrentUrl(), product5Url);
        driver.navigate().back();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnProduct5Link();
        Assert.assertEquals(driver.getCurrentUrl(), product5Url);
        driver.navigate().back();
        ShopPage.clickOnAddToCart5();
        ShopPage.clickOnCart();
        String cartUrl = excelReader.getStringData("URL", 1, 4);
        Assert.assertEquals(driver.getCurrentUrl(), cartUrl);
        Assert.assertEquals(checkQuantityInCart(), "1");
        Assert.assertEquals(CartPage.checkProduct5Title(), "Sauce Labs Onesie");
        CartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnRemoveButton5();
        ShopPage.clickOnCart();
        Assert.assertFalse(isDisplayed(CartPage.CartQuantity));
        shopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
        shopPage.clickOnLogOutButton();

    }



    //Right Side - Third Row Product
    @Test
    public void verifySixthProduct_OrderOneItem() {
        Login();
        ShopPage.clickOnProduct6Image();
        String product6Url = excelReader.getStringData("URL", 1, 9);
        Assert.assertEquals(driver.getCurrentUrl(), product6Url);
        driver.navigate().back();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnProduct6Link();
        Assert.assertEquals(driver.getCurrentUrl(), product6Url);
        driver.navigate().back();
        ShopPage.clickOnAddToCart6();
        ShopPage.clickOnCart();
        String cartUrl = excelReader.getStringData("URL", 1, 4);
        Assert.assertEquals(driver.getCurrentUrl(), cartUrl);
        Assert.assertEquals(checkQuantityInCart(), "1");
        Assert.assertEquals(CartPage.checkProduct6Title(), "Test.allTheThings() T-Shirt (Red)");
        CartPage.clickOnContinueShopping();
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnRemoveButton6();
        ShopPage.clickOnCart();
        Assert.assertFalse(isDisplayed(CartPage.CartQuantity));
        shopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
        shopPage.clickOnLogOutButton();

    }
    @AfterMethod
    public void driverClose()   {

        driver.quit();
    }
}

