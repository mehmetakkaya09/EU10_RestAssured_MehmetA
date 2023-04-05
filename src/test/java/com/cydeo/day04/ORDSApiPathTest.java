package com.cydeo.day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class ORDSApiPathTest extends HRTestBase {

    @Test
    public void test() {
        Map<String, String> query = new HashMap<>();
        query.put("q","{\"region_id\":2}");
        Response response = given().accept(ContentType.JSON).and().queryParams(query).when().get("/countries");

        System.out.println(response.path("limit").toString());

        System.out.println(response.path("items[0].country_id").toString());
        System.out.println(response.path("items[1].country_name").toString());
        System.out.println(response.path("items[2].links[0].href").toString());
        List<String> cNames = response.path("items.country_name");
        System.out.println(cNames);

    }

    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).and().queryParam("q", "{\"job_id\": \"IT_PROG\"}").when().get("/employees");

        List<String> jobs = response.path("items.job_id");
        for (String job : jobs) {
            assertEquals("IT_PROG", job);
            System.out.println(job);
        }


    }

}
