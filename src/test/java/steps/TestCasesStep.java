package steps;

import baseEntities.BaseTest;
import pages.DashboardPage;
import entities.TestCasesEntities;
import org.openqa.selenium.WebDriver;
import pages.testCases.AddTestCasePage;

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
        dashboardPage.getgoToTestCasesPageLocator().click();
        addTestCasePage.getCheckBoxedToSelectAllTestCases().setFlag();
        addTestCasePage.getDeleteAllTestCases().click();
        addTestCasePage.getDeletePermanentlyTestCasesLocator().click();
        addTestCasePage.getSubmitDeletePermanentlyTestCasesLocator().click();
    }
}
