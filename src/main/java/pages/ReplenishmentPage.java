package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReplenishmentPage {

    private WebDriver driver;
    private WebDriverWait wait;

    @CacheLookup
    @FindBy(id = "connection-type")
    private Select serviceTypeDropdown;

    @CacheLookup
    @FindBy(id = "connection-phone")
    private WebElement phoneNumberField;

    @CacheLookup
    @FindBy(id = "account-number")
    private WebElement accountNumberField;

    @CacheLookup
    @FindBy(id = "contract-number")
    private WebElement contractNumberField;

    @CacheLookup
    @FindBy(id = "connection-sum")
    private WebElement sumField;

    @CacheLookup
    @FindBy(id = "debtor-id")
    private WebElement debtorIDField;

    @CacheLookup
    @FindBy(id = "connection-email")
    private WebElement emailField;

    @CacheLookup
    @FindBy(css = "button.button__default[type='submit']")
    private WebElement continueButton;

    @CacheLookup
    @FindBy(className = "bepaid-iframe")
    private WebElement bepaidIframe;

    @CacheLookup
    @FindBy(id = "gpay-button-online-api-id")
    private WebElement gPayButton;
    private String amount;

    @FindBy(id = "connection-sum")
    private WebElement amountField;

    public ReplenishmentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void selectServiceType(String type) {
        serviceTypeDropdown.selectByVisibleText(type);
    }

    public void setPhoneNumber(String phoneNumber) {
        phoneNumberField.clear();
        phoneNumberField.sendKeys(phoneNumber);
    }

    public void setAccountNumber(String accountNumber) {
        accountNumberField.clear();
        accountNumberField.sendKeys(accountNumber);
    }

    public void setContractNumber(String contractNumber) {
        contractNumberField.clear();
        contractNumberField.sendKeys(contractNumber);
    }

    public void setDebtorID(String debtorID) {
        debtorIDField.clear();
        debtorIDField.sendKeys(debtorID);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void switchToBepaidFrame() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(bepaidIframe));
    }

    public boolean isGPayButtonDisplayed() {
        switchToBepaidFrame();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(gPayButton));
        return gPayButton.isDisplayed();
    }

    public String getErrorMessageForPhoneField() {
        return phoneNumberField.getAttribute("validationMessage");
    }

    public String getErrorMessageForAccountField() {
        return accountNumberField.getAttribute("validationMessage");
    }

    public String getErrorMessageForContractField() {
        return contractNumberField.getAttribute("validationMessage");
    }

    public String getErrorMessageForDebtorField() {
        return debtorIDField.getAttribute("validationMessage");
    }

    public void setAmount(String amount) {
        amountField.clear();
        amountField.sendKeys(amount);
    }

    public String getAmount() {
        return amount;
    }
    public String getLabelForPhoneField() {
        return phoneNumberField.getAttribute("placeholder");
    }
    public String getLabelForSumField() {
        return sumField.getAttribute("placeholder");
    }
    public String getPlaceholderForEmailField() {
        return emailField.getAttribute("placeholder");
    }
}