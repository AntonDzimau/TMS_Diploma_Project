package tests.gui;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import entities.ProjectsEntities;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.projects.ListOfProjectsPage;

public class ProjectTest extends BaseTest {

/*    @Test
    public void successfulAddProjectTest (){
        loginStep.loginSuccessful(ReadProperties.username(),ReadProperties.password());
        addProjectStep.addNewProject();
        addProjectStep.setNameProject("Some name");
        addProjectStep.seTCheckBox();
        addProjectStep.chooseRadioButtonAddProjectByValue("3");
        addProjectStep.saveProject();
        Assert.assertEquals("Congratulations! You have created your first project", addProjectStep.getSuccessfulTextForAddProject());
    }*/

    @Test(priority = 1)
    public void addSecondTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                addProjectStep.addSecondTypeProject()
                        .getListOfProjects()
                        .isFoundInTable(projectsEntities.secondTypeProject.getName())
        );
    }

    @Test(priority = 2)
    public void removeSecondTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertFalse(
                removeProjectStep.removeProjectsByName(projectsEntities.secondTypeProject.getName())
                        .getListOfProjects()
                        .isFoundInTable(projectsEntities.secondTypeProject.getName())
        );
    }

   /* @Test(description = "don put some name of project ")
    public void unsuccessfulAddProjectTest(){
        loginStep.loginSuccessful(ReadProperties.username(),ReadProperties.password());
        addNewProjectStep.addNewProject();
        addNewProjectStep.seTCheckBox();
        addNewProjectStep.saveProject();
        Assert.assertEquals("Field Name is a required field.",addNewProjectStep.getErrorText());
    }
   /* @Test(description = "delete this test ")
    public void successfulAddProjectTestDeleteMe () throws InterruptedException {
        loginStep.loginSuccessful(ReadProperties.username(),ReadProperties.password());
        addNewProjectStep.addNewProject();
        addNewProjectStep.setNameProject("Some name");
        addNewProjectStep.chooseRadioButtonAddProjectBytext("Use a single repository with baseline support");
        Thread.sleep(5000);
    } */
/*    @Test(dependsOnMethods ="successfulAddProjectTest")
    public void deleteProject(){
        loginStep.loginSuccessful(ReadProperties.username(),ReadProperties.password());
        addProjectStep.deleteProject();
        Assert.assertEquals("Successfully deleted the project.", addProjectStep.getSuccessfulTextForDeleteProject());
    }*/
}
