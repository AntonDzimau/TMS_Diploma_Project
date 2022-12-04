package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;


public class RadioButton {
    private List<UIElement> uiElement;
    private List<String> valuelist;
    private List<String> textList;
    private WebDriver driver;


    public RadioButton(WebDriver driver, By by) {
        uiElement = new ArrayList<>();
        valuelist = new ArrayList<>();
        textList=new ArrayList<>();

        for (WebElement webElement : driver.findElements(by)) {
            UIElement someElement = new UIElement(driver, webElement);
            uiElement.add(someElement);
            valuelist.add(someElement.getAttribute("value"));
            textList.add(someElement.findElement(By.xpath("parent::*/strong")).getText());
        }
    }

    public void selectByValue(String value) {
        uiElement.get(valuelist.indexOf(value)).click();
    }

    public void selectByText(String value) {
        uiElement.get(textList.indexOf(value)).click();
    }

}
