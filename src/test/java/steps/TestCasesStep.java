package steps;

import baseEntities.BaseTest;
import entities.TestCasesEntities;
import org.openqa.selenium.WebDriver;
import pages.TestCase.AddTestCasePage;
import pages.projects.AddProjectPage;

public class TestCasesStep extends BaseTest {
    AddTestCasePage addTestCasePage;


    public TestCasesStep(WebDriver driver) {
        super();
        addTestCasePage=new AddTestCasePage(driver);
    }
    public void addTestCases(){
        addTestCasePage.getNameInputLocator().sendKeys(TestCasesEntities.firstTestcases.getName());
        addTestCasePage.getExpectedResultInput().sendKeys(TestCasesEntities.firstTestcases.getExpectedResult());
        addTestCasePage.getPredictionInput().sendKeys(TestCasesEntities.firstTestcases.getPreconditions());
        addTestCasePage.getAcceptButtonLocator().click();
    }
}
