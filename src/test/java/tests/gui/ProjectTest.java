package tests.gui;

import BaseEntities.BaseTest;
import configuration.ReadProperties;
import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProjectTest extends BaseTest {
    @Test
    public void successfulAddProjectTest (){
        loginStep.loginSuccessful(ReadProperties.username(),ReadProperties.password());
        addNewProjectStep.addNewProject();
        addNewProjectStep.setNameProject("Some name");
        addNewProjectStep.seTCheckBox();
        addNewProjectStep.chooseRadioButtonAddProjectByValue("3");
        addNewProjectStep.saveProject();
        Assert.assertEquals("Congratulations! You have created your first project",addNewProjectStep.getSuccessfulTextForAddProject());
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
    @Test(dependsOnMethods ="successfulAddProjectTest")
    public void deleteProject(){
        loginStep.loginSuccessful(ReadProperties.username(),ReadProperties.password());
        addNewProjectStep.deleteProject();
        Assert.assertEquals("Successfully deleted the project.",addNewProjectStep.getSuccessfulTextForDeleteProject());
    }
}
