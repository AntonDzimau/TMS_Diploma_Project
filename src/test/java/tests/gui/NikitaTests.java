package tests.gui;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.projects.entities.ProjectsEntities;

public class NikitaTests extends BaseTest {

    @Test(groups = "Nikita's tests")
    public void loginUnsuccessfulWrongPassword() {
        Assert.assertEquals(
                loginStep.loginIncorrect(ReadProperties.username(), "some text")
                        .getErrorTextElement().getText()
                , "Email/Login or Password is incorrect. Please try again.");
    }

    @Test (groups = "Nikita's tests")
    public void loginUnsuccessfulEmptyLogin() {
        Assert.assertEquals(
                loginStep.loginIncorrect("", ReadProperties.password()).
                        getErrorTextNoLoginLocator().getText()
                , "Email/Login is required.");
    }

    @Test (groups = "Nikita's tests")
    public void loginUnsuccessfulEmptyPassword() {
        Assert.assertEquals(
                loginStep.loginIncorrect(ReadProperties.password(), "").
                        getErrorTextNoPasswordLocator().getText()
                , "Password is required.");
    }

    @Test(groups = "Nikita's tests")
    public void addFirstTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                projectSteps.addProject(ProjectsEntities.firstTypeProject)
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.firstTypeProject.getName())
        );
        System.out.println("After test id is - " + ProjectsEntities.firstTypeProject.getId());
    }

    @Test(groups = "Nikita's tests", dependsOnMethods = "addFirstTypeProjectTest")
    public void addTestCasesTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        testCasesStep.goToTestCasesStep();
        testCasesStep.addTestCasesStep();
        Assert.assertEquals(testCasesStep.getSuccessfulText(), "Successfully added the new test case. Add another");
    }

    /**ToDo: ДОБАВИТЬ АССЕРТ!!!*/
    @Test(groups = "Nikita's tests", dependsOnMethods = "uploadTest")
    public void deleteTestCasesTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        testCasesStep.deleteTestCasesStep();
        /**!!!!Доделать проверку тест работает!!!!*/
    }

    @Test(groups = "Nikita's tests", dependsOnMethods = "addTestCasesTest")
    public void uploadTest() throws InterruptedException {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        testCasesStep.goToTestCasesStep();
        fileUploadStep.uploadFile();
        Assert.assertTrue(fileUploadStep.assertFile());
    }

    @Test(groups = "Nikita's tests", dependsOnMethods = {"deleteTestCasesTest"})
    public void removeFirstTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertFalse(
                projectSteps.removeProjectsByName(ProjectsEntities.firstTypeProject.getName())
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.firstTypeProject.getName())
        );
    }
}
