package tests.gui;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import entities.MilestoneEntities;
import entities.ProjectsEntities;
import org.openqa.selenium.TimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.milestones.ListOfMilestonesPage;
import pages.projects.AddProjectPage;

public class RegressionTests extends BaseTest {

    @Test(description = "Логин с некорректным паролем"
            , groups = {"Nikita's tests", "regression"})
    public void loginUnsuccessfulWrongPassword() {
        Assert.assertEquals(
                loginStep.loginIncorrect(ReadProperties.username(), "some text")
                        .getErrorTextElement().getText()
                , "Email/Login or Password is incorrect. Please try again.");
    }

    @Test(description = "Логин с пустым полем Email"
            , groups = {"Nikita's tests", "regression"})
    public void loginUnsuccessfulEmptyLogin() {
        Assert.assertEquals(
                loginStep.loginIncorrect("", ReadProperties.password()).
                        getErrorTextNoLogin().getText()
                , "Email/Login is required.");
    }

    @Test(description = "Логин с пустым полем Password"
            , groups = {"Nikita's tests", "regression"})
    public void loginUnsuccessfulEmptyPassword() {
        Assert.assertEquals(
                loginStep.loginIncorrect(ReadProperties.password(), "").
                        getErrorTextNoPassword().getText()
                , "Password is required.");
    }

    @Test(description = "Добавление проекта 1-го типа"
            , groups = {"Nikita's tests", "regression"})
    public void addFirstTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                projectSteps.addProject(ProjectsEntities.firstTypeProject)
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.firstTypeProject.getName())
        );
        System.out.println("After test id is - " + ProjectsEntities.firstTypeProject.getId());
    }

    @Test(dependsOnMethods = "addFirstTypeProjectTest"
            , description = "Добавление тест-кейса"
            , groups = {"Nikita's tests", "regression"})
    public void addTestCasesTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        testCasesStep.goToAddTestCasePage(ProjectsEntities.firstTypeProject.getId());
        Assert.assertEquals(
                testCasesStep.addTestCase().getSuccessfulText().getText()
                , "Successfully added the new test case. Add another"
        );
    }

    @Test(dependsOnMethods = {"addFirstTypeProjectTest", "addTestCasesTest"}
            , description = "Загрузка файла при создании тест-кейса"
            , groups = {"Nikita's tests", "regression"})
    public void uploadTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        testCasesStep.goToAddTestCasePage(ProjectsEntities.firstTypeProject.getId());
        fileUploadStep.uploadFileToTestCaseInProject();
        Assert.assertTrue(fileUploadStep.didUploadedFileFindInListAttachment("Nirvana_Something_In_The_Way.mp3"));
    }

    @Test(dependsOnMethods = {"addFirstTypeProjectTest", "addTestCasesTest", "uploadTest"}
            , description = "Удаление тест-кейса"
            , groups = {"Nikita's tests", "regression"})
    public void deleteTestCasesTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                testCasesStep.deleteAllTestCasesInProject(ProjectsEntities.firstTypeProject.getId())
        );
    }

    @Test(dependsOnMethods = {"addFirstTypeProjectTest", "addTestCasesTest", "uploadTest", "deleteTestCasesTest"}
            , description = "Удаление всех проектов 1-го типа"
            , groups = {"Nikita's tests", "regression"})
    public void removeFirstTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertFalse(
                projectSteps.removeProjectsByName(ProjectsEntities.firstTypeProject.getName())
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.firstTypeProject.getName())
        );
    }

    @Test(description = "Логин с корректными данными"
            , groups = {"Anton's tests", "regression"})
    public void loginUnsuccessful() {
        Assert.assertEquals(
                loginStep.loginIncorrect("some name", ReadProperties.password())
                        .getErrorTextElement().getText()
                , "Email/Login or Password is incorrect. Please try again.");
    }

    @Test(description = "Проверка существования всплывающего сообщения"
            , groups = {"Anton's tests", "regression"})
    public void existPopUpTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        AddProjectPage addProjectPage = new AddProjectPage(driver);
        addProjectPage.openPageByUrl();
        Assert.assertEquals(
                addProjectPage.getHelpLinkFormatting().getAttribute("tooltip-text")
                , "Open the editor formatting reference.");
    }

    @Test(description = "Добавление проекта 2-го типа"
            , groups = {"Anton's tests", "regression"})
    public void addSecondTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                projectSteps.addProject(ProjectsEntities.secondTypeProject)
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.secondTypeProject.getName())
        );
    }

    @Test(dependsOnMethods = "addSecondTypeProjectTest"
            , description = "Добавление майлстоуна с именем, превышающим 250 символов"
            , groups = {"Anton's tests", "regression"})
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

    @Test(dependsOnMethods = {"addSecondTypeProjectTest", "addMilestoneWithHugeName"}
            , description = "Удаление всех завершенных майлстоунов через Actions"
            , groups = {"Anton's tests", "regression"})
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

    @Test(dependsOnMethods = {"addSecondTypeProjectTest", "addMilestoneWithHugeName", "removeHugeMilestoneWithActions"}
            , description = "Добавление завершенного майлстоуна"
            , groups = {"Anton's tests", "regression"})
    public void addCompletedMilestoneTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                milestoneSteps.addMilestoneToProject(ProjectsEntities.secondTypeProject.getId(), MilestoneEntities.completedMilestone)
                        .getListOfCompletedMilestones()
                        .isFoundInTable(MilestoneEntities.completedMilestone.getName())
        );
    }

    @Test(dependsOnMethods = {"addSecondTypeProjectTest", "addMilestoneWithHugeName", "removeHugeMilestoneWithActions", "addCompletedMilestoneTest"}
            , description = "Неудачное удаление майлстоуна"
            , groups = {"Anton's tests", "regression"})
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

    @Test(description = "Удаление всех проектов 2-го типа"
            , groups = {"Anton's tests", "regression"}
            , priority = 5)
    public void removeSecondTypeProjectTest() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertFalse(
                projectSteps.removeProjectsByName(ProjectsEntities.secondTypeProject.getName())
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.secondTypeProject.getName())
        );
    }
}
