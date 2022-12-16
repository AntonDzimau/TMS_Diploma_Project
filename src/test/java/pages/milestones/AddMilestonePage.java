package pages.milestones;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddMilestonePage extends BaseMilestonePage {
    private static String pagePath = "index.php?/milestones/add/%1$d";
    private final By acceptButtonLocator = By.xpath("//button[contains(.,'Add Milestone')]");

    public AddMilestonePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return acceptButtonLocator;
    }

    public void openPageByUrl(int projectId) {
        super.openPageByUrl(String.format(pagePath, projectId));
    }

    public Button getAcceptButton() {
        return new Button(driver, waitsService.waitForVisibilityBy(acceptButtonLocator));
    }
}
