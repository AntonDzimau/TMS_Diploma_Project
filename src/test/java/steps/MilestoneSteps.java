package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import models.Milestone;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import pages.Milestones.AddMilestonePage;
import pages.Milestones.ListOfMilestonesPage;
import pages.projects.ListOfProjectsPage;

public class MilestoneSteps extends BaseStep {
    AddMilestonePage addMilestonePage;
    ListOfMilestonesPage listOfMilestonesPage;
    Actions actions;

    public MilestoneSteps(WebDriver driver) {
        super(driver);
        addMilestonePage = new AddMilestonePage(driver);
        listOfMilestonesPage = new ListOfMilestonesPage(driver);
        actions = new Actions(driver);
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
    public ListOfMilestonesPage removeCompletedMilestoneFromProject(int projectId, String milestoneName) {
        listOfMilestonesPage.openPageByUrl(projectId);
        for (int i = listOfMilestonesPage.getListOfCompletedMilestones().getListOfRows().size(); i > 0; i--) {
            if (listOfMilestonesPage.getListOfCompletedMilestones().isFoundInTable(milestoneName)) {
                listOfMilestonesPage.getListOfCompletedMilestones().getRow(milestoneName).getCell(3).getLinkFromCell().click();
                listOfMilestonesPage.getAcceptRemovalButton().click();
                break;
            }
        }
        return listOfMilestonesPage;
    }

    @Step
    public ListOfMilestonesPage removeCompletedMilestoneFromProjectWithActions(int projectId, String milestoneName) {
        listOfMilestonesPage.openPageByUrl(projectId);
        for (int i = listOfMilestonesPage.getListOfCompletedMilestones().getListOfRows().size(); i > 0; i--) {
            if (listOfMilestonesPage.getListOfCompletedMilestones().isFoundInTable(milestoneName)) {
                actions
                        .click(listOfMilestonesPage.getListOfCompletedMilestones().getRow(milestoneName).getCell(3).getLinkFromCellLikeWebElement())
                        .build()
                        .perform();
                listOfMilestonesPage.getAcceptRemovalButton().click();
            } else {
                break;
            }
        }
        return listOfMilestonesPage;
    }

    @Step
    public ListOfProjectsPage removeAllCompletedMilestonesWithActions(int projectId) {
        listOfMilestonesPage.openPageByUrl(projectId);
        for (int i = listOfMilestonesPage.getListOfCompletedMilestones().getListOfRows().size(); i > 0; i--) {
            actions
                    .click(listOfMilestonesPage.getListOfCompletedMilestones().getRow(0).getCell(3).getLinkFromCellLikeWebElement())
                    .build()
                    .perform();
            listOfMilestonesPage.getAcceptRemovalButton().click();
        }
        return new ListOfProjectsPage(driver);
    }

    @Step
    public ListOfProjectsPage removeAllCompletedMilestones(int projectId) {
        listOfMilestonesPage.openPageByUrl(projectId);
        for (int i = listOfMilestonesPage.getListOfCompletedMilestones().getListOfRows().size(); i > 0; i--) {
            listOfMilestonesPage.getListOfCompletedMilestones().getRow(0).getCell(3).getLinkFromCell().click();
            listOfMilestonesPage.getAcceptRemovalButton().click();
        }
        return new ListOfProjectsPage(driver);
    }
}
