package BaseEntities;

import configuration.ReadProperties;
import configuration.UpdateEnvironmentProperties;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import services.BrowsersService;
import services.WaitsService;
import steps.AddNewProjectStep;
import steps.LoginStep;
import utils.InvokedListener;

import java.time.Duration;

@Listeners(InvokedListener.class)
public class BaseTest {
    protected WebDriver driver;
    private Capabilities capabilities;
    protected LoginStep loginStep;
    protected AddNewProjectStep addNewProjectStep;

    @BeforeMethod
    public void setUp(ITestContext iTestContext) {
        driver = new BrowsersService().getDriver();
        iTestContext.setAttribute("driver", driver);

        driver.get(ReadProperties.getUrl());

        capabilities = ((RemoteWebDriver) driver).getCapabilities();

        loginStep = new LoginStep(driver);

        addNewProjectStep = new AddNewProjectStep(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @AfterTest
    public void storeInfo() {
        UpdateEnvironmentProperties.setProperty("os.name", System.getProperty("os.name"));
        UpdateEnvironmentProperties.setProperty("user.home", System.getProperty("user.home"));
        UpdateEnvironmentProperties.setProperty("browser.name", capabilities.getBrowserName());
        UpdateEnvironmentProperties.setProperty("browser.version", capabilities.getBrowserVersion());
        UpdateEnvironmentProperties.storeEnvProperties();
    }
}
