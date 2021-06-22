package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.JiraDashboardPage;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class TestJiraLogin {

    WebDriver driver = new ChromeDriver();
    JiraDashboardPage objDashboardPage;

    @BeforeEach
    void setUp() {
        TestingHelper.setUp(driver);
        objDashboardPage = new JiraDashboardPage(driver);
    }

    @AfterEach
    void tearDown() {
        driver.close();
        driver.quit();
    }

    @Test
    void testSuccessfulLoginWithUserValidation() throws IOException {
        objDashboardPage.sendKeysToUsernameField(TestingHelper.getProperty("username"));
        objDashboardPage.sendKeysToPasswordField(TestingHelper.getProperty("password"));
        objDashboardPage.clickOnLoginBtn();
        objDashboardPage.waitUntilProfileIsClickable();
        objDashboardPage.clickOnProfileIcon();
        objDashboardPage.clickOnProfileOption();
        assertEquals(TestingHelper.getProperty("username"), objDashboardPage.getUsernameText());
    }

    @Test
    void testLoginWithInvalidPasswordDisplaysErrorMessage() throws IOException {
        objDashboardPage.sendKeysToUsernameField(TestingHelper.getProperty("username"));
        objDashboardPage.sendKeysToPasswordField("test");
        objDashboardPage.clickOnLoginBtn();
        objDashboardPage.waitUntilUsernameErrorIsVisible();
        assertTrue(objDashboardPage.usernameErrorIsDisplayed());
        resetFailedUserLoginCount();
    }

    @Test
    void testLoginWithEmptyCredentialsDisplaysErrorMessage() throws IOException {
        objDashboardPage.clickOnLoginBtn();
        objDashboardPage.waitUntilUsernameErrorIsVisible();
        assertTrue(objDashboardPage.usernameErrorIsDisplayed());
        resetFailedUserLoginCount();
    }

    private void resetFailedUserLoginCount() throws IOException {
        objDashboardPage.sendKeysToUsernameField(TestingHelper.getProperty("username"));
        objDashboardPage.sendKeysToPasswordField(TestingHelper.getProperty("password"));
        objDashboardPage.clickOnLoginBtn();
        objDashboardPage.waitUntilProfileIsClickable();
        objDashboardPage.clickOnProfileIcon();
        objDashboardPage.clickOnLogout();
    }

}
