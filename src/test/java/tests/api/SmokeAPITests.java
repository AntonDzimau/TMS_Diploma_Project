package tests.api;

import baseEntities.BaseAPITest;
import com.google.gson.Gson;
import configuration.ReadProperties;
import entities.MilestoneEntities;
import entities.ProjectsEntities;
import entities.TestCasesEntities;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Milestone;
import models.TestCases;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Endpoints;

import static io.restassured.RestAssured.given;

public class SmokeAPITests extends BaseAPITest {
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

    @Test(description = "Добавление тест-кейса через API запрос"
            , groups = {"Nikita's tests", "smoke"})
    public void addTestCaseTest() {
        Response response = given()
                .body(TestCasesEntities.testCasesForApiTest, ObjectMapperType.GSON)
                .log().body()
                .when()
                .pathParam("section_id", ReadProperties.apiTestcasesSectionId())
                .post(Endpoints.ADD_TESTCASE)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        TestCasesEntities.testCasesForApiTest.setId((response.getBody().jsonPath().get("id")));
        TestCases actualTestCases = new Gson().fromJson(response.getBody().asPrettyString(), TestCases.class);
        Assert.assertEquals(actualTestCases, TestCasesEntities.testCasesForApiTest);
    }

    @Test(dependsOnMethods = "addTestCaseTest"
            , description = "Удаление тест-кейса через API запрос"
            , groups = {"Nikita's tests", "smoke"})
    public void removeTestCaseTest() {
        given()
                .pathParam("case_id", TestCasesEntities.testCasesForApiTest.getId())
                .post(Endpoints.DELETE_TESTCASE)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
