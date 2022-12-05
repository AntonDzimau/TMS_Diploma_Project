package baseEntities;

import configuration.ReadProperties;
import configuration.UpdateEnvironmentProperties;
import entities.ProjectsEntities;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import services.BrowsersService;
import steps.AddProjectStep;
import steps.LoginStep;
import steps.RemoveProjectStep;
import utils.InvokedListener;

@Listeners(InvokedListener.class)
public class BaseTest {
    private Capabilities capabilities;
    protected WebDriver driver;

    protected LoginStep loginStep;
    protected AddProjectStep addProjectStep;
    protected RemoveProjectStep removeProjectStep;
    protected ProjectsEntities projectsEntities;

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
        addProjectStep = new AddProjectStep(driver);
        removeProjectStep = new RemoveProjectStep(driver);
        projectsEntities = new ProjectsEntities();
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
