package com.cydeo.day02;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class SpartansNegativeTest {

    @BeforeAll
    public static void init() {
        baseURI = "http://35.153.50.29:8000";
    }

    @Test
    public void test() {
        Response response = given().accept(ContentType.XML).when().get("/api/spartans/10");
        assertEquals("application/xml;charset=UTF-8", response.contentType());
        assertEquals(406, response.statusCode());

    }



}
