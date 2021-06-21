package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiraDashboardPage {
    WebDriver driver;

    @FindBy(id="create_link")
    WebElement createButton;

    @FindBy(id="header-details-user-fullname")
    WebElement profileIcon;

    @FindBy(id="header-details-user-fullname")
    WebElement logoutOption;

    @FindBy(id="view_profile")
    WebElement profileOption;

    @FindBy(id="create-issue-dialog")
    WebElement createIssueWindow;

    @FindBy(id="project-field")
    WebElement projectField;

    @FindBy(id="issuetype-field")
    WebElement issueField;

    @FindBy(id="summary")
    WebElement summaryField;

    @FindBy(id="create-issue-submit")
    WebElement createIssueSubmitButton;

    @FindBy(id="//div[@id='aui-flag-container']/div/div/a")
    WebElement popUpNotification;

    @FindBy(xpath="//button[contains(text(),'Cancel')]")
    WebElement cancelButton;

    public JiraDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void clickOn(WebElement webElement){
        webElement.click();
    }

    public void typeInto(WebElement webElement, String inputText){
        webElement.sendKeys(inputText);
    }

    public String getTextOf(WebElement webElement){
        return webElement.getText();
    }

    public String getAttributeOf(WebElement webElement, String identifier){
        return webElement.getAttribute(identifier);
    }

}
