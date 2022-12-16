package tests.api;

import baseEntities.BaseAPITest;
import entities.ProjectsEntities;
import entities.UserEntities;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
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
                .pathParam("project_id", 51)
                .get(Endpoints.GET_MILESTONES)
                .then()
                .log().status()
                .log().body()
                .body("milestones.get(0).name", is("The first milestone is completed"))
                .body("milestones.get(1).name", is("The second milestone is completed too"))
                .body("milestones.get(2).name", is("The third milestone isn't completed"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(description = "Некорректное обновление проекта"
            , groups = {"Anton's tests", "regression"})
    public void updateProjectTest() {
        Project updateProject = Project.builder()
                .announcement("This is a new description of project for API testing")
                .build();

        given()
                .body(updateProject)
                .when()
                .pathParam("project_id", 51)
                .post(Endpoints.UPDATE_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void addNewProject() {
        Response response = given()
                .body(ProjectsEntities.testProjectForApi, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        Assert.assertEquals(response.getBody().jsonPath().get("name"), ProjectsEntities.testProjectForApi.getName());
    }

    @Test(dependsOnMethods = "addNewProject", description = "Получение всех проектов через API запрос"
            , groups = {"Nikita tests", "regression"})
    public void getProjectTest() {
        given()
                .when()
                .get(Endpoints.GET_ALL_PROJECTS)
                .then()
                .log().status()
                .log().body()
                .body("projects.get(0).name", is("Project to Anton's API tests"))
                .body("projects.get(1).name", is("Project For Api Nikita"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(description = "Получение всех юзеров через API запрос"
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
