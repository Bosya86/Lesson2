package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage extends HomePage {


    @FindBy(className = "bepaid-iframe")
    private WebElement bepaidIframe;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public InnerPage switchToInnerPage() {
        switchToFrame(bepaidIframe);
        return getWhenVisible(InnerPage.class);
    }

    protected <T> T getWhenVisible(Class<T> clazz) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
        return PageFactory.initElements(driver, clazz);
    }

    protected void switchToFrame(WebElement frame) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
    }
}