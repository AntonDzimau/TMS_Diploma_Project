package tests.gui;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Milestones.ListOfMilestonesPage;
import pages.projects.AddProjectPage;
import pages.projects.entities.MilestoneEntities;
import pages.projects.entities.ProjectsEntities;

public class AntonTests extends BaseTest {
    @Test(groups = "Anton's tests")
    public void loginSuccessful() {
        Assert.assertTrue(
                loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password())
                        .isPageOpened());
    }

    @Test (groups = "Anton's tests")
    public void loginUnsuccessful() {
        Assert.assertEquals(
                loginStep.loginIncorrect("some name", ReadProperties.password())
                        .getErrorTextElement().getText()
                , "Email/Login or Password is incorrect. Please try again.");
    }

    @Test (groups = "Anton's tests")
    public void existPopUpTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        AddProjectPage addProjectPage = new AddProjectPage(driver);
        addProjectPage.openPageByUrl();
        Assert.assertEquals(
                addProjectPage.getHelpLinkFormatting().getAttribute("tooltip-text")
                , "Open the editor formatting reference.");
    }

    @Test  (groups = "Anton's tests")
    public void addSecondTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                projectSteps.addProject(ProjectsEntities.secondTypeProject)
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.secondTypeProject.getName())
        );
    }

    @Test(groups = "Anton's tests", dependsOnMethods = "addSecondTypeProjectTest")
    public void addMilestoneWithHugeName() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertEquals(
                milestoneSteps.addMilestoneToProject(ProjectsEntities.secondTypeProject.getId(), MilestoneEntities.completedMilestoneWithHugeName)
                        .getListOfCompletedMilestones()
                        .getListOfRows()
                        .get(0)
                        .getCell(1)
                        .getLinkFromCell()
                        .getText()
                        .length()
                , 250
        );
    }

    @Test(groups = "Anton's tests", dependsOnMethods = {"addSecondTypeProjectTest", "addMilestoneWithHugeName"}, priority = 1)
    public void removeHugeMilestoneWithActions() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        milestoneSteps.removeAllCompletedMilestonesWithActions(ProjectsEntities.secondTypeProject.getId());
        ListOfMilestonesPage listOfMilestonesPage = new ListOfMilestonesPage(driver);
        try {
            Assert.assertFalse(listOfMilestonesPage
                    .getListOfCompletedMilestones()
                    .isFoundInTable(MilestoneEntities.completedMilestone.getName()));
        } catch (TimeoutException e) {
            System.out.println("Table doesn't exist! But I can check the error message text!");
            Assert.assertEquals(
                    listOfMilestonesPage.getMessageSuccessBox().getText()
                    , "Successfully deleted the milestone (s).");
        }
    }

    @Test(groups = "Anton's tests", dependsOnMethods = {"addSecondTypeProjectTest"}, priority = 2)
    public void addCompletedMilestoneTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                milestoneSteps.addMilestoneToProject(ProjectsEntities.secondTypeProject.getId(), MilestoneEntities.completedMilestone)
                        .getListOfCompletedMilestones()
                        .isFoundInTable(MilestoneEntities.completedMilestone.getName())
        );
    }

    @Test(groups = "Anton's tests", dependsOnMethods = {"addSecondTypeProjectTest", "addMilestoneWithHugeName", "addCompletedMilestoneTest"})
    public void removeCompletedMilestoneTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        milestoneSteps.removeCompletedMilestoneFromProject(ProjectsEntities.secondTypeProject.getId(), MilestoneEntities.completedMilestone.getName());
        ListOfMilestonesPage listOfMilestonesPage = new ListOfMilestonesPage(driver);
        try {
            Assert.assertFalse(listOfMilestonesPage
                    .getListOfCompletedMilestones()
                    .isFoundInTable(MilestoneEntities.completedMilestone.getName()));
        } catch (TimeoutException e) {
            System.out.println("Table doesn't exist! But I can check the error message text!");
            Assert.assertEquals(
                    listOfMilestonesPage.getMessageSuccessBox().getText()
                    , "Successfully deleted the milestone (s).");
        }
    }

    @Test(groups = "Anton's tests", priority = 3)
    public void removeSecondTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertFalse(
                projectSteps.removeProjectsByName(ProjectsEntities.secondTypeProject.getName())
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.secondTypeProject.getName())
        );
    }
}
