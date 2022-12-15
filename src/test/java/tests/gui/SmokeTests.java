package tests.gui;

import baseEntities.BaseTest;
import configuration.ReadProperties;
import entities.ProjectsEntities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTests extends BaseTest {
    @Test(description = "Логин с корректными данными"
            , groups = "smoke")
    public void loginSuccessful() {
        Assert.assertTrue(
                loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password())
                        .isPageOpened());
    }

    @Test(dependsOnMethods = "loginSuccessful"
            , description = "Добавление тестового проекта"
            , groups = "smoke")
    public void addProject() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertTrue(
                projectSteps.addProject(ProjectsEntities.testProjectForSmokeTest)
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.testProjectForSmokeTest.getName())
        );
    }

    @Test(dependsOnMethods = {"loginSuccessful", "addProject"}
            , description = "Удаление тестового проекта"
            , groups = "smoke")
    public void removeProject() {
        loginStep.loginSuccessful(ReadProperties.username(), ReadProperties.password());
        Assert.assertFalse(
                projectSteps.removeProjectsByName(ProjectsEntities.testProjectForSmokeTest.getName())
                        .getListOfProjects()
                        .isFoundInTable(ProjectsEntities.testProjectForSmokeTest.getName())
        );
    }
}
