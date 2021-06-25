package pagefactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalTime;

public class JiraBrowsePage {
    WebDriver driver;

    WebDriverWait wait;

    @FindBy(id = "project-name-val")
    WebElement projectName;

    @FindBy(id = "key-val")
    WebElement issueIdentifier;

    @FindBy(xpath = "//dd[contains(text(),'MTP')]")
    WebElement mtpProjectIdentifier;

    @FindBy(xpath = "//dd[contains(text(),'COALA')]")
    WebElement coalaProjectIdentifier;

    @FindBy(xpath = "//dd[contains(text(),'JETI')]")
    WebElement jetiProjectIdentifier;

    @FindBy(xpath = "//dd[contains(text(),'TOUCAN')]")
    WebElement toucanProjectIdentifier;

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

    @FindBy(xpath = "//body/section[@id='edit-issue-dialog']/div[1]")
    WebElement nonInputSurfaceIssueWindow;

    @FindBy(id = "create-issue-submit")
    WebElement subtaskCreationSubmit;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement cancelSubtaskCreation;

    @FindBy(id = "edit-issue")
    WebElement editIssueButton;

    @FindBy(id = "edit-issue-submit")
    WebElement editIssueSubmit;

    @FindBy(linkText = "Test create sub-task id:937")
    WebElement createdSubtaskName;

    @FindBy(xpath = "//a[contains(text(),\'Test create sub-task id:937\')]")
    WebElement testCreatedSubtaskLinkByName;

    @FindBy(xpath = "//a[@id='parent_issue_summary']")
    WebElement issueNameInTopOfSubtaskWindow;

    @FindBy(xpath = "xpath=//section[@id='sidebar']/div/div/div/div/div[2]/h1/div/div/a")
    WebElement projectNameInSidebar;

    @FindBy(xpath = "//span[contains(text(),'Issues')]")
    WebElement issuesSidebarText;

    @FindBy(xpath = "//input[@id='issuetype-field']")
    WebElement issueTypeField;

