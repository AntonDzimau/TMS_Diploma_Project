package tests.api;

import baseEntities.BaseAPITest;
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
}
