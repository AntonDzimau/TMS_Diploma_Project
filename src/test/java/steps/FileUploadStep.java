package steps;

import baseEntities.BaseStep;
import configuration.ReadProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import pages.testCases.AddTestCasePage;
import services.WaitsService;

public class FileUploadStep extends BaseStep {

    public String targetFileName;
    AddTestCasePage addTestCasePage;
    WaitsService waitsService;


    public FileUploadStep(WebDriver driver) {
        super(driver);
        addTestCasePage = new AddTestCasePage(driver);
        waitsService = new WaitsService(driver);
        targetFileName = "Nirvana_Something_In_The_Way.mp3";
    }

    @Step
    public AddTestCasePage uploadFile() {
        String pathToFile;
        addTestCasePage.openUploadPage();
        try {
            pathToFile = this.getClass().getClassLoader().getResource(targetFileName).getPath();
            addTestCasePage.uploadFilePage.getUploadFileButton().sendKeys(pathToFile);
        } catch (InvalidArgumentException e) {
            pathToFile = this.getClass().getClassLoader().getResource(targetFileName).getPath().substring(1);
            addTestCasePage.uploadFilePage.getUploadFileButton().sendKeys(pathToFile);
        }
        if (didUploadedFileFindInListAttachment(targetFileName)) {
            addTestCasePage.uploadFilePage.getAcceptUploadButton().click();
        }
        return addTestCasePage;
    }

    public Boolean didUploadedFileFindInListAttachment(String filename) {
        boolean flag = false;
        long targetTime = System.currentTimeMillis() + ReadProperties.timeout();
        while (System.currentTimeMillis() < targetTime) {
            for (int i = 0; i < addTestCasePage.uploadFilePage.getListOfUploadedFiles().size(); i++) {
                if (addTestCasePage.uploadFilePage.getListOfUploadedFiles().get(i).getAttribute("title").contains(filename)) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}
