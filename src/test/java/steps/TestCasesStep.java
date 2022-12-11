package steps;

import baseEntities.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import pages.DashboardPage;
import pages.projects.entities.TestCasesEntities;
import org.openqa.selenium.WebDriver;
import pages.TestCase.AddTestCasePage;

public class TestCasesStep extends BaseTest {
    AddTestCasePage addTestCasePage;
    DashboardPage dashboardPage;


    public TestCasesStep(WebDriver driver) {
        super();
        addTestCasePage = new AddTestCasePage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    public void goToTestCasesStep() {
        dashboardPage.getgoToTestCasesPageLocator().click();
        addTestCasePage.getAddTestcasesLocator().click();
    }

    public void addTestCasesStep() {
        addTestCasePage.getNameInputLocator().sendKeys(TestCasesEntities.firstTestcases.getName());
        addTestCasePage.getExpectedResultInput().sendKeys(TestCasesEntities.firstTestcases.getExpectedResult());
        addTestCasePage.getPredictionInput().sendKeys(TestCasesEntities.firstTestcases.getPreconditions());
        addTestCasePage.getAcceptButtonLocator().click();
    }

    public String getSuccessfulText() {
        return addTestCasePage.getSuccessfulText().getText();
    }

    public void deleteTestCasesStep() {
        addTestCasePage = new AddTestCasePage(driver);
        addTestCasePage.getGoToTestCasesPageLocator().click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click", driver.findElement(By.xpath("//*[@id='section-1']/div[1]/a[1]/div")));
        addTestCasePage.getCheckBoxedDeleteTestCases().setFlag();
        addTestCasePage.getSubmitDeleteTestCased().click();
    }

}
