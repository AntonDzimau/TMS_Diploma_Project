package tests.api;

import baseEntities.BaseAPITest;
import com.google.gson.Gson;
import configuration.ReadProperties;
import entities.TestCasesEntities;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.TestCases;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.Endpoints;

import static io.restassured.RestAssured.given;

public class TestCasesAPITests extends BaseAPITest {
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
