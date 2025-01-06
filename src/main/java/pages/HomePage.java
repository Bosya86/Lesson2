package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver;
    private WebDriverWait wait;

    @CacheLookup
    @FindBy(className = "pay__wrapper")
    private WebElement onlineReplenishmentBlock;

    @FindBy(css = ".pay__wrapper > h2")
    private WebElement blockTitle;

    @FindBy(partialLinkText = "Подробнее о сервисе")
    private WebElement serviceDetailsLink;

    @FindBy(id = "cookie-agree")
    private WebElement cookieAgreeButton;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    protected void switchToFrame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
    }

    protected <T> T getWhenVisible(Class<T> pageClass) {
        return wait.until(new ExpectedCondition<T>() {
            @Override
            public T apply(WebDriver d) {
                return PageFactory.initElements(d, pageClass);
            }
        });
    }

    public String getBlockTitleText() {
        return blockTitle.getText().trim();
    }

    public void agreeCookies() {
        if (isCookieAgreementVisible()) {
            cookieAgreeButton.click();
        }
    }

    public boolean isCookieAgreementVisible() {
        return cookieAgreeButton.isDisplayed();
    }

    public void navigateToServiceDetailsPage() {
        serviceDetailsLink.click();
    }

    public ReplenishmentPage openReplenishmentPage() {
        return new ReplenishmentPage(driver);
    }
}
