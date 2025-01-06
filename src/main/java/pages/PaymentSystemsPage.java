package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentSystemsPage {

    private WebDriver driver;

    @CacheLookup
    @FindBy(xpath = "//img[contains(@alt,'Visa')]")
    private WebElement visaLogo;

    @CacheLookup
    @FindBy(xpath = "//img[contains(@alt,'Verified By Visa')]")
    private WebElement verifiedByVisaLogo;

    @CacheLookup
    @FindBy(xpath = "//img[contains(@alt,'MasterCard')]")
    private WebElement masterCardLogo;

    @CacheLookup
    @FindBy(xpath = "//img[contains(@alt,'MasterCard Secure Code')]")
    private WebElement masterCardSecureCodeLogo;

    @CacheLookup
    @FindBy(xpath = "//img[contains(@alt,'Белкарт')]")
    private WebElement belcardLogo;

    public PaymentSystemsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isVisaLogoPresent() {
        return visaLogo.isDisplayed();
    }

    public boolean isVerifiedByVisaLogoPresent() {
        return verifiedByVisaLogo.isDisplayed();
    }

    public boolean isMasterCardLogoPresent() {
        return masterCardLogo.isDisplayed();
    }

    public boolean isMasterCardSecureCodeLogoPresent() {
        return masterCardSecureCodeLogo.isDisplayed();
    }

    public boolean isBelcardLogoPresent() {
        return belcardLogo.isDisplayed();
    }
}

