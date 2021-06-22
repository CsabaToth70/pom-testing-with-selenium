package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JiraLoginPage {

    WebDriver driver;

    WebDriverWait wait;

    @FindBy(id="login-form-username")
    WebElement usernameField;

    @FindBy(id="login-form-password")
    WebElement passwordField;

    @FindBy(id="login-form-submit")
    WebElement loginBtn;

    @FindBy(css = ".aui-message.aui-message-error")
    WebElement usernameError;

    public JiraLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitUntilUsernameErrorIsVisible() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(usernameError));
    }

    public boolean usernameErrorIsDisplayed() {
        return usernameError.isDisplayed();
    }

    public void sendKeysToUsernameField(String input) {
        usernameField.sendKeys(input);
    }

    public void sendKeysToPasswordField(String input) {
        passwordField.sendKeys(input);
    }

    public void clickOnLoginBtn() {loginBtn.click();}
}
