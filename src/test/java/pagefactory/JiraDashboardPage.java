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

    @FindBy(id="log_out")
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

    @FindBy(linkText = "Task")
    WebElement taskNamedOption;

    @FindBy(id = "quickSearchInput")
    WebElement searchingField;

    @FindBy(xpath="//iframe[@id='gadget-10003']")
    WebElement yourCompanyTitle;

    @FindBy(id="login-form-username")
    WebElement usernameField;

    @FindBy(id="login-form-password")
    WebElement passwordField;

    @FindBy(id="login")
    WebElement loginBtn;

    @FindBy(id="up-d-username")
    WebElement username;

    @FindBy(id="usernameerror")
    WebElement usernameError;

    @FindBy(id="user-options")
    WebElement navbarLoginBtn;

    @FindBy(id = "browse_link")
    WebElement projectsNavbarDropdown;

    @FindBy(id = "project_view_all_link_lnk")
    WebElement viewAllProjectsLink;

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(issueField));
    }

    public void waitUntilSummaryFieldClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(summaryField));
    }

    public void waitUntilPopUpNotificationClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(popUpNotification));
    }

    public void waitUntilSearchingFieldClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(searchingField));
    }

    public void waitUntilYourCompanyJiraTitleClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.elementToBeClickable(yourCompanyTitle));
    }

    public void waitUntilProjectFieldClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(projectField));
    }

    public void waitUntilIssuesOptionsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(taskNamedOption));

    }

    public void waitUntilIssueTypeFieldToNotExist(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(issueField));
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("issuetype-field"))));
    }

    public void waitUntilSummaryTypeFieldToNotExist(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(summaryField));
        wait.until(ExpectedConditions.not(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("summary"))));
    }

    public void clickOnNavbarLoginBtn() {navbarLoginBtn.click();}

    public void clickOnCreateButton(){
        createButton.click();
    }

    public void clickOnProfileIcon() {
        profileIcon.click();
    }

    public void sendKeysToUsernameField(String input) {
        usernameField.sendKeys(input);
    }

    public void sendKeysToPasswordField(String input) {
        passwordField.sendKeys(input);
    }

    public String getUsernameText() {
        return username.getText();
    }

    public void waitUntilUsernameErrorIsVisible() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(usernameError));
    }

    public boolean usernameErrorIsDisplayed() {
        return usernameError.isDisplayed();
    }

    public void clickOnLoginBtn() {loginBtn.click();}

    public void clickOnLogout(){
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

    public String getSerialNumberAttributeIssue() {
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

    public void selectIssueFieldContent() {
        issueField.sendKeys(Keys.chord(Keys.CONTROL, "a"));
    }

    public void clickOnSearchingField() {
        searchingField.click();
    }

    public void setSearchingFieldContent(String textContent) {
        searchingField.sendKeys(textContent);
    }

    public void pressEnterInSearchingField() {
        searchingField.sendKeys(Keys.ENTER);
    }

    public void waitUntilProjectsNavbarIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(projectsNavbarDropdown));
    }

    public void clickOnProjectsNavbarDropdown() {
        projectsNavbarDropdown.click();
    }

    public void clickOnViewAllProjectsLink() {
        viewAllProjectsLink.click();
    }
}
