package test;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.JiraBrowsePage;
import pagefactory.JiraDashboardPage;
import pagefactory.JiraEmptyPage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        String projectName = "Main Testing Project (MTP)";
        String issueName = "Test create issue id8";

        objDashboardPage.clickOnCreateButton();
        objDashboardPage.waitUntilCreateIssueWindowClickable();
        objDashboardPage.clickOnProjectField();
        objDashboardPage.setProjectFieldContent(projectName);

        try {
            objDashboardPage.waitUntilIssueTypeFieldToNotExist();
            objDashboardPage.waitUntilIssueFieldClickable();
        } catch (Exception e) {
            System.out.println("Timeout error thrown at issue field wait.");
        }
        objDashboardPage.clickOnNonInputSurfaceOfThePage();
        objDashboardPage.waitUntilIssueFieldClickable();
        objDashboardPage.clickOnIssueField();
        objDashboardPage.selectIssueFieldContent();
        objDashboardPage.setIssueFieldContent("Bug");
        objDashboardPage.pressEnterInIssueField();

        try {
            objDashboardPage.waitUntilSummaryTypeFieldToNotExist();
            objDashboardPage.waitUntilSummaryFieldClickable();
        } catch (Exception e) {
            System.out.println("Timeout error thrown at issue field wait.");
        }
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
        objBrowsePage.waitUntilDeleteButtonConfirmationPopUpClickable();
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
        try {
            objDashboardPage.waitUntilSummaryTypeFieldToNotExist();
            objDashboardPage.waitUntilSummaryFieldClickable();
        } catch (Exception e) {
            System.out.println("Timeout error thrown at issue field wait.");
        }
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

        try {
            objDashboardPage.waitUntilSummaryTypeFieldToNotExist();
            objDashboardPage.waitUntilSummaryFieldClickable();
        } catch (Exception e) {
            System.out.println("Timeout error thrown at issue field wait.");
        }
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

        try {
            objDashboardPage.waitUntilSummaryTypeFieldToNotExist();
            objDashboardPage.waitUntilSummaryFieldClickable();
        } catch (Exception e) {
            System.out.println("Timeout error thrown at issue field wait.");
        }
        objDashboardPage.clickOnNonInputSurfaceOfThePage();
        objDashboardPage.waitUntilSummaryFieldClickable();
        objDashboardPage.clickOnSummaryField();
        objDashboardPage.setSummaryFieldContent(issueName);
        objDashboardPage.clickOnCancelCreateIssueButton();
        driver.switchTo().alert().accept();
        assertTrue(objEmptyPage.isDisplayedNoIssuesWereFound());
    }

    @Test
    void createSubtaskTest() {
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        String projectName = "Main Testing Project (MTP)";
        String issueName = "Test create sub-task id:55";
        String subtaskName = "Test create sub-task id:937";
        TestingHelper.createTestIssue(objDashboardPage, projectName, issueName);
        String serialNumberOfCreatedTestIssue = objDashboardPage.getSerialNumberAttributeIssue();

        driver.get("https://jira-auto.codecool.metastage.net/browse/" + serialNumberOfCreatedTestIssue);
        objBrowsePage.waitUntilIssueNameIsVisible();
        objBrowsePage.clickOnMoreMenuButtonIssue();
        try {
            objBrowsePage.clickOnCreateSubtaskOption();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            TestingHelper.deleteTestIssue(objDashboardPage, objBrowsePage, serialNumberOfCreatedTestIssue);
            Assert.fail("creation sub-task is not available from the concerned issue of project");
        }
        objBrowsePage.waitUntilSummaryFieldOfPopupWindowIsClickable();
        objBrowsePage.clickOnSummaryField();
        objBrowsePage.setSummaryFieldContent(subtaskName);
        objBrowsePage.clickOnNonInputSurfaceSubtaskWindow();
        objBrowsePage.clickOnSubtaskCreationSubmit();
        objBrowsePage.waitUntilTestCreatedSubtaskLinkByNameIsClickable();
        objBrowsePage.clickOnTestCreatedSubtaskLinkByName();
        objBrowsePage.waitUntilIssueNameInTopOfSubtaskWindowIsClickable();
        assertEquals(subtaskName, objBrowsePage.getIssueSummaryValue());

        TestingHelper.deleteTestIssue(objDashboardPage, objBrowsePage, serialNumberOfCreatedTestIssue);
    }

    @Test
    void createCOALASubtaskTest() {
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        String serialNumberOfTestIssue = "COALA-18";

        objDashboardPage.waitUntilYourCompanyJiraTitleClickable();
        objDashboardPage.setSearchingFieldContent(serialNumberOfTestIssue);
        objDashboardPage.waitUntilSearchingFieldClickable();
        objDashboardPage.pressEnterInSearchingField();

        objBrowsePage.waitUntilMoreMenuButtonIsVisible();
        objBrowsePage.clickOnMoreMenuButtonIssue();
        try {
            objBrowsePage.clickOnCreateSubtaskOption();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("creation sub-task is not available from the concerned issue of project");
        }
    }

    @Test
    void createTOUCANSubtaskTest() {
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        String serialNumberOfTestIssue = "TOUCAN-14";

        objDashboardPage.waitUntilYourCompanyJiraTitleClickable();
        objDashboardPage.waitUntilSearchingFieldClickable();
        objDashboardPage.setSearchingFieldContent(serialNumberOfTestIssue);
        objDashboardPage.waitUntilSearchingFieldClickable();
        objDashboardPage.pressEnterInSearchingField();

        objBrowsePage.waitUntilMoreMenuButtonIsVisible();
        objBrowsePage.clickOnMoreMenuButtonIssue();
        try {
            objBrowsePage.clickOnCreateSubtaskOption();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("creation sub-task is not available from the concerned issue of project");
        }
    }

    @Test
    void createJETISubtaskTest() {
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        String serialNumberOfTestIssue = "JETI-6";

        objDashboardPage.waitUntilYourCompanyJiraTitleClickable();
        objDashboardPage.waitUntilSearchingFieldClickable();
        objDashboardPage.setSearchingFieldContent(serialNumberOfTestIssue);
        objDashboardPage.waitUntilSearchingFieldClickable();
        objDashboardPage.pressEnterInSearchingField();

        objBrowsePage.waitUntilMoreMenuButtonIsVisible();
        objBrowsePage.clickOnMoreMenuButtonIssue();
        try {
            objBrowsePage.clickOnCreateSubtaskOption();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("creation sub-task is not available from the concerned issue of project");
        }
    }
}
