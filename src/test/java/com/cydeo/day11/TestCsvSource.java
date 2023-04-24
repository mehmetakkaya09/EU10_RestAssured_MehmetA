package com.cydeo.day11;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class TestCsvSource {


    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us/us";
    }


    @ParameterizedTest
    @CsvSource({ "NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "VA, Arlington",
            "MA, Boston",
            "NY, New York",
            "MD, Annapolis"})
    public void test(String state, String city) {
        int size = given().accept(ContentType.JSON)
                .pathParams("state", state, "city", city)
                .get("/{state}/{city}")
                .then().statusCode(200)
                .time(lessThanOrEqualTo(2000l))
                .log().all().extract().jsonPath().getList("places").size();
        System.out.println(size);
    }



}
