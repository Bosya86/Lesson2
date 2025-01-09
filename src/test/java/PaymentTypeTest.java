import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.PaymentTypePage;

import java.time.Duration;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentTypeTest {
    private WebDriver driver;
    private PaymentTypePage paymentTypePage;
    private HomePage homePage;

    @BeforeEach
    void setup() {
        String projectPath = new String();
        System.setProperty("webdriver.chrome.driver", projectPath);
        WebDriverManager.chromedriver().clearDriverCache().setup();
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
        homePage = new HomePage(driver);
        paymentTypePage = new PaymentTypePage(driver);

        // Согласие на использование куки
        homePage.agreeCookies();

        // Установка тестовой куки
        Cookie cookie = new Cookie("test_cookie", "cookie_value");
        paymentTypePage.addCookie(cookie);
    }

    static Stream<Arguments> providePaymentTypes() {
        return Stream.of(
                Arguments.of("Услуги связи", "Номер телефона", "Сумма", "E-mail для отправки чека"),
                Arguments.of("Домашний интернет", "Номер абонента", "Сумма", "E-mail для отправки чека"),
                Arguments.of("Рассрочка", "Номер счета на 44", "Сумма", "E-mail для отправки чека"),
                Arguments.of("Задолженность", "Номер счета на 2073", "Сумма", "E-mail для отправки чека")
        );
    }

    @ParameterizedTest
    @MethodSource("providePaymentTypes")
    void checkPaymentFields(String optionName, String field1, String field2, String field3) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30)); // Увеличили время ожидания до 15 секунд

        // Нажатие на кнопку выбора типа платежа
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.select__header"))).click();

        // Выбор нужного типа платежа
        By optionLocator = By.xpath("//ul[@class='select__list']//p[text()='" + optionName + "']/.."); // Изменённый XPath
        wait.until(ExpectedConditions.visibilityOfElementLocated(optionLocator)); // Ждем появления элемента
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click(); // Затем ждем, когда он станет кликабельным

        // Проверяем, что выбранный тип платежа отображается корректно
        String actualOption = paymentTypePage.getSelectedPaymentOption();
        assertEquals(optionName, actualOption, "Тип платежа не соответствует ожидаемому значению.");

        // Проверяем наличие полей ввода
        switch (optionName) {
            case "Услуги связи":
                // Ожидаем появления поля с номером телефона
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
                String phonePlaceholder = driver.findElement(By.id("connection-phone")).getAttribute("placeholder");
                assertEquals(field1, phonePlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");

                // Ожидаем появления поля с суммой
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
                String sumPlaceholder = driver.findElement(By.id("connection-sum")).getAttribute("placeholder");
                assertEquals(field2, sumPlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");

                // Ожидаем появления поля с e-mail
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-email")));
                String emailPlaceholder = driver.findElement(By.id("connection-email")).getAttribute("placeholder");
                assertEquals(field3, emailPlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");
                break;

            case "Домашний интернет":
                // Ожидаем появления поля с номером абонента
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("internet-phone")));
                String internetPhonePlaceholder = driver.findElement(By.id("internet-phone")).getAttribute("placeholder");
                assertEquals(field1, internetPhonePlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");

                // Ожидаем появления поля с суммой
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("internet-sum")));
                String internetSumPlaceholder = driver.findElement(By.id("internet-sum")).getAttribute("placeholder");
                assertEquals(field2, internetSumPlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");

                // Ожидаем появления поля с e-mail
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("internet-email")));
                String internetEmailPlaceholder = driver.findElement(By.id("internet-email")).getAttribute("placeholder");
                assertEquals(field3, internetEmailPlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");
                break;

            case "Рассрочка":
                // Ожидаем появления поля с номером счета на 44
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("score-instalment")));
                String instalmentScorePlaceholder = driver.findElement(By.id("score-instalment")).getAttribute("placeholder");
                assertEquals(field1, instalmentScorePlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");

                // Ожидаем появления поля с суммой
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("instalment-sum")));
                String instalmentSumPlaceholder = driver.findElement(By.id("instalment-sum")).getAttribute("placeholder");
                assertEquals(field2, instalmentSumPlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");

                // Ожидаем появления поля с e-mail
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("instalment-email")));
                String instalmentEmailPlaceholder = driver.findElement(By.id("instalment-email")).getAttribute("placeholder");
                assertEquals(field3, instalmentEmailPlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");
                break;

            case "Задолженность":
                // Ожидаем появления поля с номером счета на 2073
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("score-arrears")));
                String arrearsScorePlaceholder = driver.findElement(By.id("score-arrears")).getAttribute("placeholder");
                assertEquals(field1, arrearsScorePlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");

                // Ожидаем появления поля с суммой
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("arrears-sum")));
                String arrearsSumPlaceholder = driver.findElement(By.id("arrears-sum")).getAttribute("placeholder");
                assertEquals(field2, arrearsSumPlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");

                // Ожидаем появления поля с e-mail
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("arrears-email")));
                String arrearsEmailPlaceholder = driver.findElement(By.id("arrears-email")).getAttribute("placeholder");
                assertEquals(field3, arrearsEmailPlaceholder, "Текст в placeholder не соответствует ожидаемому значению.");
                break;
        }
    }
    @AfterEach
    void teardown() {
        if (driver != null) {
            // Удаление всех куки после завершения теста
            paymentTypePage.deleteAllCookies();
            driver.quit();
        }
    }
}