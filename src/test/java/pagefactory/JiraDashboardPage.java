package pagefactory;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalTime;

public class JiraDashboardPage {
    WebDriver driver;

    WebDriverWait wait;

    @FindBy(id = "create_link")
    WebElement createButton;

    @FindBy(id = "header-details-user-fullname")
    WebElement profileIcon;

    @FindBy(id = "header-details-user-fullname")
    WebElement logoutOption;

    @FindBy(id = "view_profile")
    WebElement profileOption;

    @FindBy(id = "create-issue-dialog")
    WebElement createIssueWindow;

    @FindBy(id = "project-field")
    WebElement projectField;

    @FindBy(id = "issuetype-field")
    WebElement issueField;

    @FindBy(id = "summary")
    WebElement summaryField;

    @FindBy(id = "create-issue-submit")
    WebElement createIssueSubmitButton;

    @FindBy(xpath = "//div[@id='aui-flag-container']/div/div/a")
    WebElement popUpNotification;

    @FindBy(xpath = "//button[contains(text(),'Cancel')]")
    WebElement cancelButton;

    @FindBy(xpath = "//body/section[@id='create-issue-dialog']/div[1]")
    WebElement nonInputSurfaceOfThePage;

    @FindBy(xpath = "//*[@id=\"dialog-form\"]/div/div[2]/div[1]/div")
    WebElement alertSummarySpecification;

    @FindBy(xpath = "//body/div[8]")
    WebElement projectDropdownMenu;

    @FindBy(css = "#issuetype-single-select > .icon")
    WebElement dropDownArrow;

    @FindBy(linkText = "Task")
    WebElement taskNamedOption;

    @FindBy(linkText = "Bug")
    WebElement bugNamedOption;

    @FindBy(id="quickSearchInput")
    WebElement searchingField;

    public JiraDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 5), this);
    }

    public void waitUntilProfileIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(profileIcon));
    }

    public void waitUntilCreateIssueWindowClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(createIssueWindow));
    }

    public void waitUntilIssueFieldClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(issueField));
    }

    public void waitUntilSummaryFieldClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(summaryField));
    }

    public void waitUntilPopUpNotificationClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(popUpNotification));
    }

    public void waitUntilProjectFieldClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(projectField));
    }

    public void waitUntilIssuesOptionsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(taskNamedOption));

    }

    public void waitUntilInvisibilityOfIssueField() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(issueField));
    }

    public void waitUntilInvisibilityOfSummaryField() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.invisibilityOf(summaryField));
    }

    public void waitForIssueTypeFieldToNotExist() {
        Boolean isExists = true;
        Boolean isNotExpiredTime = true;
        int waitedSeconds = 2;
        LocalTime endTime = LocalTime.now().plusSeconds(waitedSeconds);
        while (isExists && isNotExpiredTime) {
            try {
                driver.findElement(By.id("issuetype-field"));
            } catch (NoSuchElementException e) {
                System.out.println("NoSuchElementException thrown from waiting for not issue field being not exist.");
                waitUntilIssueFieldClickable();
                isExists = false;
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException thrown from waiting for not issue field being not exist.");
                waitUntilIssueFieldClickable();
                isExists = false;
                break;
            }
            if (endTime.isBefore(LocalTime.now())) {
                isNotExpiredTime = false;
                System.out.println("Waiting for the issue field being not exist ran out of set time limit.");
            }
        }
    }

    public void waitForSummaryTypeFieldToNotExist() {
        Boolean isExists = true;
        Boolean isNotExpiredTime = true;
        int waitedSeconds = 2;
        LocalTime endTime = LocalTime.now().plusSeconds(waitedSeconds);
        while (isExists && isNotExpiredTime) {
            try {
                driver.findElement(By.id("summary"));
            } catch (NoSuchElementException e) {
                System.out.println("NoSuchElementException thrown from waiting for not issue field being not exist.");
                waitUntilSummaryFieldClickable();
                isExists = false;
                break;
            } catch (StaleElementReferenceException e) {
                System.out.println("StaleElementReferenceException thrown from waiting for not issue field being not exist.");
                waitUntilSummaryFieldClickable();
                isExists = false;
                break;
            }
            if (endTime.isBefore(LocalTime.now())) {
                isNotExpiredTime = false;
                System.out.println("Waiting for the issue field being not exist ran out of set time limit.");
            }
        }
    }

    public void clickOnCreateButton() {
        createButton.click();
    }

    public void clickOnProfileIcon() {
        profileIcon.click();
    }

    public void clickOnLogout() {
        logoutOption.click();
    }

    public void clickOnProfileOption() {
        profileOption.click();
    }

    public void clickOnCreateIssueWindow() {
        createIssueWindow.click();
    }

    public void clickOnProjectField() {
        projectField.click();
    }

    public void clickOnIssueField() {
        issueField.click();
    }

    public void setIssueFieldContent(String content) {

        issueField.sendKeys(content);
    }

    public void pressEnterInIssueField() {
        issueField.sendKeys(Keys.ENTER);
    }

    public void clickOnSummaryField() {
        summaryField.click();
    }

    public void setSummaryFieldContent(String content) {
        summaryField.sendKeys(content);
    }

    public void clickOnCreateIssueSubmitButton() {
        createIssueSubmitButton.click();
    }

    public void clickOnPopUpNotification() {
        popUpNotification.click();
    }

    public String getSerialNumberAttributeIssue(){
        return popUpNotification.getAttribute("data-issue-key");
    }

    public void clickOnCancelCreateIssueButton() {
        cancelButton.click();
    }

    public void setProjectFieldContent(String content) {
        projectField.sendKeys(content);
    }

    public void pressEnterInProjectField() {
        projectField.sendKeys(Keys.ENTER);
    }

    public void clickOnNonInputSurfaceOfThePage() {
        nonInputSurfaceOfThePage.click();
    }

    public String getProjectFieldContent() {
        return projectField.getAttribute("value");
    }

    public Boolean isDisplayedSummaryAlert() {
        return alertSummarySpecification.isDisplayed();
    }

    public void clearIssueFieldContent() {
        issueField.clear();
    }

    public void selectIssueFieldContent() {
        issueField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    }

    public void clickOnIssueDropdownArrow() {
        dropDownArrow.click();
    }

    public void clickOnTaskOption() {
        taskNamedOption.click();
    }

    public void clickOnBugOption() {
        bugNamedOption.click();
    }

    public void clickOnSearchingField(){
        searchingField.click();
        }

    public void setSearchingFieldContent(String textContent){
        searchingField.sendKeys(textContent);
    }

    public void pressEnterInSearchingField() {
        searchingField.sendKeys(Keys.ENTER);
    }

}
