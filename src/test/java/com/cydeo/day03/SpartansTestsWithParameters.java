package com.cydeo.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpartansTestsWithParameters {
    @BeforeAll
    public static void init() {
        baseURI = "http://3.216.30.92:8000";
    }

    @Test
    public void test() {
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 5).when().get("/api/spartans/{id}");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Blythe"));
    }


    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON).log().all().and().pathParam("id", 500).when().get("/api/spartans/{id}");
        assertEquals(404, response.statusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Not Found"));
    }

    @Test
    public void test3() {
        Response response = given().log().all().accept(ContentType.JSON).and().queryParam("gender", "Female").and().queryParam("nameContains", "e").get("/api/spartans/search");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));

    }

    @Test
    public void test4() {
        Map<String, Object> query = new HashMap<>();
        query.put("nameContains", "e");
        query.put("gender", "Female");

        Response response = given().log().all().accept(ContentType.JSON).and().queryParams(query).when().get("/api/spartans/search");
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json", response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));


    }

    @Test
    public void test5() {
        Response response = given().accept(ContentType.JSON).when().get("/api/spartans/10");
        assertEquals(200, response.statusCode());
        assertEquals("application/json", response.contentType());
        int id = response.path("id");
        String name = response.path("name");
        String gender = response.path("gender");
        long phone = response.path("phone");
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);
        assertEquals(10, id);
        assertEquals("Lorenza", name);
        assertEquals("Female", gender);
        assertEquals(3312820936l, phone);

    }

    @Test
    public void test6() {
        Response response = given().accept(ContentType.JSON).when().get("/api/spartans");
        System.out.println("response.path(\"gender[0]\") = " + response.path("gender[0]"));
        System.out.println("response.path(\"id[1]\") = " + response.path("id[1]"));
        System.out.println("response.path(\"name[-1]\") = " + response.path("name[-1]"));
        System.out.println("response.path(\"phone[-2]\") = " + response.path("phone[-2]"));

       List<String> names = response.path("name");

        System.out.println(names);
        for (String name : names) {
            System.out.println(name);
        }

    }
}
