package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.JiraBrowsePage;
import pagefactory.JiraDashboardPage;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class TestJiraEditIssue {
    WebDriver driver;
    JiraBrowsePage objBrowsePage;
    JiraDashboardPage objDashboardPage;

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
    void editIssue(){
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        String originalIssueName = "Created issue to test editing function";
        String modifiedIssueName = "Edited summary content 8888";

        objDashboardPage.waitUntilYourCompanyJiraTitleClickable();
        driver.get("https://jira-auto.codecool.metastage.net/browse/MTP-193");
        objBrowsePage.waitUntilIssueNameIsVisible();
        objBrowsePage.waitUntilEditIssueButtonIsClickable();
        objBrowsePage.clickOnEditIssueButton();
        objBrowsePage.waitUntilSummaryFieldOfPopupWindowIsClickable();
        objBrowsePage.clickOnSummaryField();
        objBrowsePage.clearSummaryFieldContent();
        objBrowsePage.setSummaryFieldContent(modifiedIssueName);
        objBrowsePage.clickOnNonInputSurfaceIssueWindow();
        objBrowsePage.waitUntilIssueTypeFieldIsClickable();
        objBrowsePage.clickOnEditIssueSubmit();
        objBrowsePage.waitUntilEditIssueSubmitBeNotClickable();
        objBrowsePage.waitUntilIssueSummaryValueIsClickable();

        try{
            assertEquals(modifiedIssueName, objBrowsePage.getIssueSummaryValue());
        } catch (Exception e){
            System.out.println(e + ": " + e.getMessage());
        } finally {
            objBrowsePage.waitUntilIssueNameIsVisible();
            objBrowsePage.waitUntilEditIssueButtonIsClickable();
            objBrowsePage.clickOnEditIssueButton();
            objBrowsePage.waitUntilSummaryFieldOfPopupWindowIsClickable();
            objBrowsePage.clickOnSummaryField();
            objBrowsePage.clearSummaryFieldContent();
            objBrowsePage.setSummaryFieldContent(originalIssueName);
            objBrowsePage.clickOnNonInputSurfaceIssueWindow();
            objBrowsePage.waitUntilIssueTypeFieldIsClickable();
            objBrowsePage.clickOnEditIssueSubmit();
        }
    }

    @Test
    void editCOALATest(){
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        objDashboardPage.waitUntilYourCompanyJiraTitleClickable();
        driver.get("https://jira-auto.codecool.metastage.net/browse/COALA-18");
        try {
            assertTrue(objBrowsePage.isDisplayedEditIssueButton());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            fail("No permission to edit issue in this project");
        }
    }

    @Test
    void editTOUCANTest(){
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        objDashboardPage.waitUntilYourCompanyJiraTitleClickable();
        driver.get("https://jira-auto.codecool.metastage.net/browse/TOUCAN-14");
        try {
            assertTrue(objBrowsePage.isDisplayedEditIssueButton());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            fail("No permission to edit issue in this project");
        }
    }

    @Test
    void editJETITest(){
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        objDashboardPage.waitUntilYourCompanyJiraTitleClickable();
        driver.get("https://jira-auto.codecool.metastage.net/browse/JETI-6");
        try {
            assertTrue(objBrowsePage.isDisplayedEditIssueButton());
        } catch (org.openqa.selenium.NoSuchElementException e) {
            fail("No permission to edit issue in this project");
        }
    }


}
