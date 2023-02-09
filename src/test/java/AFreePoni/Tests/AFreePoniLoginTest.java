package AFreePoni.Tests;

import AFreePoni.Base.BaseTest;
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


public class AFreePoniLoginTest extends BaseTest {
    @BeforeMethod
    public void pageSetUp() {
        driver = new ChromeDriver();
        wdwait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(homeURL);
        loginPage = new LoginPage();
        shopPage = new ShopPage();

    }
    @Test
    public void userCanLogIn() {


        for (int i = 1; i <4; i++) {
            driver.get(homeURL);
            String validUsername = excelReader.getStringData("Login", i, 0);
            String validPassword = excelReader.getStringData("Login", 1, 1);

            loginPage.insertUsername(validUsername);
            loginPage.insertPassword(validPassword);
            loginPage.clickOnLoginButton();

            Assert.assertFalse(isDisplayed(LoginPage.LoginButton));
            String expectedURL = excelReader.getStringData("URL", 1, 1);
            Assert.assertEquals(driver.getCurrentUrl(), expectedURL);

        }
            shopPage.clickOnMeniButton();
            wdwait.until(ExpectedConditions.elementToBeClickable(ShopPage.LogOutButton));
            shopPage.clickOnLogOutButton();
    }
        @Test
        public void userCanNotLoginWithInvalidUsername() {


            for (int i = 1; i<= excelReader.getLastRow("InvalidUsername"); i++) {
                driver.get(homeURL);
                String invalidUsername = excelReader.getStringData("InvalidUsername", i, 0);
                String validPassword = excelReader.getStringData("Login", 1, 1);

                loginPage.insertUsername(invalidUsername);
                loginPage.insertPassword(validPassword);
                loginPage.clickOnLoginButton();

                Assert.assertTrue(isDisplayed(LoginPage.LoginButton));
                String expectedURL = excelReader.getStringData("URL", 1,0);
                Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
            }

            }
    @Test
    public void userCanNotLoginWithInvalidPassword() {


        for (int i = 1; i<= excelReader.getLastRow("InvalidPassword"); i++) {
            driver.get(homeURL);
            String validUsername = excelReader.getStringData("Login", 1, 0);
            String invalidPassword = excelReader.getStringData("InvalidPassword", i, 0);

            loginPage.insertUsername(validUsername);
            loginPage.insertPassword(invalidPassword);
            loginPage.clickOnLoginButton();

            Assert.assertTrue(isDisplayed(LoginPage.LoginButton));
            String expectedURL = excelReader.getStringData("URL", 1,0);
            Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        }

    }
    @Test
    public void userCanNotLoginWithLockedUsername() {


        for (int i = 1; i<= excelReader.getLastRow("LockedUsername"); i++) {
            driver.get(homeURL);
            String invalidUsername = excelReader.getStringData("LockedUsername", 1, 0);
            String validPassword = excelReader.getStringData("Login", 1, 1);

            loginPage.insertUsername(invalidUsername);
            loginPage.insertPassword(validPassword);
            loginPage.clickOnLoginButton();

            Assert.assertTrue(isDisplayed(LoginPage.LoginButton));
            String expectedURL = excelReader.getStringData("URL", 1,0);
            Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        }

    }
    @Test
    public void userCanNotLoginWithoutUsername() {


            String invalidUsername = "";
            String validPassword = excelReader.getStringData("Login", 1, 1);

            loginPage.insertUsername(invalidUsername);
            loginPage.insertPassword(validPassword);
            loginPage.clickOnLoginButton();

            Assert.assertTrue(isDisplayed(LoginPage.LoginButton));
            String expectedURL = excelReader.getStringData("URL", 1,0);
            Assert.assertEquals(driver.getCurrentUrl(), expectedURL);



    }
    @Test
    public void userCanNotLoginWithoutPassword() {


        String validUsername = excelReader.getStringData("Login", 1, 0);
        String invalidPassword = "";

        loginPage.insertUsername(validUsername);
        loginPage.insertPassword(invalidPassword);
        loginPage.clickOnLoginButton();

        Assert.assertTrue(isDisplayed(LoginPage.LoginButton));
        String expectedURL = excelReader.getStringData("URL", 1,0);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);



    }
    @Test
    public void userCanNotLoginWithoutUsernameAndPassword() {


        String invalidUsername = "";
        String invalidPassword = "";

        loginPage.insertUsername(invalidUsername);
        loginPage.insertPassword(invalidPassword);
        loginPage.clickOnLoginButton();

        Assert.assertTrue(isDisplayed(LoginPage.LoginButton));
        String expectedURL = excelReader.getStringData("URL", 1,0);
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);



    }

    @AfterMethod
    public void driverClose()   {

        driver.quit();
    }


}

