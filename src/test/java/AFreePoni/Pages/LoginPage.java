package AFreePoni.Pages;

import AFreePoni.Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {
    public LoginPage(){
        PageFactory.initElements(driver, this);
    }



    public @FindBy(id = "user-name")
    WebElement Username;

    public @FindBy(id = "password")
    WebElement Password;

    public static @FindBy(id = "login-button")
    WebElement LoginButton;

    public @FindBy(css = ".error-message-container.error")
    WebElement Notification;

//----------------------------------------------------------------------------------


    public void insertUsername(String username) {

        Username.clear();
        Username.sendKeys(username);
      //  wdwait.until(ExpectedConditions.urlToBe("https://www.saucedemo.com/"));
    }

    public void insertPassword(String password) {
        Password.clear();
        Password.sendKeys(password);
    }

    public void clickOnLoginButton() {
        LoginButton.click();
    }
    public void errorMessage() {Notification.getText();

    }
}
