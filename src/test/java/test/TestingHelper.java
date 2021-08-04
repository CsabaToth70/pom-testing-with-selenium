package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pagefactory.JiraBrowsePage;
import pagefactory.JiraDashboardPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestingHelper {
    private static final String rootPath = System.getProperty("user.dir") + "/";
    private static final String appConfigPath = rootPath + "app.properties";
    private static Properties appProps = new Properties();

    public static String getProperty(String userData) throws IOException {
        String userProperty = "";
        appProps.load(new FileInputStream(appConfigPath));
        if (userData.equals("username")) {
            userProperty = appProps.getProperty("username");
        } else if (userData.equals("password")) {
            userProperty = appProps.getProperty("password");
        } else {
            System.out.println("Wrong input");
        }
        return userProperty;
    }

    public static void setUpWithLogin(WebDriver driver) throws IOException {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver.manage().window().maximize();
        driver.get("https://jira-auto.codecool.metastage.net/");
        login(driver);
    }

    public static void setUp(WebDriver driver) {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        driver.manage().window().maximize();
        driver.get("https://jira-auto.codecool.metastage.net/");
    }

    static void login(WebDriver driver) throws IOException {
        driver.findElement(By.id("login-form-username")).click();
        driver.findElement(By.id("login-form-username")).sendKeys(getProperty("username"));
        driver.findElement(By.id("passwordlabel")).click();
        driver.findElement(By.id("login-form-password")).sendKeys(getProperty("password"));
        driver.findElement(By.id("login")).click();
    }

    public static void createIssueInAProjectOf(String projectName, JiraDashboardPage objDashboardPage) {
        objDashboardPage.clickOnCreateButton();
        objDashboardPage.waitUntilCreateIssueWindowClickable();
        objDashboardPage.clickOnProjectField();
        objDashboardPage.setProjectFieldContent(projectName);
        try {
            objDashboardPage.clickOnNonInputSurfaceOfThePage();
            objDashboardPage.waitUntilSummaryFieldClickable();
            objDashboardPage.clickOnSummaryField();
        } catch (Exception e) {
            System.out.println("Timeout error thrown at summary field wait.");
            System.out.println(e + ": " + e.getMessage());
        }

    }

    public static void createTestIssue(JiraDashboardPage objDashboardPage, String projectName, String issueName) {
        objDashboardPage.clickOnCreateButton();
        objDashboardPage.waitUntilCreateIssueWindowClickable();
        objDashboardPage.clickOnProjectField();
        objDashboardPage.setProjectFieldContent(projectName);

        try {
            objDashboardPage.waitUntilIssueTypeFieldToNotExist();
        } catch (Exception e) {
            System.out.println("Timeout error thrown at issue field wait.");
            System.out.println(e + ": " + e.getMessage());
        }
        objDashboardPage.waitUntilIssueFieldClickable();
        objDashboardPage.clickOnNonInputSurfaceOfThePage();
        objDashboardPage.waitUntilIssueFieldClickable();
        objDashboardPage.clickOnIssueField();
        objDashboardPage.selectIssueFieldContent();
        objDashboardPage.setIssueFieldContent("Bug");
        objDashboardPage.pressEnterInIssueField();

        try {
            objDashboardPage.waitUntilSummaryTypeFieldToNotExist();
        } catch (Exception e) {
            System.out.println("Timeout error thrown at issue field wait.");
            System.out.println(e + ": " + e.getMessage());
        }
        objDashboardPage.waitUntilSummaryFieldClickable();
        objDashboardPage.clickOnNonInputSurfaceOfThePage();
        objDashboardPage.waitUntilSummaryFieldClickable();
        objDashboardPage.clickOnSummaryField();
        objDashboardPage.setSummaryFieldContent(issueName);
        objDashboardPage.clickOnCreateIssueSubmitButton();
        objDashboardPage.waitUntilPopUpNotificationClickable();
    }

    public static void deleteTestIssue(JiraDashboardPage objDashboardPage, JiraBrowsePage objBrowsePage,
                                       String serialNumberOfIssueToDelete) {
        objDashboardPage.setSearchingFieldContent(serialNumberOfIssueToDelete);
        objDashboardPage.pressEnterInSearchingField();
        objBrowsePage.waitUntilMoreMenuButtonIsVisible();
        objBrowsePage.clickOnMoreMenuButtonIssue();
        objBrowsePage.clickOnDeleteOption();
        objBrowsePage.waitUntilDeleteButtonConfirmationPopUpClickable();
        objBrowsePage.clickOnDeleteButtonConfirmationPopUp();
    }
}
