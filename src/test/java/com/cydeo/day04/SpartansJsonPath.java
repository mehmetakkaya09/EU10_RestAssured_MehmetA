package com.cydeo.day04;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class SpartansJsonPath extends SpartanTestBase {
    @Test
    public void test() {
        Response response = given().accept(ContentType.JSON).pathParam("id", 10).when().get("/api/spartans/{id}");

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.getInt("id"));
        System.out.println(jsonPath.getString("name"));
        System.out.println(jsonPath.getString("gender"));
        System.out.println(jsonPath.getLong("phone"));
    }





}
