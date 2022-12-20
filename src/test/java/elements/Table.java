package elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Table {
    private UIElement uiElement;
    private WebDriver driver;

    public Table(WebDriver driver, By by) {
        this.driver = driver;
        this.uiElement = new UIElement(driver, by);
    }

    public Table(WebDriver driver, WebElement webElement) {
        this.driver = driver;
        this.uiElement = new UIElement(driver, webElement);
    }

    public ArrayList<UIElement> findUIElements(By by) {
        return uiElement.findUIElements(by);
    }

    public boolean isFoundInTable(String value) {
        ArrayList<UIElement> listOfRows = uiElement.findUIElements(By.tagName("tr"));
        boolean flag = false;
        for (int i = 0; i < listOfRows.size(); i++) {
            ArrayList<UIElement> listOfColumns = listOfRows.get(i).findUIElements(By.cssSelector("td a"));
            for (int j = 0; j < listOfColumns.size(); j++) {
                if (listOfColumns.get(j).getText().contains(value)) {
                    flag = true;
                }
            }
        }
        return flag;
    }


    public UIElement getCell(String columnName, int rowNumber) {
        return null;
    }

    public UIElement getCell(String targetColumnName, String targetValue, String columnName) {
        return null;
    }

    public UIElement getCell( int rowNumber, int columnNumber) {
        TableRow row = getRow(rowNumber);
        ArrayList<UIElement> list = row.findUIElements(By.tagName("td"));
        return list.get(columnNumber);
    }

    public UIElement getCell(int rowNumber, String columnName) {
        TableRow row = getRow(rowNumber);
        ArrayList<UIElement> list = row.findUIElements(By.tagName("td"));
        UIElement targetCell = null;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getText().contains(columnName)) {
                targetCell = list.get(i);
            }
        }
        return targetCell;
    }

    private List<UIElement> getColumns() {
        return uiElement.findUIElements(By.tagName("th"));
    }

    public ArrayList<TableRow> getListOfRows() {
        ArrayList<TableRow> list = new ArrayList<>();
        for (WebElement element : uiElement.findElements(By.tagName("tr"))) {
            list.add(new TableRow(driver, element));
        }
        return list;
    }

    public TableRow getRow(int rowNumber) {
        TableRow targetRow = null;
        for (int i = 0; i < getListOfRows().size(); i++) {
            if (i == rowNumber){
                targetRow = getListOfRows().get(i);
            }
        }
        return targetRow;
    }

    public TableRow getRow(String value) {
        ArrayList<UIElement> listOfRows = uiElement.findUIElements(By.tagName("tr"));
        TableRow targetRow = null;
        for (int i = 0; i < getListOfRows().size(); i++) {
            ArrayList<UIElement> listOfColumns = listOfRows.get(i).findUIElements(By.cssSelector("td a"));
            for (int j = 0; j < listOfColumns.size(); j++) {
                if (listOfColumns.get(j).getText().contains(value)) {
                    targetRow = getListOfRows().get(i);
                }
            }
        }
        return targetRow;
    }

    public UIElement getElementFromCell(UIElement cellElement, By by) {
        return cellElement.findElement(by);
    }

    public boolean isDisplayed() {
        return uiElement.isDisplayed();
    }
}
