package com.cydeo.day10;

import com.cydeo.utilities.AuthSpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class TestSchemaValidator extends AuthSpartanTestBase {
    @Test
    public void test() {

        given().accept(ContentType.JSON).auth().basic("user","user")
                .pathParam("id",13)
                .get("/api/spartans/{id}").then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("SingleSpartanSchema.json")).log().all();
   }

    @Test
    public void test2() {

        given().accept(ContentType.JSON)
                .auth().basic("user","user")
                .get("/api/spartans")
                .then().statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/java/com/cydeo/day10/allSpartansSchema.json"))).log().all();
    }

    @Test
    public void test3() {
        Response response = given().auth().basic("user", "user")
                .accept(ContentType.JSON)
                .get("/api/spartans")
                .then().time(lessThan(2000l)).log().all().extract().response();
        System.out.println(response.time());
    }





}
