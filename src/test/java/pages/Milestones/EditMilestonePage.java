package pages.Milestones;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditMilestonePage extends BaseMilestonePage {
    private final static String pagePath = "index.php?/milestones/edit/";
    private final By acceptButtonLocator = By.xpath("//button[contains(.,'Save Milestone')]");

    public EditMilestonePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return acceptButtonLocator;
    }

    public void openPageByUrl(int milestoneId) {
        super.openPageByUrl(pagePath + milestoneId);
    }

    public Button getAcceptButton() {
        return new Button(driver, waitsService.waitForVisibilityBy(acceptButtonLocator));
    }
}
