import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.*;

public class MTSTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 20);

        driver.get("https://www.mts.by/");

    }

    public void tearDown() {

        driver.quit();

    }

    @Test
    public void checkOnlineReplenishmentBlockTitle() {
        driver.get("https://www.mts.by/");

        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pay__wrapper > h2")));

        String titleText = blockTitle.getText().trim();

        assertEquals("Онлайн пополнение\nбез комиссии", titleText, "Заголовок блока не совпадает с ожидаемым текстом.");
        driver.quit();
    }

    @Test
    public void checkPaymentSystemLogos() {

        driver.get("https://www.mts.by/");

        WebElement visaLogo = driver.findElement(By.xpath("//img[contains(@alt,'Visa')]"));
        WebElement verifiedByVisaLogo = driver.findElement(By.xpath("//img[contains(@alt,'Verified By Visa')]"));
        WebElement masterCardLogo = driver.findElement(By.xpath("//img[contains(@alt,'MasterCard')]"));
        WebElement masterCardSecureCodeLogo = driver.findElement(By.xpath("//img[contains(@alt,'MasterCard Secure Code')]"));
        WebElement belcardLogo = driver.findElement(By.xpath("//img[contains(@alt,'Белкарт')]"));

        Assertions.assertNotNull(visaLogo, "Логотип Visa отсутствует.");
        Assertions.assertNotNull(verifiedByVisaLogo, "Логотип Visa отсутствует.");
        Assertions.assertNotNull(masterCardLogo, "Логотип Visa отсутствует.");
        Assertions.assertNotNull(masterCardSecureCodeLogo, "Логотип Visa отсутствует.");
        Assertions.assertNotNull(belcardLogo, "Логотип Visa отсутствует.");
        driver.quit();
    }

    @Test
    public void checkLinkToServiceDetails() {

        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
        By cookieAgreeButtonLocator = By.id("cookie-agree");
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(cookieAgreeButtonLocator)) != null) {
            driver.findElement(cookieAgreeButtonLocator).click();
        }

        WebElement wrapper = driver.findElement(By.className("pay__wrapper"));

        WebElement link = wrapper.findElement(By.partialLinkText("Подробнее о сервисе"));

        Assertions.assertNotNull(link, "Ссылка 'Подробнее о сервисе' отсутствует.");

        link.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String currentUrl = driver.getCurrentUrl();
        Assertions.assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", currentUrl, "Переход по ссылке выполнен неверно.");
        driver.quit();
    }

    @Test
    public void fillFieldsAndCheckContinueButton() {

        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
        By cookieAgreeButtonLocator = By.id("cookie-agree");
        if (wait.until(ExpectedConditions.visibilityOfElementLocated(cookieAgreeButtonLocator)) != null) {
            driver.findElement(cookieAgreeButtonLocator).click();
        }

        WebElement amountField = driver.findElement(By.id("connection-sum"));
        amountField.sendKeys("200");

        WebElement phoneNumberField = driver.findElement(By.id("connection-phone"));
        phoneNumberField.sendKeys("297777777");

        WebElement continueButton = driver.findElement(By.cssSelector("button.button__default[type='submit']"));
        continueButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);

        WebElement bepaidIframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("bepaid-iframe")));
        driver.switchTo().frame(bepaidIframe);

        WebElement gPayButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gpay-button-online-api-id")));

        assert(gPayButton.isDisplayed());

        driver.quit();
    }



}

