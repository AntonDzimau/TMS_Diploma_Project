package pages.projects.tabs;

import baseEntities.BasePage;
import elements.Button;
import elements.CheckBox;
import elements.Table;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestCasesTab extends BasePage {
    private final By addTestCaseButtonLocator = By.id("sidebar-cases-add");
    private final By checkBoxedToSelectAllTestCases = By.xpath("//*[@class='header sectionRow caseDroppable']/th[2]/input");
    private final By deleteAllTestCases = By.id("deleteCases");
    private final By deletePermanentlyTestCasesLocator = By.linkText("Delete Permanently");
    private final By submitDeletePermanentlyTestCasesLocator = By.linkText("Delete Permanently");
    private final By testCaseRowLocator = By.className("caseRow");
    private final By listOfTestCasesLocator = By.xpath("//*[contains(@id,'grid-')]");
    private final By testCasesCountLocator = By.xpath("//*[contains(@id,'sectionCount-')]");

    public TestCasesTab(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return null;
    }

    public Button getAddTestCaseButton() {
        return new Button(driver, waitsService.waitForVisibilityBy(addTestCaseButtonLocator));
    }

    public void goToAddTestCasePage(){
        getAddTestCaseButton().click();
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

    public WebElement getTestCaseRow() {
        return waitsService.waitForVisibilityBy(testCaseRowLocator);
    }

    public Table getListOfTestCases() {
        return new Table(driver, waitsService.waitForExists(listOfTestCasesLocator));
    }

    public WebElement getSectionCountTestCases(){
        return waitsService.waitForVisibilityBy(testCasesCountLocator);
    }
}
