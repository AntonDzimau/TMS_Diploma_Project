package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;


public class DropDown{
    private List<UIElement> uiElement;
    private List<String> valuelist;
    private List<String> textList;
    private WebDriver driver;


    public DropDown(WebDriver driver, By by) {
        uiElement = new ArrayList<>();
        valuelist = new ArrayList<>();
        textList = new ArrayList<>();

        for (WebElement webElement : driver.findElements(by)) {
            UIElement someElement = new UIElement(driver, webElement);
            uiElement.add(someElement);
            valuelist.add(someElement.findElement(By.xpath("//*[@class='active-result']")).getAttribute("style"));
            textList.add(someElement.findElement(By.xpath("parent::*/strong")).getText());
        }
    }

    public void selectByValue(String value) {
        valuelist.get(valuelist.indexOf(value)).;
    }

    public void selectByText(String value) {
        uiElement.get(textList.indexOf(value)).click();
    }

}