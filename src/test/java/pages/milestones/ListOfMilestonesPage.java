package pages.milestones;

import baseEntities.BasePage;
import elements.Button;
import elements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ListOfMilestonesPage extends BasePage {
    private final static String pagePath = "index.php?/milestones/overview/%1$d";
    private final By pageHeaderTitleLocator = By.xpath("//*[contains(@class, 'page_title')][contains(.,'Milestones')]");
    private final By listOfActiveMilestonesLocator = By.id("active");
    private final By listOfCompletedMilestonesLocator = By.className("grid");
    private final By activeMilestonesLinksLocator = By.cssSelector(".flex-milestones-2 .text-ppp a");
    private final By acceptRemovalButtonLocator = By.cssSelector("div#deleteDialog  a.button-ok");
    private final By messageSuccessBoxLocator = By.className("message-success");

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

    public Table getListOfCompletedMilestones() {
        return new Table(driver, waitsService.waitForExists(listOfCompletedMilestonesLocator));
    }

    public WebElement getListOfActiveMilestones() {
        return waitsService.waitForVisibilityBy(listOfActiveMilestonesLocator);
    }

    public WebElement getMessageSuccessBox() {
        return waitsService.waitForVisibilityBy(messageSuccessBoxLocator);
    }

    public List<WebElement> getActiveMilestonesLinks() {
        return getListOfActiveMilestones().findElements(activeMilestonesLinksLocator);
    }

    public Button getAcceptRemovalButton() {
        return new Button(driver, waitsService.waitForClickableBy(acceptRemovalButtonLocator));
    }
}
