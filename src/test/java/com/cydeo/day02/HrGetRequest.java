package com.cydeo.day02;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HrGetRequest {

    @BeforeAll
    public static void init() {
        baseURI = "http://35.153.50.29:1000/ords/hr";
    }

    @DisplayName("Get Request / Regions")
    @Test
    public void testGetRequest() {
        Response response = get("/regions");

    }

    @Test
    public void testGet() {
        Response response = given().accept(ContentType.JSON).when().get("/regions/2");
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Americas"));
    }



}
