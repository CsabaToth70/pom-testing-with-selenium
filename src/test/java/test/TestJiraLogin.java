package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagefactory.JiraDashboardPage;
import pagefactory.JiraLoginPage;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

public class TestJiraLogin {

    WebDriver driver = new ChromeDriver();
    JiraDashboardPage objDashboardPage;
    JiraLoginPage objLoginPage;

    @BeforeEach
    void setUp() {
        TestingHelper.setUp(driver);
        objDashboardPage = new JiraDashboardPage(driver);
        objLoginPage = new JiraLoginPage(driver);
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
        resetFailedUserLoginCount("dashboard");
    }

    @Test
    void testLoginWithEmptyCredentialsDisplaysErrorMessage() throws IOException {
        objDashboardPage.clickOnLoginBtn();
        objDashboardPage.waitUntilUsernameErrorIsVisible();
        assertTrue(objDashboardPage.usernameErrorIsDisplayed());
        resetFailedUserLoginCount("dashboard");
    }

    @Test
    void testSuccessfulLoginWithUserValidationOnLoginPage() throws IOException {
        objDashboardPage.clickOnNavbarLoginBtn();
        objLoginPage.sendKeysToUsernameField(TestingHelper.getProperty("username"));
        objLoginPage.sendKeysToPasswordField(TestingHelper.getProperty("password"));
        objLoginPage.clickOnLoginBtn();
        objDashboardPage.waitUntilProfileIsClickable();
        objDashboardPage.clickOnProfileIcon();
        objDashboardPage.clickOnProfileOption();
        assertEquals(TestingHelper.getProperty("username"), objDashboardPage.getUsernameText());
    }

    @Test
    void testLoginWithInvalidPasswordDisplaysErrorMessageOnLoginPage() throws IOException {
        objDashboardPage.clickOnNavbarLoginBtn();
        objLoginPage.sendKeysToUsernameField(TestingHelper.getProperty("username"));
        objLoginPage.sendKeysToPasswordField("test");
        objLoginPage.clickOnLoginBtn();
        objLoginPage.waitUntilUsernameErrorIsVisible();
        assertTrue(objLoginPage.usernameErrorIsDisplayed());
        resetFailedUserLoginCount("login");
    }



    private void resetFailedUserLoginCount(String page) throws IOException {
        objDashboardPage.sendKeysToUsernameField(TestingHelper.getProperty("username"));
        objDashboardPage.sendKeysToPasswordField(TestingHelper.getProperty("password"));
        if (page.equals("login")) {
            objLoginPage.clickOnLoginBtn();
        } else if (page.equals("dashboard")) {
            objDashboardPage.clickOnLoginBtn();
        }
        objDashboardPage.waitUntilProfileIsClickable();
        objDashboardPage.clickOnProfileIcon();
        objDashboardPage.clickOnLogout();
    }

}
