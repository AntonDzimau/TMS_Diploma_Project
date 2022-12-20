package pages.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class UploadFilePage extends BaseTestCasePage {
    private final By uploadFileInputLocator = By.xpath("/html/body/input[5]");
    private final By acceptUploadButtonLocator = By.id("attachmentNewSubmit");
    private final By uploadedFileContainerLocator = By.cssSelector("#attachmentsNewList .attachment-block");

    public UploadFilePage(WebDriver driver) {
        super(driver);
    }

    @Override
    protected By getPageIdentifier() {
        return acceptUploadButtonLocator;
    }

    public WebElement getUploadFileButton() {
        return waitsService.waitForExists(uploadFileInputLocator);
    }

    public WebElement getAcceptUploadButton() {
        return waitsService.waitForVisibilityBy(acceptUploadButtonLocator);
    }

    public List<WebElement> getListOfUploadedFiles() {
        return waitsService.waitForAllVisibleElementsLocatedBy(uploadedFileContainerLocator);
    }
}
