package com.cydeo.day11;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class TestCsvFileSource {
    @BeforeAll
    public static void setup() {
        baseURI = "https://api.zippopotam.us/us";
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/postalcode.csv", numLinesToSkip = 1)
    public void test(String state, String city, int zip_count) {
        Map<String, String> params = new HashMap<>();
        params.put("state",state);
        params.put("city", city);
        given().accept(ContentType.JSON)
                .pathParams(params)
                .get("/{state}/{city}")
                .then()
                .statusCode(200)
                .time(lessThan(3000l))
                .body("places.'place name'", everyItem(containsStringIgnoringCase(city)))
                .body("places", hasSize(zip_count));
    }




}
