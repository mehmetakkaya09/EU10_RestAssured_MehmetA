package com.cydeo.day05;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.withNoArgs;

public class JSONToJAVA extends SpartanTestBase {
    @Test
    public void test() {
        Response response = given()
                .and().pathParam("id", 15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200)
                .extract().response();
        Map<String, Object> jsonMap = response.as(Map.class);
        System.out.println(jsonMap.toString());
        String name = jsonMap.get("name").toString();
        System.out.println(name);
    }

    @Test
    public void test2() {
        Response response = get("/api/spartans").then().statusCode(200).extract().response();

        List<Map<String, Object>> spartans = response.as(List.class);
        System.out.println(spartans.toString());
        System.out.println(spartans.get(1).get("name"));

        Map<String,Object> spartan3 = spartans.get(2);
        System.out.println(spartan3.toString());
    }
}
