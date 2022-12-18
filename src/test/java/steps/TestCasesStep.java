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
        dashboardPage.getLinkToTestCasesPage().click();
        addTestCasePage.getAddTestCases().click();
    }

    public void addTestCasesStep() {
        addTestCasePage.getNameInputLocator().sendKeys(TestCasesEntities.firstTestcases.getName());
        addTestCasePage.getExpectedResultInput().sendKeys(TestCasesEntities.firstTestcases.getExpectedResult());
        addTestCasePage.getPredictionInput().sendKeys(TestCasesEntities.firstTestcases.getPreconditions());
        addTestCasePage.getAcceptButton().click();
    }

    public String getSuccessfulText() {
        return addTestCasePage.getSuccessfulText().getText();
    }

    public void deleteTestCasesStep() {
        dashboardPage.goToTestCasesPage();
        addTestCasePage.getCheckBoxedToSelectAllTestCases().setFlag();
        addTestCasePage.getDeleteAllTestCases().click();
        addTestCasePage.getDeletePermanentlyTestCasesLocator().click();
        addTestCasePage.getSubmitDeletePermanentlyTestCasesLocator().click();
    }

    public boolean checkForDelete(){
        return addTestCasePage.getDeleteTestCases().isDisplayed();
    }
}
