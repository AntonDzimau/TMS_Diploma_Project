package tests.api;

import baseEntities.BaseAPITest;
import com.google.gson.Gson;
import entities.MilestoneEntities;
import entities.TestCasesEntities;
import io.restassured.response.Response;
import models.Milestone;
import models.TestCases;
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

    @Test(description = "Добавление тест-кейса через API запрос"
            , groups = {"Nikita's tests", "smoke"})
    public void addTestCasesTest() {
        Response response = given()
                .body(TestCasesEntities.testCasesForApiTest)
                .when()
                .pathParam("section_id", 16)
                .post(Endpoints.ADD_TESTCASE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        TestCasesEntities.testCasesForApiTest.setId((response.getBody().jsonPath().get("id")));
        TestCases actualTestCases = new Gson().fromJson(response.getBody().asPrettyString(), TestCases.class);
        Assert.assertEquals(actualTestCases, TestCasesEntities.testCasesForApiTest);
    }

    @Test(dependsOnMethods = "addTestCasesTest", description = "Добавление тест-кейса через API запрос"
            , groups = {"Nikita's tests", "smoke"})
    public void DeleteTestCasesTest() {
        given()
                .pathParam("case_id", TestCasesEntities.testCasesForApiTest.getId())
                .post(Endpoints.Delete_TESTCASE)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
