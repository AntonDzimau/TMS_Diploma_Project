package pages.projects;

import baseEntities.BasePage;
import elements.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseProjectPage extends BasePage {
    private final By nameInputLocator = By.id("name");
    private final By announcementTextareaLocator = By.id("announcement");
    private final By cancelButtonLocator = By.id("admin-integration-form-cancel");
    private final By showAnnouncementCheckBoxLocator = By.id("show_announcement");
    private final By radioButtonLocator = By.cssSelector("[name='suite_mode']");

    public BaseProjectPage(WebDriver driver) {
        super(driver);
    }


    public Input getNameInput() {
        return new Input(driver, waitsService.waitForVisibilityBy(nameInputLocator));
    }

    public Textarea getAnnouncementTextarea() {
        return new Textarea(driver, waitsService.waitForVisibilityBy(announcementTextareaLocator));
    }

    public Button getCancelButton() {
        return new Button(driver, waitsService.waitForVisibilityBy(cancelButtonLocator));
    }

    public CheckBox getShowAnnouncementCheckBox() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(showAnnouncementCheckBoxLocator));
    }

    public RadioButton getRadioButton() {
        return new RadioButton(driver, radioButtonLocator);
    }

}
