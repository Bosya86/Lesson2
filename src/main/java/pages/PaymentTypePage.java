package pages;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Set;

public class PaymentTypePage {
    private WebDriver driver;

    @FindBy(css = "li.select__item.active p.select__option")
    private WebElement selectedPaymentOption;

    // Остальные поля для ввода данных
    @FindBy(id = "phoneNumberField")
    private WebElement phoneNumberField;

    @FindBy(id = "amountField")
    private WebElement amountField;

    @FindBy(id = "emailField")
    private WebElement emailField;

    public PaymentTypePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSelectedPaymentOption() {
        return selectedPaymentOption.getText();
    }

    public void setPhoneNumber(String number) {
        phoneNumberField.clear();
        phoneNumberField.sendKeys(number);
    }

    public void setAmount(String amount) {
        amountField.clear();
        amountField.sendKeys(amount);
    }

    public void setEmail(String email) {
        emailField.clear();
        emailField.sendKeys(email);
    }

    /** * Получение всех куки текущего домена */
    public Set<Cookie> getCookies() {
        return driver.manage().getCookies();
    }

    /** * Установка куки */
    public void addCookie(Cookie cookie) {
        driver.manage().addCookie(cookie);
    }

    /** * Удаление всех куки */
    public void deleteAllCookies() {
        driver.manage().deleteAllCookies();
    }

    /** * Удаление конкретной куки по имени */
    public void deleteCookieNamed(String name) {
        driver.manage().deleteCookieNamed(name);
    }
}