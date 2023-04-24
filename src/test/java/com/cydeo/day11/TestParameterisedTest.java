package com.cydeo.day11;

import com.cydeo.utilities.AuthSpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TestParameterisedTest extends AuthSpartanTestBase {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void test(int id) {
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", id)
                .auth().basic("user", "user")
                .get("/api/spartans/{id}")
                .then().time(lessThan(2000l))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json")).log().all()
                .statusCode(200)
                .extract().response();
    }


}
