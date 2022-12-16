package pages.testCases;

import baseEntities.BasePage;
import elements.CheckBox;
import elements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseTestCasePage extends BasePage {

    private final By nameInputLocator = By.id("title");
    private final By predictionInput = By.id("custom_steps_display");
    private final By expectedResultInput = By.id("custom_expected_display");
    private final By droupDownType = By.className("class");
    private final By checkBoxedToSelectAllTestCases = By.xpath("//*[@class='header sectionRow caseDroppable']/th[2]/input");
    private final By deleteAllTestCases = By.id("deleteCases");
    private final By deletePermanentlyTestCasesLocator = By.linkText("Delete Permanently");
    private final By submitDeletePermanentlyTestCasesLocator = By.linkText("Delete Permanently");

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

    public CheckBox getCheckBoxedToSelectAllTestCases() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(checkBoxedToSelectAllTestCases));
    }

    public WebElement getDeleteAllTestCases() {
        return waitsService.waitForVisibilityBy(deleteAllTestCases);
    }

    public WebElement getDeletePermanentlyTestCasesLocator() {
        return waitsService.waitForVisibilityBy(deletePermanentlyTestCasesLocator);
    }

    public WebElement getSubmitDeletePermanentlyTestCasesLocator() {
        return waitsService.waitForVisibilityBy(submitDeletePermanentlyTestCasesLocator);
    }
}

