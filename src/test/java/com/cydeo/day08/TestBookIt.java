package com.cydeo.day08;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class TestBookIt {
    @BeforeAll
    public static void before() {
        baseURI = "http://cybertek-reservation-api-qa3.herokuapp.com";
    }
    String accessToken = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0.xNvdQRyrYMb3Ec5QByHwXowBo3zPK2jQQS1eJ2RYIto";

    @Test
    public void test() {
        given().header("Authorization", accessToken)
                .accept(ContentType.JSON)
                .get("/api/campuses")
                .then().statusCode(200)
                .log().all();
    }
}
