package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Textarea {
    private UIElement uiElement;

    public Textarea(WebDriver driver, By by) {
        this.uiElement = new UIElement(driver, by);
    }

    public Textarea(WebDriver driver, WebElement webElement) {
        this.uiElement = new UIElement(driver, webElement);
    }

    public void sendKeys(CharSequence... keysToSend) {
        uiElement.sendKeys(keysToSend);
    }

    public String getAttribute(String name) {
        return uiElement.getAttribute(name);
    }

    public String getText() {
        return uiElement.getText();
    }

    public void clear() {
        uiElement.clear();
    }

    public void rewrite(String newValue){
        clear();
        sendKeys(newValue);
    }

}
