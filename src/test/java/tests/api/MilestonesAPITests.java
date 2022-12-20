package tests.api;

import baseEntities.BaseAPITest;
import com.google.gson.Gson;
import configuration.ReadProperties;
import entities.MilestoneEntities;
import entities.UserEntities;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Milestone;
import models.Project;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class MilestonesAPITests extends BaseAPITest {
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

    @Test(description = "Добавление майлстоуна через API запрос"
            , groups = {"Anton's tests", "smoke"})
    public void addMilestoneTest() {
        Response response = given()
                .body(MilestoneEntities.milestoneForApiTest, ObjectMapperType.GSON)
                .when()
                .pathParam("project_id", ReadProperties.apiProjectId())
                .post(Endpoints.ADD_MILESTONE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        MilestoneEntities.milestoneForApiTest.setId(response.getBody().jsonPath().get("id"));

        Milestone actualMilestone = new Gson().fromJson(response.getBody().asPrettyString(), Milestone.class);
        Assert.assertEquals(actualMilestone, MilestoneEntities.milestoneForApiTest);
    }

    @Test(dependsOnMethods = "addMilestoneTest"
            , description = "Удаление созданного майлстоуна через API запрос"
            , groups = {"Anton's tests", "smoke"})
    public void removeApiCreatedMilestoneTest() {
        given()
                .pathParam("milestone_id", MilestoneEntities.milestoneForApiTest.getId())
                .post(Endpoints.DELETE_MILESTONE)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
