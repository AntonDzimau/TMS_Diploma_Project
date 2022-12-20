package tests.api;

import baseEntities.BaseAPITest;
import entities.UserEntities;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.Endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class UsersAPITests extends BaseAPITest {
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
