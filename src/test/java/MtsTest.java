import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.PaymentSystemsPage;
import pages.ReplenishmentPage;

import java.time.Duration;
import java.util.List;

import static org.junit.Assert.fail;
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
                            By.cssSelector("label.ng-star-inserted") // Упрощенный селектор
                    )
            );
            String cardNumberText = cardNumberLabel.getText().trim();
            System.out.println("Номер карты: " + cardNumberText);
            Assert.assertEquals(cardNumberText, "Номер карты");

            WebElement expirationDateLabel = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.cssSelector("label.ng-tns-c46-4.ng-star-inserted"))
            );
            String expirationDateText = expirationDateLabel.getText().trim();
            System.out.println("Срок действия: " + expirationDateText);
            assertEquals("Срок действия", expirationDateText);

            WebElement cvcLabel = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("label.ng-tns-c46-5.ng-star-inserted")
                    )
            );
            String cvcText = cvcLabel.getText().trim();
            System.out.println("CVC: " + cvcText);
            Assert.assertEquals(cvcText, "CVC");

            WebElement holderNameLabel = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("label.ng-tns-c46-3.ng-star-inserted")
                    )
            );
            String holderNameText = holderNameLabel.getText().trim();
            System.out.println("Имя держателя (как на карте): " + holderNameText);
            Assert.assertEquals(holderNameText, "Имя держателя (как на карте)");

            WebElement payButton = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("button.colored.disabled")
                    )
            );
            String payButtonText = payButton.getAttribute("innerText").trim();
            System.out.println("Кнопка оплаты: " + payButtonText);
            Assert.assertEquals(payButtonText, "Оплатить 200.00 BYN");

            WebElement firstLogoContainer = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.cssSelector("div.cards-brands.cards-brands__container.ng-tns-c61-0.ng-trigger.ng-trigger-brandsState.ng-star-inserted")
                    )
            );

            // Ищем все изображения логотипов внутри первого контейнера
            List<WebElement> firstLogos = firstLogoContainer.findElements(By.cssSelector("img.ng-tns-c61-0.ng-star-inserted"));

            // Проверяем количество найденных логотипов в первом контейнере
            Assert.assertTrue("Первый контейнер должен содержать хотя бы три логотипа", firstLogos.size() >= 3);

            // Ожидаем видимость второго контейнера с логотипами
            WebElement secondLogoContainer = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            By.cssSelector("div.cards-brands_random.ng-star-inserted")
                    )
            );

            // Ищем все изображения логотипов внутри второго контейнера
            List<WebElement> secondLogos = secondLogoContainer.findElements(By.cssSelector("img.ng-tns-c61-0.ng-trigger.ng-trigger-randomCardState.ng-star-inserted"));

            // Проверяем количество найденных логотипов во втором контейнере
            Assert.assertTrue("Второй контейнер должен содержать хотя бы два логотипа", secondLogos.size() >= 2);

            // Дополнительно можем вывести информацию о каждом логотипе
            System.out.println("Логотипы в первом контейнере:");
            for (WebElement logo : firstLogos) {
                System.out.println("Найден логотип: " + logo.getAttribute("src"));
            }

            System.out.println("\nЛоготипы во втором контейнере:");
            for (WebElement logo : secondLogos) {
                System.out.println("Найден логотип: " + logo.getAttribute("src"));
            }
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
}
