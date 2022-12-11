package pages.TestCase;

import baseEntities.BasePage;
import elements.CheckBox;
import elements.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BaseTestCasePage extends BasePage {

    private final By nameInputLocator = By.id("title");
    private final By predictionInput = By.id("custom_steps_display");
    private final By stepInput = By.id("custom_steps_display");
    private final By expectedResultInput = By.id("custom_expected_display");
    private final By droupDownType = By.className("class");
    private final By goToTestCasesPageLocator=By.linkText("Test Cases");
    private final By deleteTestCases = By.xpath("//*[@id='section-1']/div[1]/a[1]/div");
    private final By checkBoxedDeleteTestCases = By.xpath("/html/body/div[4]/div/div[1]/div[2]/div[17]/div/div/div/div[1]/a[2]/div");
    private final By submitDeleteTestCases = By.xpath("//*[@id='deleteDialog']/div[3]/a[1]");


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

    public WebElement getDeleteTestCases() {
        return waitsService.waitForVisibilityBy(deleteTestCases);
    }
    public WebElement getGoToTestCasesPageLocator(){
        return waitsService.waitForVisibilityBy(goToTestCasesPageLocator);
    }

    public Input getStepInput() {
        return new Input(driver, waitsService.waitForVisibilityBy(stepInput));
    }

    public CheckBox getCheckBoxedDeleteTestCases() {
        return new CheckBox(driver, waitsService.waitForVisibilityBy(checkBoxedDeleteTestCases));
    }

    public WebElement getSubmitDeleteTestCased() {
        return waitsService.waitForVisibilityBy(submitDeleteTestCases);
    }
}
