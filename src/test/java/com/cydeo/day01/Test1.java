package com.cydeo.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class Test1 {

    String url = "http://35.153.50.29:8000/api/spartans";

    @Test
    public void test1() {
        Response response = RestAssured.get(url);
        System.out.println(response.statusCode());
        response.prettyPrint();





    }
}
