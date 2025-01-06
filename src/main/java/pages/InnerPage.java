package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InnerPage extends HomePage {

    @FindBy(css = "div.pay-description__cost span")
    private WebElement costElement;


    @FindBy(css = "div.payment-page__order-description.pay-description__text span")
    private WebElement descriptionElement;


    @FindBy(xpath = "//label[contains(text(), 'Номер карты')]")
    private WebElement cardNumberLabel;


    @FindBy(xpath = "//label[contains(text(), 'Срок действия')]")
    private WebElement expirationDateLabel;


    @FindBy(xpath = "//label[contains(text(), 'CVC')]")
    private WebElement cvcLabel;


    @FindBy(xpath = "//label[contains(text(), 'Имя держателя (как на карте)')]")
    private WebElement holderNameLabel;


    @FindBy(className = "colored disabled")
    private WebElement payButton;


    public InnerPage(WebDriver driver) {
        super(driver);
    }


    public String getCostText() {
        return costElement.getText().trim();
    }

    public String getDescriptionText() {
        return descriptionElement.getText().trim();
    }

    public String getCardNumberLabelText() {
        return cardNumberLabel.getText();
    }

    public String getExpirationDateLabelText() {
        return expirationDateLabel.getText();
    }

    public String getCVCLabelText() {
        return cvcLabel.getText();
    }

    public String getHolderNameLabelText() {
        return holderNameLabel.getText();
    }

    public String getPayButtonText() {
        return payButton.getAttribute("innerText").trim();
    }
}