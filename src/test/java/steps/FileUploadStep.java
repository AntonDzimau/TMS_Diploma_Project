package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import pages.TestCase.AddTestCasePage;

public class FileUploadStep extends BaseStep {

    AddTestCasePage addTestCasePage;

    public FileUploadStep(WebDriver driver) {
        super(driver);
        addTestCasePage = new AddTestCasePage(driver);
    }

    @Step
    public void uploadFile() throws InterruptedException {
        addTestCasePage.getUploadPageLocator().click();
        String pathToFile;
        try {
            pathToFile = this.getClass().getClassLoader().getResource("3-martin-adams-764547-unsplash.jpeg").getPath();
            addTestCasePage.getUploadButtonLocator().sendKeys(pathToFile);
        } catch (InvalidArgumentException e) {
            pathToFile = this.getClass().getClassLoader().getResource("3-martin-adams-764547-unsplash.jpeg").getPath().substring(1);
            addTestCasePage.getUploadButtonLocator().sendKeys(pathToFile);
        } finally {
            //ToDo: Попробовать сделать какое-то другое ожидание
            Thread.sleep(5000);
            addTestCasePage.getAcceptUpload().click();
        }
    }

    public boolean assertFile() throws InterruptedException {
        //ToDo: Попробовать сделать какое-то другое ожидание
        Thread.sleep(5000);
        return addTestCasePage.getUploadFileLocator().isEnabled();
    }

}
