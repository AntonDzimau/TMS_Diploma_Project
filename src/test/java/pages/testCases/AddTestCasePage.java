package pages.testCases;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddTestCasePage extends BaseTestCasePage {

    private final By pageIdentifier = By.className("page_title");
    private final By acceptButtonLocator = By.id("accept");
    private final By uploadPageLocator = By.id("entityAttachmentListEmptyIcon");
    private final By addTestcasesLocator = By.id("sidebar-cases-add");
    private final By successfulTextLocator = By.className("message-success");
    private final By deleteTestCasesLocator = By.className("caseRow");

    public UploadFilePage uploadFilePage;

    public AddTestCasePage(WebDriver driver) {
        super(driver);
        uploadFilePage = new UploadFilePage(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return pageIdentifier;
    }

    public WebElement getAcceptButton() {
        return waitsService.waitForVisibilityBy(acceptButtonLocator);
    }

    public WebElement getUploadPage() {
        return waitsService.waitForVisibilityBy(uploadPageLocator);
    }

    public void openUploadPage() {
        getUploadPage().click();
    }

    public WebElement getDeleteTestCases() {
        return waitsService.waitForVisibilityBy(deleteTestCasesLocator);
    }

    public WebElement getSuccessfulText() {
        return driver.findElement(successfulTextLocator);
    }

    public Button getAddTestCases() {
        return new Button(driver, waitsService.waitForVisibilityBy(addTestcasesLocator));
    }
}
