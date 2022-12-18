package tests.api;

import baseEntities.BaseAPITest;
import configuration.ReadProperties;
import entities.UserEntities;
import models.Project;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.Endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RegressionAPITests extends BaseAPITest {
    @Test(description = "Получение всех майлстоунов через API запрос"
            , groups = {"Anton's tests", "regression"})
    public void getMilestonesTest() {
        given()
                .when()
                .pathParam("project_id", ReadProperties.apiProjectId())
                .get(Endpoints.GET_MILESTONES)
                .then()
                .log().status()
                .log().body()
                .body("milestones.get(0).name", is("The first milestone is completed"))
                .body("milestones.get(1).name", is("The second milestone is completed too"))
                .body("milestones.get(2).name", is("The third milestone isn't completed"))
                .statusCode(HttpStatus.SC_OK);
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

    @Test(description = "Получение всех пользователей через API запрос"
            , groups = {"Nikita tests", "regression"})
    public void getAllUser() {
        given()
                .when()
                .get(Endpoints.GET_ALL_USERS)
                .then()
                .log().body()
                .body("get(0).name", is(UserEntities.UserForApiTest.getName()))
                .body("get(0).id", is(UserEntities.UserForApiTest.getId()))
                .statusCode(HttpStatus.SC_OK);
    }
}
