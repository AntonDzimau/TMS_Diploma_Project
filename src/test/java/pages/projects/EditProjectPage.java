package pages.projects;

import elements.Button;
import elements.CheckBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditProjectPage extends BaseProjectPage {
    private final static String pagePath = "index.php?/admin/projects/edit/11";
    private final By acceptButtonLocator = By.xpath("//button[contains(.,'Save Project')]");
    private final By completedProjectCheckBoxLocator = By.id("is_completed");

    public EditProjectPage(WebDriver driver) {
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

    public CheckBox getCompletedProjectCheckBox() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(completedProjectCheckBoxLocator));
    }
}