    public JiraBrowsePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public void waitUntilDeleteButtonConfirmationPopUpClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("Delete")));
    }

    public void waitUntilMoreMenuButtonIsVisible() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(moreMenuButtonIssue));
    }

    public void waitUntilIssueSummaryValueIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(issueSummaryValue));
    }

    public void waitUntilSummaryFieldOfPopupWindowIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(summaryField));
    }

    public void clearSummaryFieldContent(){
        summaryField.clear();
    }

    public void setSelectionOfSummaryFieldContent(){
        summaryField.sendKeys(Keys.CONTROL, "a");
    }

    public void waitUntilCreatedSubtaskNameIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(createdSubtaskName));
    }

    public void waitUntilIssuesSidebarTextIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(issuesSidebarText));
    }

    public void waitUntilIssueNameInTopOfSubtaskWindowIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(issueNameInTopOfSubtaskWindow));

    }

    public void waitUntilTestCreatedSubtaskLinkByNameIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(testCreatedSubtaskLinkByName));
    }

    public void waitUntilIssueTypeFieldIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(issueTypeField));
    }

    public void waitUntilEditIssueSubmitIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(editIssueSubmit));
    }

    public void waitUntilEditIssueSubmitBeNotClickable() {
        Boolean isExists = true;
        Boolean isNotExpiredTime = true;
        int waitedSeconds = 5;
        LocalTime endTime = LocalTime.now().plusSeconds(waitedSeconds);
        while (isExists && isNotExpiredTime) {
            try {
                driver.findElement(By.id("edit-issue-submit"));
                isExists = true;
            } catch (NoSuchElementException e) {
                System.out.println("NoSuchElementException thrown from waiting for edit issue submit button being not exist.");
                isExists = false;
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException thrown from waiting for edit issue submit button being not exist.");
                isExists = false;
                break;
            }
            if (endTime.isBefore(LocalTime.now())) {
                isNotExpiredTime = false;
                System.out.println("Waiting for the edit issue submit button being not exist ran out of set time limit.");
            }
        }
    }

    public void waitUntilEditIssueButtonIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(editIssueButton));
    }

    public void waitUntilMoreMenuButtonIssueIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(moreMenuButtonIssue));
    }

    public void waitUntilProjectNameInSidebarIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(projectNameInSidebar));
    }

    public String getProjectName() {
        return projectName.getText();
    }

    public String getIssueIdentifier() {
        return issueIdentifier.getText();
    }

    public String getIssueSummaryValue() {
        return issueSummaryValue.getText();
    }

    public void waitUntilIssueNameIsVisible() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.visibilityOf(issueSummaryValue));
    }

    public void clickOnMoreMenuButtonIssue() {
        moreMenuButtonIssue.click();
    }

    public void clickOnDeleteOption() {
        deleteOption.click();
    }

    public void clickOnDeleteButtonConfirmationPopUp() {
        deleteButtonConfirmationPopUp.click();
    }

    public void clickOnCancelButtonConfirmationPopUp() {
        cancelButtonConfirmationPopUp.click();
    }

    public Boolean isDisplayedCreateSubtask() {
        return createSubtaskOption.isDisplayed();
    }

    public void clickOnCreateSubtaskOption() {
        createSubtaskOption.click();
    }

    public void clickOnSummaryField() {
        summaryField.click();
    }

    public void setSummaryFieldContent(String content) {
        summaryField.sendKeys(content);
    }

    public String getSummaryFieldContent() {
        return summaryField.getText();
    }

    public void clickOutOfField() {
        nonInputSurfaceSubtaskWindow.click();
    }

    public void clickOnSubtaskCreationSubmit() {
        subtaskCreationSubmit.click();
    }

    public void clickOnCancelSubtaskCreation() {
        cancelSubtaskCreation.click();
    }

    public void clickOnEditIssueButton() {
        editIssueButton.click();
    }

    public Boolean isDisplayedEditIssueButton() {
        return editIssueButton.isDisplayed();
    }

    public void clickOnEditIssueSubmit() {
        editIssueSubmit.click();
    }

    public Boolean isDisplayedEditIssueSubmit() {
        return editIssueSubmit.isDisplayed();
    }

    public void clickOnNonInputSurfaceSubtaskWindow() {
        nonInputSurfaceSubtaskWindow.click();
    }

    public void clickOnTestCreatedSubtaskLinkByName() {
        testCreatedSubtaskLinkByName.click();
    }

    public void clickOnNonInputSurfaceIssueWindow(){
        nonInputSurfaceIssueWindow.click();
    }

    public void navigateToProjectSummary(String url) {
        driver.get(url);
    }

    void waitUntilProjectIdentifierIsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public String getProjectIdentifierText(WebElement element) {
        return element.getText();
    }

    public void waitForProjectIdentifierByInput(String project) {
        switch (project) {
            case "MTP":
                waitUntilProjectIdentifierIsVisible(mtpProjectIdentifier);
                break;
            case "JETI":
                waitUntilProjectIdentifierIsVisible(jetiProjectIdentifier);
                break;
            case "COALA":
                waitUntilProjectIdentifierIsVisible(coalaProjectIdentifier);
                break;
            case "TOUCAN":
                waitUntilProjectIdentifierIsVisible(toucanProjectIdentifier);
                break;
        }
    }

    public String getProjectIdentifierTextByInput(String project) {
        String identifierText = "";
        switch (project) {
            case "MTP":
                identifierText = getProjectIdentifierText(mtpProjectIdentifier);
                break;
            case "JETI":
                identifierText = getProjectIdentifierText(jetiProjectIdentifier);
                break;
            case "COALA":
                identifierText = getProjectIdentifierText(coalaProjectIdentifier);
                break;
            case "TOUCAN":
                identifierText = getProjectIdentifierText(toucanProjectIdentifier);
                break;
        }
        return identifierText;
    }
}
