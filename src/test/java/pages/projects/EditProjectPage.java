package pages.projects;

import elements.Button;
import elements.CheckBox;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditProjectPage extends BaseProjectPage {
    private final By acceptButtonLocator = By.xpath("//button[contains(.,'Save Project')]");
    private final By completedProjectCheckBoxLocator = By.id("is_completed");

    public EditProjectPage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return acceptButtonLocator;
    }

    public Button getAcceptButton() {
        return new Button(driver, waitsService.waitForVisibilityBy(acceptButtonLocator));
    }

    public CheckBox getCompletedProjectCheckBox() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(completedProjectCheckBoxLocator));
    }
}
