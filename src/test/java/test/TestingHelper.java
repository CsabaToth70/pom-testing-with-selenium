package test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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

    private static void login(WebDriver driver) throws IOException {
        driver.findElement(By.id("login-form-username")).click();
        driver.findElement(By.id("login-form-username")).sendKeys(getProperty("username"));
        driver.findElement(By.id("passwordlabel")).click();
        driver.findElement(By.id("login-form-password")).sendKeys(getProperty("password"));
        driver.findElement(By.id("login")).click();
    }
}
