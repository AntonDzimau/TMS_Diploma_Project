package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import models.Milestone;
import org.openqa.selenium.WebDriver;
import pages.Milestones.AddMilestonePage;
import pages.Milestones.ListOfMilestonesPage;

public class MilestoneSteps extends BaseStep {
    AddMilestonePage addMilestonePage;
    ListOfMilestonesPage listOfMilestonesPage;

    public MilestoneSteps(WebDriver driver) {
        super(driver);
        addMilestonePage = new AddMilestonePage(driver);
        listOfMilestonesPage = new ListOfMilestonesPage(driver);
    }

    @Step
    public ListOfMilestonesPage addMilestoneToProject(int projectId, Milestone milestone) {
        addMilestonePage.openPageByUrl(projectId);
        addMilestonePage.getNameInput().sendKeys(milestone.getName());
        addMilestonePage.getReferencesInput().sendKeys(milestone.getReferences());
        addMilestonePage.getDescriptionArea().sendKeys(milestone.getDescription());
        addMilestonePage.getMilestoneCompletedCheckBox().flag(milestone.isCompleted());
        addMilestonePage.getAcceptButton().click();
        return listOfMilestonesPage;
    }

    @Step
    public ListOfMilestonesPage removeCompletedMilestoneFromProject(int projectId, String milestoneName) throws InterruptedException {
        listOfMilestonesPage.openPageByUrl(projectId);
        Thread.sleep(1000);
        System.out.println(listOfMilestonesPage.getListOfCompletedMilestones().getListOfRows().size());
        for (int i = listOfMilestonesPage.getListOfCompletedMilestones().getListOfRows().size(); i > 0; i--) {
            Thread.sleep(1000);
            if (listOfMilestonesPage.getListOfCompletedMilestones().isFoundInTable(milestoneName)) {
                listOfMilestonesPage.getListOfCompletedMilestones().getRow(milestoneName).getCell(3).getLinkFromCell().click();
                Thread.sleep(1000);
                listOfMilestonesPage.getAcceptRemovalButton().click();
                Thread.sleep(1000);
            } else {
                break;
            }
        }
        return listOfMilestonesPage;
    }
}
