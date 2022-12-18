package steps;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import entities.TestCasesEntities;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.DashboardPage;
import pages.projects.ProjectDetailsPage;
import pages.testCases.AddTestCasePage;
import pages.testCases.TestCaseDetailsPage;

public class TestCasesStep extends BaseTest {
    AddTestCasePage addTestCasePage;
    DashboardPage dashboardPage;
    ProjectDetailsPage projectDetailsPage;
    TestCaseDetailsPage testCaseDetailsPage;


    public TestCasesStep(WebDriver driver) {
        super();
        addTestCasePage = new AddTestCasePage(driver);
        dashboardPage = new DashboardPage(driver);
        projectDetailsPage = new ProjectDetailsPage(driver);
        testCaseDetailsPage = new TestCaseDetailsPage(driver);
    }

    @Step
    public AddTestCasePage goToAddTestCasePage(int projectId) {
        projectDetailsPage.openPageByUrl(projectId);
        projectDetailsPage.goToTestCasesTab();
        projectDetailsPage.testCasesTab.goToAddTestCasePage();
        return addTestCasePage;
    }

    @Step
    public TestCaseDetailsPage addTestCase() {
        addTestCasePage.getNameInputLocator().sendKeys(TestCasesEntities.firstTestcases.getName());
        addTestCasePage.getExpectedResultInput().sendKeys(TestCasesEntities.firstTestcases.getExpectedResult());
        addTestCasePage.getPredictionInput().sendKeys(TestCasesEntities.firstTestcases.getPreconditions());
        addTestCasePage.getAcceptButton().click();
        return testCaseDetailsPage;
    }

    @Step
    public boolean deleteAllTestCasesInProject(int projectId) {
        projectDetailsPage.openPageByUrl(projectId);
        projectDetailsPage.goToTestCasesTab();
        projectDetailsPage.testCasesTab.getCheckBoxedToSelectAllTestCases().setFlag();
        projectDetailsPage.testCasesTab.getDeleteAllTestCases().click();
        projectDetailsPage.testCasesTab.getDeletePermanentlyTestCasesLocator().click();
        projectDetailsPage.testCasesTab.getSubmitDeletePermanentlyTestCasesLocator().click();
        return isListOfTestCasesEmpty();
    }

    private boolean isListOfTestCasesEmpty() {
        boolean flag = false;
        long targetTime = System.currentTimeMillis() + ReadProperties.timeout() * 1000L;
        while (System.currentTimeMillis() < targetTime) {
            if (projectDetailsPage.testCasesTab.getSectionCountTestCases().getText().contains("0")) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
