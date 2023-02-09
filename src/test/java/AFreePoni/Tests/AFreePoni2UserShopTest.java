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

public class AFreePoni2UserShopTest extends BaseTest {
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
        String validUsername = excelReader.getStringData("Login", 2, 0);
        String validPassword = excelReader.getStringData("Login", 1, 1);
        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(validPassword);
        loginPage.clickOnLoginButton();
    }


    @Test
    public void verifyLeftDropdownMeni() {
//About Page Link is invalid -it is in Found Bugs
        Login();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
        ShopPage.clickOnAddToCart();
        Assert.assertEquals(CartBadge.getText(), "1");
        ShopPage.clickOnMeniButton();
        wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
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
        Assert.assertEquals(CartPage.checkProduct1Title(), "Sauce Labs Backpack");
        CartPage.clickOnCartRemoveButton();
        Assert.assertFalse(isDisplayed(CartPage.CartQuantity));
        CartPage.clickOnCheckoutButton();
        String checkoutUrl = excelReader.getStringData("URL", 15, 0);
        Assert.assertEquals(driver.getCurrentUrl(), checkoutUrl);
        driver.navigate().back();
        CartPage.clickOnContinueShopping();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);

    }


        @Test
    public void verifyFirstProduct_OrderOneItem() {
        //In Found Bugs - product image, product link, remove button
        Login();
        ShopPage.clickOnAddToCart();
        ShopPage.clickOnCart();
        String cartUrl = excelReader.getStringData("URL", 1, 4);
        Assert.assertEquals(driver.getCurrentUrl(), cartUrl);
        Assert.assertEquals(checkQuantityInCart(), "1");
        Assert.assertEquals(CartPage.checkProduct1Title(), "Sauce Labs Backpack" );
        CartPage.clickOnContinueShopping();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl );

    }


    @Test
    public void verifyOrderTwoDifferentItems() {
        Login();
        //1.product
        ShopPage.clickOnAddToCart();
        //2.product
        ShopPage.clickOnAddToCart2();
        Assert.assertEquals(CartBadge.getText(), "2");

    }


    @Test
    public void verifySecondProduct_OrderOneItem() {
      //In  Found Bugs - product image, product link, remove button
        Login();
        ShopPage.clickOnAddToCart2();
        ShopPage.clickOnCart();
        String cartUrl = excelReader.getStringData("URL", 1, 4);
        Assert.assertEquals(driver.getCurrentUrl(), cartUrl);
        Assert.assertEquals(checkQuantityInCart(), "1");
        Assert.assertEquals(CartPage.checkProduct2Title(), "Sauce Labs Bike Light");
        CartPage.clickOnContinueShopping();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);

    }


    @Test
    public void verifyThirdProduct_OrderOneItem() {
      //  In Found Bugs - Product Image link, Product image title link, Remove button, Add to cart button
        Login();
        ShopPage.clickOnCart();
        CartPage.clickOnContinueShopping();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);

    }


    @Test
    public void verifyFourthProduct_OrderOneItem() {
        // In Found Bugs - Product Image link, Product image title link, Remove button, Add to cart button
        Login();
        ShopPage.clickOnCart();
        CartPage.clickOnContinueShopping();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
    }


    @Test
    public void verifyFifthProduct_OrderOneItem() {
        // In Found Bugs - Product Image link, Product image title link, Remove button
        Login();
        ShopPage.clickOnAddToCart5();
        ShopPage.clickOnCart();
        String cartUrl = excelReader.getStringData("URL", 1, 4);
        Assert.assertEquals(driver.getCurrentUrl(), cartUrl);
        Assert.assertEquals(checkQuantityInCart(), "1");
        Assert.assertEquals(CartPage.checkProduct5Title(), "Sauce Labs Onesie");
        CartPage.clickOnContinueShopping();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);

    }


    @Test
    public void verifySixthProduct_OrderOneItem() {
        Login();
        ShopPage.clickOnCart();
        CartPage.clickOnContinueShopping();
        String shopUrl = excelReader.getStringData("URL", 1, 1);
        Assert.assertEquals(driver.getCurrentUrl(), shopUrl);
    }


    @AfterMethod
    public void driverClose()   {

        driver.quit();
    }

}
