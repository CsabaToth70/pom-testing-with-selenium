package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.JiraBrowsePage;
import pagefactory.JiraDashboardPage;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class TestBrowseIssue {

    WebDriver driver = new ChromeDriver();

    JiraBrowsePage objBrowsePage;
    JiraDashboardPage objDashboardPage;

    @BeforeEach
    void setUp() throws IOException {
        TestingHelper.setUpWithLogin(driver);
        objDashboardPage = new JiraDashboardPage(driver);
        objBrowsePage = new JiraBrowsePage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.close();
        driver.quit();
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/issueData.csv", numLinesToSkip = 1)
    void testBrowseProjectIssuesWithKeyValidation(String key, String url) {
        objDashboardPage.waitUntilProfileIsClickable();
        objBrowsePage.navigateToIssue(url);
        String actualKey = "";
        try {
            actualKey = objBrowsePage.getIssueIdentifierText();
        } catch (NoSuchElementException e) {
            System.out.println("No permission to view this issue");
            System.out.println(e + ": " + e.getMessage());
        }
        assertEquals(key, actualKey);
    }
}
