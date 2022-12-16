package pages.testCases;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddTestCasePage extends BaseTestCasePage {

    private final By pageIdentifier = By.className("page_title");
    private final By acceptButtonLocator = By.id("accept");
    private final By uploadPageLocator = By.xpath("//*[@for='custom_preconds']/span/a[2]/div");
    private final By uploadButtonLocator = By.xpath("/html/body/input[5]");
    private final By acceptUploadLocator = By.id("attachmentNewSubmit");
    private final By uploadFileLocator = By.cssSelector("#custom_preconds_display .attachment-list-item");
    private final By addTestcasesLocator = By.id("sidebar-cases-add");
    private final By successfulTextLocator = By.className("message-success");

    private final By deleteTectCasesLocator=By.className("caseRow");


    public AddTestCasePage(WebDriver driver) {
        super(driver);
    }


    @Override
    protected By getPageIdentifier() {
        return pageIdentifier;
    }

    public WebElement getAcceptButtonLocator() {
        return waitsService.waitForVisibilityBy(acceptButtonLocator);
    }

    public WebElement getUploadPageLocator() {
        return waitsService.waitForVisibilityBy(uploadPageLocator);
    }

    public WebElement getUploadButtonLocator() {
        return driver.findElement(uploadButtonLocator);
    }
    public WebElement getDeleteTectCasesLocator(){
        return driver.findElement(deleteTectCasesLocator);
    }

    public WebElement getAcceptUpload() {

        return driver.findElement(acceptUploadLocator);
    }

    public WebElement getSuccessfulText() {
        return driver.findElement(successfulTextLocator);
    }

    public Button getAddTestcasesLocator() {
        return new Button(driver, waitsService.waitForVisibilityBy(addTestcasesLocator));
    }

    public WebElement getUploadFileLocator() {
        return driver.findElement(uploadFileLocator);
    }
}
