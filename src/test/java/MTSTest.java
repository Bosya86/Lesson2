import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MTSTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.mts.by/");
    }

    @AfterEach
    public void teardown() {

            driver.quit();

    }

    @Test
    public void checkBlockTitle() {
        driver.get("https://www.mts.by/");
        String blockTitle = driver.findElement(By.cssSelector("h2")).getText();
        assertEquals("Онлайн пополнение\\nбез комиссии", blockTitle);
    }

    @Test
    public void checkPaymentLogos() {
        boolean visaLogoPresent = driver.findElements(By.cssSelector(".pay__partners[alt='Visa']")).size() > 0;
        boolean verifiedbyvisaLogoPresent = driver.findElements(By.cssSelector(".pay__partners[alt='Verified By Visa']")).size() > 0;
        assertTrue(visaLogoPresent && verifiedbyvisaLogoPresent);
        boolean mastercardLogoPresent = driver.findElements(By.cssSelector(".pay__partners[alt='MasterCard']")).size() > 0;
        boolean masterCardsecurecodeLogoPresent = driver.findElements(By.cssSelector(".pay__partners[alt='MasterCard Secure Code']")).size() > 0;
        boolean belcartLogoPresent = driver.findElements(By.cssSelector(".pay__partners[alt='Белкарт']")).size() > 0;
        assertTrue(mastercardLogoPresent && masterCardsecurecodeLogoPresent && belcartLogoPresent);

    }

    @Test
    public void checkServiceLink() {
        driver.findElement(By.linkText("Подробнее о сервисе")).click();
        wait.until(ExpectedConditions.urlContains("/service-details"));
        assertTrue(driver.getCurrentUrl().contains("/service-details"));
    }

    @Test
    public void fillFormAndCheckContinueButton() {

        driver.findElement(By.id("connection-phone")).sendKeys("2977777777");
        driver.findElement(By.id("connection-sum")).sendKeys("300");
        driver.findElement(By.id("connection-email")).sendKeys("bosya7@mail.ru");
        driver.findElement(By.xpath("//button[text()='Продолжить']")).click();

        wait.until(ExpectedConditions.urlContains("/payment-form"));
        assertTrue(driver.getCurrentUrl().contains("/payment-form"));
    }
}