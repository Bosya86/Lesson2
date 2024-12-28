import org.junit.jupiter.api.AfterEach;
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
import pages.HomePage;
import pages.PaymentSystemsPage;
import pages.ReplenishmentPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MtsTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void checkOnlineReplenishmentBlockTitle() {
        HomePage homePage = new HomePage(driver);
        homePage.agreeCookies();

        String titleText = homePage.getBlockTitleText();

        assertEquals("Онлайн пополнение\nбез комиссии", titleText, "Заголовок блока не совпадает с ожидаемым текстом.");
    }

    @Test
    public void checkLinkToServiceDetails() {
        HomePage homePage = new HomePage(driver);
        homePage.agreeCookies();

        homePage.navigateToServiceDetailsPage();

        String currentUrl = driver.getCurrentUrl();
        assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", currentUrl, "Переход по ссылке выполнен неверно.");
    }

    @Test
    public void fillFieldsAndCheckContinueButton() {
        HomePage homePage = new HomePage(driver);
        homePage.agreeCookies();

        ReplenishmentPage replenishmentPage = homePage.openReplenishmentPage();
        replenishmentPage.setAmount("200");
        replenishmentPage.setPhoneNumber("297777777");
        replenishmentPage.clickContinueButton();

        WebElement continueButton = driver.findElement(By.cssSelector("button.button__default[type='submit']"));
        continueButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", continueButton);


        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement bepaidIframe = wait.until(ExpectedConditions.elementToBeClickable(By.className("bepaid-iframe")));
        driver.switchTo().frame(bepaidIframe);



        boolean isFrameLoaded = driver.findElement(By.cssSelector(".bepaid-iframe")).isDisplayed();
        if (isFrameLoaded) {
            driver.switchTo().frame(bepaidIframe);
            WebElement costElement = driver.findElement(By.cssSelector(".app-wrapper__content"));
            String costText = costElement.getText();
            assertEquals("200.00 BYN", costText);
        } else {
            System.out.println("Фрейм не загружен.");
        }

        String serviceText = driver.findElement(By.xpath("//div[contains(text(), 'Оплата: Услуги связи')]"))
                .getText();
        assertEquals("Оплата: Услуги связи", serviceText);

        String numberText = driver.findElement(By.xpath("//label[contains(text(), 'Номер:375297777777')]"))
                .getText();
        assertEquals("Номер:375297777777", numberText);

        String cardNumberLabel = driver.findElement(By.xpath("//label[contains(text(), 'Номер карты')]"))
                .getText();
        assertEquals("Номер карты", cardNumberLabel);

        String expirationDateLabel = driver.findElement(By.xpath("//label[contains(text(), 'Срок действия')]"))
                .getText();
        assertEquals("Срок действия", expirationDateLabel);

        String cvcLabel = driver.findElement(By.xpath("//label[contains(text(), 'CVC')]")).getText();
        assertEquals("CVC", cvcLabel);

        String holderNameLabel = driver.findElement(By.xpath("//label[contains(text(), 'Имя держателя (как на карте)')]"))
                .getText();
        assertEquals("Имя держателя (как на карте)", holderNameLabel);

        String payButtonText = driver.findElement(By.className("colored disabled")).getAttribute("innerText");
        assertEquals("Оплатить 200.00 BYN", payButtonText);

        System.out.println("Все элементы проверены успешно!");
        // Закрытие браузера
        driver.quit();
    }

    @Test
    public void checkPaymentSystemLogos() {
        PaymentSystemsPage paymentSystemsPage = new PaymentSystemsPage(driver);

        assertTrue(paymentSystemsPage.isVisaLogoPresent(), "Логотип Visa отсутствует.");
        assertTrue(paymentSystemsPage.isVerifiedByVisaLogoPresent(), "Логотип Verified By Visa отсутствует.");
        assertTrue(paymentSystemsPage.isMasterCardLogoPresent(), "Логотип MasterCard отсутствует.");
        assertTrue(paymentSystemsPage.isMasterCardSecureCodeLogoPresent(), "Логотип MasterCard Secure Code отсутствует.");
        assertTrue(paymentSystemsPage.isBelcardLogoPresent(), "Логотип Белкарт отсутствует.");
    }
    @Test
    public void verifyFieldLabelsForCommunicationServices() {
        HomePage homePage = new HomePage(driver);
        homePage.agreeCookies();

        ReplenishmentPage replenishmentPage = homePage.openReplenishmentPage();

        // Переключаем на услугу "Услуги связи"
        replenishmentPage.selectServiceType("Услуги связи");

        // Проверяем надписи в полях
        assertEquals("Номер телефона", replenishmentPage.getLabelForPhoneField());
        assertEquals("Сумма", replenishmentPage.getLabelForSumField());
        assertEquals("E-mail для отправки чека", replenishmentPage.getPlaceholderForEmailField());
    }
}