package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FirstRestAssuredTest {
    String url = "http://35.153.50.29:8000";

    @Test
    public void test() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url + "/api/spartans");
        System.out.println(response.statusCode());
        System.out.println(response.contentType());
        response.prettyPrint();

        Assertions.assertEquals(response.statusCode(), 200);
        Assertions.assertEquals(response.contentType(),"application/json");

    }
}
