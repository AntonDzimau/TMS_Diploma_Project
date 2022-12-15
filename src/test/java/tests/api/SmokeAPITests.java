package tests.api;

import baseEntities.BaseAPITest;
import com.google.gson.Gson;
import entities.MilestoneEntities;
import io.restassured.response.Response;
import models.Milestone;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Endpoints;

import static io.restassured.RestAssured.given;

public class SmokeAPITests extends BaseAPITest {
    @Test(description = "Добавление майлстоуна через API запрос"
            , groups = {"Anton's tests", "smoke"})
    public void addMilestoneTest() {
        Response response = given()
                .body(MilestoneEntities.milestoneForApiTest)
                .when()
                .pathParam("project_id", 51)
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
