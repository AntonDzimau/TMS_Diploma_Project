package pages.TestCase;

import baseEntities.BasePage;
import elements.DropDown;
import elements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BaseTestCasePage extends BasePage {

    private final By nameInputLocator = By.id("title");
    private final By predictionInput = By.id("custom_steps_display");
    private final By stepInput = By.id("custom_steps_display");
    private final By expectedResultInput = By.id("custom_expected_display");
    private final By droupDownType = By.className("class");

    public BaseTestCasePage(WebDriver driver) {
        super(driver);
    }

    public Input getNameInputLocator() {
        return new Input(driver, waitsService.waitForVisibilityBy(nameInputLocator));
    }

    public Input getPredictionInput() {
        return new Input(driver, waitsService.waitForVisibilityBy(predictionInput));
    }

    public Input getExpectedResultInput() {
        return new Input(driver, waitsService.waitForVisibilityBy(expectedResultInput));
    }
    public DropDown getDropdownType(){
        return new DropDown(driver,droupDownType);
    }

    public Input getStepInput() {
        return new Input(driver, waitsService.waitForVisibilityBy(stepInput));
    }
}
