package pages.Milestones;

import elements.Button;
import entities.ProjectsEntities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddMilestonePage extends BaseMilestonePage {
    private final static String pagePath = "index.php?/milestones/add/";
    private final By acceptButtonLocator = By.xpath("//button[contains(.,'Add Milestone')]");

    public AddMilestonePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return acceptButtonLocator;
    }

    public void openPageByUrl(int projectId) {
        super.openPageByUrl(pagePath + projectId);
    }

    public Button getAcceptButton() {
        return new Button(driver, waitsService.waitForVisibilityBy(acceptButtonLocator));
    }
}
