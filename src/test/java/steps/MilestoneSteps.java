package steps;

import baseEntities.BaseStep;
import entities.MilestoneEntities;
import io.qameta.allure.Step;
import models.Milestone;
import org.openqa.selenium.WebDriver;
import pages.Milestones.AddMilestonePage;
import pages.Milestones.ListOfMilestonesPage;

public class MilestoneSteps extends BaseStep {
    AddMilestonePage addMilestonePage;
    MilestoneEntities milestoneEntities;
    ListOfMilestonesPage listOfMilestonesPage;

    public MilestoneSteps(WebDriver driver) {
        super(driver);
        addMilestonePage = new AddMilestonePage(driver);
        listOfMilestonesPage = new ListOfMilestonesPage(driver);
        milestoneEntities = new MilestoneEntities();
    }

    @Step
    public void openPageByUrl(int projectId) {
        addMilestonePage.openPageByUrl(projectId);
    }

    @Step
    public ListOfMilestonesPage addMilestoneToProject(int projectId, Milestone milestone) {
        openPageByUrl(projectId);

        addMilestonePage.getNameInput().sendKeys(milestone.getName());
        addMilestonePage.getReferencesInput().sendKeys(milestone.getReferences());
        addMilestonePage.getDescriptionArea().sendKeys(milestone.getDescription());
        addMilestonePage.getMilestoneCompletedCheckBox().flag(milestone.isCompleted());
        addMilestonePage.getAcceptButton().click();
        return listOfMilestonesPage;
    }

    public boolean findMilestoneInList(Milestone targetMilestone) {
        boolean flag = false;
        for (int i = listOfMilestonesPage.getMilestonesLinks().size(); i > 0; i--) {
            if (listOfMilestonesPage.getMilestonesLinks().get(i - 1).getText().contains(targetMilestone.getName())) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
