package pages.Milestones;

import baseEntities.BasePage;
import elements.Button;
import elements.CheckBox;
import elements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ListOfMilestonesPage extends BasePage {
    private final static String pagePath = "index.php?/milestones/overview/";
    private final By pageHeaderTitleLocator = By.xpath("//*[contains(@class, 'page_title')][contains(.,'Milestones')]");
/*    private final By listOfProjectsTableLocator = By.className("grid");
    private final By checkboxForRemovalLocator = By.cssSelector("div#deleteDialog  input");
    private final By acceptRemovalButtonLocator = By.cssSelector("div#deleteDialog  a.button-ok");*/

    public ListOfMilestonesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return pageHeaderTitleLocator;
    }

    public void openPageByUrl(int projectId) {
        super.openPageByUrl(pagePath + projectId);
    }

    public WebElement getPageHeaderTitle() {
        return waitsService.waitForVisibilityBy(pageHeaderTitleLocator);
    }

/*    public Table getListOfProjects() {
        return new Table(driver, waitsService.waitForVisibilityBy(listOfProjectsTableLocator));
    }

    public CheckBox getCheckboxForRemoval() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(checkboxForRemovalLocator));
    }

    public Button getAcceptRemovalButton() {
        return new Button(driver, waitsService.waitForClickableBy(acceptRemovalButtonLocator));
    }*/
}
