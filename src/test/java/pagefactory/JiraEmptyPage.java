package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class JiraEmptyPage {
    WebDriver driver;
    WebDriverWait wait;

    @FindBy(xpath="//*[@id=\"main\"]/div/div[2]/div/div/div/div/div/div")
    WebElement noIssuesWereFoundMessage;

    public JiraEmptyPage(WebDriver driver) {
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
        PageFactory.initElements(driver, this);
    }

    public Boolean isDisplayedNoIssuesWereFound(){
        driver.navigate().to("https://jira-auto.codecool.metastage.net/issues/?jql=text%20~%20%22Issue%20does%20not%20exist%20id%3A9238%22");
        return noIssuesWereFoundMessage.isDisplayed();
    }


}
