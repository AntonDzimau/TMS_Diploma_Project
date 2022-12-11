package tests.gui;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import elements.UIElement;
import org.openqa.selenium.InvalidArgumentException;
import pages.projects.entities.MilestoneEntities;
import pages.projects.entities.ProjectsEntities;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Milestones.ListOfMilestonesPage;

import java.util.List;
import java.util.Objects;

public class ProjectTest extends BaseTest {
    private List<UIElement> uiElement;

    @Test
    public void addSecondTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                projectSteps.addProject(ProjectsEntities.secondTypeProject)
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.secondTypeProject.getName())
        );
        System.out.println("After test id is - " + ProjectsEntities.secondTypeProject.getId());
    }


    @Test(dependsOnMethods = "addSecondTypeProjectTest")
    public void addCompletedMilestoneTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                milestoneSteps.addMilestoneToProject(ProjectsEntities.secondTypeProject.getId(), MilestoneEntities.completedMilestone)
                        .getListOfCompletedMilestones()
                        .isFoundInTable(MilestoneEntities.completedMilestone.getName())
        );
    }

    @Test(dependsOnMethods = {"addSecondTypeProjectTest", "addCompletedMilestoneTest"})
    public void removeCompletedMilestoneTest() throws InterruptedException {
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

    @Test(dependsOnMethods = {"addSecondTypeProjectTest", "addCompletedMilestoneTest", "removeCompletedMilestoneTest"})
    public void removeSecondTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertFalse(
                projectSteps.removeProjectsByName(ProjectsEntities.secondTypeProject.getName())
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.secondTypeProject.getName())
        );
    }

    @Test
    public void addFirstTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                projectSteps.addProject(ProjectsEntities.firstTypeProject)
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.firstTypeProject.getName())
        );
        System.out.println("After test id is - " + ProjectsEntities.firstTypeProject.getId());
    }

    @Test(dependsOnMethods = "addFirstTypeProjectTest")
    public void addTectCasesTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        testCasesStep.goToTestCasesStep();
        testCasesStep.addTestCasesStep();
        Assert.assertEquals(testCasesStep.getSuccessfulText(), "Successfully added the new test case. Add another");
    }

    @Test
    public void deleteTestCasesTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        testCasesStep.deleteTestCasesStep();
    }

    @Test
    public void downloadTest() throws InterruptedException {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        testCasesStep.goToTestCasesStep();
        fileDownloadStep.downloadFile();
        Assert.assertTrue(fileDownloadStep.assertFile());
    }
}

