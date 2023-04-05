package com.cydeo.day01;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

public class Testt2 {

    @Test
    public void test() {
        String url = "http://35.153.50.29:1000/ords/hr";
        Response response = given().accept(ContentType.JSON).when().get(url + "/employees");
        response.statusCode();
        response.contentType();
        System.out.println(response.body().asString());
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        System.out.println(response.path("first_name[0]").toString());


    }

    @Test
    public void test2() {
        String url = "http://3.216.30.92:8000";
        Response response = given().accept(ContentType.JSON).when().get(url + "/api/spartans");

        List<String > results = response.path("name");
        //System.out.println(response.path("name[0]").toString());
        for (String result : results) {
            System.out.println(result);
        }
//        System.out.println(response.path("id[0]").toString());
//        System.out.println(response.path("name[0]").toString());
//        System.out.println(response.path("id[2]").toString());
//        System.out.println(response.path("name[-1]").toString());
//        System.out.println(response.path("phone[-2]").toString());


    }



}
