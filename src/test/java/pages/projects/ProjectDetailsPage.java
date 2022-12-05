package pages.projects;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProjectDetailsPage extends BasePage {
    private final static String pagePath = "index.php?/projects/overview/";
    private final By contentHeaderIdLocator = By.cssSelector(".content-header-id");

    public ProjectDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return contentHeaderIdLocator;
    }

    public void openPageByUrl() {
        super.openPageByUrl(pagePath + getProjectId());
    }

    public WebElement getContentHeaderId() {
        return waitsService.waitForVisibilityBy(contentHeaderIdLocator);
    }

    public int getProjectId() {
        return Integer.parseInt(getContentHeaderId().getText().substring(1));
    }

}
