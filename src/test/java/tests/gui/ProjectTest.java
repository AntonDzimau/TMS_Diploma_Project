package tests.gui;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTest extends BaseTest {

    @Test(priority = 1)
    public void addSecondTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                projectSteps.addProject(projectsEntities.secondTypeProject)
                        .getListOfProjects()
                        .isFoundInTable(projectsEntities.secondTypeProject.getName())
        );
        System.out.println("second project after test " + projectsEntities.secondTypeProject.getId());
    }

    @Test(dependsOnMethods = "addSecondTypeProjectTest")
    public void addMilestoneTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        System.out.println(projectsEntities.secondTypeProject.getId());
        milestoneSteps.addMilestoneToProject(projectsEntities.secondTypeProject.getId(), milestoneEntities.firstMilestone);
        Assert.assertTrue(
                milestoneSteps.findMilestoneInList(milestoneEntities.firstMilestone)
        );
    }

    @Test(priority = 2)
    public void removeSecondTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertFalse(
                projectSteps.removeProjectsByName(projectsEntities.secondTypeProject.getName())
                        .getListOfProjects()
                        .isFoundInTable(projectsEntities.secondTypeProject.getName())
        );
    }
}
