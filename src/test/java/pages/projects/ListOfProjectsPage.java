package pages.projects;

import baseEntities.BasePage;
import elements.Button;
import elements.CheckBox;
import elements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListOfProjectsPage extends BasePage {
    private final static String pagePath = "index.php?/admin/projects/overview";
    private final By pageHeaderTitleLocator = By.xpath("//*[contains(@class, 'page_title')][contains(.,'Projects')]");
    private final By listOfProjectsTableLocator = By.className("grid");
    private final By checkboxForRemovalLocator = By.cssSelector("div#deleteDialog  input");
    private final By acceptRemovalButtonLocator = By.cssSelector("div#deleteDialog  a.button-ok");

    public ListOfProjectsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return pageHeaderTitleLocator;
    }

    public void openPageByUrl() {
        super.openPageByUrl(pagePath);
    }

    public WebElement getPageHeaderTitle() {
        return waitsService.waitForVisibilityBy(pageHeaderTitleLocator);
    }

    public Table getListOfProjects() {
        return new Table(driver, waitsService.waitForVisibilityBy(listOfProjectsTableLocator));
    }

    public CheckBox getCheckboxForRemoval() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(checkboxForRemovalLocator));
    }

    public Button getAcceptRemovalButton() {
        return new Button(driver, waitsService.waitForClickableBy(acceptRemovalButtonLocator));
    }
}
