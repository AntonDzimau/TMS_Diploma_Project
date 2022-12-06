package pages.Milestones;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ListOfMilestonesPage extends BasePage {
    private final static String pagePath = "index.php?/milestones/overview/%1$d";
    private final By pageHeaderTitleLocator = By.xpath("//*[contains(@class, 'page_title')][contains(.,'Milestones')]");
    private final By listOfMilestonesLocator = By.id("active");
    private final By milestonesLinksLocator = By.cssSelector(".flex-milestones-2 .text-ppp a");

    public ListOfMilestonesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return pageHeaderTitleLocator;
    }

    public void openPageByUrl(int projectId) {
        super.openPageByUrl(String.format(pagePath, projectId));
    }

    public WebElement getPageHeaderTitle() {
        return waitsService.waitForVisibilityBy(pageHeaderTitleLocator);
    }

    public WebElement getListOfMilestones() {
        return waitsService.waitForVisibilityBy(listOfMilestonesLocator);
    }

    public List<WebElement> getMilestonesLinks() {
        return getListOfMilestones().findElements(milestonesLinksLocator);
    }
}
