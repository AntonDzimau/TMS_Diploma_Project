package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class TableRow {
    private UIElement uiElement;
    private WebDriver driver;

    public TableRow(WebDriver driver, By by) {
        this.driver = driver;
        this.uiElement = new UIElement(driver, by);
    }

    public TableRow(WebDriver driver, WebElement webElement) {
        this.uiElement = new UIElement(driver, webElement);
    }

    public TableRow(WebDriver driver, UIElement uiElement) {
        this.uiElement = uiElement;
    }

    public ArrayList<UIElement> findUIElements(By by) {
        return uiElement.findUIElements(by);
    }

    public ArrayList<TableCell> getListOfCellsInRow() {
        ArrayList<TableCell> list = new ArrayList<>();
        for (int i = 0; i < findUIElements(By.tagName("td")).size(); i++) {
            list.add(new TableCell(driver, findUIElements(By.tagName("td")).get(i)));
        }
        return list;
    }

    public TableCell getCell(int columnNumber) {
        return getListOfCellsInRow().get(columnNumber);
    }
}
