package com.cydeo.day04;

import com.cydeo.utilities.CydeoApiTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.Matchers.*;

public class Homework extends CydeoApiTestBase {

    @Test
    public void testHomework() {
        //send a get request to student id 23401 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */
        given().log().all().accept(ContentType.JSON).pathParam("id", 2)
                .get("/student/{id}")
                .then()
                .statusCode(200)
                .assertThat()
                .contentType("application/json;charset=UTF-8")
                .and()
                .body("students[0].firstName", equalTo("Mark"), "students[0].company.address.zipCode", equalTo(33222));

//        assertEquals(200,response.statusCode());
//        assertEquals("application/json;charset=UTF-8", response.header("content-Type"));
//        assertEquals("chunked", response.header("transfer-encoding"));
//        assertTrue(response.header("date").contains("2023"));
//        JsonPath jsonPath = response.jsonPath();
//        assertEquals("Mark", jsonPath.getString("students[0].firstName"));
//        assertEquals("mark@email.com", jsonPath.getString("students[0].contact.emailAddress"));
//        System.out.println(jsonPath.getString("students[0].contact.emailAddress"));
//        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");
//        System.out.println(zipCode);
//        assertEquals(33222, zipCode);


    }

    @Test
    public void test() {

        Response response = given().accept(ContentType.JSON).and().get("/student/all");
        JsonPath jsonPath = response.jsonPath();



    }



}
