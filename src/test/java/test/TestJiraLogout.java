package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.JiraDashboardPage;
import pagefactory.JiraLoginPage;
import pagefactory.JiraLogoutPage;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class TestJiraLogout {

    WebDriver driver = new ChromeDriver();
    JiraDashboardPage objDashboardPage;
    JiraLogoutPage objLogoutPage;

    @BeforeEach
    void setUp() throws IOException {
        TestingHelper.setUpWithLogin(driver);
        objDashboardPage = new JiraDashboardPage(driver);
        objLogoutPage = new JiraLogoutPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    void testSuccessfulLogoutWithLogoutMessageValidation() {
        objDashboardPage.waitUntilProfileIsClickable();
        objDashboardPage.clickOnProfileIcon();
        objDashboardPage.clickOnLogout();
        assertTrue(objLogoutPage.mainTextBoxContainsLogoutMessage());
    }
}
