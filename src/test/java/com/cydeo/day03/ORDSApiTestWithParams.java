package com.cydeo.day03;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSApiTestWithParams {
    @BeforeAll
    public static void init() {
        baseURI = "http://35.153.50.29:1000/ords/hr";
    }

    @Test
    public void test() {
        Map<String, String> query = new HashMap<>();
        query.put("q","{\"region_id\":2}");
        Response response = given().log().all().accept(ContentType.JSON).and().queryParams(query).when().get("/countries");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));


    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON).log().all().and().queryParam("q", "{\"job_id\":\"IT_PROG\"}").when().get("/employees");
        response.prettyPrint();
    }





}
