package pages.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddTestCasePage extends BaseTestCasePage {
    private final By pageIdentifier = By.className("page_title");
    private final By acceptButtonLocator = By.id("accept");
    private final By uploadPageLocator = By.id("entityAttachmentListEmptyIcon");

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
}
