import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.PaymentSystemsPage;
import pages.ReplenishmentPage;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.time.Duration;

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
    public void verifyPaymentFormElements() {
        try {
            HomePage homePage = new HomePage(driver);
            homePage.agreeCookies();

            ReplenishmentPage replenishmentPage = homePage.openReplenishmentPage();
            replenishmentPage.setAmount("200");
            replenishmentPage.setPhoneNumber("297777777");
            replenishmentPage.clickContinueButton();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            WebElement bepaidIframe = wait.until(ExpectedConditions.elementToBeClickable(By.className("bepaid-iframe")));
            driver.switchTo().frame(bepaidIframe);

            WebElement costElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.pay-description__cost span")));
            String costText = costElement.getText().trim();
            System.out.println("Полученная цена: " + costText);
            assertEquals("200.00 BYN", costText);

            WebElement descriptionElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.pay-description__text > span")
                    )
            );
            String descriptionText = descriptionElement.getText().trim();
            System.out.println("Описание платежа: " + descriptionText);
            assertEquals("Оплата: Услуги связи Номер:375297777777", descriptionText);


            WebElement cardNumberLabel = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.ng-tns-c46-1 ng-untouched ng-pristine ng-invalid > label.ng-tns-c46-1.ng-star-inserted")
                    )
            );
            String cardNumberText = cardNumberLabel.getText().trim();
            System.out.println("Номер карты: " + cardNumberText);
            assertEquals("Номер карты", cardNumberLabel);

            WebElement expirationDateLabel = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label.ng-tns-c46-4.ng-star-inserted"))
            );
            String expirationDateText = expirationDateLabel.getText().trim();
            System.out.println("Срок действия: " + expirationDateText);
            assertEquals("Срок действия", expirationDateText);

            String cvcLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'CVC')]"))).getText();
            assertEquals("CVC", cvcLabel);

            String holderNameLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[contains(text(), 'Имя держателя (как на карте)')]"))).getText();
            assertEquals("Имя держателя (как на карте)", holderNameLabel);

            String payButtonText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("colored disabled"))).getAttribute("innerText");
            assertEquals("Оплатить 200.00 BYN", payButtonText);

            System.out.println("Все элементы проверены успешно!");

            driver.switchTo().defaultContent();

        } catch (AssertionError | TimeoutException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
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