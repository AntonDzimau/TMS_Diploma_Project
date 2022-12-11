package steps;

import baseEntities.BaseStep;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import pages.TestCase.AddTestCasePage;

public class FileDownloadStep extends BaseStep {

    AddTestCasePage addTestCasePage;

    public FileDownloadStep(WebDriver driver) {
        super(driver);
        addTestCasePage = new AddTestCasePage(driver);
    }

    public void downloadFile() throws InterruptedException {
        addTestCasePage.getDownloadPageLocator().click();
        String pathToFile;
        try {
            pathToFile = this.getClass().getClassLoader().getResource("3-martin-adams-764547-unsplash.jpeg").getPath();
            addTestCasePage.getDownloadButtonLocator().sendKeys(pathToFile);
        } catch (InvalidArgumentException e) {
            pathToFile = this.getClass().getClassLoader().getResource("3-martin-adams-764547-unsplash.jpeg").getPath().substring(1);
            addTestCasePage.getDownloadButtonLocator().sendKeys(pathToFile);
        } finally {
            Thread.sleep(5000);
            addTestCasePage.getAcceptDownload().click();
        }

    }

    public boolean assertFile() throws InterruptedException {
        Thread.sleep(5000);
        return addTestCasePage.getDownloadFileLocator().isEnabled();
    }

}
