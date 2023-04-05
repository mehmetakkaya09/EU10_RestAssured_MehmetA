package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SpartansRestAssured {

    String url = "http://35.153.50.29:8000";

    @Test
    public void test() {
        Response response = RestAssured.given().accept(ContentType.JSON).when().get(url + "/api/spartans/3");
        Assertions.assertEquals(response.statusCode(), 200);
        Assertions.assertEquals(response.contentType(), "application/json");
        Assertions.assertTrue(response.body().asString().contains("Fidole"));
    }

    @Test
    public void test2() {
        Response response = RestAssured.when().get(url + "/api/hello");
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("text/plain;charset=UTF-8", response.contentType());
        Assertions.assertTrue(response.headers().hasHeaderWithName("Date"));
        Assertions.assertEquals(response.header("Content-Length"), "17");
        System.out.println(response.header("Date"));
        Assertions.assertEquals(response.body().asString(), "Hello from Sparta");
    }


}
