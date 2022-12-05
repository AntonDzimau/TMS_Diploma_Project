package pages.projects;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddProjectPage extends BaseProjectPage{
    private final static String pagePath = "index.php?/admin/projects/add";
    private final By acceptButtonLocator = By.xpath("//button[contains(.,'Add Project')]");

    public AddProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return acceptButtonLocator;
    }

    public void openPageByUrl() {
        super.openPageByUrl(pagePath);
    }

    public Button getAcceptButton() {
        return new Button(driver, waitsService.waitForVisibilityBy(acceptButtonLocator));
    }

}
