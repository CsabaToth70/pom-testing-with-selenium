package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.JiraBrowsePage;
import pagefactory.JiraDashboardPage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJiraBrowseProject {

    WebDriver driver = new ChromeDriver();
    JiraBrowsePage objBrowsePage;
    JiraDashboardPage objDashboardPage;

    @BeforeEach
    void setUp() throws IOException {
        TestingHelper.setUpWithLogin(driver);
        objBrowsePage = new JiraBrowsePage(driver);
        objDashboardPage = new JiraDashboardPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.close();
        driver.quit();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/projectData.csv", numLinesToSkip = 1)
    void testBrowseProjectWithKeyValidation(String key, String url) {
        objDashboardPage.waitUntilProjectsNavbarIsClickable();
        objBrowsePage.navigateToProjectSummary(url);
        objBrowsePage.waitForProjectIdentifierByInput(key);
        assertEquals(key, objBrowsePage.getProjectIdentifierTextByInput(key));
    }
}
