package steps;

import baseEntities.BaseStep;
import entities.MilestoneEntities;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.Milestones.AddMilestonePage;
import pages.Milestones.MilestoneDetailsPage;
import pages.projects.ProjectDetailsPage;

public class AddMilestoneStep extends BaseStep {
    AddMilestonePage addMilestonePage;
    ProjectDetailsPage projectDetailsPage;
    MilestoneDetailsPage milestoneDetailsPage;
    MilestoneEntities milestoneEntities;

    public AddMilestoneStep(WebDriver driver) {
        super(driver);
        addMilestonePage = new AddMilestonePage(driver);
        projectDetailsPage = new ProjectDetailsPage(driver);
        milestoneDetailsPage = new MilestoneDetailsPage(driver);
        milestoneEntities = new MilestoneEntities();
    }

    @Step
    public void openPageByUrl(int projectId) {
        addMilestonePage.openPageByUrl(projectId);
    }

    @Step
    public void addMilestoneToProject(int projectId) {
        openPageByUrl(projectId);
        addMilestonePage.getNameInput().sendKeys(milestoneEntities.firstMilestone.getName());
        addMilestonePage.getReferencesInput().sendKeys(milestoneEntities.firstMilestone.getReferences());
        addMilestonePage.getDescriptionArea().sendKeys(milestoneEntities.firstMilestone.getDescription());
        addMilestonePage.getMilestoneCompletedCheckBox().flag(milestoneEntities.firstMilestone.isCompleted());
        addMilestonePage.getAcceptButton().click();
        milestoneEntities.firstMilestone.setId(milestoneDetailsPage.getMilestoneId());
    }
}
