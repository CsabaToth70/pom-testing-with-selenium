package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JiraBrowsePage {
    WebDriver drive;

    @FindBy(id = "project-name-val")
    WebElement projectName;

    @FindBy(id = "key-val")
    WebElement issueIdentifier;

    @FindBy(id = "summary-val")
    WebElement issueSummaryValue;

    @FindBy(id = "opsbar-operations_more")
    WebElement moreMenuButtonIssue;

    @FindBy(xpath = "//*[@id=\"delete-issue\"]/a")
    WebElement deleteOption;

    @FindBy(name = "Delete")
    WebElement deleteButtonConfirmationPopUp;

    @FindBy(id = "delete-issue-cancel")
    WebElement cancelButtonConfirmationPopUp;

    @FindBy(id = "create-subtask")
    WebElement createSubtaskOption;

    @FindBy(id = "summary")
    WebElement summaryField;

    @FindBy(css = ".content > .field-group:nth-child(1)")
    WebElement nonInputSurfaceSubtaskWindow;

    @FindBy(id = "create-issue-submit")
    WebElement subtaskCreationSubmit;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement cancelSubtaskCreation;

    @FindBy(id = "edit-issue")
    WebElement editIssueButton;

    @FindBy(id = "edit-issue-submit")
    WebElement editIssueSubmit;

    public JiraBrowsePage(WebDriver drive) {
        this.drive = drive;
        PageFactory.initElements(drive, this);
    }

    public void clickOn(WebElement webElement) {
        webElement.click();
    }

    public void typeInto(WebElement webElement, String inputText) {
        webElement.sendKeys(inputText);
    }

    public String getTextOf(WebElement webElement) {
        return webElement.getText();
    }

    public String getAttributeOf(WebElement webElement, String identifier) {
        return webElement.getAttribute(identifier);
    }


}
