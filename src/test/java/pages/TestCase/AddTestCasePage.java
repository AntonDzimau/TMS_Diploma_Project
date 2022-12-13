package pages.TestCase;

import elements.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddTestCasePage extends BaseTestCasePage {

    private final By pageIdentifier = By.className("page_title");
    private final By acceptButtonLocator = By.id("accept");
    private final By downloadPageLocator = By.xpath("//*[@for='custom_preconds']/span/a[2]/div");
    private final By downloadButtonLocator = By.xpath("/html/body/input[5]");
    private final By acceptDownload = By.xpath("//*[@id='attachmentNewSubmit']/span");

    private final By downloadFileLocator = By.cssSelector("#custom_preconds_display .attachment-list-item");
    private final By addTestcasesLocator = By.id("sidebar-cases-add");
    private final By successfulTextLocator = By.className("message-success");


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

    public WebElement getDownloadPageLocator() {
        return waitsService.waitForVisibilityBy(downloadPageLocator);
    }

    public WebElement getDownloadButtonLocator() {
        return driver.findElement(downloadButtonLocator);
    }

    public WebElement getAcceptDownload() {
        return driver.findElement(acceptDownload);
    }

    public WebElement getSuccessfulText() {
        return driver.findElement(successfulTextLocator);
    }

    public Button getAddTestcasesLocator() {
        return new Button(driver, waitsService.waitForVisibilityBy(addTestcasesLocator));
    }

    public WebElement getDownloadFileLocator() {
        return driver.findElement(downloadFileLocator);
    }
}
