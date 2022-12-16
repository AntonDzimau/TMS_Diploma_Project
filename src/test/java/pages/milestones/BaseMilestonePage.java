package pages.milestones;

import baseEntities.BasePage;
import elements.Button;
import elements.CheckBox;
import elements.Input;
import elements.Textarea;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseMilestonePage extends BasePage {
    private final By nameInputLocator = By.id("name");
    private final By referencesInputLocator = By.id("reference");
    private final By descriptionAreaLocator = By.id("description_display");
    private final By milestoneCompletedCheckBoxLocator = By.id("is_completed");
    private final By cancelButtonLocator = By.cssSelector(".milestone-form-cancel");

    public BaseMilestonePage(WebDriver driver) {
        super(driver);
    }

    public Input getNameInput() {
        return new Input(driver, waitsService.waitForVisibilityBy(nameInputLocator));
    }

    public Input getReferencesInput() {
        return new Input(driver, waitsService.waitForVisibilityBy(referencesInputLocator));
    }

    public Textarea getDescriptionArea() {
        return new Textarea(driver, waitsService.waitForVisibilityBy(descriptionAreaLocator));
    }

    public CheckBox getMilestoneCompletedCheckBox() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(milestoneCompletedCheckBoxLocator));
    }

    public Button getCancelButton() {
        return new Button(driver, waitsService.waitForVisibilityBy(cancelButtonLocator));
    }
}
