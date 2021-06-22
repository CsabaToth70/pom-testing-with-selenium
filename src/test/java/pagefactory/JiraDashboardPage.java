package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JiraDashboardPage {
    WebDriver driver;

    WebDriverWait wait;

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

    public JiraDashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitUntilProfileIsClickable() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(profileIcon));
    }

    public void clickOnCreateButton(){
        createButton.click();
    }

    public void clickOnProfileIcon(){
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

    public void clickOnProfileOption(){
        profileOption.click();
    }

    public void clickOnCreateIssueWindow(){
        createIssueWindow.click();
    }

    public void clickOnProjectField(){
        projectField.click();
    }

    public void clickOnIssueField(){
        issueField.click();
    }

    public void setIssueFieldContent(String content){
        issueField.sendKeys(content);
    }

    public void pressEnterInIssueField(){
        issueField.sendKeys(Keys.ENTER);
    }

    public void clickOnSummaryField(){
        summaryField.click();
    }

    public void setSummaryFieldContent(String content){
        summaryField.sendKeys(content);
    }

    public void clickOnCreateIssueSubmitButton(){
        createIssueSubmitButton.click();
    }

    public void clickOnPopUpNotification(){
        popUpNotification.click();
    }

    public void clickOnCancelCreateIssueButton(){
        cancelButton.click();
    }








}
