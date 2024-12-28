package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends HomePage {
    @FindBy(className = "bepaid-iframe")
    private WebElement bepaidIframe;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public InnerPage switchToInnerPage() {
        switchToFrame(bepaidIframe);
        return getWhenVisible(InnerPage.class);
    }
}
