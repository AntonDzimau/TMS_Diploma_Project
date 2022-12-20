package tests.api;

import baseEntities.BaseAPITest;
import configuration.ReadProperties;
import entities.ProjectsEntities;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ProjectsAPITests extends BaseAPITest {

    @Test(description = "Получение всех проектов через API запрос"
            , groups = {"Nikita tests", "regression"})
    public void getAllProjectsTest() {
        given()
                .when()
                .get(Endpoints.GET_ALL_PROJECTS)
                .then()
                .log().status()
                .log().body()
                .body("projects.get(0).name", is("Project to Anton's API tests"))
                .body("projects.get(1).name", is("Project to Nikita's API tests"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(description = "Добавление нового проекта через API"
            , groups = {"Nikita's tests", "smoke"})
    public void addNewProjectThroughAPITest() {
        Response response = given()
                .body(ProjectsEntities.testProjectForApi, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        ProjectsEntities.testProjectForApi.setId(response.getBody().jsonPath().get("id"));
        Assert.assertEquals(response.getBody().jsonPath().get("name")
                , ProjectsEntities.testProjectForApi.getName());

    }

    @Test(description = "Обновление проекта без поля 'Имя'"
            , groups = {"Anton's tests", "regression"})
    public void updateProjectTest() {
        Project updateProject = Project.builder()
                .announcement("This is a new description of project for API testing")
                .build();

        given()
                .body(updateProject)
                .when()
                .pathParam("project_id", ReadProperties.apiProjectId())
                .post(Endpoints.UPDATE_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(dependsOnMethods = "addNewProjectThroughAPITest"
            , description = "Удаление проекта через API"
            , groups = {"Nikita's tests", "smoke"})
    public void removeProjectThroughAPITest() {
        given()
                .pathParam("project_id", ProjectsEntities.testProjectForApi.getId())
                .post(Endpoints.DELETE_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
