package pages.projects;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.projects.tabs.TestCasesTab;

public class ProjectDetailsPage extends BasePage {
    private final static String pagePath = "index.php?/projects/overview/%1$d";
    private final By contentHeaderIdLocator = By.cssSelector(".content-header-id");
    private final By testCasesTabLinkLocator = By.linkText("Test Cases");

    public TestCasesTab testCasesTab;

    public ProjectDetailsPage(WebDriver driver) {
        super(driver);
        testCasesTab = new TestCasesTab(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return contentHeaderIdLocator;
    }

    public void openPageByUrl(int projectId) {
        super.openPageByUrl(String.format(pagePath, projectId));
    }

    public WebElement getLinkToTestCasesTab() {
        return waitsService.waitForVisibilityBy(testCasesTabLinkLocator);
    }

    public void goToTestCasesTab() {
        getLinkToTestCasesTab().click();
    }
}
