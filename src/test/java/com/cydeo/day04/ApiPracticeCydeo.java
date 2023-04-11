package com.cydeo.day04;

import com.cydeo.utilities.CydeoApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
public class ApiPracticeCydeo extends CydeoApiTestBase {


    @Test
    public void test() {
        Response response = given().accept(ContentType.JSON).get("/student/all");
        JsonPath jsonPath = response.jsonPath();
        assertEquals(200, response.getStatusCode());
        assertEquals("application/json;charset=UTF-8",response.header("content-type"));
        assertEquals("chunked", response.header("transfer-encoding"));
        assertTrue(response.header("date").contains("2023"));
        String name = jsonPath.getString("students.findAll {it.studentId==2}.firstName");
        System.out.println(name);
    }

    @Test
    public void test2() {
        Response response = given().accept(ContentType.JSON).pathParam("id",2).get("/student/{id}");
        assertThat(response.statusCode(), is(200));

    }





}
