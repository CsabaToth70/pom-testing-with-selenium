package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.JiraBrowsePage;
import pagefactory.JiraDashboardPage;
import pagefactory.JiraEmptyPage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestJiraCreateIssue {
    WebDriver driver;
    JiraBrowsePage objBrowsePage;
    JiraDashboardPage objDashboardPage;
    JiraEmptyPage objEmptyPage;

    @BeforeEach
    public void setup() throws IOException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        TestingHelper.setUpWithLogin(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    void createIssue() {
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        String issueName = "Test create issue id8";

        objDashboardPage.clickOnCreateButton();
        objDashboardPage.waitUntilCreateIssueWindowClickable();
        objDashboardPage.clickOnProjectField();
        objDashboardPage.setProjectFieldContent("Main Testing Project (MTP)");

        objDashboardPage.clickOnNonInputSurfaceOfThePage();
        objDashboardPage.waitUntilIssueFieldClickable();
        objDashboardPage.setIssueFieldContent("Bug");

        objDashboardPage.clickOnNonInputSurfaceOfThePage();
        objDashboardPage.waitUntilSummaryFieldClickable();

        objDashboardPage.clickOnSummaryField();
        objDashboardPage.setSummaryFieldContent(issueName);
        objDashboardPage.clickOnCreateIssueSubmitButton();

        objDashboardPage.waitUntilPopUpNotificationClickable();
        objDashboardPage.clickOnPopUpNotification();
        assertTrue(objBrowsePage.getIssueSummaryValue().equals(issueName));

        objBrowsePage.clickOnMoreMenuButtonIssue();
        objBrowsePage.clickOnDeleteOption();
        objBrowsePage.clickOnDeleteButtonConfirmationPopUp();
    }

    @Test
    void createIssueInTOUCANProject() {
        objDashboardPage = new JiraDashboardPage(driver);
        String projectName = "TOUCAN project (TOUCAN)";
        TestingHelper.createIssueInAProjectOf(projectName, objDashboardPage);
        assertTrue(objDashboardPage.getProjectFieldContent().equals(projectName));
    }

    @Test
    void createIssueInJETIProject() {
        objDashboardPage = new JiraDashboardPage(driver);
        String projectName = "JETI project (JETI)";
        TestingHelper.createIssueInAProjectOf(projectName, objDashboardPage);
        assertTrue(objDashboardPage.getProjectFieldContent().equals(projectName));
    }

    @Test
    void createIssueInCOALAProject() {
        objDashboardPage = new JiraDashboardPage(driver);
        String projectName = "COALA project (COALA)";
        TestingHelper.createIssueInAProjectOf(projectName, objDashboardPage);
        assertTrue(objDashboardPage.getProjectFieldContent().equals(projectName));
    }

    @Test
    void createIssueInNonExistentProject() {
        objDashboardPage = new JiraDashboardPage(driver);
        String projectName = "Main Testing Project (MTP)";
        TestingHelper.createIssueInAProjectOf(projectName, objDashboardPage);
        // wait project field - skipped step here
        objDashboardPage.clickOnProjectField();
        objDashboardPage.setProjectFieldContent("noname");
        objDashboardPage.clickOnNonInputSurfaceOfThePage();
        objDashboardPage.waitUntilSummaryFieldClickable();
        objDashboardPage.clickOnSummaryField();
        assertTrue(objDashboardPage.getProjectFieldContent().equals(projectName));
    }

    @Test
    void createIssueWithNoSummary() {
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        String projectName = "Main Testing Project (MTP)";

        objDashboardPage.clickOnCreateButton();
        objDashboardPage.waitUntilCreateIssueWindowClickable();
        objDashboardPage.clickOnProjectField();
        objDashboardPage.setProjectFieldContent(projectName);

        objDashboardPage.clickOnNonInputSurfaceOfThePage();
        objDashboardPage.waitUntilSummaryFieldClickable();
        objDashboardPage.clickOnSummaryField();
        objDashboardPage.clickOnCreateIssueSubmitButton();
        assertTrue(objDashboardPage.isDisplayedSummaryAlert());
    }

    @Test
    void cancelIssueCreation() {
        objEmptyPage = new JiraEmptyPage(driver);
        objDashboardPage = new JiraDashboardPage(driver);
        String projectName = "Main Testing Project (MTP)";
        String issueName = "Issue does not exist id:9238";

        objDashboardPage.clickOnCreateButton();
        objDashboardPage.waitUntilCreateIssueWindowClickable();
        objDashboardPage.clickOnProjectField();
        objDashboardPage.setProjectFieldContent(projectName);

        objDashboardPage.clickOnNonInputSurfaceOfThePage();
        objDashboardPage.waitUntilSummaryFieldClickable();
        objDashboardPage.clickOnSummaryField();
        objDashboardPage.setSummaryFieldContent(issueName);
        objDashboardPage.clickOnCancelCreateIssueButton();
        driver.switchTo().alert().accept();
        assertTrue(objEmptyPage.isDisplayedNoIssuesWereFound());
    }


}
