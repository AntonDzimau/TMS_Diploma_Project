package pages;

import baseEntities.BasePage;
import elements.Button;
import elements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    private final By emailInputLocator = By.id("name");
    private final By pswInputLocator = By.id("password");
    private final By loginButtonLocator = By.id("button_primary");
    private final By errorTextLocator = By.className("error-text");
    private final By errorTextNoLoginLocator=By.xpath("//*[@id='content']/form/div[3]");
    private final By errorTextNoPasswordLocator=By.xpath("//*[@id='content']/form/div[4]/div/div");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return loginButtonLocator;
    }

    public Input getEmailInput() {
        return new Input(driver, waitsService.waitForVisibilityBy(emailInputLocator));
    }

    public Input getPswInput() {
        return new Input(driver,  waitsService.waitForVisibilityBy(pswInputLocator) );
    }

    public Button getLoginButton() {
        return new Button(driver, waitsService.waitForClickableBy(loginButtonLocator)  );
    }
    public WebElement getErrorTextNoLoginLocator(){
        return waitsService.waitForVisibilityBy(errorTextNoLoginLocator);
    }

    public void setEmail(String value) {
        getEmailInput().sendKeys(value);
    }

    public void setPsw(String value) {
        getPswInput().sendKeys(value);
    }

    public void clickLoginButton() {
        getLoginButton().click();
    }

    public WebElement getErrorTextElement() {
        return waitsService.waitForVisibilityBy(errorTextLocator);
    }
    public WebElement getErrorTextNoPasswordLocator(){
        return waitsService.waitForVisibilityBy(errorTextNoPasswordLocator);
    }
}
