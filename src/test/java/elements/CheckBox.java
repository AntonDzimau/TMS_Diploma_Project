package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckBox {
    private UIElement uiElement;

    public CheckBox(WebDriver driver, By by) {
        this.uiElement = new UIElement(driver, by);
    }

    public CheckBox(WebDriver driver, WebElement webElement) {
        this.uiElement = new UIElement(driver, webElement);
    }

    public void setFlag() {
        click(true);
    }

    public void removeFlag() {
        click(false);
    }

    public void flag(boolean value) {
        click(value);
    }

    public boolean isSelected() {
        return uiElement.isSelected();
    }

    private void click(boolean flag) {
        if (flag != uiElement.isSelected()) {
            uiElement.click();
        }
    }
}
