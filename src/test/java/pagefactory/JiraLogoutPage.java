package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JiraLogoutPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(css=".aui-message.aui-message-info")
    WebElement loggedOutMessage;

    public JiraLogoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
    }

    String getLoggedOutMessageText() {
        return loggedOutMessage.getText();
    }

    public void waitUntilLoggedOutMessageIsVisible() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(loggedOutMessage));
    }

    public boolean mainTextBoxContainsLogoutMessage() {
        return getLoggedOutMessageText().toLowerCase().contains("logged out");
    }
}
