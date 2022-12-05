package pages.Milestones;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MilestoneDetailsPage extends BasePage {
    private final static String pagePath = "index.php?/milestones/view/";
    private final By milestoneIdLocator = By.cssSelector(".content-header-id");

    public MilestoneDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return milestoneIdLocator;
    }

    public void openPageByUrl() {
        super.openPageByUrl(pagePath + getMilestoneId());
    }

    public WebElement getContentHeaderId() {
        return waitsService.waitForVisibilityBy(milestoneIdLocator);
    }

    public int getMilestoneId() {
        return Integer.parseInt(getContentHeaderId().getText().substring(1));
    }

}
