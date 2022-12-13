package baseEntities;

import configuration.ReadProperties;
import configuration.UpdateEnvironmentProperties;
import pages.projects.entities.MilestoneEntities;
import pages.projects.entities.ProjectsEntities;
import pages.projects.entities.TestCasesEntities;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import services.BrowsersService;
import steps.*;
import utils.InvokedListener;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Listeners(InvokedListener.class)
public class BaseTest {
    private Capabilities capabilities;
    protected WebDriver driver;

    protected LoginStep loginStep;
    protected ProjectSteps projectSteps;
    protected MilestoneSteps milestoneSteps;
    protected ProjectsEntities projectsEntities;
    protected MilestoneEntities milestoneEntities;
    protected TestCasesEntities testCasesEntities;
    protected TestCasesStep testCasesStep;
    protected FileDownloadStep fileDownloadStep;


    @BeforeMethod
    public void setUp(ITestContext iTestContext) {
        driver = new BrowsersService().getDriver();
        iTestContext.setAttribute("driver", driver);

        driver.get(ReadProperties.getUrl());
        capabilities = ((RemoteWebDriver) driver).getCapabilities();

        if (!UpdateEnvironmentProperties.isFileExist()) {
            UpdateEnvironmentProperties.setProperty("os.name", System.getProperty("os.name"));
            UpdateEnvironmentProperties.setProperty("user.name", System.getProperty("user.name"));
            UpdateEnvironmentProperties.setProperty("java.version", System.getProperty("java.version"));
            UpdateEnvironmentProperties.setProperty("browser.name", capabilities.getBrowserName());
            UpdateEnvironmentProperties.setProperty("browser.version", capabilities.getBrowserVersion());
            UpdateEnvironmentProperties.storeEnvProperties();
        }
        loginStep = new LoginStep(driver);
        projectSteps = new ProjectSteps(driver);
        milestoneSteps = new MilestoneSteps(driver);
        projectsEntities = new ProjectsEntities();
        milestoneEntities = new MilestoneEntities();
        testCasesEntities=new TestCasesEntities();
        testCasesStep=new TestCasesStep(driver);
        fileDownloadStep=new FileDownloadStep(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterTest
    public void copyEnvironment() throws IOException {
        File copied = new File("target/test-classes/environment.properties");
        File target = new File("target/allure-results/environment.properties");
        Files.copy(copied.toPath(), target.toPath());
    }
}
