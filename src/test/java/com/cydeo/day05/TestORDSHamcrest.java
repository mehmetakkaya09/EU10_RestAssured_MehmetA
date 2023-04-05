package com.cydeo.day05;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class TestORDSHamcrest extends HRTestBase {

    @Test
    public void test() {
        //send a get request to emplyoees endpoint with query parameter job_id IT_PROG
        //verify each job_id is IT_PROG
        //verify first names are .... (find proper method to check list against list)
        //verify emails without checking order (provide emails in different order,just make sure it has same emails)
        //expected names
        List<String> names = Arrays.asList("Alexander","Bruce", "David", "Valli", "Diana");

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().assertThat()
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .body("items.first_name", containsInAnyOrder("Alexander", "Valli", "David", "Bruce", "Diana"))
                .body("items.email", containsInRelativeOrder("DAUSTIN", "VPATABAL", "DLORENTZ"))
                .body("items.first_name", equalToObject(names)).log().all().extract().response();
        //böyle Then sonrası yapıları response veya jsonpath gibi başka variablelar ile tanımlamak istiyorsak bu durumda extract() metodunu kullanırız bundan sonra respense() veya jsonpath() gibi metodları yazarak return type'ı belirleyebiliriz.
        response.prettyPrint();
    }

    @Test
    public void test2() {
        JsonPath jsonPath = given().accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when().get("/employees")
                .then().statusCode(200)
                .and().contentType("application/json")
                .and().assertThat()
                .body("items.job_id", everyItem(equalTo("IT_PROG"))).extract().jsonPath();
        assertThat(jsonPath.getList("items.first_name"), hasSize(5));
    }
}
