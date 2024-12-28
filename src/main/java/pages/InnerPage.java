package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InnerPage extends HomePage {
    @FindBy(id = "some_inner_element_id")
    private WebElement innerElement;

    public InnerPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnInnerElement() {
        innerElement.click();
    }
}